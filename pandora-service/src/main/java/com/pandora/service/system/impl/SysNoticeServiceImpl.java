package com.pandora.service.system.impl;

import com.pandora.dao.system.entity.SysNoticeEntity;
import com.pandora.dao.system.mapper.SysNoticeMapper;
import com.pandora.service.system.ISysNoticeService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知公告表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNoticeEntity> implements ISysNoticeService {

    @Override
    public Page<SysNoticeEntity> getNoticeList(Page<SysNoticeEntity> page, Wrapper<SysNoticeEntity> queryWrapper) {
        return this.baseMapper.getNoticeList(page, queryWrapper);
    }

}
