package org.epam.rptaf.core.enums;

import lombok.Getter;

public enum TestContextKey {
    ADDED_FILTER_VALUE("ADDED_FILTER_VALUE"),
    ADDED_FILTER_NAME("ADDED_FILTER_NAME");

    @Getter
    private final String key;

    TestContextKey(String key) {
        this.key = key;
    }

}
