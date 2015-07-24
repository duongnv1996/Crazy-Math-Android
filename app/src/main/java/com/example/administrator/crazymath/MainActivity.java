package com.example.administrator.crazymath;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends Activity {




    File file;
    TextView tvBestScore;
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView IMGLOGO = (ImageView) findViewById(R.id.imgView);
        final Button BTNENTER = (Button) findViewById(R.id.buttonEnter);
        tvBestScore = (TextView)findViewById(R.id.tvBest);
        readData();

        BTNENTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tao doi tuong intent      //From              //to
                Intent intent = new Intent(MainActivity.this,GameView.class);
                //Goi Activity
                startActivity(intent);

                overridePendingTransition(R.anim.in,R.anim.out);
            }
        });


    }



    public void readData()
    {
        try {
            FileInputStream in= openFileInput("best.txt");
            BufferedReader reader=new
                    BufferedReader(new InputStreamReader(in));
            String data="";
            StringBuilder builder=new StringBuilder();
            while((data=reader.readLine())!=null)
            {
                builder.append(data);
            }
            in.close();
            tvBestScore.setText("Best Score: "+builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

