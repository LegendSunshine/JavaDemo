package com.sign.util;




import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.crypto.SecureUtil;
import com.sun.deploy.util.ArrayUtil;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */
public class SignUtil {

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        //升序排序
        Map<String, String> sortMap = new TreeMap<>(String::compareTo);
        sortMap.putAll(map);
        return sortMap;
    }

    public static String sign(String body, Map<String, String[]> params, Map<String, String> requestPathMap, String secretKey, String timestamp) {
        StringBuilder sb = new StringBuilder();
        if (CharSequenceUtil.isNotBlank(body)) {
            sb.append(body).append('&');
        }

        if (!CollectionUtils.isEmpty(params)) {
            params.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(paramEntry -> {
                        String paramValue = String.join(",", Arrays.stream(paramEntry.getValue()).sorted().toArray(String[]::new));
                        sb.append(paramEntry.getKey()).append("=").append(paramValue).append('&');
                    });
        }

        if (!requestPathMap.isEmpty()) {
            for (String key : requestPathMap.keySet()) {
                String value = requestPathMap.get(key);
                sb.append(key).append("=").append(value).append('#');
            }

        }


        return SecureUtil.md5(String.join("#", secretKey, timestamp, sb.toString()));
    }
}