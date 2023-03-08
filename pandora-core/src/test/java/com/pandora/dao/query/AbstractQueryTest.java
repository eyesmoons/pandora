package com.pandora.dao.query;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Date;

import com.pandora.dao.system.query.AbstractQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractQueryTest {

    private AbstractQuery query;

    @BeforeEach
    public void getNewQuery() {
        query = new AbstractQuery() {
            @Override
            public QueryWrapper toQueryWrapper() {
                return null;
            }
        };
    }


    @Test
    void addTimeConditionWithNull() {
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        query.addTimeCondition(queryWrapper, "login_time");

        String targetSql = queryWrapper.getTargetSql();
        Assertions.assertEquals("", targetSql);
    }


    @Test
    void addTimeConditionWithBothValue() {
        query.setBeginTime(new Date());
        query.setEndTime(new Date());

        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        query.addTimeCondition(queryWrapper, "login_time");

        String targetSql = queryWrapper.getTargetSql();
        Assertions.assertEquals("(login_time >= ? AND login_time <= ?)", targetSql);
    }


    @Test
    void addTimeConditionWithBeginValueOnly() {
        query.setBeginTime(new Date());

        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        query.addTimeCondition(queryWrapper, "login_time");

        String targetSql = queryWrapper.getTargetSql();
        Assertions.assertEquals("(login_time >= ?)", targetSql);
    }




}
