package com.pandora.domain.system.notice.model;

import cn.hutool.core.bean.BeanUtil;
import com.pandora.domain.system.notice.command.NoticeUpdateCommand;
import com.pandora.dao.system.entity.SysNoticeEntity;
import com.pandora.dao.system.enums.dictionary.StatusEnum;
import com.pandora.dao.system.enums.dictionary.NoticeTypeEnum;
import com.pandora.dao.system.enums.interfaces.BasicEnumUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeModel extends SysNoticeEntity {

    public NoticeModel(SysNoticeEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public void loadUpdateCommand(NoticeUpdateCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this);
        }
    }

    public void checkFields() {
        BasicEnumUtil.fromValue(NoticeTypeEnum.class, getNoticeType());
        BasicEnumUtil.fromValue(StatusEnum.class, getStatus());
    }

}
