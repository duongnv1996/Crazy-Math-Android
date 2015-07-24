package com.example.administrator.crazymath;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 23/7/2015.
 */
public class FragmentStop extends Fragment {
    TextView tvScore,tvBestScore;
    int mScore,mBest=0;
    Button btnReplay,btnQuit;
    Intent intent;
    public void setmScore(int mScore) {
        this.mScore = mScore;
    }
    IGiaoTiepFragment iGiaoTiepFragment;
FragmentManager fragmentManager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_stop, container, false);
        final GameView gameView = (GameView) getActivity();
        mScore = gameView.getDiemSo();
        tvScore = (TextView) view.findViewById(R.id.tvMyScore);
        btnReplay = (Button) view.findViewById(R.id.btnReset);
        btnQuit= (Button) view.findViewById(R.id.btnExit);
        tvBestScore = (TextView) view.findViewById(R.id.tvBestScore);
        tvScore.setText("Score :" + mScore);
        tvBestScore.setText("Best :"+ gameView.getmBest());
        btnReplay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(GameView,MainActivity.class);
                gameView.finish();
            }
        });
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Out Game
                gameView.finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        return view;
    }


//
//    public void readData()
//    {
//        String data;
//        InputStream in= getResources().openRawResource(R.raw.best);
//        InputStreamReader inreader=new InputStreamReader(in);
//        BufferedReader bufreader=new BufferedReader(inreader);
//        StringBuilder builder=new StringBuilder();
//        if(in!=null)
//        {
//            try
//            {
//                while((data=bufreader.readLine())!=null)
//                {
//                    builder.append(data);
//                    builder.append("\n");
//                }
//                in.close();
//               mBest=Integer.parseInt(builder.toString());
//            }
//            catch(IOException ex){
//                Log.e("ERROR", ex.getMessage());
//            }
//        }
//    }

}

