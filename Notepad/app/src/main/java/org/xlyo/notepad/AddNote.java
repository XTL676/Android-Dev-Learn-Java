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
import org.xlyo.notepad.util.CommonUtil;
import org.xlyo.notepad.util.NoteDbHelper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class AddNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);
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
        findViewById(R.id.btn_save).setOnClickListener(v -> {
            MainActivity.noteItemAdapter.AddNote(
                    new NoteBean(title.getText().toString(),
                    content.getText().toString(),
                            CommonUtil.getTimeNow())
            );
            MainActivity.noteItemAdapter.notifyDataSetChanged();
            finish();
        });
    }
}