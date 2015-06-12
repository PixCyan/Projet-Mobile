package fr.pixcyan.android.raffennn.data;

/**
 * TODO
 *
 * @author PixCyan
 */
public class Compte {

    private int id;
    private String login;
    private String mdp;
    private int score_mult;
    private int score_add;
    private int score_art;
    private int score_capitales;

    public  Compte() {

    }

    public Compte(int score_add, int score_mult, String mdp, String login, int score_art, int score_capitales) {
        this.score_add = score_add;
        this.score_mult = score_mult;
        this.mdp = mdp;
        this.login = login;
        this.score_art = score_art;
        this.score_capitales = score_capitales;
    }


    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public int getScore_mult() {
        return score_mult;
    }

    public int getScore_add() {
        return score_add;
    }

    public int getScore_art() {
        return score_art;
    }

    public int getScore_capitales() {
        return score_capitales;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setScore_mult(int score_mult) {
        this.score_mult = score_mult;
    }

    public void setScore_add(int score_add) {
        this.score_add = score_add;
    }

    public void setScore_art(int score_art) {
        this.score_art = score_art;
    }

    public void setScore_capitales(int score_capitales) {
        this.score_capitales = score_capitales;
    }



}
