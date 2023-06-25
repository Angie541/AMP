package org.epam.rptaf.core.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true) //make setter return the object
public class OrderDto {
    private boolean isAsc;
    private String sortingColumn;
}
