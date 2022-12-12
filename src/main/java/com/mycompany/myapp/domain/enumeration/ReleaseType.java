package com.mycompany.myapp.domain.enumeration;

/**
 * The ReleaseType enumeration.
 */
public enum ReleaseType {
    TRACK("Track"),
    SINGLE("Single"),
    ALBUM("Album");

    private final String value;

    ReleaseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
