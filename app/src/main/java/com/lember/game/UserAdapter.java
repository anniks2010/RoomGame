package com.lember.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lember.game.room.User;
import com.lember.game.room.UserDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ScoreViewHolder> {
    private Context context;
    private List<User> list;
    public  UserAdapter(Context context){this.context=context;}
    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_scores,parent,false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        holder.txtUserData.setText(list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }
        return list.size();
    }

    public void setTasks(List<User> scores){list=scores; notifyDataSetChanged();}
    public class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView txtUserData;
        UserDatabase userDatabase;
        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            userDatabase=UserDatabase.getInstance(context);
            txtUserData=itemView.findViewById(R.id.txtUserData);
        }
    }
}
