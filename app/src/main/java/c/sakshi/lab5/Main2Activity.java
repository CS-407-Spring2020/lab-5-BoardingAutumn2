package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //need to redefine it here so i can use it in this class, but its the same one
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String usernameKey = "username";
        //TODO i am right before step 4
        //check sharedPreferences for preexisting stored username, go to second activity if so
        if(!sharedPreferences.getString(usernameKey, "").equals("")) {
            String str = sharedPreferences.getString("username", "");
            textView2.setText("Welcome " + str + "!");
        }
        else {
            textView2 = (TextView) findViewById(R.id.welcome);
            Intent intent = getIntent();
            String str = intent.getStringExtra("name");
            textView2.setText("Welcome " + str + "!");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        switch (item.getItemId()) {
            case R.id.signout:
                //return to first activity
                Intent intent = new Intent(this, MainActivity.class);
                String usernameKey = "username";
                SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove(usernameKey).apply();
                startActivity(intent);
                return true;
            case R.id.addnote:
                //fill in later
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }
}
