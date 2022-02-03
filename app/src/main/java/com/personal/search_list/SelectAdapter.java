package com.personal.search_list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.SelectViewHolder> {

    private final ArrayList<DutyName> selectedDutyNames;
    private Context context;

    Adapter adapter;
//    private ArrayList<DutyName> getSelectedDutyNames = adapter.getSelected();



    //로그
    private static String TAG ="선택업무";

    public SelectAdapter(ArrayList<DutyName> selectedDutyNames, Context context) {
        this.selectedDutyNames = selectedDutyNames;
        this.context = context;
    }

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new SelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectViewHolder holder, int position) {
        holder.tv_selectedDuty.setText(selectedDutyNames.get(position).getDuty_name());
        holder.cv_selectedDuty.setTag(selectedDutyNames.get(position));
        holder.ib_selectCancel.setTag(selectedDutyNames.get(position));
        Log.d(TAG, "onBindViewHolder: " + selectedDutyNames.get(position));

    }

    @Override
    public int getItemCount() {
        return selectedDutyNames.size();
    }

    public class SelectViewHolder extends RecyclerView.ViewHolder{

//        TextView tv_selectedDutyName;
//        CardView cv_selectedDutyName;

        TextView tv_selectedDuty;
        CardView cv_selectedDuty;
        ImageButton ib_selectCancel;

        public SelectViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_selectedDuty = itemView.findViewById(R.id.tv_selectedDuty);
            cv_selectedDuty = itemView.findViewById(R.id.cv_selectedDuty);
            ib_selectCancel  = itemView.findViewById(R.id.ib_selectCancel);

            ib_selectCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedDutyNames.remove(selectedDutyNames.get(getAbsoluteAdapterPosition()));
                    notifyDataSetChanged();
                    Log.d(TAG, "onClick: ib_selectCancel" + getAbsoluteAdapterPosition());
//                    Log.d(TAG, "다음에는 " + selectedDutyNames.get(getAbsoluteAdapterPosition()));

                    Log.d("선택항목 리스트", selectedDutyNames.toString().trim());
                }
            });
        }
    }
}
