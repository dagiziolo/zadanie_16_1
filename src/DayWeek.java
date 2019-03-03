public enum DayWeek {
    MONDAY(6),
    TUESDAY(5),
    WEDNESDAY(4),
    THURSDAY(3),
    FRIDAY(2),
    SATURDAY(1),
    SUNDAY(1);

    private long dayToEndWeek;

    DayWeek(int dayToEndWeek) {
        this.dayToEndWeek = dayToEndWeek;
    }

    public long getDayToEndWeek() {
        return dayToEndWeek;
    }
}
