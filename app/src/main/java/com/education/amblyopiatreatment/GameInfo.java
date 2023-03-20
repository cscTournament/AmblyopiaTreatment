package com.education.amblyopiatreatment;

import com.education.amblyopiatreatment.datetime.TimeSpan;

/**
 * информация об игре: название и время, которое за ней провёл пользователь
 *
 * @author Катя Левкович и Егор Силин
 * @version 1.2, 30.12.2022
 */
public class GameInfo {
    private String name;
    private String htmlFileUrl;
    private TimeSpan timeSpan;

    private static final String PARAM_SEPARATOR = "!@#@!";

    /**
     * создание объекта GameInfo из строки файла конфигурации
     */
    public static GameInfo createFromConfig(String config) {
        if (config == null)
            return new GameInfo();

        String[] params = config.split(PARAM_SEPARATOR);
        if (params.length != 3) {
            return new GameInfo();
        }
        GameInfo obj = new GameInfo();
        obj.name = params[0];
        obj.htmlFileUrl = params[1];
        obj.timeSpan = TimeSpan.createFromConfig(params[2]);
        return obj;
    }

    /**
     * создание новой игры по имени и url (не из файла)
     */
    public static GameInfo createNew(String name, String url) {
        GameInfo obj = new GameInfo();
        obj.name = name;
        obj.htmlFileUrl = url;
        obj.timeSpan = new TimeSpan(0, 0, 0);
        return obj;
    }

    /**
     * приватный конструктор
     */
    private GameInfo() {
    }

    /**
     * заполнен ли объект информации, или неудачно прочтён
     */
    public boolean isPresent() {
        return (name != null && htmlFileUrl != null && timeSpan != null);
    }

    public String getName() {
        return name;
    }
    public String getUrl() {
        return htmlFileUrl;
    }
    public TimeSpan getTimeSpan() {
        return timeSpan;
    }

    /**
     * получение строки конфигурации для сохранения в файл
     */
    public String getConfig() {
        return name + PARAM_SEPARATOR + htmlFileUrl + PARAM_SEPARATOR + timeSpan.getConfig();
    }
}
