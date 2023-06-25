package org.epam.rptaf.core.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FilterDto {
    private String owner;
    private boolean share;
    private int id;
    private String name;

    private List<ConditionDto> conditions;
    private List<OrderDto> orders;
    private String type;

}
