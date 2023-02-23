package com.pandora.domain.common.command;

import cn.hutool.core.collection.CollUtil;
import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode;
import java.util.List;
import lombok.Data;

@Data
public class BulkOperationCommand<T> {

    public BulkOperationCommand(List<T> idList) {
        if (CollUtil.isEmpty(idList)) {
            throw new ApiException(ErrorCode.Business.BULK_DELETE_IDS_IS_INVALID);
        }

        this.ids = idList;
    }

    private List<T> ids;

}
