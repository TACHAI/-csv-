package com.xmduruo.vo;

import lombok.Data;

/**
 * Created by @Author tachai
 * date 2018/8/1 9:59
 *
 * @Email 1206966083@qq.com
 */
@Data
public class PageHelp<T> {
    private Long total;
    private  T rows;
}
