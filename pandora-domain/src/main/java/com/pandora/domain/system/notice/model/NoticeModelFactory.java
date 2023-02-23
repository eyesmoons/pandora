package com.pandora.domain.system.notice.model;

import cn.hutool.core.bean.BeanUtil;
import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode;
import com.pandora.domain.system.notice.command.NoticeAddCommand;
import com.pandora.dao.system.entity.SysNoticeEntity;
import com.pandora.service.system.ISysNoticeService;

/**
 * 公告模型工厂
 * @author valarchie
 */
public class NoticeModelFactory {

    public static NoticeModel loadFromDb(Long noticeId, ISysNoticeService noticeService) {
        SysNoticeEntity byId = noticeService.getById(noticeId);

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, noticeId, "通知公告");
        }

        return new NoticeModel(byId);
    }

    public static NoticeModel loadFromAddCommand(NoticeAddCommand command, NoticeModel model) {
        if (command != null && model != null) {
            BeanUtil.copyProperties(command, model);
        }
        return model;
    }


}
