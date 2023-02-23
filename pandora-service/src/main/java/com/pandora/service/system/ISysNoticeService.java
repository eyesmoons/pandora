package com.pandora.service.system;

import com.pandora.dao.system.entity.SysNoticeEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 通知公告表 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
public interface ISysNoticeService extends IService<SysNoticeEntity> {

    Page<SysNoticeEntity> getNoticeList(Page<SysNoticeEntity> page,
        @Param(Constants.WRAPPER) Wrapper<SysNoticeEntity> queryWrapper);

}
