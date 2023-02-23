package com.pandora.domain.system.notice.query;

import cn.hutool.core.util.StrUtil;
import com.pandora.dao.system.entity.SysNoticeEntity;
import com.pandora.dao.system.query.AbstractPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class NoticeQuery extends AbstractPageQuery {

    private String noticeType;

    private String noticeTitle;

    private String creatorName;


    @SuppressWarnings("rawtypes")
    @Override
    public QueryWrapper toQueryWrapper() {
        QueryWrapper<SysNoticeEntity> sysNoticeWrapper = new QueryWrapper<>();
        sysNoticeWrapper.like(StrUtil.isNotEmpty(noticeTitle), "notice_title", noticeTitle)
            .eq(noticeType != null, "notice_type", noticeType)
            .like(StrUtil.isNotEmpty(creatorName), "u.username", creatorName);

        return sysNoticeWrapper;
    }
}
