package fr.pixcyan.android.raffennn;

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

public class QuestionArt extends ActionBarActivity {
    private static final String SCORE_FINAL = "score";
    private static final int QUESTION_REQUEST = 0;
    private int score = 0;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_art);
        Button suivant = (Button) findViewById(R.id.suivant);
        suivant.setVisibility(suivant.INVISIBLE);
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

        TextView questionView = (TextView)findViewById(R.id.question);
        RadioButton bonneReponseView = (RadioButton)findViewById(R.id.bonne_reponse);
        RadioButton mauvaiseReponse1 = (RadioButton)findViewById(R.id.mauvaise_reponse_1);
        RadioButton mauvaiseReponse2 = (RadioButton)findViewById(R.id.mauvaise_reponse_2);

        DAOQuestion questionDAO = new DAOQuestion(this);
        Question qTest = new Question("Quelle oeuvre a peint Jacques-Louis David ?", "Le serment du jeu de paume", "Les trois Grâces", "Le radeau de la Méduse");
        questionDAO.open();
        questionDAO.insert(qTest);

        //Question question = questionDAO.selectAll().get(1);
        Question question = questionDAO.getQuestionRandom();
        questionView.setText(question.getQuestion());
        bonneReponseView.setText(question.getBonneReponse());
        mauvaiseReponse1.setText(question.getMauvaiseReponse1());
        mauvaiseReponse2.setText(question.getMauvaiseReponse2());

        questionDAO.close();
    }


    public void changerQuestion(View view) {
        Button suivant = (Button) findViewById(R.id.suivant);
        suivant.setVisibility(suivant.INVISIBLE);
        TextView textValidation = (TextView) findViewById(R.id.resultat);
        textValidation.setText("");
        miseAJour();
    }

    public void valider(View view) {
        count++;
        TextView textValidation = (TextView) findViewById(R.id.resultat);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rb);
        if(radioGroup.getCheckedRadioButtonId() == R.id.bonne_reponse) {
            textValidation.setText("Bravo !");
            score++;
        } else {
            textValidation.setText("Perdu !");
        }
        if(count == 10) {
            count = 0;
            Intent intent = new Intent(this, Score.class);
            intent.putExtra(SCORE_FINAL, score);
            startActivityForResult(intent, QUESTION_REQUEST);
        } else {
            Button suivant = (Button) findViewById(R.id.suivant);
            suivant.setVisibility(suivant.VISIBLE);
        }
    }

    public void retourMenu(View view) {
        Intent intent = new Intent(this, Jeux.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void quitter(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
