package org.epam.rptaf.test.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.internal.mapping.Jackson2Mapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiTestUtil {
    public static final String AUTH_BEARER = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODY2NjQwMjcsInVzZXJfbmFtZSI6InN1cGVyYWRtaW4iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOSVNUUkFUT1IiXSwianRpIjoiMjM3ZjhlOGMtMjQ0NS00MjY0LThmYjgtNmM1MThlZGRkYWFhIiwiY2xpZW50X2lkIjoidWkiLCJzY29wZSI6WyJ1aSJdfQ.DNjckL0CUZ4Ugp6cnPqo91Dc0EfpEMbkX-LttArys3I";
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
}
