package com.pandora.infrastructure.cache.guava;


import com.pandora.dao.system.entity.SysDeptEntity;
import com.pandora.service.system.ISysConfigService;
import com.pandora.service.system.ISysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author valarchie
 */
@Component
@Slf4j
public class GuavaCacheService {

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private ISysDeptService deptService;

    public GuavaCacheTemplate<String> configCache = new GuavaCacheTemplate<String>() {
        @Override
        public String getObjectFromDb(Object id) {
            return configService.getConfigValueByKey(id.toString());
        }
    };

    public GuavaCacheTemplate<SysDeptEntity> deptCache = new GuavaCacheTemplate<SysDeptEntity>() {
        @Override
        public SysDeptEntity getObjectFromDb(Object id) {
            return deptService.getById(id.toString());
        }
    };


}
