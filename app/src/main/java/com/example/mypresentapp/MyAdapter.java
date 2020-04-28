package com.example.mypresentapp;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private Context context;
    private List<DataHandeler> dataHandelerList;
    private OnItemclickListener listener;

    public MyAdapter(Context context, List<DataHandeler> dataHandelerList) {
        this.context = context;
        this.dataHandelerList = dataHandelerList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.userdata_list_samle,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        DataHandeler data=dataHandelerList.get(position);
        holder.firstnameTextview.setText(data.getFirstName().concat(data.getLastName()));
        holder.emailTextview.setText(data.getEmail());

    }

    @Override
    public int getItemCount() {
        return dataHandelerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener,View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView firstnameTextview;
        TextView emailTextview;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            firstnameTextview=itemView.findViewById(R.id.firstNameTextviewid);
            emailTextview=itemView.findViewById(R.id.emailTextviewid);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.setHeaderTitle("Chose an action");
            MenuItem showfirstName=menu.add(Menu.NONE,1,1,"Show First Name");
            MenuItem showlastName=menu.add(Menu.NONE,2,2,"Show Last Name");
            MenuItem showEmail=menu.add(Menu.NONE,3,3,"Show Email");
            MenuItem showMobile=menu.add(Menu.NONE,4,4,"Show Mobile");

            showfirstName.setOnMenuItemClickListener(this);
            showlastName.setOnMenuItemClickListener(this);
            showEmail.setOnMenuItemClickListener(this);
            showMobile.setOnMenuItemClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            if(listener!=null){
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 1:
                            listener.showFirstName(position);
                                 return true  ;
                        case 2:

                            listener.showLastName(position);
                                    return  true;
                       case 3:
                            listener.showEmail(position);
                                 return true  ;
                        case 4:

                            listener.showMobile(position);
                                    return  true;
                    }
                }
            }


            return false;
        }
    }
    public interface  OnItemclickListener{
        void onItemClick(int position);


        void showFirstName(int position);
        void showLastName(int position);
        void showEmail(int position);
        void showMobile(int position);


    }
    public void setOnitemclickListener(OnItemclickListener listener){
        this.listener=listener;

    }


}
