package com.sign.config;

import com.sign.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */
@Aspect   //定义一个切面
@Configuration
@Slf4j
public class CheckSignAspect {

    @Value("${sign.expireTime}")
    private long expireTime;//接口签名验证超时时间
    @Value("${sign.secretKey}")
    private String secretKey;//接口签名唯一密钥

    // 定义切点Pointcut
    @Pointcut("@annotation(com.sign.config.CheckSign)")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        log.info("开始验证签名");
        try {
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = Objects.requireNonNull(sra).getRequest();

            String timestamp = request.getHeader("timestamp");//获取timestamp参数
            String sign = request.getHeader("sign");//获取sign参数

            if (timestamp.isEmpty() || sign.isEmpty()) {
                return ResponseEntity.ok("timestamp和sign参数不能为空");
            }
            long requestTime = Long.valueOf(timestamp);
            long now = System.currentTimeMillis() / 1000;
            log.info("now={}", now);
            // 请求发起时间与当前时间超过expireTime，则接口请求过期
            if (now - requestTime > expireTime) {
                return ResponseEntity.ok("接口请求过期");
            }

            String generatedSign = generatedSignature(request, timestamp);
            if (!generatedSign.equals(sign)) {
                return ResponseEntity.ok("签名校验错误");
            }

            Object result = joinPoint.proceed();
            return result;
        } catch (Throwable t) {
            return ResponseEntity.ok("签名校验异常");
        }

    }

    //获取请求参数并生成签名
    private String generatedSignature(HttpServletRequest request, String timestamp) {
        //获取RequestBody参数，此处需要配合过滤器处理request后才能获取
        String bodyParam = null;
        if (request instanceof ContentCachingRequestWrapper) {
            bodyParam = new String(((ContentCachingRequestWrapper) request).getContentAsByteArray(), StandardCharsets.UTF_8);
        }

        //获取RequestParam参数
        Map<String, String[]> requestParameterMap = request.getParameterMap();

        //获取PathVariable参数
        ServletWebRequest webRequest = new ServletWebRequest(request, null);
        Map<String, String> requestPathMap = (Map<String, String>) webRequest.getAttribute(
                HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);

        return SignUtil.sign(bodyParam,requestParameterMap,requestPathMap,secretKey,timestamp);
    }

}
