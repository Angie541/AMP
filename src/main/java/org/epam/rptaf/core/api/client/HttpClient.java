package org.epam.rptaf.core.api.client;

import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.epam.rptaf.core.api.dto.FiltersDto;

import static org.epam.rptaf.test.api.ApiTestUtil.*;

public class HttpClient {

    private final String baseUrl;

    public HttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @SneakyThrows
    public FiltersDto sendFiltersGet() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(baseUrl + FILTER_PATH);
        httpget.addHeader(HttpHeaders.AUTHORIZATION, AUTH_BEARER);
        ResponseHandler<FiltersDto> responseHandler = new FiltersDtoResponseHandler();
        return httpclient.execute(httpget, responseHandler);
    }

    private static class FiltersDtoResponseHandler implements ResponseHandler<FiltersDto> {
        @SneakyThrows
        @Override
        public FiltersDto handleResponse(HttpResponse httpResponse) {
            HttpEntity entity = httpResponse.getEntity();
            return getObjectMapper().readValue(entity.getContent().readAllBytes(), FiltersDto.class);
        }
    }
}