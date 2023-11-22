package cn.czh0123.garbageclassification.enums;

/**
 * 星期枚举
 */
public enum DayOfWeekEnum {
    MONDAY("analysis.monday", 1),
    TUESDAY("analysis.tuesday", 2),
    WEDNESDAY("analysis.wednesday", 3),
    THURSDAY("analysis.thursday", 4),
    FRIDAY("analysis.friday", 5),
    SATURDAY("analysis.saturday", 6),
    SUNDAY("analysis.sunday", 7);

    private String name;
    private int value;

    DayOfWeekEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}