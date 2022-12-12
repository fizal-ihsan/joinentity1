package com.mycompany.myapp.domain.enumeration;

/**
 * The ReleaseMediaType enumeration.
 */
public enum ReleaseMediaType {
    CD("Compact Disc"),
    VINYL("Vinyl"),
    DIGITAL("Digital");

    private final String value;

    ReleaseMediaType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
