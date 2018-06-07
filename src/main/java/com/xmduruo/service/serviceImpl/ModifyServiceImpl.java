package com.xmduruo.service.serviceImpl;

import com.xmduruo.common.ResponseCode;
import com.xmduruo.common.ServerResponse;
import com.xmduruo.dao.MoveAndCsvMapper;
import com.xmduruo.po.MoveAndCsv;
import com.xmduruo.service.ModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by @Author tachai
 * date 2018/6/4 13:10
 *
 * @Email 1206966083@qq.com
 */
@Service
public class ModifyServiceImpl implements ModifyService {
    @Autowired
    private MoveAndCsvMapper moveAndCsvMapper;

    @Override
    public ServerResponse<String> addPath(String mp4Name,String csvName,String mp4Path,String csvPath) {
        int result= moveAndCsvMapper.add(mp4Name,csvName,mp4Path,csvPath);
        if(result>0){
            return ServerResponse.createBySuccessMessage("增加成功");
        }else {
            return ServerResponse.createByErrorMessage("增加失败");
        }
    }

    @Override
    public ServerResponse<List<MoveAndCsv>> getAll() {
        List<MoveAndCsv> list= moveAndCsvMapper.getAll();
        if(list!=null){
            return ServerResponse.createBySuccess(list, ResponseCode.SUCCESS.getDesc());
        }else {
            return ServerResponse.createByErrorMessage(ResponseCode.ERROR.getDesc());
        }
    }
}
