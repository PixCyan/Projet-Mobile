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


public class Sinscrire extends ActionBarActivity {
    private static final String LOGIN = "login";
    private static final String MDP = "mot_de_passe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinscrire);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sinscrire, menu);
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
        if(login.getText().toString().matches("")|| mdp.getText().toString().matches("")) {
            Toast.makeText(this, "Un ou plusieurs chmaps sont vides", Toast.LENGTH_SHORT).show();
        } else {
            DAOCompte compteDAO = new DAOCompte(this);
            compteDAO.open();
            //compteDAO.selectCompte(login.getText().toString(), mdp.getText().toString()).isEmpty()
            if(compteDAO.compteExist(login.getText().toString())) {
                Compte compte = new Compte(login.getText().toString(), mdp.getText().toString(), 0, 0, 0, 0);
                compteDAO.insert(compte);
                compteDAO.close();
                //TODO message confirmation
                Toast.makeText(this, "Le compte a été créé", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Le compte existe déjà", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void quitter(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
