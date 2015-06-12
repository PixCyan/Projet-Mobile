package fr.pixcyan.android.raffennn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.pixcyan.android.raffennn.data.Compte;
import fr.pixcyan.android.raffennn.data.DAOCompte;

import java.util.Random;


public class Addition extends ActionBarActivity {
    public static final String COMPTE = "compte";
    public static String FINAL_SCORE = "score";
    public final static int ADD_REQUEST = 0;
    private static final Random random = new Random();
    private static int nbCalc = 0;
    private String login;
    private int nb1;
    private int nb2;
    private Calculs c;

    private DAOCompte daoCompte;
    private Compte compte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
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
        if(view2.getText().toString().matches("")) {
            Toast.makeText(this, "Vous n'avez pas rempli le champ", Toast.LENGTH_SHORT).show();
        } else {
            if (c.compareRes(c.calculAdd(), Integer.parseInt(view2.getText().toString()))) {
                c.scorePlus();
                Toast.makeText(this, "Bravo", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect : " + c.calculAdd(), Toast.LENGTH_SHORT).show();
            }
            nbCalc++;
            if (nbCalc != 10) {
                miseAjour();
            } else {
                if(compte != null) {
                    this.daoCompte.open();
                    this.compte.setScore_add(c.getScoreFinal());
                    this.daoCompte.update(this.compte);
                    this.daoCompte.close();
                }
                nbCalc = 0;
                Intent intent = new Intent(this, Score.class);
                intent.putExtra(FINAL_SCORE, c.getScoreFinal());
                intent.putExtra(COMPTE, login);
                c.setScoreFinal(0);
                startActivityForResult(intent, ADD_REQUEST);
            }
        }
    }

    public void aide(View view) {
        final Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Donnez le résultat de l'addition.").setTitle("Aide");
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
