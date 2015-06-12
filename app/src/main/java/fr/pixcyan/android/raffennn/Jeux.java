package fr.pixcyan.android.raffennn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import fr.pixcyan.android.raffennn.data.Compte;
import fr.pixcyan.android.raffennn.data.DAOCompte;
import fr.pixcyan.android.raffennn.data.DBHelper;


public class Jeux extends ActionBarActivity {
    public final static int JEUX_REQUEST = 0;
    public final static int EXERCICES_REQUEST = 1;
    public final static int CULTURE_REQUEST = 2;
    public static final String COMPTE = "compte";
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeux);
        login = getIntent().getStringExtra(Login.COMPTE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jeux, menu);
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

    public void exoMaths(View view) {
        // Création d'une intention
        Intent intent = new Intent(this, ExercicesMaths.class);
        intent.putExtra(COMPTE, login);
        // Lancement de la demande de changement d'activité
        startActivityForResult(intent, EXERCICES_REQUEST);
    }

    public void exoCulture(View view) {
        // Création d'une intention
        Intent intent = new Intent(this, ExosCulture.class);
        intent.putExtra(COMPTE, login);
        // Lancement de la demande de changement d'activité
        startActivityForResult(intent, CULTURE_REQUEST);
    }

    public void mesScores(View view) {
        Intent intent = new Intent(this, MesScores.class);
        intent.putExtra(COMPTE, login);
        startActivityForResult(intent, CULTURE_REQUEST);
    }

    public void quitter(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
