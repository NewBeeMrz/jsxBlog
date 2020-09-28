package com.ninth.jsx.service;

import com.ninth.jsx.entity.Log;
import com.ninth.jsx.utils.Page;

public interface LogService {
    public void createLog(Log log);
    public int deleteLogById(int id);
    public Page<Log> getLogList(Integer page, Integer rows);
}
