package com.seabornlee.springboot.memberservice.util.enumeration;


public enum DataType {
    SKU(0),
    WAREHOUSE_BIN(1),
    WAREHOUSE(2);

    private int value;

    DataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DataType valueOf(int value) {
        for (DataType type : DataType.values()) {
            if (value == type.getValue()) {
                return type;
            }
        }
        return null;
    }
}
