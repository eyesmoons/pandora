package com.pandora.dao.metadata.enums;

public enum DatasourceTypeEnum {
    MYSQL("mysql", "com.mysql.cj.jdbc.Driver", "mysql数据源"),
    DORIS("doris", "com.mysql.cj.jdbc.Driver", "doris数据源");

    private String name;
    private String driverName;
    private String remark;

    DatasourceTypeEnum(String name, String driverName, String remark) {
        this.name = name;
        this.driverName = driverName;
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
