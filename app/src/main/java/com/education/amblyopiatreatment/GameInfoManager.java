package com.education.amblyopiatreatment;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализующий шаблон "синглтон", содержит информацию об играх:
 * об их названиях и сколько пользователь провёл за ними времени
 *
 * @author Катя Левкович и Егор Силин
 * @version 1.1, 30.12.2022
 */
public class GameInfoManager {
    private static GameInfoManager instance;

    private static SharedPreferences sharedPreferences;
    /**
     * установка разделяемых ресурсов для доступа к файлам
     */
    public static void setSharedPreferences(SharedPreferences preferences) {
        sharedPreferences = preferences;
    }

    private List<GameInfo> games;

    private static final String[] PREFERENCE_GAMES = {"Tanks", "Platformer", "Shooter",
            "Quest", "Quest 2", "Tale"};
    /**
     * получение названий игр
     */
    public static String[] getGamesNames() {
        return PREFERENCE_GAMES;
    }
    private static final String[] HTML_GAMES = {
            "file:///android_asset/tanks/index.html",
            "file:///android_asset/platformer/index.html",
            "file:///android_asset/shooter/index.html",
            "file:///android_asset/quest/index.html",
            "file:///android_asset/quest2/index.html",
            "file:///android_asset/tale/index.html"};

    /**
     * получение сущности
     */
    public static GameInfoManager getInstance() {
        if (instance == null)
            instance = new GameInfoManager();
        return instance;
    }

    /**
     * приватный конструктор, подгружающий данные из файлов
     */
    private GameInfoManager() {
        this.games = loadGamesFromFiles();
    }

    /**
     * чтение сохранённой информации из файлов
     */
    private List<GameInfo> loadGamesFromFiles() {
        List<GameInfo> list = new ArrayList<>();

        for (int i = 0; i < PREFERENCE_GAMES.length; i++ ) {
            String gameConfig = sharedPreferences.getString(PREFERENCE_GAMES[i], null);
            GameInfo game = GameInfo.createFromConfig(gameConfig);
            if (!game.isPresent())
                game = GameInfo.createNew(PREFERENCE_GAMES[i], HTML_GAMES[i]);

            list.add(game);
        }
        return list;
    }

    /**
     * получение игры по названию
     */
    public GameInfo findGame(String name) {
        for (GameInfo game : games) {
            if (game.getName().equals(name))
                return game;
        }
        return null;
    }

    /**
     * получение целого списка игр
     */
    public List<GameInfo> getGames() {
        return games;
    }

    /**
     * сохранение информации об играх
     */
    public void saveInfo() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < games.size(); i++) {
            editor.putString(PREFERENCE_GAMES[i], games.get(i).getConfig());
        }
        editor.apply();
    }
}
