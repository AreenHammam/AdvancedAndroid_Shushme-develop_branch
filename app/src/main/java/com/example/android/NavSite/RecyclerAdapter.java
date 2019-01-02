//package com.example.android.shushme;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by hamma on 02/05/2018.
// */
//
//class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnLongClickListener{
//    public TextView txt_description;
//    private ItemClickListener itemClickListener;
//
//
//    public RecyclerViewHolder(View itemView) {
//        super(itemView);
//        txt_description = (TextView)itemView.findViewById(R.id.txtDescription);
//        itemView.setOnClickListener(this);
//        itemView.setOnLongClickListener(this);
//    }
//
//    public void setItemClickListener (ItemClickListener itemClickListener){
//        this.itemClickListener = itemClickListener;
//    }
//
//    @Override
//    public boolean onLongClick(View v) {
//        itemClickListener.onclick(v,getAdapterPosition(),true);
//        return true;
//    }
//
//    @Override
//    public void onClick(View v) {
//        itemClickListener.onclick(v,getAdapterPosition(),false);
//
//
//    }
//}
//
//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
//
//    private List<String> listData = new ArrayList<String>();
//    private Context context;
//
//    public RecyclerAdapter (List<String> listData, Context context){
//        this.listData = listData;
//        this.context = context;
//    }
//
//
//
//    @Override
//    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View itemView = inflater.inflate(R.layout.layout_item_recycler_view,parent,false);
//        return  new RecyclerViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
//        holder.txt_description.setText(listData.get(position));
//
//        holder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onclick(View v, int position, boolean isLongClick) {
//                if(isLongClick)
//                    Toast.makeText(context,"Long Click: "+listData.get(position),Toast.LENGTH_SHORT);
//                else
//                    Toast.makeText(context,"Short Click: "+listData.get(position),Toast.LENGTH_SHORT);
//
//            }
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return listData.size();
//    }
//}
