package com.mycompany.myapp.domain.enumeration;

/**
 * The ReleaseStatus enumeration.
 */
public enum ReleaseStatus {
    ACTIVE("Active");

    private final String value;

    ReleaseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
