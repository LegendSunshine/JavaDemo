package com.example.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */
@Controller
@ResponseBody
@RequestMapping("/v1/demo")
public class NacosController {

    @NacosValue(value = "${useconfig:true}",autoRefreshed = true)
    private Boolean nacosConfige;

    @GetMapping("/get/nacos")
    public boolean getNacos() {

        return nacosConfige;
    }
}
