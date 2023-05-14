package com.qrcode.controller;

import com.qrcode.application.QrcodeService;

import com.qrcode.until.ResponseResult;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName qrcodeController
 * @Date 2023/3/26 10:05
 * @Author legend
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/qrcode/demos")
public class QrcodeController {

    private final QrcodeService qrcodeService;


    @PostMapping("/zip")
    @ApiOperation("获取图片url下载并保存为zip")
    public ResponseResult<String> qrcodeZip() throws IOException {
        List<String> fileList = new ArrayList<>();
        fileList.add("https://*********.jpg");
        String url = "D:\\测试11" + ".zip";
        File zipFile = new File(url);
        // 调用压缩方法
        Integer total = qrcodeService.zipFiles(fileList, zipFile);
        return new ResponseResult<String>("成功"+total+"条","ok",200);
    }

    @PostMapping("/create/qrcode")
    @ApiOperation("生成二维码并下载返回zip")
    public void qrcode(HttpServletRequest request,HttpServletResponse response){
        qrcodeService.createQrCode(request,response);

    }

}
