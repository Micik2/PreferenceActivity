package com.example.maciek.prefact;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView weight0;
    private TextView weight1;
    private TextView weight2;
    private float weight01 = (float) 55.6;;
    private float weight02 = (float) 85.12;
    private float weight03 = (float) 70.0;
    private SharedPreferences sharedPreferences;
    private String unit;
    private String latestUnit;
    private float converter;
    private float weight001;
    private float weight002;
    private float weight003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getApplicationContext().getSharedPreferences("Jednostka", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.clear();
        //editor.commit();
        unit = sharedPreferences.getString("Jednostka", "2");
        latestUnit = sharedPreferences.getString("Jedn", "kg");
        weight001 = sharedPreferences.getFloat("Waga1", (float) 55.6);
        weight002 = sharedPreferences.getFloat("Waga2", (float) 85.12);
        weight003 = sharedPreferences.getFloat("Waga3", (float) 70.0);
        converter = (float) 2.20;

        Log.d("Unit po onCreate()", unit);
        Log.d("Wcześniejszy unit", latestUnit);

        if (unit.equals("0") && latestUnit.equals("lb")) {
            weight001 = weight001 / converter;
            weight002 = weight002 / converter;
            weight003 = weight003 / converter;

            editor.putString("Jedn", "kg");
            editor.putFloat("Waga1", weight001);
            editor.putFloat("Waga2", weight002);
            editor.putFloat("Waga3", weight003);
            editor.commit();
        }
        else if (unit.equals("1") && latestUnit.equals("kg")) {
            weight001 = weight001 * converter;
            weight002 = weight002 * converter;
            weight003 = weight003 * converter;

            editor.putString("Jedn", "lb");
            editor.putFloat("Waga1", weight001);
            editor.putFloat("Waga2", weight002);
            editor.putFloat("Waga3", weight003);
            editor.commit();
        }
        else if (unit.equals("2")) {
            weight001 = weight01;
            weight002 = weight02;
            weight003 = weight03;
            editor.putFloat("Waga1", weight001);
            editor.putFloat("Waga2", weight002);
            editor.putFloat("Waga3", weight003);
            editor.commit();
        }

        weight0 = (TextView) findViewById(R.id.weight0);
        weight1 = (TextView) findViewById(R.id.weight1);
        weight2 = (TextView) findViewById(R.id.weight2);

        weight0.setText(String.valueOf(weight001));
        weight1.setText(String.valueOf(weight002));
        weight2.setText(String.valueOf(weight003));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.preferences: {
                startActivity(new Intent(this, PrefActivity.class));
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


}
