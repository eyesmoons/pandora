package com.pandora.domain.system.post.model;

import cn.hutool.core.bean.BeanUtil;
import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode.Business;
import com.pandora.domain.system.post.command.AddPostCommand;
import com.pandora.dao.system.entity.SysPostEntity;
import com.pandora.service.system.ISysPostService;

public class PostModelFactory {

    public static PostModel loadFromDb(Long postId, ISysPostService postService) {
        SysPostEntity byId = postService.getById(postId);
        if (byId == null) {
            throw new ApiException(Business.OBJECT_NOT_FOUND, postId, "职位");
        }
        return new PostModel(byId);
    }

    public static PostModel loadFromAddCommand(AddPostCommand addCommand, PostModel model) {
        if (addCommand != null && model != null) {
            BeanUtil.copyProperties(addCommand, model);
        }
        return model;
    }


}
