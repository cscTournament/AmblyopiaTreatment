package com.education.amblyopiatreatment.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.education.amblyopiatreatment.GameInfoManager;
import com.education.cs.binocularvision.R;

/**
 * Класс-activity с меню для выбора игр
 *
 * @authors Danila Gourianov, Tatiana Gourianova, Ivan Korolev, Andrei Murashko, Kostia Tserpitski, Vladimir Polotniuk
 * @version 2023
 */
public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCE_FILE_KEY = "com.amblyopiatreatment.way_2020";

    private Button buttonGame1;
    private Button buttonGame2;
    private Button buttonGame3;
    private Button buttonGame4;
    private Button buttonGame5;
    private Button buttonGame6;
    private Button buttonStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        GameInfoManager.setSharedPreferences(preferences);
        GameInfoManager.getInstance();

        defineComponents();
        defineButtonsFunctionality();
    }

    /**
     * установка функциональности графических компонентов
     */
    private void defineButtonsFunctionality() {
        buttonGame1.setOnClickListener(view -> startGame(GameInfoManager.getGamesNames()[0]));
        buttonGame2.setOnClickListener(view -> startGame(GameInfoManager.getGamesNames()[1]));
        buttonGame3.setOnClickListener(view -> startGame(GameInfoManager.getGamesNames()[2]));
        buttonGame4.setOnClickListener(view -> startGame(GameInfoManager.getGamesNames()[3]));
        buttonGame5.setOnClickListener(view -> startGame(GameInfoManager.getGamesNames()[4]));
        buttonGame6.setOnClickListener(view -> startGame(GameInfoManager.getGamesNames()[5]));

        buttonStats.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
            MainActivity.this.startActivity(intent);
            overridePendingTransition(R.anim.stats_open, R.anim.menu_anim);
        });
    }

    /**
     * запустить activity с игрой
     */
    private void startGame(String gameName) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("gameName", gameName);
        MainActivity.this.startActivity(intent);
        overridePendingTransition(R.anim.no_animation, R.anim.no_animation);
    }

    /**
     * привязка и настройка графических компонентов
     */
    private void defineComponents() {
        ConstraintLayout layout = findViewById(R.id.mainLayout);
        buttonGame1 = layout.findViewById(R.id.buttonGame1);
        buttonGame2 = layout.findViewById(R.id.buttonGame2);
        buttonGame3 = layout.findViewById(R.id.buttonGame3);
        buttonGame4 = layout.findViewById(R.id.buttonGame4);
        buttonGame5 = layout.findViewById(R.id.buttonGame5);
        buttonGame6 = layout.findViewById(R.id.buttonGame6);
        buttonStats = layout.findViewById(R.id.buttonStats);
    }
}
