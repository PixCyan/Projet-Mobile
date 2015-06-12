package fr.pixcyan.android.raffennn;

/**
 * TODO
 *
 * @author PixCyan
 */
public class Addition extends Operation {

    public Addition(int a, int b) {
        super(a, b);
    }

    @Override
    public int calc() {
        return a + b;
    }

}
