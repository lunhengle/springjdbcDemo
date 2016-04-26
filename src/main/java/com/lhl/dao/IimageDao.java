package com.lhl.dao;

/**
 * Created by lenovo on 2016/4/23.
 */
public interface IimageDao {
    /**
     * 更新图片的宽和高.
     *
     * @param width      宽
     * @param height     高
     * @param targetType 类型
     * @param type       细分type
     * @param url        路径
     */
    void addDefaultImage(int width, int height, int targetType, int type, String url);
}
