package fr.pixcyan.android.raffennn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import fr.pixcyan.android.raffennn.data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionArt extends ActionBarActivity {
    public static final String COMPTE = "compte";
    private static final String SCORE_FINAL = "score";
    private static final int QUESTION_REQUEST = 0;
    private static final Random rand = new Random();
    private String login;
    private int score = 0;
    private int count = 0;

    private DAOCompte daoCompte;
    private Compte compte;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_art);
        Button suivant = (Button) findViewById(R.id.suivant);
        suivant.setVisibility(Button.INVISIBLE);
        login = getIntent().getStringExtra(Login.COMPTE);
        this.daoCompte = new DAOCompte(this);

        final String login = getIntent().getStringExtra(Login.COMPTE);
        if (login != null) {
            this.daoCompte.open();
            this.compte = this.daoCompte.getCompte(login);
            this.daoCompte.close();
        } else {
            this.compte = null;
        }

        miseAJour();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_question_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void miseAJour() {

        ((RadioGroup)findViewById(R.id.rg)).clearCheck();

        List<RadioButton> buttons = new ArrayList<>(3);
        buttons.add((RadioButton)findViewById(R.id.rep1));
        buttons.add((RadioButton)findViewById(R.id.rep2));
        buttons.add((RadioButton) findViewById(R.id.rep3));

        DAOQuestion questionDAO = new DAOQuestion(this);
        questionDAO.open();
        question = questionDAO.getQuestionRandom();
        questionDAO.close();

        TextView questionView = (TextView)findViewById(R.id.question);
        questionView.setText(question.getQuestion());
        for (int i = 0; i < 3; i++) {
            buttons.remove(rand.nextInt(buttons.size())).setText(question.getReponse(i));
        }
    }


    public void changerQuestion(View view) {
        Button suivant = (Button) findViewById(R.id.suivant);
        suivant.setVisibility(Button.INVISIBLE);
        TextView textValidation = (TextView) findViewById(R.id.resultat);
        textValidation.setText("");
        miseAJour();
    }

    public void valider(View view) {
        count++;
        TextView textValidation = (TextView) findViewById(R.id.resultat);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg);
        String checkedButtonValue = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        if(question.getBonneReponse().equals(checkedButtonValue)) {
            textValidation.setText("Bravo !");
            score++;
        } else {
            textValidation.setText("Perdu !");
        }
        if(count == 10) {
            if(compte != null) {
                this.daoCompte.open();
                this.compte.setScore_art(score);
                this.daoCompte.update(this.compte);
                this.daoCompte.close();
            }
            count = 0;
            Intent intent = new Intent(this, Score.class);
            intent.putExtra(SCORE_FINAL, score);
            intent.putExtra(COMPTE, login);
            startActivityForResult(intent, QUESTION_REQUEST);
        } else {
            Button suivant = (Button) findViewById(R.id.suivant);
            suivant.setVisibility(Button.VISIBLE);
        }
    }

    public void aide(View view) {
        final Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Cochez la réponse juste.").setTitle("Aide");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void retourMenu(View view) {
        Intent intent = new Intent(this, Jeux.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(COMPTE, login);
        startActivity(intent);
    }

    public void quitter(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
