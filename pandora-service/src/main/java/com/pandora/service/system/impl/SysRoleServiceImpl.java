package com.pandora.service.system.impl;

import com.pandora.dao.system.entity.SysRoleEntity;
import com.pandora.dao.system.entity.SysUserEntity;
import com.pandora.dao.system.mapper.SysRoleMapper;
import com.pandora.dao.system.mapper.SysRoleMenuMapper;
import com.pandora.dao.system.mapper.SysUserMapper;
import com.pandora.service.system.ISysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements ISysRoleService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public boolean isRoleNameDuplicated(Long roleId, String roleName) {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(roleId != null, "role_id", roleId)
            .eq("role_name", roleName);
        return this.baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean isRoleKeyDuplicated(Long roleId, String roleKey) {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(roleId != null, "role_id", roleId)
            .eq("role_key", roleKey);
        return this.baseMapper.exists(queryWrapper);
    }

    @Override
    public boolean isAssignedToUsers(Long roleId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        return userMapper.exists(queryWrapper);
    }


}
