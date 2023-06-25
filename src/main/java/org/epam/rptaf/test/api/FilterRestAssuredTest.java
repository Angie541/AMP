package org.epam.rptaf.test.api;

import org.epam.rptaf.core.api.client.RestAssuredClient;

public class FilterRestAssuredTest extends FilterTestBase {

    public FilterRestAssuredTest() {
        super(new RestAssuredClient("http://localhost:8080/api/v1"));
    }
}
