package com.pandora.domain.system.dept.model;

import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode;
import com.pandora.domain.system.dept.command.AddDeptCommand;
import com.pandora.dao.system.entity.SysDeptEntity;
import com.pandora.service.system.ISysDeptService;

/**
 * 部门模型工厂
 * @author valarchie
 */
public class DeptModelFactory {

    public static DeptModel loadFromDb(Long deptId, ISysDeptService deptService) {
        SysDeptEntity byId = deptService.getById(deptId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, deptId, "部门");
        }
        return new DeptModel(byId);
    }

    public static DeptModel loadFromAddCommand(AddDeptCommand addCommand, DeptModel model) {
        model.setParentId(addCommand.getParentId());
        model.setAncestors(addCommand.getAncestors());
        model.setDeptName(addCommand.getDeptName());
        model.setOrderNum(addCommand.getOrderNum());
        model.setLeaderName(addCommand.getLeaderName());
        model.setPhone(addCommand.getPhone());
        model.setEmail(addCommand.getEmail());
        return model;
    }



}
