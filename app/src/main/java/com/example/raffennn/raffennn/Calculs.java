package com.example.raffennn.raffennn;

import java.util.Random;

/**
 * TODO
 *
 * @author PixCyan
 */
public class Calculs {

    private int nb1;
    private int nb2;
    private int scoreFinal = 0;
    private int res;


    public Calculs(int nb1, int nb2) {
        this.nb1 = nb1;
        this.nb2 = nb2;
    }


    public int caclculMult() {
        return res = nb1*nb2;
    }

    public  int calculAdd() {
        return res = nb1+nb2;
    }

    public void scrorePlus() {
        scoreFinal++;
    }

    public int getScoreFinal() {
        return scoreFinal;
    }

}
