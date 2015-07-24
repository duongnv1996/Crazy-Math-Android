package com.example.administrator.crazymath;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.*;
import java.lang.Thread;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import BieuThuc.BieuThuc;

import static com.example.administrator.crazymath.R.*;

/**
 * Created by Administrator on 5/7/2015.
 */
public class GameView extends Activity {

    private boolean isCheck = false;
    private int diemSo = 0,mBest=0;
    private String str;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    boolean isAlive = true;
    Button BUTTONT;
    RelativeLayout GView;
    Button BUTTONF;
    TextView TXTBIEUTHUC;
    TextView TXTKETQUA;
    TextView TXTDIEM, tvTime;
    MediaPlayer mediaPlayerF;
    MediaPlayer mediaPlayerT;
    TypedArray arrColor;
    Random rand;
    BieuThuc bieuThuc;
    KetQua ketQua;
    Button btnSound;
    FragmentStop fragmentStop;

    int mTime = 5;
    Thread mThread;
   ProgressBar bar,bar_2,bar_3;
    int max=250,min=0;
    boolean isRunning=false;
    int index=0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameview);
        khoiTao();
        setGiaTriBanDau();
        bar=(ProgressBar)findViewById(id.progressBar);
        bar_2=(ProgressBar)findViewById(id.progressBar2);
       // bar_3=(ProgressBar)findViewById(id.progressBar3);
        bar.setProgress(0);
        bar.setMax(max);
        bar_2.setMax(max);
        bar_2.setProgress(0);
       // bar.incrementProgressBy(10);
       mThread =getThread();
        mThread.start();
        BUTTONT.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View v){
                TXTBIEUTHUC.setText("");
                TXTKETQUA.setText("");
                int randNumber = rand.nextInt((11 - 0) + 1);
                GView.setBackgroundColor(arrColor.getColor(randNumber, 0));
                if (ketQua.getRandKQ() == ketQua.getKetQua()) {
                    tiepTucGame();
                } else {
                    dungGame();
                }

            }
            }

            );

            BUTTONF.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                int randNumber = rand.nextInt((11 - 0) + 1);
                GView.setBackgroundColor(arrColor.getColor(randNumber, 0));
                //Xoa TXT
                TXTBIEUTHUC.setText("");
                TXTKETQUA.setText("");
                if (ketQua.getKetQua() != ketQua.getRandKQ()) {
                    tiepTucGame();
                } else {
                    dungGame();

                }
            }
            }

            );
        }

    private Thread getThread() {

        return new Thread() {

            @Override


            public void run() {

                for (index = 0; index <= max; index++) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bar.incrementProgressBy(index);
                    bar_2.incrementProgressBy(index);
                    if (mThread.isInterrupted()) {
                        return;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (index == (max / 10) - 1 && isAlive) {
                                dungGame();
                            }
                        }
                    });

                }
            }

        };
    }

    public int getDiemSo() {
        return diemSo;
    }
    public int getmBest() {
        return mBest;
    }

    private void dungGame() {
        readData();
        if (getDiemSo()>mBest){
            writeData();
            mBest=getDiemSo();
        }
        mediaPlayerF.start();
        // mFragmentManager.beginTransaction().setTransitionStyle(anim.in);
        mFragmentManager.beginTransaction().setCustomAnimations(anim.in_gameover, anim.out_game)
                .replace(id.gameView, new FragmentStop())
                .commit();
        TXTBIEUTHUC.setVisibility(View.INVISIBLE);
        TXTKETQUA.setVisibility(View.INVISIBLE);
        TXTDIEM.setVisibility(View.INVISIBLE);
        BUTTONF.setEnabled(isCheck);
        BUTTONT.setEnabled(isCheck);
        isAlive=false;

    }
    public void writeData()
    {
        try {
            FileOutputStream out=
                    openFileOutput("best.txt",0);
            OutputStreamWriter writer=
                    new OutputStreamWriter(out);
            writer.write(""+diemSo);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tiepTucGame() {
        mediaPlayerT.start();
        bieuThuc.resetBien();
        ketQua.resetBien(0);
        //Random bieu thuc
        bieuThuc.setSoHang();
        TXTBIEUTHUC.setText(bieuThuc.toString());
        // random ket qua
        str = bieuThuc.getStr();
        ketQua.set(str);
        ketQua.xuLyStr();
        ketQua.randKetQua();
        TXTKETQUA.setText("=" + ketQua.getRandKQ() + "");
        //set diem so sau moi lan nhan
        diemSo++;
        TXTDIEM.setText("Score : " + diemSo);
        mThread.interrupt();
        bar.setProgress(0);
        bar_2.setProgress(0);
        index=0;
    }

    private void setGiaTriBanDau() {
        //Set bieu thuc ban dau
        bieuThuc = new BieuThuc();
        bieuThuc.setSoHang();
        TXTBIEUTHUC.setText(bieuThuc.toString());
        //Set ket qua ban dau

        ketQua = new KetQua();
        str = bieuThuc.getStr();
        ketQua.set(str);
        ketQua.xuLyStr();
        ketQua.randKetQua();
        TXTKETQUA.setText("=" + ketQua.getRandKQ());
        TXTDIEM.setText("Score : " + diemSo);
    }

    private void khoiTao() {
        BUTTONT = (Button) findViewById(id.buttonTrue);
        GView = (RelativeLayout) findViewById(id.gameView);
        BUTTONF = (Button) findViewById(id.buttonFalse);
        TXTBIEUTHUC = (TextView) findViewById(id.BieuThuc);
        TXTKETQUA = (TextView) findViewById(id.KetQua);
        TXTDIEM = (TextView) findViewById(id.DiemSo);
        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        arrColor = getResources().obtainTypedArray(R.array.rainbow);
        rand = new Random();
        //Tao am thanh nhan button
        mediaPlayerF = MediaPlayer.create(GameView.this, R.raw.false_sound);
        mediaPlayerT = MediaPlayer.create(GameView.this, raw.sound);
        fragmentStop = new FragmentStop();
        tvTime = (TextView) findViewById(id.tvTime);

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
            mBest=Integer.parseInt(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
