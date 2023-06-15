package org.epam.rptaf.test.api;

import static org.epam.rptaf.test.api.ApiTestUtil.getFilterDto;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.epam.rptaf.core.api.client.HttpClient;
import org.epam.rptaf.core.api.client.HttpClientException;
import org.epam.rptaf.core.api.client.RestAssuredClient;
import org.epam.rptaf.core.api.dto.FilterCreateResponseDto;
import org.epam.rptaf.core.api.dto.FilterDto;
import org.epam.rptaf.core.api.dto.FilterUpdateResponseDto;
import org.epam.rptaf.core.api.dto.FiltersDto;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public abstract class FilterTestBase {
    protected final HttpClient restClient;
    private Integer filterId;

    public FilterTestBase(HttpClient restClient) {
        this.restClient = restClient;
    }

    @AfterMethod
    public void after() {
        if (filterId != null) {
            restClient.sendFilterDelete(filterId);
            filterId = null;
        }
    }

    @Test()
    public void createUserFilter() {
        createFilter(true);

        assertThat(filterId, notNullValue());
    }

    @Test(expectedExceptions = HttpClientException.class, expectedExceptionsMessageRegExp = "400")
    public void createWrongUserFilter() {
        createFilter(false);

        assertThat(filterId, nullValue());
    }

    @Test(expectedExceptions = HttpClientException.class, expectedExceptionsMessageRegExp = "409")
    public void createExistedUserFilter() {
        createFilter(true);

        createFilter(true);
    }

    @Test
    public void getPermittedFilters() {
        createFilter(true);

        FiltersDto dto = restClient.sendFiltersGet();

        assertThat(dto.getContent(), hasSize(1));
    }

    @Test
    public void getExistedUserFilter() {
        createFilter(true);

        FilterDto filterDto = restClient.sendFilterGet(filterId);

        assertThat(filterDto.getId(), is(filterId));
        assertThat(filterDto.getName(), is("demo"));
        assertThat(filterDto.getType(), is("Launch"));
    }

    @Test(expectedExceptions = HttpClientException.class, expectedExceptionsMessageRegExp = "404")
    public void getNotExistedUserFilter() {
        restClient.sendFilterGet(225);
    }

    @Test
    public void updateExistedUserFilter() {
        createFilter(true);
        FilterDto updateDto = getFilterDto()
                .setShare(false);

        FilterUpdateResponseDto updateResponse = restClient.sendFilterPut(filterId, updateDto);

        assertThat(updateResponse.getMessage(), is("User filter with ID = '" + filterId + "' successfully updated."));
    }

    @Test(expectedExceptions = HttpClientException.class, expectedExceptionsMessageRegExp = "404")
    public void updateNotExistedUserFilter() {
        FilterDto updateDto = getFilterDto();

        restClient.sendFilterPut(225, updateDto);
    }

    @Test(expectedExceptions = HttpClientException.class, expectedExceptionsMessageRegExp = "404")
    public void deleteNotExistedUserFilter() {
        restClient.sendFilterDelete(225);
    }

    @Test
    public void deleteExistedUserFilter() {
        createFilter(true);

        restClient.sendFilterDelete(filterId);

        filterId = null;
    }

    private void createFilter(boolean correct) {
        FilterCreateResponseDto responseDto = restClient.sendFilterPost(correct ? getFilterDto() : new FilterDto());
        filterId = responseDto.getId();
    }
}