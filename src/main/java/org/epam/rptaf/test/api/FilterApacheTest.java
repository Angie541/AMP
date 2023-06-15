package org.epam.rptaf.test.api;

import org.epam.rptaf.core.api.client.ApacheHttpClient;

public class FilterApacheTest extends FilterTestBase {

    public FilterApacheTest() {
        super(new ApacheHttpClient("http://localhost:8080/api/v1"));
    }

}