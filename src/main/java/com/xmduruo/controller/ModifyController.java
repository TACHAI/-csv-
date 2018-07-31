package com.xmduruo.controller;

import com.xmduruo.common.ServerResponse;
import com.xmduruo.po.MoveAndCsv;
import com.xmduruo.service.ModifyService;
import com.xmduruo.util.AddCsvTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by @Author tachai
 * date 2018/6/4 12:32
 *
 * @Email 1206966083@qq.com
 */
@Controller
@RequestMapping("/mc/")
public class ModifyController {
    @Autowired
    private ModifyService modifyService;

    @PostMapping("modifybytime")
    @ResponseBody
    public ServerResponse<String> modifybytime(String name, String startTime, String endTime, Integer a, Integer b) {

        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(a);
        System.out.println(b);
        if (StringUtils.isEmpty(startTime)) {
            startTime = "0";
        }
        if ("0".equals(endTime)) {
            endTime = "18000";
        }
        AddCsvTime.modifyAtoB(name, startTime, endTime, a, b);
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    @PostMapping("addPath")
    @ResponseBody
    public ServerResponse<String> addPath(String mp4Name, String csvName, String mp4Path, String csvPath) {
        return modifyService.addPath(mp4Name, csvName, mp4Path, csvPath);
    }

    @GetMapping("getAll")
    @ResponseBody
    public ServerResponse<List<MoveAndCsv>> getAll() {
        return modifyService.getAll();
    }

    /**
     * 根据文件名下载文件
     *
     * @param response
     * @param name
     */
    @RequestMapping("downloadfile")
    public void getFile(HttpServletResponse response, String name) {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + name + ".csv");
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("/usr/java/modifyCsv_jar/file/" + name )));
//            bis = new BufferedInputStream(new FileInputStream(new File("/usr/java/modifyCsv_jar/file/" + name + ".csv")));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("成功下载");
    }


    //文件上传    addPath(String mp4Name,String csvName,String mp4Path,String csvPath);
    //@Param("files")MultipartFile[] files,
    @RequestMapping("springUpload.do")
    public ServerResponse<String> springUpload(HttpSession session, HttpServletRequest request) throws IllegalStateException, IOException {

        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                //防止插空的数据
                if (!StringUtils.isEmpty(file.getOriginalFilename())) {
                    String path = "/usr/java/modifyCsv_jar/file/";


                    System.out.println(file.getName());


                    modifyService.addPath(file.getOriginalFilename(), "", "", file.getOriginalFilename());
                    file.transferTo(new File(path));
                    return ServerResponse.createBySuccessMessage("上传失败");

                }
            }
        }
        return ServerResponse.createBySuccessMessage("上传成功");
    }


}
