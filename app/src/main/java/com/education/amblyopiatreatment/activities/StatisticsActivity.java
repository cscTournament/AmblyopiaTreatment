package com.education.amblyopiatreatment.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.education.cs.binocularvision.R;
import com.education.amblyopiatreatment.StatsAdapter;

/**
 * класс-activity, отображающая статистику по играм
 *
 * @author Катя Левкович и Егор Силин
 * @version 1.0, 31.12.2022
 */
public class StatisticsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        defineComponents();
    }

    /**
     * метод при нажатии кнопки "назад" для изменения анимации возвращения
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.menu_anim, R.anim.stats_close);
    }

    /**
     * привязка и настройка графических компонентов
     */
    private void defineComponents() {
        RecyclerView gameStatsRecycler = findViewById(R.id.statsRecycler);
        GridLayoutManager gridLayout = new GridLayoutManager(this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        gameStatsRecycler.setLayoutManager(gridLayout);

        gameStatsRecycler.setAdapter(new StatsAdapter(this));
    }
}