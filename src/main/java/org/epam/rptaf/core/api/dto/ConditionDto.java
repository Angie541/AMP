package org.epam.rptaf.core.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConditionDto {
    private String filteringField;
    private String condition;
    private String value;
}
