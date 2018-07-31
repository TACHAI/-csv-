package com.xmduruo.service;

import com.xmduruo.common.ServerResponse;
import com.xmduruo.po.TestItems;

import java.util.List;

/**
 * Created by @Author tachai
 * date 2018/7/31 15:50
 *
 * @Email 1206966083@qq.com
 */
public interface IListService {
   ServerResponse<List<TestItems>>  list(String testName);
   ServerResponse<String> deleteById(Integer id);
   ServerResponse<String>  add(TestItems items);
   ServerResponse<String> update(TestItems items);
}
