package com.pandora.domain.system.operationlog;

import com.pandora.common.core.page.PageDTO;
import com.pandora.domain.common.command.BulkOperationCommand;
import com.pandora.domain.system.operationlog.dto.OperationLogDTO;
import com.pandora.domain.system.operationlog.query.OperationLogQuery;
import com.pandora.dao.system.entity.SysOperationLogEntity;
import com.pandora.service.system.ISysOperationLogService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
public class OperationLogApplicationService {

    @Autowired
    private ISysOperationLogService operationLogService;

    public PageDTO getOperationLogList(OperationLogQuery query) {
        Page<SysOperationLogEntity> page = operationLogService.page(query.toPage(), query.toQueryWrapper());
        List<OperationLogDTO> records = page.getRecords().stream().map(OperationLogDTO::new).collect(Collectors.toList());
        return new PageDTO(records, page.getTotal());
    }

    public void deleteOperationLog(BulkOperationCommand<Long> deleteCommand) {
        operationLogService.removeBatchByIds(deleteCommand.getIds());
    }

}
