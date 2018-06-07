package com.xmduruo.dao;

import com.xmduruo.po.MoveAndCsv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by @Author tachai
 * date 2018/6/4 23:03
 *
 * @Email 1206966083@qq.com
 */
@Mapper
public interface MoveAndCsvMapper {
    int add(@Param("mp4Name")String mp4Name, @Param("csvName")String csvName, @Param("mp4Path")String mp4Path, @Param("csvPath")String csvPath);
    List<MoveAndCsv> getAll();
}
