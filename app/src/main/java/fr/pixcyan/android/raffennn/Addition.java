package fr.pixcyan.android.raffennn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Addition extends ActionBarActivity {
    public static String FINAL_SCORE = "score";
    public final static int ADD_REQUEST = 0;
    private static final Random random = new Random();
    private static int nbCalc = 0;
    private int nb1;
    private int nb2;
    private Calculs c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        miseAjour();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addition, menu);
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

    private void miseAjour() {
        nb1 = random.nextInt(100);
        nb2 = random.nextInt(100);
        c = new Calculs(nb1, nb2);
        TextView view1 = (TextView) findViewById(R.id.chiffre1);
        view1.setText(nb1 + " + " + nb2 + " = ");
        EditText editText = (EditText) findViewById(R.id.rep);
        editText.setText("");
        TextView view2 = (TextView) findViewById(R.id.test1);
        view2.setText("");
    }

    public void valider(View view) {
        TextView view2 = (TextView) findViewById(R.id.rep);
        TextView v = (TextView) findViewById(R.id.test1);
        if(view2.getText().toString().matches("")) {
            Toast.makeText(this, "Vous n'avez pas rempli le champ", Toast.LENGTH_SHORT).show();
        } else {
            if (c.compareRes(c.calculAdd(), Integer.parseInt(view2.getText().toString()))) {
                c.scorePlus();
                v.setText("Correct !");
            } else {
                v.setText("Incorrect !");
            }
            nbCalc++;
            if (nbCalc != 10) {
                miseAjour();
            } else {
                nbCalc = 0;
                Intent intent = new Intent(this, Score.class);
                intent.putExtra(FINAL_SCORE, c.getScoreFinal());
                c.setScoreFinal(0);
                startActivityForResult(intent, ADD_REQUEST);
            }
        }
    }

    //TODO aide add
    public void aide(View view) {

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
