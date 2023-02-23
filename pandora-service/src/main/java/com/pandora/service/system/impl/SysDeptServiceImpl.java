package com.pandora.service.system.impl;

import com.pandora.dao.system.entity.SysDeptEntity;
import com.pandora.dao.system.entity.SysUserEntity;
import com.pandora.dao.system.mapper.SysDeptMapper;
import com.pandora.dao.system.mapper.SysUserMapper;
import com.pandora.service.system.ISysDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements ISysDeptService {

    @Autowired
    private SysUserMapper userMapper;


    @Override
    public boolean isDeptNameDuplicated(String deptName, Long deptId, Long parentId) {
        QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_name", deptName)
            .ne(deptId != null, "dept_id", deptId)
            .eq(parentId != null, "parent_id", parentId);

        return this.baseMapper.exists(queryWrapper);
    }


    @Override
    public boolean hasChildrenDept(Long deptId, Boolean enabled) {
        QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(enabled != null, "status", 1)
            .and(o -> o.eq("parent_id", deptId).or()
                .apply("FIND_IN_SET (" + deptId + " , ancestors)")
            );
        return this.baseMapper.exists(queryWrapper);
    }


    @Override
    public boolean isChildOfTheDept(Long parentId, Long childId) {
        QueryWrapper<SysDeptEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("dept_id = '" + childId + "' and FIND_IN_SET ( " + parentId + " , ancestors)");
        return this.baseMapper.exists(queryWrapper);
    }


    @Override
    public boolean isDeptAssignedToUsers(Long deptId) {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", deptId);
        return userMapper.exists(queryWrapper);
    }

}
