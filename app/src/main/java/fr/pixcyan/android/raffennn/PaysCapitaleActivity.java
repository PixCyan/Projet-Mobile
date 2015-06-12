package fr.pixcyan.android.raffennn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import fr.pixcyan.android.raffennn.data.*;


public class PaysCapitaleActivity extends ActionBarActivity {
    public static final String COMPTE = "compte";
    private static final String SCORE_FINAL = "score";
    private static final int CAPITALE_REQUEST = 0;
    private String login;
    private int count = 0;
    private int score = 0;

    private DAOCompte daoCompte;
    private Compte compte;
    private Capitale capitale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pays_capitale);
        Button suivant = (Button) findViewById(R.id.suivant);
        suivant.setVisibility(Button.INVISIBLE);
        login = getIntent().getStringExtra(LoginActivity.COMPTE);
        this.daoCompte = new DAOCompte(this);

        final String login = getIntent().getStringExtra(LoginActivity.COMPTE);
        if (login != null) {
            this.daoCompte.open();
            this.compte = this.daoCompte.getCompte(login);
            this.daoCompte.close();
        } else {
            this.compte = null;
        }
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
        EditText capitale = (EditText) findViewById(R.id.capitale);
        capitale.setText("");
        TextView paysView = (TextView) findViewById(R.id.pays);
        DAOCapitale capitaleDAO = new DAOCapitale(this);
        capitaleDAO.open();
        this.capitale = capitaleDAO.getPaysRandom();
        paysView.setText(this.capitale.getPays());
        capitaleDAO.close();
    }

    public void valider(View view) {
        count++;
        EditText cap = (EditText) findViewById(R.id.capitale);
        if(capitale.estCapitale(cap.getText().toString())) {
            Toast.makeText(this, "Bravo", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, "Perdu : " + capitale.getCapitale(), Toast.LENGTH_SHORT).show();
        }
        if(count == 10) {
            if(compte != null) {
                daoCompte.open();
                compte.setScore_capitales(Math.max(score, compte.getScore_capitales()));
                daoCompte.update(this.compte);
                daoCompte.close();
            }
            count = 0;
            Intent intent = new Intent(this, ScoreActivity.class);
            Bundle extras = new Bundle();
            extras.putInt(SCORE_FINAL,score);
            extras.putString(COMPTE,login);
            intent.putExtras(extras);
            startActivityForResult(intent, CAPITALE_REQUEST);
        } else {
            Button suivant = (Button) findViewById(R.id.suivant);
            suivant.setVisibility(Button.VISIBLE);
        }
    }

    public void aide(View view) {
        final Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Donnez la capitale qui correspond au pays donné.").setTitle("Aide");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void changerQuestion(View view) {
        Button suivant = (Button) findViewById(R.id.suivant);
        suivant.setVisibility(Button.INVISIBLE);
        miseAJour();
    }

    public void retourMenu(View view) {
        Intent intent = new Intent(this, JeuxActivity.class);
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
