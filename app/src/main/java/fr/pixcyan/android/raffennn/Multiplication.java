package fr.pixcyan.android.raffennn;

/**
 * TODO
 *
 * @author PixCyan
 */
public class Multiplication extends Operation {

    public Multiplication(int a, int b) {
        super(a, b);
    }

    @Override
    public int calc() {
        return a * b;
    }

}
