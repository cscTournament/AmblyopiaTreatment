package com.education.amblyopiatreatment.datetime;

/**
 * класс, отвечающий за время
 *
 * @author Катя Левкович
 * @version 1.1, 29.11.2022
 */
public class TimeSpan {
    private int hour;
    private int minute;
    private int second;

    private final static String SEPARATOR = ":";

    public TimeSpan(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static TimeSpan createFromConfig(String config) {
        String[] values = config.split(SEPARATOR);
        if (values.length != 3)
            return new TimeSpan(0, 0, 0);

        try {
            int hour = Integer.parseInt(values[0]);
            int minute = Integer.parseInt(values[1]);
            int second = Integer.parseInt(values[2]);
            return new TimeSpan(hour, minute, second);
        } catch (NumberFormatException ex) {
            return new TimeSpan(0, 0, 0);
        }
    }

    public String getConfig() {
        return hour + SEPARATOR + minute + SEPARATOR + second;
    }

    /**
     * конструктор с разбиением минут
     */
    public static TimeSpan createFromSeconds(int seconds) {
        TimeSpan object = new TimeSpan();
        object.setTotalSeconds(seconds);

        return object;
    }

    /**
     * распределение секунд в объекте
     */
    private void setTotalSeconds(int totalSeconds) {
        int secondsInMinute = 60;
        int secondsInHour = secondsInMinute * 60;

        hour = totalSeconds / secondsInHour;
        totalSeconds %= secondsInHour;
        minute = totalSeconds / secondsInMinute;
        totalSeconds %= secondsInMinute;
        second = totalSeconds;
    }

    /**
     * конструктор с разбиением миллисекунд
     */
    public static TimeSpan createFromMillis(int millis) {
        int msInSecond = 1000;
        int msInMinute = msInSecond * 60;
        int msInHour = msInMinute * 60;

        TimeSpan object = new TimeSpan();
        object.hour = millis / msInHour;
        millis %= msInHour;
        object.minute = millis / msInMinute;
        millis %= msInMinute;
        object.second = millis / msInSecond;

        return object;
    }

    private TimeSpan() {
    }

    /**
     * добавить к имеющемуся времени другое
     */
    public void add(TimeSpan timeSpan) {
        setTotalSeconds(getTotalSeconds() + timeSpan.getTotalSeconds());
    }

    /**
     * общее кол-во секунд
     */
    public int getTotalSeconds() {
        int secondsInMinute = 60;
        int secondsInHour = secondsInMinute * 60;
        return second + secondsInMinute * minute + secondsInHour * hour;
    }

    @Override
    public String toString() {
        String result = "";
        if (hour != 0)
            result += hour + " hours";
        if (minute != 0) {
            if (result.length() != 0)
                result += ", ";
            result += minute + " minutes";
        }
        if (result.length() != 0)
            result += ", ";
        result += second + " seconds";
        return result;
    }
}
