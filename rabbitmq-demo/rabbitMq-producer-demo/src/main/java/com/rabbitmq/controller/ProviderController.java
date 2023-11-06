package com.rabbitmq.controller;

import com.rabbitmq.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/message")
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping("/add")
    public void pushMessage(){
        providerService.providerMethod();
    }

}
