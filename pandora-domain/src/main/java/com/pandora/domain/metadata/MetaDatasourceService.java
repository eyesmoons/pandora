package com.pandora.domain.metadata;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pandora.common.core.page.PageDTO;
import com.pandora.dao.metadata.entity.MetaDatasourceEntity;
import com.pandora.domain.metadata.command.AddMetaDatasourceCommand;
import com.pandora.domain.metadata.command.UpdateMetaDatasourceCommand;
import com.pandora.domain.metadata.dto.MetaDatasourceDTO;
import com.pandora.domain.metadata.model.MetaDatasourceModel;
import com.pandora.domain.metadata.model.MetaDatasourceModelFactory;
import com.pandora.domain.metadata.query.DatasourceQuery;
import com.pandora.domain.system.dept.model.DeptModel;
import com.pandora.domain.system.dept.model.DeptModelFactory;
import com.pandora.domain.system.role.command.UpdateRoleCommand;
import com.pandora.domain.system.role.model.RoleModel;
import com.pandora.domain.system.role.model.RoleModelFactory;
import com.pandora.infrastructure.web.domain.login.LoginUser;
import com.pandora.service.metadata.IMetadataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaDatasourceService {

    @Autowired
    private IMetadataSourceService metadataSourceService;

    public PageDTO getDatasourceList(DatasourceQuery query) {
        Page<MetaDatasourceEntity> page = metadataSourceService.page(query.toPage(), query.toQueryWrapper());
        return new PageDTO(page.getRecords(), page.getTotal());
    }

    public void addDatasource(AddMetaDatasourceCommand addCommand) {
        MetaDatasourceModel model = MetaDatasourceModelFactory.loadFromAddCommand(addCommand, new MetaDatasourceModel());
        model.checkDatasourceNameUnique(metadataSourceService);
        model.insert();
    }

    public void updateDatasource(UpdateMetaDatasourceCommand updateCommand, LoginUser loginUser) {
        MetaDatasourceModel model = MetaDatasourceModelFactory.loadFromDb(updateCommand.getDatasourceId(), metadataSourceService);
        model.loadUpdateCommand(updateCommand);
        model.checkDatasourceNameUnique(metadataSourceService);
        model.updateById();
    }

    public void removeDatasource(Long datasourceId) {
        metadataSourceService.removeById(datasourceId);
    }

    public MetaDatasourceDTO getDatasourceInfo(Long datasourceId) {
        MetaDatasourceEntity byId = metadataSourceService.getById(datasourceId);
        return new MetaDatasourceDTO(byId);
    }
}
