package com.lhl.dao.impl;

import com.lhl.dao.IimageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 2016/4/23.
 */
@Repository
public class ImageDaoImpl implements IimageDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 更新图片的宽和高.
     *
     * @param width      宽
     * @param height     高
     * @param targetType 类型
     * @param type       细分type
     * @param url        路径
     */
    public void addDefaultImage(int width, int height, int targetType, int type, String url) {
        String sql = "insert into defaultImage(targetType,type,width,height,url) value(:targetType,:type,:width,:height,:url)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("targetType", targetType);
        parameterSource.addValue("type", type);
        parameterSource.addValue("url", url);
        parameterSource.addValue("width", width);
        parameterSource.addValue("height", height);
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }
}
