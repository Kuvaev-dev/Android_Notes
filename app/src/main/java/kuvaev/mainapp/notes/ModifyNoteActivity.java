package kuvaev.mainapp.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText titleText, descText;

    private long _id;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify_note);

        databaseManager = new DatabaseManager(this);
        databaseManager.Open();

        titleText = findViewById(R.id.subject_edittext);
        descText = findViewById(R.id.description_edittext);
        Button updateBtn = findViewById(R.id.btn_update);
        Button deleteBtn = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);

        titleText.setText(name);
        descText.setText(desc);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_update) {
            String title = titleText.getText().toString();
            String desc = descText.getText().toString();
            databaseManager.Update(_id, title, desc);
            this.returnHome();
        } else if (view.getId() == R.id.btn_delete) {
            databaseManager.Delete(_id);
            this.returnHome();
        }
    }

    public void returnHome() {
        Intent intent = new Intent(getApplicationContext(), NotesActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}