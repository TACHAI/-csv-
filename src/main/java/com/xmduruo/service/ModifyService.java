package com.xmduruo.service;

import com.xmduruo.common.ServerResponse;
import com.xmduruo.po.MoveAndCsv;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by @Author tachai
 * date 2018/6/4 12:33
 *
 * @Email 1206966083@qq.com
 */
public interface ModifyService {
    ServerResponse<String> addPath(String mp4Name,String csvName,String mp4Path,String csvPath);
    ServerResponse<List<MoveAndCsv>> getAll();
}
