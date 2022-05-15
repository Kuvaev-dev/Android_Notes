package kuvaev.mainapp.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class NotesActivity extends AppCompatActivity {
    final String[] from = new String[] {
            DatabaseHelper._ID,
            DatabaseHelper.SUBJECT,
            DatabaseHelper.DESC
    };

    final int[] to = new int[] {
            R.id.id,
            R.id.title,
            R.id.desc
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_empty_list);

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.Open();
        Cursor cursor = databaseManager.Fetch();

        ListView listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.activity_view_record,
                cursor, from, to);
        simpleCursorAdapter.notifyDataSetChanged();
        listView.setAdapter(simpleCursorAdapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            TextView tvId = view.findViewById(R.id.id);

            String id = tvId.getText().toString();
            String title = tvId.getText().toString();
            String desc = tvId.getText().toString();

            Intent intent = new Intent(getApplicationContext(), ModifyNoteActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("desc", desc);
            intent.putExtra("id", id);

            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent intent = new Intent(this, AddNoteActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}