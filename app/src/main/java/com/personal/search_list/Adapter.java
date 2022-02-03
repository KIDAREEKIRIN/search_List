package com.personal.search_list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private ArrayList<DutyName> dutyNames;
    private Context context;

    public Adapter(ArrayList<DutyName> dutyNames, Context context) {
        this.dutyNames = dutyNames;
        this.context = context;
    }

    public void setDutyNames(ArrayList<DutyName> dutyNames){
        this.dutyNames = new ArrayList<>();
        this.dutyNames = dutyNames;
        notifyDataSetChanged();
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
        holder.bind(dutyNames.get(position));
//        holder.tv_dutyName.setText(dutyNames.get(position).getDuty_name());
//        holder.cv_dutyName.setTag(dutyNames.get(position));
    }

    @Override
    public int getItemCount() {
        return dutyNames.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_dutyName;
        CardView cv_dutyName;
        ImageView iv_check;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_dutyName = itemView.findViewById(R.id.tv_dutyName);
            cv_dutyName = itemView.findViewById(R.id.cv_dutyName);
            iv_check = itemView.findViewById(R.id.iv_check);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //인터페이스 클릭리스너
//                    int currentPos = getAbsoluteAdapterPosition();
//                    if(currentPos != RecyclerView.NO_POSITION) {
//                        if (mListener != null) {
//                            dutyNames.sret(currentPos, dutyNames.get(currentPos));
//                            notifyItemChanged(currentPos);
//                            mListener.onItemClick(v, currentPos);
//                        }
//                    }
//                }
//            });

        }

        // 새로운 bind ViewHolder
        void bind(final DutyName dutyName) {
            iv_check.setVisibility(dutyName.isChecked() ? View.VISIBLE : View.GONE);
            tv_dutyName.setText(dutyName.getDuty_name());

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
                    dutyName.setChecked(!dutyName.isChecked());
                    iv_check.setVisibility(dutyName.isChecked() ?View.VISIBLE : View.GONE);
                }
            });
        }
    }

    //Getting All Items Selected (전체선택)
    public ArrayList<DutyName> getAll() {return dutyNames; }

    // Getting selected when btn clicked

    public ArrayList<DutyName> getSelected() {
        ArrayList<DutyName> selected = new ArrayList<>();
        for (int i = 0; i < dutyNames.size(); i++){
            if (dutyNames.get(i).isChecked()) {
                selected.add(dutyNames.get(i));
            }
        }
        return selected;
    }

    // 전체 취소.
    public ArrayList<DutyName> getAllCancel() {
        ArrayList<DutyName> canceled = new ArrayList<>();
        getSelected().clear();
        return canceled;
    }

}
