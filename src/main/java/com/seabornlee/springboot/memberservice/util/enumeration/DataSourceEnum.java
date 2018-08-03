package com.seabornlee.springboot.memberservice.util.enumeration;

public enum DataSourceEnum {
    ESpirit(1);

    private int value;

    DataSourceEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DataSourceEnum valueOf(int value) {
        DataSourceEnum[] enums = DataSourceEnum.values();
        for (DataSourceEnum dataSourceEnum : enums) {
            if (dataSourceEnum.getValue() == value) {
                return dataSourceEnum;
            }
        }
        return null;
    }
}
