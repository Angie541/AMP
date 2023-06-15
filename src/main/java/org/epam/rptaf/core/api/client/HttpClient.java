package org.epam.rptaf.core.api.client;

import org.epam.rptaf.core.api.dto.FilterCreateResponseDto;
import org.epam.rptaf.core.api.dto.FilterDto;
import org.epam.rptaf.core.api.dto.FilterUpdateResponseDto;
import org.epam.rptaf.core.api.dto.FiltersDto;

public interface HttpClient {

    FilterCreateResponseDto sendFilterPost(FilterDto filterDto);

    FilterDto sendFilterGet(int filterId);

    FiltersDto sendFiltersGet();

    FilterUpdateResponseDto sendFilterPut(int filterId, FilterDto filterDto);

    void sendFilterDelete(int filterId);
}
