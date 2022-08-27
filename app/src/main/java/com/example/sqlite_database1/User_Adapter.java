package com.example.sqlite_database1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class User_Adapter extends RecyclerView.Adapter<User_Adapter.viewHolder> {

    List<User> users;

    public User_Adapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_design_user,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        User user = users.get(position);

        holder.idTextView.setText(String.valueOf(user.getId()));
        holder.nameTextView.setText(user.getName());
        holder.ageTextView.setText(user.getAge());


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView idTextView,nameTextView,ageTextView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTV);
            nameTextView = itemView.findViewById(R.id.nameTV);
            ageTextView = itemView.findViewById(R.id.ageTV);

        }
    }
}
