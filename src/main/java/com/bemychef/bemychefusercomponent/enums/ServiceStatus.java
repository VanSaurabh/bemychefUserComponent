package com.bemychef.bemychefusercomponent.enums;

public enum ServiceStatus {

    ACTIVE("Active"), INACTIVE("InActive"), DELETED("Deleted");

    private String statusValue;

    private ServiceStatus(String status) {
        this.statusValue = status;
    }

    public String getStatusValue() {
        return statusValue;
    }

    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case INACTIVE:
                return "Inactive";
            case DELETED:
                return "Deleted";
        }
        return null;
    }
}
