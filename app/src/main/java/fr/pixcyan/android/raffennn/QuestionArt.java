package fr.pixcyan.android.raffennn;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class QuestionArt extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_question_art);
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

        /*
        TextView questionView = (TextView)findViewById(R.id.question);
        RadioButton bonneReponseView = (RadioButton)findViewById(R.id.bonne_reponse);
        RadioButton mauvaiseReponse1 = (RadioButton)findViewById(R.id.mauvaise_reponse_1);
        RadioButton mauvaiseReponse2 = (RadioButton)findViewById(R.id.mauvaise_reponse_2);

        //
        DAOQuestion questionDAO = new DAOQuestion(this);
        questionDAO.open();

        //
        //Question question = DAO-question.selectAll().get(1);
        Question question = questionDAO.getQuestionRandom();
        questionView.setText(question.getQuestion());
        bonneReponseView.setText(question.getBonneReponse());
        mauvaiseReponse1.setText(question.getMauvaiseReponse1());
        mauvaiseReponse2.setText(question.getMauvaiseReponse2());

        //
        questionDAO.close();
*/
    }


    public void changerQuestion(View view) {
        miseAJour();
    }
}
