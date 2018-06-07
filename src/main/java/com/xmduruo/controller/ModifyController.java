package com.xmduruo.controller;

import com.xmduruo.common.ServerResponse;
import com.xmduruo.po.MoveAndCsv;
import com.xmduruo.service.ModifyService;
import com.xmduruo.util.AddCsvTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    public ServerResponse<String> modifybytime(String name,String startTime,String endTime,Integer a ,Integer b){

        AddCsvTime addCsvTime=new AddCsvTime();
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(a);
        System.out.println(b);
        if(StringUtils.isEmpty(startTime)){
            startTime="0";
        }
        if("0".equals(endTime)){
            endTime="18000";
        }
        addCsvTime.modifyAtoB(name,startTime,endTime,a,b);
        return ServerResponse.createBySuccessMessage("修改成功");
    }
    @PostMapping("addPath")
    @ResponseBody
    public ServerResponse<String> addPath(String mp4Name,String csvName,String mp4Path,String csvPath){
        return modifyService.addPath(mp4Name,csvName,mp4Path,csvPath);
    }

    @GetMapping("getAll")
    @ResponseBody
    public ServerResponse<List<MoveAndCsv>> getAll(){
        return modifyService.getAll();
    }

    /**
     * 根据文件名下载文件
     * @param response
     * @param name
     */
    @RequestMapping("downloadfile")
    public void getFile(HttpServletResponse response,String name){
        response.setHeader("content-type","application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=" + name+".csv");
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("/usr/java/modifyCsv_jar/file/"+ name+".csv")));
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
}
