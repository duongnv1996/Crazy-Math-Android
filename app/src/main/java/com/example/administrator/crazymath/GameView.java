package com.example.administrator.crazymath;

import android.app.Activity;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.crazymath.MainActivity;
import com.example.administrator.crazymath.R;
import com.example.administrator.crazymath.MainActivity;

import java.util.Random;

import BieuThuc.BieuThuc;

import static com.example.administrator.crazymath.R.*;

/**
 * Created by Administrator on 5/7/2015.
 */
public class GameView extends Activity{



    private String str;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ket noi voi xml giao dien
        setContentView(R.layout.gameview);
        final Button BUTTONT = (Button) findViewById(R.id.buttonTrue);
        final RelativeLayout GView= (RelativeLayout) findViewById(R.id.gameView);
        final Button BUTTONF = (Button) findViewById(R.id.buttonFalse);
        final TextView TXTBIEUTHUC= (TextView)findViewById(id.BieuThuc);
        final TextView TXTKETQUA = (TextView)findViewById(id.KetQua);

        //Tao thay doi mau ne khi nhan button
        final TypedArray arrColor;
        arrColor = getResources().obtainTypedArray(R.array.rainbow);
        final Random rand = new Random();
        //Tao am thanh nhan button
        final MediaPlayer mediaPlayerF;
        mediaPlayerF = MediaPlayer.create(GameView.this,R.raw.false_sound);
        final MediaPlayer mediaPlayerT;
        mediaPlayerT = MediaPlayer.create(GameView.this, raw.sound);
        //Set bieu thuc ban dau
        final BieuThuc bieuThuc= new BieuThuc();
        bieuThuc.setSoHang();
        TXTBIEUTHUC.setText(bieuThuc.toString());
        //Set ket qua ban dau
        final KetQua ketQua;
        ketQua= new KetQua();
        str =bieuThuc.getStr();
        ketQua.set(str);
        ketQua.xuLyStr();
        TXTKETQUA.setText("=" + ketQua.randKetQua() + "");
        bieuThuc.resetBien();
        ketQua.resetBien(0);
        BUTTONT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TXTBIEUTHUC.setText("");
                TXTKETQUA.setText("");
                mediaPlayerT.start();
                int randNumber = rand.nextInt((11 - 0) + 1);
                GView.setBackgroundColor(arrColor.getColor(randNumber, 0));

                //Random bieu thuc
                bieuThuc.setSoHang();
                TXTBIEUTHUC.setText(bieuThuc.toString());
                //ket qua
                str =bieuThuc.getStr();
                ketQua.set(str);
                ketQua.xuLyStr();
                TXTKETQUA.setText("=" + ketQua.randKetQua()+"");
                bieuThuc.resetBien();
                ketQua.resetBien(0);

            }
        });

        BUTTONF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randNumber = rand.nextInt((11-0)+1);
                    GView.setBackgroundColor(arrColor.getColor(randNumber,0));
                    mediaPlayerF.start();

            }
        });

    }

}
