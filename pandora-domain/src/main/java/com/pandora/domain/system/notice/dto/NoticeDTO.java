package com.pandora.domain.system.notice.dto;

import com.pandora.infrastructure.cache.CacheCenter;
import com.pandora.dao.system.entity.SysNoticeEntity;
import com.pandora.dao.system.entity.SysUserEntity;
import java.util.Date;
import lombok.Data;

@Data
public class NoticeDTO {

    public NoticeDTO(SysNoticeEntity entity) {
        if (entity != null) {
            this.noticeId = entity.getNoticeId() + "";
            this.noticeTitle = entity.getNoticeTitle() + "";
            this.noticeType = entity.getNoticeType() + "";
            this.noticeContent = entity.getNoticeContent();
            this.status = entity.getStatus() + "";
            this.createTime = entity.getCreateTime();

            SysUserEntity cacheUser = CacheCenter.userCache.getObjectById(entity.getCreatorId());
            if (cacheUser != null) {
                this.creatorName = cacheUser.getUsername();
            }
        }
    }

    private String noticeId;

    private String noticeTitle;

    private String noticeType;

    private String noticeContent;

    private String status;

    private Date createTime;

    private String creatorName;

}
