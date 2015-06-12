package fr.pixcyan.android.raffennn;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import fr.pixcyan.android.raffennn.data.Compte;
import fr.pixcyan.android.raffennn.data.DAOCompte;


public class MesScores extends ActionBarActivity {
    public static final String COMPTE = "compte";
    private String login;
    private DAOCompte daoCompte;
    private Compte compte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_scores);
        login = getIntent().getStringExtra(Login.COMPTE);
        if (login != null) {
            this.daoCompte.open();
            this.compte = this.daoCompte.getCompte(login);
            this.daoCompte.close();
        } else {
            this.compte = null;
        }

        //Création dynamique
        for(int i = 0; i < 4; i++) {
            TextView table = new TextView(this);
            table.setText(nb1 + " x " + nb2 + " = ");
            table.setWidth(50);
            table.setHeight(50);

            EditText rep = new EditText(this);
            rep.setText(" ? ");
            rep.setLayoutParams(params);
            rep.setTextSize(10);
            layout.addView(table);
            layout.addView(rep);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mes_scores, menu);
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
}
