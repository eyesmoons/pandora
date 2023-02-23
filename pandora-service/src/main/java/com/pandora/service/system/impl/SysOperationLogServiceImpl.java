package com.pandora.service.system.impl;

import com.pandora.dao.system.entity.SysOperationLogEntity;
import com.pandora.dao.system.mapper.SysOperationLogMapper;
import com.pandora.service.system.ISysOperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-08
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLogEntity> implements
        ISysOperationLogService {

}
