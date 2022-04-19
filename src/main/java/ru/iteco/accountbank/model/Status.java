package ru.iteco.accountbank.model;

public enum Status {

    PROCESSING("processing"),
    SUCCESSFUL("successful"),
    DECLINED("declined");

    public final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
