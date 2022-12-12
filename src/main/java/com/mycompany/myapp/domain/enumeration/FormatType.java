package com.mycompany.myapp.domain.enumeration;

/**
 * The FormatType enumeration.
 */
public enum FormatType {
    DIGITAL("Digital"),
    PHYSICAL("Physical");

    private final String value;

    FormatType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
