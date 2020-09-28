package com.ninth.jsx.service.Impl;

import com.ninth.jsx.dao.LogDao;
import com.ninth.jsx.entity.Log;
import com.ninth.jsx.entity.User;
import com.ninth.jsx.service.LogService;
import com.ninth.jsx.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("logService")
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    LogDao logDao;

    @Override
    public void createLog(Log log) {
        this.logDao.createLog(log);
    }

    @Override
    public int deleteLogById(int id) {
        return this.logDao.deleteLogById(id);
    }

    @Override
    public Page<Log> getLogList(Integer page, Integer rows) {
        // 创建用户对象
        Log log = new Log();

        // 当前页
        log.setStart((page-1) * rows) ;
        // 每页数
        log.setRows(rows);
        // 查询日志列表
        List<Log> logs = this.logDao.getLogList(log);
        // 查询日志列表总记录数
        Integer count = this.logDao.getLogListCount();
        // 创建Page返回对象
        Page<Log> result = new Page<>();
        result.setPage(page);
        result.setRows(logs);
        result.setSize(rows);
        result.setTotal(count);
        return result;
    }
}
