package com.pandora.domain.system.user.dto;

import com.pandora.dao.system.entity.SysPostEntity;
import com.pandora.dao.system.entity.SysRoleEntity;
import com.pandora.dao.system.entity.SysUserEntity;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UserProfileDTO {

    public UserProfileDTO(SysUserEntity userEntity, SysPostEntity postEntity, SysRoleEntity roleEntity) {
        if (userEntity != null) {
            this.user = new UserDTO(userEntity);
        }

        if (postEntity != null) {
            this.postName = postEntity.getPostName();
        }

        if (roleEntity != null) {
            this.roleName = roleEntity.getRoleName();
        }
    }

    private UserDTO user;
    private String roleName;
    private String postName;

}
