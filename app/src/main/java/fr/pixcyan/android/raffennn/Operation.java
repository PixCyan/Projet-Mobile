package fr.pixcyan.android.raffennn;


/**
 * TODO
 *
 * @author PixCyan
 */
public abstract class Operation {

    protected int a;
    protected int b;

    public Operation(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public boolean estJuste(int res) {
        return res == calc();
    }

    public abstract int calc();

}
