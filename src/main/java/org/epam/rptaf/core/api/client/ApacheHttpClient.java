package org.epam.rptaf.core.api.client;

import static org.epam.rptaf.test.api.ApiTestUtil.AUTH_BEARER;
import static org.epam.rptaf.test.api.ApiTestUtil.FILTER_PATH;
import static org.epam.rptaf.test.api.ApiTestUtil.getObjectMapper;

import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.epam.rptaf.core.api.dto.FilterCreateResponseDto;
import org.epam.rptaf.core.api.dto.FilterDto;
import org.epam.rptaf.core.api.dto.FilterUpdateResponseDto;
import org.epam.rptaf.core.api.dto.FiltersDto;

public class ApacheHttpClient implements HttpClient {

    private final String baseUrl;

    public ApacheHttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @SneakyThrows
    @Override
    public FilterCreateResponseDto sendFilterPost(FilterDto filterDto) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(baseUrl + FILTER_PATH);
            addAuthHeader(httpPost);
            //serialization
            String requestDtoJson = getObjectMapper().writeValueAsString(filterDto);
            httpPost.setEntity(new StringEntity(requestDtoJson, ContentType.APPLICATION_JSON));
            return httpclient.execute(httpPost, convertResponseTo(FilterCreateResponseDto.class));
        }
    }

    @SneakyThrows
    @Override
    public FilterDto sendFilterGet(int filterId) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(baseUrl + FILTER_PATH + filterId);
            addAuthHeader(httpGet);
            return httpclient.execute(httpGet, convertResponseTo(FilterDto.class));
        }
    }

    @SneakyThrows
    @Override
    public FiltersDto sendFiltersGet() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(baseUrl + FILTER_PATH);
            addAuthHeader(httpGet);
            return httpclient.execute(httpGet, convertResponseTo(FiltersDto.class));
        }
    }

    @SneakyThrows
    @Override
    public FilterUpdateResponseDto sendFilterPut(int filterId, FilterDto filterDto) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(baseUrl + FILTER_PATH + filterId);
            addAuthHeader(httpPut);
            //serialization
            String requestDtoJson = getObjectMapper().writeValueAsString(filterDto);
            httpPut.setEntity(new StringEntity(requestDtoJson, ContentType.APPLICATION_JSON));
            return httpclient.execute(httpPut, convertResponseTo(FilterUpdateResponseDto.class));
        }
    }

    @SneakyThrows
    @Override
    public void sendFilterDelete(int filterId) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(baseUrl + FILTER_PATH + filterId);
            addAuthHeader(httpDelete);
            httpclient.execute(httpDelete, convertResponseTo(Void.class));
        }
    }

    private <T> ResponseHandler<T> convertResponseTo(Class<T> tClass) {
        return httpResponse -> {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode <= 399) {
                //deserialization
                return getObjectMapper().readValue(httpResponse.getEntity().getContent().readAllBytes(), tClass);
            } else {
                throw new HttpClientException(statusCode);
            }
        };
    }

    private void addAuthHeader(HttpRequestBase request) {
        request.addHeader(HttpHeaders.AUTHORIZATION, "bearer " + AUTH_BEARER);
    }
}