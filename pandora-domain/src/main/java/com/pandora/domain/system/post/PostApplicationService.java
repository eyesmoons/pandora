package com.pandora.domain.system.post;

import com.pandora.common.core.page.PageDTO;
import com.pandora.domain.common.command.BulkOperationCommand;
import com.pandora.domain.system.post.command.AddPostCommand;
import com.pandora.domain.system.post.command.UpdatePostCommand;
import com.pandora.domain.system.post.dto.PostDTO;
import com.pandora.domain.system.post.model.PostModel;
import com.pandora.domain.system.post.model.PostModelFactory;
import com.pandora.domain.system.post.query.PostQuery;
import com.pandora.dao.system.entity.SysPostEntity;
import com.pandora.service.system.ISysPostService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
public class PostApplicationService {

    @Autowired
    private ISysPostService postService;

    public PageDTO getPostList(PostQuery query) {
        Page<SysPostEntity> page = postService.page(query.toPage(), query.toQueryWrapper());
        List<PostDTO> records = page.getRecords().stream().map(PostDTO::new).collect(Collectors.toList());
        return new PageDTO(records, page.getTotal());
    }

    public PostDTO getPostInfo(Long postId) {
        SysPostEntity byId = PostModelFactory.loadFromDb(postId, postService);
        return new PostDTO(byId);
    }

    public void addPost(AddPostCommand addCommand) {
        PostModel postModel = PostModelFactory.loadFromAddCommand(addCommand, new PostModel());

        postModel.checkPostNameUnique(postService);
        postModel.checkPostCodeUnique(postService);

        postModel.insert();
    }

    public void updatePost(UpdatePostCommand updateCommand) {
        PostModel postModel = PostModelFactory.loadFromDb(updateCommand.getPostId(), postService);
        postModel.loadFromUpdateCommand(updateCommand);

        postModel.checkPostNameUnique(postService);
        postModel.checkPostCodeUnique(postService);

        postModel.updateById();
    }


    public void deletePost(BulkOperationCommand<Long> deleteCommand) {
        for (Long id : deleteCommand.getIds()) {
            PostModel postModel = PostModelFactory.loadFromDb(id, postService);
            postModel.checkCanBeDelete(postService);
        }

        postService.removeBatchByIds(deleteCommand.getIds());
    }



}
