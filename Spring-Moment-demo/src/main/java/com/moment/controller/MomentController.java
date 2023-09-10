package com.moment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class MomentController {

    @GetMapping("/")
    public String moment(){


        return "";
    }
}
