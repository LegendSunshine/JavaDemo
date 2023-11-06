package com.sign.controller;

import com.sign.config.CheckSign;
import com.sign.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 类名称: SignController
 * 类描述: 接口签名
 *
 * @author legend
 * @since 2023/7/8
 */
@RestController
@RequestMapping("/v1/sign")
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @PostMapping("/checkSign/{name}/{age}")
    @CheckSign
    public ResponseEntity postTestPdf(
            @PathVariable("name") String name,
            @PathVariable("age") String age
    ) {
        return ResponseEntity.ok(signService.add(name,age));
    }
}
