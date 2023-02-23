package com.pandora.service.system.impl;

import cn.hutool.core.util.StrUtil;
import com.pandora.dao.system.entity.SysConfigEntity;
import com.pandora.dao.system.mapper.SysConfigMapper;
import com.pandora.service.system.ISysConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-09
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfigEntity> implements
        ISysConfigService {

    @Override
    public String getConfigValueByKey(String key) {
        if (StrUtil.isBlank(key)) {
            return StrUtil.EMPTY;
        }
        QueryWrapper<SysConfigEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("config_key", key);
        SysConfigEntity one = this.getOne(queryWrapper);
        if (one == null || one.getConfigValue() == null) {
            return StrUtil.EMPTY;
        }
        return one.getConfigValue();
    }
}
