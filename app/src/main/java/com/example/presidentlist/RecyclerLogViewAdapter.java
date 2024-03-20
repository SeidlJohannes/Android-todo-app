package com.example.presidentlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerLogViewAdapter extends RecyclerView.Adapter<RecyclerLogViewAdapter.MyViewHolder> {

    List<ItemLog> itemLogList;
    Context context;

    public RecyclerLogViewAdapter(List<ItemLog> itemLogList, Context context) {
        this.itemLogList = itemLogList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_log,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_changedName.setText(itemLogList.get(position).getChangedName());
        holder.tv_changedValue.setText(itemLogList.get(position).getChangedValue());
        holder.tv_changedURL.setText(itemLogList.get(position).getChangedURL());

    }

    @Override
    public int getItemCount() {
        return itemLogList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_changedName;
        TextView tv_changedValue;
        TextView tv_changedURL;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_changedName = itemView.findViewById(R.id.tv_changedName);
            tv_changedValue = itemView.findViewById(R.id.tv_changedValue);
            tv_changedURL = itemView.findViewById(R.id.tv_changedURL);
        }
    }
}
