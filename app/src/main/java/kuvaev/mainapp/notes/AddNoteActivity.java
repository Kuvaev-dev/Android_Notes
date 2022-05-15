package kuvaev.mainapp.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText subjectEditText;
    private EditText descEditText;

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add_note);

        subjectEditText = findViewById(R.id.subject_edittext);
        descEditText = findViewById(R.id.description_edittext);
        Button addToDoBtn = findViewById(R.id.add_record);

        databaseManager = new DatabaseManager(this);
        databaseManager.Open();
        addToDoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_record) {
            final String name = subjectEditText.getText().toString();
            final String desc = descEditText.getText().toString();
            databaseManager.Insert(name, desc);
            Intent intent = new Intent(AddNoteActivity.this, NotesActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}