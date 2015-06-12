package fr.pixcyan.android.raffennn.data;

import android.content.Context;

/**
 * TODO
 *
 * @author PixCyan
 */
public class Capitale {

    private String pays;
    private String capitale;

    public Capitale() {

    }

    public Capitale(String pays, String capitale) {
        this.pays = pays;
        this.capitale = capitale;
    }

    public boolean estCapitale(String capitale) {
        return this.capitale.equals(capitale);
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setCapitale(String capitale) {
        this.capitale = capitale;
    }

    public String getPays() {
        return pays;
    }

    public String getCapitale() {
        return capitale;
    }

}
