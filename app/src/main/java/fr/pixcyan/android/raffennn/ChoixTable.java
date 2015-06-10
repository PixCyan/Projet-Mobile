package fr.pixcyan.android.raffennn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;


public class ChoixTable extends ActionBarActivity {
    public static final String table = "1";
    public final static int MULT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_table);
        NumberPicker np;
        np = (NumberPicker) findViewById(R.id.numPicker);
        np.setMinValue(0);
        np.setMaxValue(10);
        np.setWrapSelectorWheel(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choix_table, menu);
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

    //TODO valider()
    public void valider(View view) {
        NumberPicker np = (NumberPicker) findViewById(R.id.numPicker);
        String value = Integer.toString(np.getValue());
        //Integer.toString(value);
        Intent intent = new Intent(this, Multiplication.class);
        intent.putExtra(table, value);
        startActivityForResult(intent, MULT_REQUEST);
    }

}
