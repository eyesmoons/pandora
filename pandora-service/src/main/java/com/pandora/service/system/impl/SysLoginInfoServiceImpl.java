package com.pandora.service.system.impl;

import com.pandora.dao.system.entity.SysLoginInfoEntity;
import com.pandora.dao.system.mapper.SysLoginInfoMapper;
import com.pandora.service.system.ISysLoginInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-07-10
 */
@Service
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLoginInfoMapper, SysLoginInfoEntity> implements
        ISysLoginInfoService {

}
