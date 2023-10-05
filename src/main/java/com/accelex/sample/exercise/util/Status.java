package com.accelex.sample.exercise.util;

public enum Status {
    PENDING(1, "Pending"),
    OUT(2, "Out"),
    RETURNED_OK(3, "Returned OK"),
    RETURNED_DAMAGED(4, "Returned Damaged");

    private final int value;
    private String displayName;

    Status(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isAvailable() {
        return this == RETURNED_OK;
    }
}