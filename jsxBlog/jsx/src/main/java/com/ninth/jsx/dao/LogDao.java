package com.ninth.jsx.dao;

import com.ninth.jsx.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDao {
    public void createLog(Log log);
    public int deleteLogById(@Param(value="id")int id);
    public List<Log> getLogList(Log log);
    public int getLogListCount();
}
