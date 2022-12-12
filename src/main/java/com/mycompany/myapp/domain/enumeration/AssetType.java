package com.mycompany.myapp.domain.enumeration;

/**
 * The AssetType enumeration.
 */
public enum AssetType {
    RECORDING("Recording"),
    MUSICALWORKS("Musical Works"),
    MERCHANDISE("Merchandise");

    private final String value;

    AssetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
