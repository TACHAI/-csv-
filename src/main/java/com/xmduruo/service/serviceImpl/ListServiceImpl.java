package com.xmduruo.service.serviceImpl;

import com.xmduruo.common.ServerResponse;
import com.xmduruo.dao.TestItemsMapper;
import com.xmduruo.po.TestItems;
import com.xmduruo.service.IListService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by @Author tachai
 * date 2018/7/31 15:50
 *
 * @Email 1206966083@qq.com
 */
@Service("iListService")
public class ListServiceImpl implements IListService {
    @Autowired
    private TestItemsMapper testItemsMapper;
    @Override
    public ServerResponse<List<TestItems>> list(String testName) {
        List<TestItems> list = testItemsMapper.list(testName);
        if(list!=null){
            return ServerResponse.createBySuccess(list,"查询成功");
        }
        return ServerResponse.createByErrorMessage("未找到相关数据");
    }

    @Override
    public ServerResponse<String> deleteById(Integer id) {
        int result = testItemsMapper.deleteByPrimaryKey(id);
        if(result>0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @Override
    public ServerResponse<String> add(TestItems items) {
        if(StringUtils.isBlank(items.getTestName())){
            List<TestItems> list = testItemsMapper.list(items.getTestName());
            if(list.size()>0){
                return ServerResponse.createByErrorMessage("该事项已存在");
            }
        }

        int result = testItemsMapper.insert(items);
        if(result>0){
            return ServerResponse.createBySuccessMessage("新增事项成功");
        }
        return ServerResponse.createByErrorMessage("新增失败");
    }

    @Override
    public ServerResponse<String> update(TestItems items) {
        return null;
    }
}
