package com.lember.game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.lember.game.room.User;
import com.lember.game.room.UserDatabase;
import com.lember.game.room.appExcutors;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView humanView, computerView;
    private TextView score, humanName;
    String humChoice, compChoice, result, name;
    int humScore, compScore=0;
    Random random;
    ConstraintLayout constraintLayout;
    UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout=findViewById(R.id.mainView);
        score=findViewById(R.id.txtScore);
        humanName=findViewById(R.id.txtUserName);
        humanView=findViewById(R.id.imgHuman);
        computerView=findViewById(R.id.imgComputer);
        random=new Random();
        userDatabase=UserDatabase.getInstance(getApplicationContext());

        //region Building AlterDialog
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final EditText userInput=new EditText(this);
        userInput.requestFocus();
        userInput.setHint(getResources().getString(R.string.name));
        userInput.setSingleLine();
        userInput.setGravity(Gravity.CENTER_HORIZONTAL);
        userInput.setBackgroundResource(android.R.color.transparent);
        linearLayout.addView(userInput);
        alert.setTitle(getResources().getString(R.string.title)).setMessage(getResources().getString(R.string.rules)).setView(linearLayout).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                name= userInput.getText().toString().trim();
                humanName.setText(name);
            }
        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAndRemoveTask();

            }
        }).show();
        ///endregion
    }

    public void onRock(View view) {
        humChoice="rock";
        calculateWin();
        humanView.setImageResource(R.drawable.rock);
        score.setText(String.format("%s%s",getResources().getString(R.string.msg,humScore,compScore)));
    }

    public void onPaper(View view) {
        humChoice="paper";
        calculateWin();
        humanView.setImageResource(R.drawable.paper);
        score.setText(String.format("%s%s",getResources().getString(R.string.msg,humScore,compScore)));
    }

    public void onScissors(View view) {
        humChoice="scissors";
        calculateWin();
        humanView.setImageResource(R.drawable.scissors);
        score.setText(String.format("%s%s",getResources().getString(R.string.msg,humScore,compScore)));
    }

    private void calculateWin() {
        int computer = random.nextInt(3);
        if (computer==0){
            compChoice="rock";
            computerView.setImageResource(R.drawable.rock);
        }else if (computer==1){
            compChoice="paper";
            computerView.setImageResource(R.drawable.paper);
        }else if(computer==2){
            compChoice="scissors";
            computerView.setImageResource(R.drawable.scissors);
        }
        //checking what comp and human chose

        if(humChoice.equals("rock")&& compChoice.equals("paper")){
            result=getResources().getString(R.string.loose);
            compScore++;
        }else if (humChoice.equals("rock")&& compChoice.equals("scissors")){
            result=getResources().getString(R.string.win);
            humScore++;
        }else if (humChoice.equals("paper")&& compChoice.equals("rock")){
            result=getResources().getString(R.string.win);
            humScore++;
        }else if (humChoice.equals("paper")&& compChoice.equals("scissors")){
            result=getResources().getString(R.string.loose);
            compScore++;
        }else if (humChoice.equals("scissors")&& compChoice.equals("rock")){
            result=getResources().getString(R.string.loose);
            compScore++;
        }else if (humChoice.equals("scissors")&& compChoice.equals("paper")){
            result=getResources().getString(R.string.win);
            humScore++;
        }else result=getResources().getString(R.string.tie);
        Snackbar.make(constraintLayout,result,Snackbar.LENGTH_LONG).show();
    }

    public void onGameEnd(View view) {
        final User user=new User(name, humScore);
        appExcutors.getsInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                userDatabase.getUserDAO().insertScores(user);
                finish();
            }
        });
        startActivity(new Intent(getApplicationContext(), ScoreActivity.class));
    }
}