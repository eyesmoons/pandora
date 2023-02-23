package com.pandora.domain.system.logininfo;

import com.pandora.common.core.page.PageDTO;
import com.pandora.domain.common.command.BulkOperationCommand;
import com.pandora.domain.system.logininfo.dto.LoginInfoDTO;
import com.pandora.domain.system.logininfo.query.LoginInfoQuery;
import com.pandora.dao.system.entity.SysLoginInfoEntity;
import com.pandora.service.system.ISysLoginInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
public class LoginInfoApplicationService {

    @Autowired
    private ISysLoginInfoService loginInfoService;

    public PageDTO getLoginInfoList(LoginInfoQuery query) {
        Page<SysLoginInfoEntity> page = loginInfoService.page(query.toPage(), query.toQueryWrapper());
        List<LoginInfoDTO> records = page.getRecords().stream().map(LoginInfoDTO::new).collect(Collectors.toList());
        return new PageDTO(records, page.getTotal());
    }

    public void deleteLoginInfo(BulkOperationCommand<Long> deleteCommand) {
        QueryWrapper<SysLoginInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("info_id", deleteCommand.getIds());
        loginInfoService.remove(queryWrapper);
    }

}
