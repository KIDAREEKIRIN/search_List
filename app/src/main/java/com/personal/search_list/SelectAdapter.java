package com.personal.search_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.SelectViewHolder> {

    private final List<String> selectedDutyNames;
    private Context context;

    public SelectAdapter(List<String> selectedDutyNames, Context context) {
        this.selectedDutyNames = selectedDutyNames;
        this.context = context;
    }

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new SelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectViewHolder holder, int position) {
        holder.tv_selectedDutyName.setText(selectedDutyNames.get(position));
        holder.cv_selectedDutyName.setTag(selectedDutyNames.get(position));

    }

    @Override
    public int getItemCount() {
        return selectedDutyNames.size();
    }

    public class SelectViewHolder extends RecyclerView.ViewHolder{

        TextView tv_selectedDutyName;
        CardView cv_selectedDutyName;

        public SelectViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_selectedDutyName = itemView.findViewById(R.id.tv_dutyName);
            cv_selectedDutyName = itemView.findViewById(R.id.cv_dutyName);
        }
    }
}
