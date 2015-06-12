package fr.pixcyan.android.raffennn;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import fr.pixcyan.android.raffennn.data.Capitale;
import fr.pixcyan.android.raffennn.data.DAOCapitale;
import fr.pixcyan.android.raffennn.data.DAOQuestion;
import fr.pixcyan.android.raffennn.data.Question;


public class PaysCapitale extends ActionBarActivity {
    private static final String SCORE_FINAL = "score";
    private static final int CAPITALE_REQUEST = 0;
    private String repJuste;
    private int count = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pays_capitale);
        miseAJour();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pays_capitale, menu);
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
        TextView paysView = (TextView) findViewById(R.id.pays);
        DAOCapitale capitaleDAO = new DAOCapitale(this);
        capitaleDAO.open();
        Capitale cap = capitaleDAO.getPaysRandom();
        paysView.setText(cap.getPays());
        repJuste = cap.getCapitale();
        capitaleDAO.close();
    }

    public void valider(View view) {
        count++;
        EditText cap = (EditText) findViewById(R.id.capitale);
        if(cap.getText().toString().equals(repJuste)) {
            Toast.makeText(this, "Bravo", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, "Perdu", Toast.LENGTH_SHORT).show();
        }
        if(count == 10) {
            count = 0;
            Intent intent = new Intent(this, Score.class);
            intent.putExtra(SCORE_FINAL, score);
            startActivityForResult(intent, CAPITALE_REQUEST);
        } else {
            Button suivant = (Button) findViewById(R.id.suivant);
            suivant.setVisibility(Button.VISIBLE);
        }
    }

    public void changerQuestion(View view) {
        Button suivant = (Button) findViewById(R.id.suivant);
        suivant.setVisibility(Button.INVISIBLE);
        miseAJour();
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
