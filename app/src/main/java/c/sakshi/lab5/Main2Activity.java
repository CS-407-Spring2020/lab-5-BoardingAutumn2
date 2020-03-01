package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    TextView textView2;

    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //1
        //need to redefine it here so i can use it in this class, but its the same one
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String usernameKey = "username";

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

        //2
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase =
                context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        //TODO 3


        //4
        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note : notes) {
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        //5
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.notesListView);
        listView.setAdapter(adapter);

        //6
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });

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
                Intent intent1 = new Intent(this, ThirdActivity.class);
                startActivity(intent1);
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }
}
