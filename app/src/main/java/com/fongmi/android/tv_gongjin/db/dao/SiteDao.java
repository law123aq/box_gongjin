package com.fongmi.android.tv_gongjin.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.fongmi.android.tv_gongjin.bean.Site;

@Dao
public abstract class SiteDao extends BaseDao<Site> {

    @Query("SELECT * FROM Site WHERE `key` = :key")
    public abstract Site find(String key);
}