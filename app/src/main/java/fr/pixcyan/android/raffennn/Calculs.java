package fr.pixcyan.android.raffennn;


/**
 * TODO
 *
 * @author PixCyan
 */
public class Calculs {

    private int nb1;
    private int nb2;
    private static int scoreFinal = 0;

    public Calculs(int nb1, int nb2) {
        this.nb1 = nb1;
        this.nb2 = nb2;
    }


    public int caclculMult() {
        return nb1 * nb2;
    }

    public int calculAdd() {
        return nb1 + nb2;
    }

    public void scorePlus() {
        scoreFinal++;
    }

    public int getScoreFinal() {
        return scoreFinal;
    }

    public boolean compareRes(int a, int b) {
        return a == b;
    }

}
