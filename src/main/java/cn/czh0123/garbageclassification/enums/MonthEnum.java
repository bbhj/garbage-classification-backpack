package cn.czh0123.garbageclassification.enums;

/**
 * 月份枚举
 */
public enum MonthEnum {
    JANUARY("analysis.january", 1),
    FEBRUARY("analysis.february", 2),
    MARCH("analysis.march", 3),
    APRIL("analysis.april", 4),
    MAY("analysis.may", 5),
    JUNE("analysis.june", 6),
    JULY("analysis.july", 7),
    AUGUST("analysis.august", 8),
    SEPTEMBER("analysis.september", 9),
    OCTOBER("analysis.october", 10),
    NOVEMBER("analysis.november", 11),
    DECEMBER("analysis.december", 12);

    private String name;
    private int value;

    MonthEnum(String name, int value) {
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