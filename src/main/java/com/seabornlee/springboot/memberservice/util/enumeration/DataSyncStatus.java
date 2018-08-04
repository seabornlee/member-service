package com.seabornlee.springboot.memberservice.util.enumeration;

public enum DataSyncStatus {
    SUBMITTED(0),
    EXECUTING(1),
    COMPLETED(2);

    private int value;

    DataSyncStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DataSyncStatus valueOf(int value) {
        for (DataSyncStatus status : DataSyncStatus.values()) {
            if (value == status.getValue()) {
                return status;
            }
        }
        return null;
    }
}
