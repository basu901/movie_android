package com.first.android.moviedatabase;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
>>>>>>> upstream/master
import android.view.Menu;
import android.view.MenuItem;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if(savedInstanceState==null){
<<<<<<< HEAD
            getSupportFragmentManager().beginTransaction().add(R.id.fragment,new DetailActivityFragment()).commit();
=======
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_detail,new DetailActivityFragment()).commit();
>>>>>>> upstream/master
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
<<<<<<< HEAD
        if (id == R.id.action_settings){
            startActivity(new Intent(this,SettingsActivity.class));
=======
        if (id == R.id.action_settings) {
>>>>>>> upstream/master
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
