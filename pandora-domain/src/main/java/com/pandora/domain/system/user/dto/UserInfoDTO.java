package com.pandora.domain.system.user.dto;

import com.pandora.domain.system.role.dto.RoleDTO;
import lombok.Data;

@Data
public class UserInfoDTO {

    private UserDTO user;
    private RoleDTO role;

}
