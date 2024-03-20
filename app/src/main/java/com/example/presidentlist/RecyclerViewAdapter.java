package com.example.presidentlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Item> presidentList;
    Context context;

    public RecyclerViewAdapter(List<Item> presidentList, Context context) {
        this.presidentList = presidentList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_president,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_itemName.setText(presidentList.get(position).getName());
        holder.tv_itemValue.setText(String.valueOf(presidentList.get(position).getValue()));
        Glide.with(this.context).load(presidentList.get(position).getImageURL()).into(holder.iv_itemPic);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send the control to the EditOneItem activity
                Intent intent = new Intent(context, AddEditOne.class);
                //Als "Extra" wird mit dem Namen "id" die ID mitgegeben, die dann in AddEdit One genommen werden kann
                intent.putExtra("id", presidentList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return presidentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_itemPic;
        TextView tv_itemName;
        TextView tv_itemValue;
        ConstraintLayout parentLayout;

        @SuppressLint("ResourceType")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_itemPic = itemView.findViewById(R.id.iv_itemPicture);
            tv_itemName = itemView.findViewById(R.id.tv_itemName);
            tv_itemValue = itemView.findViewById(R.id.tv_itemValue);
            parentLayout = itemView.findViewById(R.id.OneLinePresidentLayout);
        }
    }
}
