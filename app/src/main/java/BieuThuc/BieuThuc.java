package BieuThuc;

import java.util.Random;

/**
 * Created by Administrator on 6/7/2015.
 */
public class BieuThuc {
    private int soHang[]= new int[3];
                          // 0   1   2
    private char [] toanTu={'+','-','x'};
    Random random= new Random();

    public void setSoHang() {

        for (int i = 0; i < soHang.length; i++) {
            //                           [0;9]
            soHang[i]=random.nextInt((9-0)+1)+0;
        }
    }

    public void setToanTu() {
        for (int i = 0; i <toanTu.length ; i++) {

        }
    }

    public int[] getSoHang() {
        return soHang;
    }
    public String str = "";
    public    String toString(){

        for (int i = 0; i <soHang.length ; i++) {
            if(i==soHang.length-1) {
                str+=soHang[i];
            }

            else
                str +=soHang[i] + " " + toanTu[random.nextInt((2 - 0) + 1)] + " ";
        }
        return(str);
    }

    public String getStr() {
        return str;
    }
public void resetBien(){
    str="";
}



}
