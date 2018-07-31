package com.xmduruo.dao;

import com.xmduruo.po.TestItems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TestItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestItems record);

    int insertSelective(TestItems record);

    TestItems selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestItems record);

    int updateByPrimaryKey(TestItems record);

    List<TestItems> list(@Param("testName") String testName);
}