package com.lhl.service;

/**
 * Created by lenovo on 2016/4/19.
 * 人 service 接口
 */
public interface IPersonService {
    /**
     * 修改名称.
     *
     * @param name 名称
     * @param id   id
     */
    void modifyName(String name, long id);

}
