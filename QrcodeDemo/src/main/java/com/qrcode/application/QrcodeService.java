package com.qrcode.application;



import com.qrcode.until.QRCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 类名称: QrcodeService
 * 类描述: qrcode服务
 *
 * @author legend
 * @since 2023/08/17
 */

@Service
@Slf4j
public class QrcodeService {
    public  Integer zipFiles(List<String> srcFiles, File zipFile) throws IOException {
        // 创建 FileInputStream 对象
        InputStream fileInputStream = null;
        // 实例化 FileOutputStream 对象
        FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
        // 实例化 ZipOutputStream 对象
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
                int total =0;
        try {
            if (!srcFiles.isEmpty()) {
                // 判断压缩后的文件存在不，不存在则创建
                if (!zipFile.exists()) {
                    zipFile.createNewFile();
                } else {
                    zipFile.delete();
                    zipFile.createNewFile();
                }
                // 创建 ZipEntry 对象
                ZipEntry zipEntry = null;
                // 遍历源文件数组
                for (int i = 0; i < srcFiles.size(); i++) {
                    // 将源文件数组中的当前文件读入 FileInputStream 流中
                    URL uri = new URL(srcFiles.get(i));
                    fileInputStream = uri.openStream();
                    // 实例化 ZipEntry 对象，源文件数组中的当前文件
                        zipEntry = new ZipEntry(i+".jpg");
                    //输出i 输出格式 总共size条 完成i+1 条
                    total =i+1;
                    zipOutputStream.putNextEntry(zipEntry);
                    // 该变量记录每次真正读的字节个数
                    int len;
                    // 定义每次读取的字节数组
                    byte[] buffer = new byte[1024];
                    while ((len = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, len);

                    }
                }

            }
            return total;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            assert fileInputStream != null;
            fileInputStream.close();
            fileOutputStream.close();
            System.out.println("下载完成");
        }
        return total;
    }


    public void  createQrCode(HttpServletRequest request, HttpServletResponse response){
            String count = request.getParameter("count");
            int number  = count!=null?Integer.parseInt(count):5;
            //1获取到随机生成的uuid
            List<String> uuidArray = new ArrayList<>();
            for (int i = 0; i < number; i++) {
                String uuid = UUID.randomUUID().toString();
                uuidArray.add(uuid);
            }
            //二维码图像
            BufferedImage bufferedImage;
            //图片路径流
            InputStream imageInputStream = null;

            try (ServletOutputStream outputStream = response.getOutputStream()) {
                ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

                try {
                    LinkedHashMap<String, byte[]> hashMap = new LinkedHashMap<>();

                    for (String uuid : uuidArray) {
                        //使用zxing 生成二维码
                        bufferedImage = QRCodeUtils.generateQrCodeBack(uuid);
                        log.info("二维码生成成功");
                        byte[] bytes = imageToBytes(bufferedImage);
                        //MockMultipartFile 需要导入spring-test的依赖包
                        /*这里自定义流程 可以将图片上传到OSS 也可以直接获取生成的图片输出
                        *  本段代码实现的是直接将图片输出
                        * */
                       // MockMultipartFile mockMultipartFile = new MockMultipartFile("QRCode",uuid+".jpg","jpg", bytes);
                        hashMap.put(uuid, bytes);
                        log.info("完成");
                    }
                    for (Map.Entry<String, byte[]> entry : hashMap.entrySet()) {
                        // 获取到oss的图片路径,使用上传到Oss放开这两行代码
                        // URL url = new URL(entry.getValue());
                        // imageInputStream = url.openStream();

                        imageInputStream= new ByteArrayInputStream(entry.getValue());
                        //二维码图片名称
                        ZipEntry zipEntry = new ZipEntry(entry.getKey() + ".jpg");
                        zipOutputStream.putNextEntry(zipEntry);
                        int len;
                        // 定义每次读取的字节数组
                        byte[] buffer = new byte[10240];
                        log.info("开始写入zip");
                        while ((len = imageInputStream.read(buffer)) > 0) {
                            zipOutputStream.write(buffer, 0, len);
                        }
                    }
                }finally {
                    zipOutputStream.closeEntry();
                    zipOutputStream.flush();
                    //关闭流
                    zipOutputStream.close();
                    if (imageInputStream != null) {
                        imageInputStream.close();
                    }
                    outputStream.flush();
                }
            } catch (Exception e) {
                //捕获到异常就回滚
//                Transa ctionAspectSupport.currentTransactionStatus().setRollbackOnly();
                log.error("导出异常",e);
            }

    }

    /**
     *  将图片转换成字节数组
     *
     * @param bImg 图片流
     */

    private byte[] imageToBytes(BufferedImage bImg) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImg, "jpg", out);
        } catch (IOException e) {
            log.error("转成字节数组异常",e);
        }
        return out.toByteArray();
    }
}


