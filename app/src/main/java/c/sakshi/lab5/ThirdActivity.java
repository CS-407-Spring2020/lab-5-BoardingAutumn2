package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ThirdActivity extends AppCompatActivity {

    int noteid = -1;

    public void clickFunction (View view) {
        //1
        EditText listView = (EditText) findViewById(R.id.editText3);
        String content = listView.getText().toString();
        //2
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase =
                context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        //3
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        //4
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String usernameKey = "username";
        String username = sharedPreferences.getString("username", "");
        //5
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (Main2Activity.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNotes(title, date, content, username);
        }
        //6
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //1
        EditText listView = (EditText) findViewById(R.id.editText3);
        //2
        Intent intent = getIntent();
        //3 and 4
        noteid = intent.getIntExtra("noteid", noteid);

        if (noteid != -1){
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
            listView.setText(noteContent);
        }

    }
}
