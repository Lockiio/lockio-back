package com.miage.lockio.lockioback.enums;
import lombok.Getter;
@Getter
public enum Hours {
    EVERYTIME("24H/24", null, "everyday", null);
    private final String openingHour;
    private final String closingHour;
    private final String openingDay;
    private final String closingDay;
    Hours(String openingHour, String closingHour, String openingDay, String closingDay) {
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.openingDay = openingDay;
        this.closingDay = closingDay;
    }
}
