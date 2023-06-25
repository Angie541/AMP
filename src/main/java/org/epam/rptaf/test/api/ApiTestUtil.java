package org.epam.rptaf.test.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.internal.mapping.Jackson2Mapper;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.epam.rptaf.core.api.dto.ConditionDto;
import org.epam.rptaf.core.api.dto.FilterDto;
import org.epam.rptaf.core.api.dto.OrderDto;

@UtilityClass
public class ApiTestUtil {
    public static final String AUTH_BEARER = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODY4NjM5OTYsInVzZXJfbmFtZSI6InN1cGVyYWRtaW4iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOSVNUUkFUT1IiXSwianRpIjoiYWY0OWM1OGEtM2FkZi00YzMyLTk0YjYtODg1OTU5Y2Q1ODIwIiwiY2xpZW50X2lkIjoidWkiLCJzY29wZSI6WyJ1aSJdfQ.pY_6DtoPGXl3ZK1DjGugjleeLubLxnUTJVITU_Dlbd0";
    public static final String PROJECT = "/API_FILTERS_PROJECT";
    public static final String FILTER_PATH = PROJECT + "/filter/";
    private static ObjectMapper objectMapper;

    public static Jackson2Mapper getMapperLambda() {
        return new Jackson2Mapper((type, s) -> getObjectMapper());
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper().findAndRegisterModules();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return objectMapper;
    }

    public static FilterDto getFilterDto() {
        ConditionDto conditionDto = new ConditionDto()
                .setCondition("cnt")
                .setFilteringField("name")
                .setValue("name");

        OrderDto orderDto = new OrderDto()
                .setAsc(true)
                .setSortingColumn("name");

        return new FilterDto()
                .setConditions(List.of(conditionDto))
                .setName("demo")
                .setOrders(List.of(orderDto))
                .setShare(true)
                .setType("Launch");
    }
}
