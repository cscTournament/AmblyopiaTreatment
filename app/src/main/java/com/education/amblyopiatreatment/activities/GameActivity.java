package com.education.amblyopiatreatment.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import com.education.amblyopiatreatment.GameInfo;
import com.education.amblyopiatreatment.GameInfoManager;
import com.education.cs.binocularvision.R;
import com.education.amblyopiatreatment.datetime.DateTimeValue;
import com.education.amblyopiatreatment.datetime.TimeSpan;

/**
 * Класс-activity для запуска игры html во встроенном браузере
 *
 * @author Катя Левкович и Егор Силин
 * @version 1.0, 30.12.2022
 */
public class GameActivity extends AppCompatActivity {
    private DateTimeValue dateTimeLastStart;
    private GameInfo game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String gameName = getIntent().getStringExtra("gameName");
        game = GameInfoManager.getInstance().findGame(gameName);

        defineComponents();
    }

    /**
     * метод, вызываемый во время приостановки activity,
     * чтобы записать время, проведённое в ней пользователем
     */
    @Override
    public void onPause() {
        TimeSpan timePassed = dateTimeLastStart.getTimeBefore(DateTimeValue.timeNow());
        game.getTimeSpan().add(timePassed);
        GameInfoManager.getInstance().saveInfo();

        super.onPause();
    }

    /**
     * метод, вызываемый при возобновлении activity,
     * чтобы установить, от какого времени нужно будет отталкиваться
     */
    @Override
    public void onResume() {
        super.onResume();

        dateTimeLastStart = DateTimeValue.timeNow();
    }

    /**
     * привязка и настройка графических компонентов
     */
    private void defineComponents() {
        WebView myWeb = findViewById(R.id.webView);

        myWeb.getSettings().setJavaScriptEnabled(true);
        myWeb.getSettings().setAllowFileAccessFromFileURLs(true);
        myWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        myWeb.loadUrl(game.getUrl());
    }
}