package com.example.administrator.crazymath;

import android.content.Intent;
import android.widget.Toast;

import com.example.administrator.crazymath.GameView;

import java.util.Random;

import BieuThuc.BieuThuc;

/**
 * Created by Administrator on 7/7/2015.
 */
public class KetQua {
    BieuThuc bieuThuc= new BieuThuc();
    private int ketQua=0;
    // Lay string tu bieu thuc gan vao str de xu ly
    private String str;

    public void set(String str) {
        this.str =str;

    }

    public String getStr() {
        return str;
    }

    public void xulyToanTu(char toanTu,char so1,char so2){

        switch (toanTu){
            case '+':{
               this.ketQua+=Integer.parseInt(String.valueOf(so1))+Integer.parseInt(String.valueOf(so2));

                break;
            }
            case '-':{
            this.ketQua+=Integer.parseInt(String.valueOf(so1))-Integer.parseInt(String.valueOf(so2));

                break;
            }
            case 'x':{
              this.ketQua+=(Integer.parseInt(String.valueOf(so2))* Integer.parseInt(String.valueOf(so1)));

                break;
            }
        }
    }
    public void xuLyStr() {
        //Duyet tu dau mang den vi tri cac toan tu

        if ((str.charAt(6) == 'x')) {
            //TH : .... x
            xulyToanTu(str.charAt(6), str.charAt(4), str.charAt(8));
            str=str.replace(str.charAt(4),'0');
            //Truong hop ngoai le - x
            if(str.charAt(2)=='-'){
                ketQua= Integer.parseInt(String.valueOf(str.charAt(0))) - ketQua;
            }
            else if (str.charAt(2)=='x'){
                // TH x x
                ketQua*=Integer.parseInt(String.valueOf(str.charAt(0)));
            }
            else {
                //Truong hop + x
                xulyToanTu(str.charAt(2), str.charAt(0), str.charAt(4));

            }
        }
        else {
            // TH : x ... // + + // -- // + -
            //Xet bieu thuc dau tien
            xulyToanTu(str.charAt(2), str.charAt(0), str.charAt(4));
            //Xet bthuc thu 2 : tong vs sohang[3]
            xulyToanTu(str.charAt(6),'0',str.charAt(8));
     }

    }



    public int getKetQua() {
        return ketQua;
    }
    Random random= new Random();
    private int randKQ;
    public int randKetQua(){            //[0;2]
         randKQ= ketQua - random.nextInt((1-(-1))+1)+(-1);
        return  randKQ;
    }
    public boolean kiemTraTrungSo(){
        if(randKQ==ketQua){
            return true;
        }
        return  false;
    }
    public void resetBien(int a){
        this.ketQua=a;
    }
}
