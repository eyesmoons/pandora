package com.pandora.domain.system.user.dto;

import com.pandora.domain.system.post.dto.PostDTO;
import com.pandora.domain.system.role.dto.RoleDTO;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class UserDetailDTO {

    private UserDTO user;

    /**
     * 返回所有role
     */
    private List<RoleDTO> roles;

    /**
     * 返回所有posts
     */
    private List<PostDTO> posts;

    private Long postId;

    private Long roleId;

    private Set<String> permissions;

}
