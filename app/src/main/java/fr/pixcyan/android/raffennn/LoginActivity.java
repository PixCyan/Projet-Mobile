package fr.pixcyan.android.raffennn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import fr.pixcyan.android.raffennn.data.Compte;
import fr.pixcyan.android.raffennn.data.DAOCompte;


public class LoginActivity extends ActionBarActivity {

    public static final String COMPTE = "compte";
    public final static int JEUX_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void valider(View view) {
        EditText login = (EditText) findViewById(R.id.login);
        EditText mdp = (EditText) findViewById(R.id.mdp);
        DAOCompte compteDAO = new DAOCompte(this);
        if(login.getText().toString().matches("")|| mdp.getText().toString().matches("")) {
            Toast.makeText(this, "Un ou plusieurs chmaps sont vides", Toast.LENGTH_SHORT).show();
        } else {
            compteDAO.open();
            if (compteDAO.compteExiste(login.getText().toString())) {
                final Compte compte = compteDAO.getCompte(login.getText().toString());
                compteDAO.close();
                if (compte.getMdp().equals(mdp.getText().toString())) {
                    Intent intent = new Intent(this, JeuxActivity.class);
                    intent.putExtra(COMPTE, compte.getLogin());
                    startActivityForResult(intent, JEUX_REQUEST);
                } else {
                    Toast.makeText(this, "Mot de passe invalide", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Le compte n'existe pas", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void quitter(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
