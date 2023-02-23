package com.pandora.service.system.impl;

import com.pandora.dao.system.entity.SysRoleMenuEntity;
import com.pandora.dao.system.mapper.SysRoleMenuMapper;
import com.pandora.service.system.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuEntity> implements
        ISysRoleMenuService {

}
