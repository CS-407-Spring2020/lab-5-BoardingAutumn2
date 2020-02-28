package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    public void clickFunction (View view){
        //1: get username and password via EditText view
        EditText username = (EditText) findViewById(R.id.username);
        String str = username.getText().toString();

        //2: Add username to SharedPreferences Object
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str);

        //3: start second activity
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("name", str);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        //check sharedPreferences for preexisting stored username, go to second activity if so
        if(!sharedPreferences.getString(usernameKey, "").equals("")) {
            String str = sharedPreferences.getString("username", "");

            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("name", str);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }
}
