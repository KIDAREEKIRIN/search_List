package com.personal.search_list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Callback;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private final List<DutyName> dutyNames;
    private Context context;

    public Adapter(List<DutyName> dutyNames, Context context) {
        this.dutyNames = dutyNames;
        this.context = context;
    }

    // InterFace
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
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


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_dutyName;
        CardView cv_dutyName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_dutyName = itemView.findViewById(R.id.tv_dutyName);
            cv_dutyName = itemView.findViewById(R.id.cv_dutyName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //인터페이스 클릭리스너
                    int currentPos = getAbsoluteAdapterPosition();
                    if(currentPos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            dutyNames.set(currentPos, dutyNames.get(currentPos));
                            notifyItemChanged(currentPos);
                            mListener.onItemClick(v, currentPos);
                        }
                    }
                }
            });

        }
    }
}
