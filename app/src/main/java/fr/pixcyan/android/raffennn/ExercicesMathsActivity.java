package fr.pixcyan.android.raffennn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ExercicesMathsActivity extends ActionBarActivity {
    public final static int CHOIXTABLE_REQUEST = 1;
    public final static int ALEA_REQUEST = 2;
    public final static int ADDITION_REQUEST = 3;
    public static final String COMPTE = "compte";
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices_maths);
        login = getIntent().getStringExtra(LoginActivity.COMPTE);
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
        Intent intent = new Intent(this, AdditionActivity.class);
        intent.putExtra(COMPTE, login);
        startActivityForResult(intent, ADDITION_REQUEST);
    }

    public void multiplications(View view) {
        Intent intent = new Intent(this, MultiplicationActivity.class);
        intent.putExtra(COMPTE, login);
        startActivityForResult(intent, CHOIXTABLE_REQUEST);
    }

    //TODO aleatoire()
    public void aleatoire(View view) {
        // Création d'une intention
        Intent intent = new Intent(this, AleatoireActivity.class);
        intent.putExtra(COMPTE, login);
        startActivityForResult(intent, ALEA_REQUEST);
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
