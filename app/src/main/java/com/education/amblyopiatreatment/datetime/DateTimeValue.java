package com.education.amblyopiatreatment.datetime;

/**
 * класс для форматирования даты и времени,
 * для хранения в памяти и парсинга
 *
 * @author Катя Левкович
 * @version 1.3, 28.11.2022
 */
public class DateTimeValue {
    private long millis;

    /**
     * формирование объекта из сохранённого состояния
     */
    public static DateTimeValue createFromConfig(String config) {
        try {
            long result = Long.parseLong(config);
            return new DateTimeValue(result);
        } catch (NumberFormatException ex) {
            return new DateTimeValue();
        }
    }

    /**
     * формирование объекта по умолчанию
     */
    public static DateTimeValue createDefault() {
        return new DateTimeValue();
    }

    private DateTimeValue() {
        this.millis = 0;
    }
    private DateTimeValue(long millis) {
        this.millis = millis;
    }

    /**
     * актуальное ли время
     */
    public boolean isPresent() {
        return millis != 0;
    }

    /**
     * формирование объекта времени "сейчас"
     */
    public static DateTimeValue timeNow() {
        DateTimeValue object = new DateTimeValue();
        object.millis = System.currentTimeMillis();
        return object;
    }

    /**
     * добавление определённого кол-ва минут
     */
    public DateTimeValue addMinutes(int minutes) {
        int msInMinute = 1000 * 60;
        millis += (long) minutes * msInMinute;
        return this;
    }

    /**
     * получить разницу между временами
     */
    public TimeSpan getTimeBefore(DateTimeValue dateTimeValue) {
        int millisDifference = (int)(dateTimeValue.millis - this.millis);
        return TimeSpan.createFromMillis(millisDifference);
    }

    /**
     * добавить промежуток времени
     */
    public void add(TimeSpan timeSpan) {
        int msInSecond = 1000;
        millis += (long) timeSpan.getTotalSeconds() * msInSecond;
    }

    /**
     * узнать, наступило ли время
     */
    public boolean isAfterThan(DateTimeValue dateTimeValue) {
        return this.millis > dateTimeValue.millis;
    }

    /**
     * получение строки конфигурации для запись в файл
     */
    public String getConfig() {
        return String.valueOf(millis);
    }
}
