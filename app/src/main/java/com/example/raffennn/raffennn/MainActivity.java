package com.example.raffennn.raffennn;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    public final static int JEUX_REQUEST = 1;
    public final static int LOGIN_REQUEST = 2;
    public final static int SINSCRIRE_REQUEST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void jeux(View view) {
        // Création d'une intention
        Intent intent = new Intent(this, Jeux.class);

        // Lancement de la demande de changement d'activité
        startActivityForResult(intent, JEUX_REQUEST);
    }

    //TODO login()
    public void login(View view) {

    }

    //TODO sinscrire()
    public void sinscrire(View view) {

    }

}
