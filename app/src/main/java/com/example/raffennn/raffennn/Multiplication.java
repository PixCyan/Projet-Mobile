package com.example.raffennn.raffennn;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.util.Random;


public class Multiplication extends ActionBarActivity {
    public static final String FINAL_SCORE = "0";
    public final static int MULT_REQUEST = 0;
    private static final Random random = new Random();
    private int nbCalc = 0;
    private int nb1;
    private int nb2;
    private Calculs c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        nb1 = random.nextInt(10);
        nb2 = random.nextInt(10);
        c = new Calculs(nb1, nb2);

        TextView view1 = (TextView)findViewById(R.id.chiffre1);
        view1.setText(nb1 + " x " + nb2 + " = ");




        /*
        //Cr�ation dynamique
        for(int i = 0; i < 3; i++) {
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
        Button boutonAide = new Button(this);
        boutonAide.setText("Quitter");
        boutonAide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                aide();
            }
        });
        Button boutonMenu = new Button(this);
        boutonMenu.setText("Quitter");
        boutonMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                quitter();
            }
        });
        layout.addView(boutonAide);
        layout.addView(boutonMenu);*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multiplication, menu);
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

    //TODO valider � revoir
    public void valider(View view) {
        TextView view2 = (TextView)findViewById(R.id.rep);
        int res = Integer.parseInt((String)view2.getText());
        int res2 = c.caclculMult();
        if(res == res2) {
            c.scrorePlus();
        }
        /*if(nbCalc != 3) {
            Intent intent = new Intent(this, Multiplication.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {*/
            Intent intent = new Intent(this, Score.class);
            int score = c.getScoreFinal();
            intent.putExtra(FINAL_SCORE, score);
            startActivityForResult(intent, MULT_REQUEST);
        //}
    }


    //TODO aide
   public void aide(View view) {

   }

    public void quitter(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
