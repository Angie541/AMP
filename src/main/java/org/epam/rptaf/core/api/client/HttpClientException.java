package org.epam.rptaf.core.api.client;

public class HttpClientException extends RuntimeException {

    public HttpClientException(int status) {
        super(String.valueOf(status));
    }
}