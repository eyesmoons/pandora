package com.pandora.domain.metadata.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class UpdateMetaDatasourceCommand extends AddMetaDatasourceCommand{

    @NotNull
    @PositiveOrZero
    private Long datasourceId;
}
