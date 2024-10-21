package org.xlyo.notepad;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xlyo.notepad.bean.NoteBean;

import java.time.Instant;

public class ModifyNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modify_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView title = (TextView) findViewById(R.id.et_title);
        TextView content = (TextView) findViewById(R.id.etm_content);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            title.setText("");
            content.setText("");
        });
        Bundle bundle = getIntent().getExtras();

        findViewById(R.id.btn_mod_save).setOnClickListener(v -> {
            MainActivity.noteDbHelper.updateNote(
                    String.valueOf(bundle.getInt("id")),
                    title.getText().toString(),
                    content.getText().toString(),
                    Instant.now().toString());
            ((ListView) findViewById(R.id.note_list)).deferNotifyDataSetChanged();
        });

        if (bundle != null && bundle.containsKey("note")) {
            NoteBean note = (NoteBean) bundle.getSerializable("note");
            ((TextView) findViewById(R.id.et_title)).setText(note.getTitle());
            ((TextView) findViewById(R.id.etm_content)).setText(note.getContent());
            ((TextView) findViewById(R.id.mod_note_time)).setText(note.getTime());
        }
    }
}