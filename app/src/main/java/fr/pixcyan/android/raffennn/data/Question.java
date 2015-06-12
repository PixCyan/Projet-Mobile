package fr.pixcyan.android.raffennn.data;

/**
 * TODO
 *
 * @author PixCyan
 */
public class Question {

    private int id;
    private String question;
    private String[] reponses;

    public Question() {
        this.reponses = new String[3];
    }

    public Question(String question, String bonneReponse, String mauvaiseReponse1, String mauvaiseReponse2) {
        this.setQuestion(question);
        this.reponses = new String[]{
                bonneReponse,
                mauvaiseReponse1,
                mauvaiseReponse2
        };
    }

    public boolean estBonneReponse(String reponse) {
        return getBonneReponse().equals(reponse);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public String getBonneReponse() {
        return reponses[0];
    }

    public String getReponse(int i) {
        if (i < 0 || i >= reponses.length) {
            throw new IllegalArgumentException("Invalid response index: " + i);
        }
        return reponses[i];
    }


    public void setQuestion(String question) {
        this.question = question;
    }

    public void setReponse(int i, String value) {
        if (i < 0 || i >= reponses.length) {
            throw new IllegalArgumentException("Invalid response index: " + i);
        }
        reponses[i] = value;
    }
}
