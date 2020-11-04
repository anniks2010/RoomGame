package com.lember.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lember.game.R;
import com.lember.game.room.User;
import com.lember.game.room.UserDatabase;
import com.lember.game.room.appExcutors;

import java.util.List;

public class ScoreActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private UserDatabase userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter=new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);
        userDatabase= UserDatabase.getInstance(getApplicationContext());

    }

    @Override
    protected void onResume() {
        super.onResume();
        getTasks();
    }

    private void getTasks() {
        appExcutors.getsInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<User> scores =userDatabase.getUserDAO().getAllScores();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        userAdapter.setTasks(scores);
                    }
                });
            }
        });
    }
}