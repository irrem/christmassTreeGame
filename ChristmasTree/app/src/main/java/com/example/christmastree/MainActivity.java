package com.example.christmastree;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreTxt,timeTxt;
    int score=0;
    ImageView img0,img1,img2,img3,img4,img5,img6,img7,img8,img9,img10;
    ImageView[] imgArr;
    Handler handler;
    Runnable runnable;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTxt=(TextView)findViewById(R.id.timeText);
        scoreTxt=(TextView)findViewById(R.id.scoreText);
        img0=findViewById(R.id.imageView0);
        img1=findViewById(R.id.imageView00);
        img2=findViewById(R.id.imageView01);
        img3=findViewById(R.id.imageView1);
        img4=findViewById(R.id.imageView02);
        img5=findViewById(R.id.imageView03);
        img6=findViewById(R.id.imageView3);
        img7=findViewById(R.id.imageView2);
        img8=findViewById(R.id.imageView5);
        img9=findViewById(R.id.imageView6);
        img10=findViewById(R.id.imageView7);

        imgArr=new ImageView[] {img0,img1,img2,img3,img4,img5,img6,img7,img8,img9,img10};
        hideImgs();

        score=0;
        new CountDownTimer(60000,1000){

            @Override
            public void onTick(long l) {
                timeTxt.setText("Time "+l/1000);
            }

            @Override
            public void onFinish() {
                for(ImageView i:imgArr){
                    i.setVisibility(View.VISIBLE);
                }
                handler.removeCallbacks(runnable);

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("GAME OVER");
                alert.setMessage("Do yo want restart the game?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"QUIT",Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();


            }
        }.start();
    }
    public void hideImgs(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView i:imgArr){
                    i.setVisibility(View.INVISIBLE);
                }

                Random r=new Random();
                int i=r.nextInt(10);
                imgArr[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,400);
            }
        };

        handler.post(runnable);
    }
    public void increaseScore(View view){
        score++;
        scoreTxt.setText("\t    "+score);
    }



}