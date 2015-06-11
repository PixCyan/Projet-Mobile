package fr.pixcyan.android.raffennn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ExercicesMaths extends ActionBarActivity {
    public final static int CHOIXTABLE_REQUEST = 1;
    public final static int ALEA_REQUEST = 2;
    public final static int ADDITION_REQUEST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices_maths);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercices_maths, menu);
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

    public void additions(View view) {
        // Création d'une intention
        Intent intent = new Intent(this, Addition.class);
        // Lancement de la demande de changement d'activité
        startActivityForResult(intent, ADDITION_REQUEST);
    }

    public void multiplications(View view) {
        // Création d'une intention
        //Intent intent = new Intent(this, ChoixTable.class);
        Intent intent = new Intent(this, Multiplication.class);
        // Lancement de la demande de changement d'activité
        startActivityForResult(intent, CHOIXTABLE_REQUEST);
    }

    //TODO aleatoire()
    public void aleatoire(View view) {
        // Création d'une intention
        Intent intent = new Intent(this, Aleatoire.class);
        // Lancement de la demande de changement d'activité
        startActivityForResult(intent, ALEA_REQUEST);
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
