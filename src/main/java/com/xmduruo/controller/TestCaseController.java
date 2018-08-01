package com.xmduruo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xmduruo.common.ServerResponse;
import com.xmduruo.dao.TestItemsMapper;
import com.xmduruo.po.TestItems;
import com.xmduruo.service.IListService;
import com.xmduruo.util.TestJson;
import com.xmduruo.vo.PageHelp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * Created by @Author tachai
 * date 2018/7/31 10:51
 *
 * @Email 1206966083@qq.com
 */
@RestController
@RequestMapping("/test/")
public class TestCaseController {
    @Autowired
    private IListService listService;
    @Autowired
    private TestItemsMapper testItemsMapper;

    @PostMapping("byCase")
    @ResponseBody
    public ServerResponse<List<String>> testJIU(String fileName,String str){
        if(!StringUtils.isBlank(fileName)&&!StringUtils.isBlank(str)){
            String [] strs = str.split("-");
            //改变上次审核时间
            List<TestItems> list  =  testItemsMapper.list(fileName);
            TestItems testItems = list.get(0);
            testItems.setTestTime(new Date());
            List<String> listStr = TestJson.testByTestCase(fileName,strs);

            if(listStr.get(listStr.size()-1).indexOf("祝您生活愉快")>-1){
                testItems.setStatus("测试通过");
            }else {
                testItems.setStatus("测试失败");
            }
            //更新数据
            testItemsMapper.updateByPrimaryKeySelective(testItems);
           return ServerResponse.createBySuccess(listStr);
        }
        return ServerResponse.createByErrorMessage("文件名和回答的值不能为空");
    }

    @GetMapping("list")
    @ResponseBody
    public ServerResponse<List<TestItems>> list(String testName){
//        Page page = PageHelper.startPage(pageNumber,pageSize);

        String name =URLDecoder.decode(testName);
        System.out.println(name);
        List<TestItems> list = testItemsMapper.list(name);
        System.out.println("时间"+list.get(1).getTestTime().toString());
        //        if(list!=null){
//            Long total = page.getTotal();
//            PageHelp pageHelper = new PageHelp();
//            pageHelper.setTotal(total);
//            pageHelper.setRows(list);
//            return pageHelper;
//        }
        return listService.list(name);
    }

    @PostMapping("add")
    @ResponseBody
    public ServerResponse<String> add(TestItems items){
        if(items!=null){
            return listService.add(items);
        }
        return ServerResponse.createByErrorMessage("传过来的值为空");
    }

    @PostMapping("delete")
    @ResponseBody
    public ServerResponse<String> delete(Integer id){
        if(id != null){
            return listService.deleteById(id);
        }
        return ServerResponse.createByErrorMessage("缺少关键参数");
    }

}
