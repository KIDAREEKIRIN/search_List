package com.personal.search_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<DutyName> dutyNames;
    private Context context;

    public Adapter(List<DutyName> dutyNames, Context context) {
        this.dutyNames = dutyNames;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_dutyName.setText(dutyNames.get(position).getDuty_name());
        holder.cv_dutyName.setTag(dutyNames.get(position));
    }

    @Override
    public int getItemCount() {
        return dutyNames.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_dutyName;
        CardView cv_dutyName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_dutyName = itemView.findViewById(R.id.tv_dutyName);
            cv_dutyName = itemView.findViewById(R.id.cv_dutyName);

        }
    }
}
