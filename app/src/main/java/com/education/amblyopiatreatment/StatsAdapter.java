package com.education.amblyopiatreatment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.education.cs.binocularvision.R;

/**
 * абстрактный класс для класса-адаптера отображения
 * множества иконок
 *
 * @author Катя Левкович
 * @version 1.2, 29.10.2022
 */
public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder> {
    private Context context;

    /**
     * @param ct контекст Activity
     */
    public StatsAdapter(Context ct) {
        this.context = ct.getApplicationContext();
    }

    /**
     * создание нового элемента в списке компонентов
     */
    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stats_line, viewGroup, false);
        return new StatsViewHolder(view);
    }

    /**
     * привязка компонента списка к определённой позиции и её данным
     * @param holder компонент
     * @param i индекс
     */
    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int i) {
        int index = holder.getAdapterPosition();
        GameInfo game = GameInfoManager.getInstance().getGames().get(index);

        holder.textGameName.setText(game.getName() + ":");
        holder.textGameTime.setText(game.getTimeSpan().toString());
    }

    /**
     * @return количество элементов
     */
    @Override
    public int getItemCount() {
        return GameInfoManager.getInstance().getGames().size();
    }

    /**
     * класс компонента в списке компонентов,
     * отображает статистику об игре
     */
    public static class StatsViewHolder extends RecyclerView.ViewHolder {
        private TextView textGameName;
        private TextView textGameTime;

        /**
         * создание элемента для списка компонентов
         * @param itemView графический компонент
         */
        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);
            textGameName = itemView.findViewById(R.id.gameName);
            textGameTime = itemView.findViewById(R.id.gameTime);
        }
    }
}
