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
        TextView time = (TextView) findViewById(R.id.et_time2);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            title.setText("");
            content.setText("");
            time.setText("");
        });
        Bundle bundle = getIntent().getExtras();

        findViewById(R.id.btn_mod_save).setOnClickListener(v -> {
            MainActivity.noteItemAdapter.UpdateNote(
                    String.valueOf(bundle.getInt("id")),
                    new NoteBean(
                            title.getText().toString(),
                            content.getText().toString(),
                            CommonUtil.convertTime(time.getText().toString()))
            );
            MainActivity.noteItemAdapter.notifyDataSetChanged();
            finish();
        });

        if (bundle != null && bundle.containsKey("note")) {
            NoteBean note = (NoteBean) bundle.getSerializable("note");
            ((TextView) findViewById(R.id.et_title)).setText(note.getTitle());
            ((TextView) findViewById(R.id.etm_content)).setText(note.getContent());
            ((TextView) findViewById(R.id.mod_note_time)).setText(note.getTime());
            ((TextView) findViewById(R.id.et_time2)).setText(note.getTime()
                            .replace("年","/")
                            .replace("月","/").replace("日",""));
        }
    }
}