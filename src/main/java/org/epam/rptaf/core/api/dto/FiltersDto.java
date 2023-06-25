package org.epam.rptaf.core.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class FiltersDto {
    private List<FilterDto> content;
}
