package org.xlyo.notepad;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xlyo.notepad.bean.NoteBean;
import org.xlyo.notepad.util.NoteDbHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static NoteDbHelper noteDbHelper;
    public static NoteItemAdapter noteItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        noteDbHelper = new NoteDbHelper(getApplicationContext());
//        noteDbHelper.deleteAllNotes();
        findViewById(R.id.btn_add).setOnClickListener(v ->
                startActivity(new Intent(this, AddNote.class)));
        noteItemAdapter = new NoteItemAdapter();
        ListView noteList = (ListView) findViewById(R.id.note_list);
        noteList.setAdapter(noteItemAdapter);
        noteList.setOnItemClickListener((adapterView, view, i, l) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("note", (NoteBean) noteItemAdapter.getItem(i));
            bundle.putInt("id", i);
            Intent intent = new Intent(this, ModifyNote.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        noteList.setOnItemLongClickListener((adapterView, view, i, l) -> {
            // 创建一个AlertDialog.Builder对象
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("是否删除此记录？")
                    .setPositiveButton("删除", (dialog, which) -> {
                        // 执行删除操作
                        MainActivity.noteItemAdapter.DelNote((NoteBean) MainActivity.noteItemAdapter.getItem(i)); // 移除指定位置的元素
                        ((NoteItemAdapter) noteList.getAdapter()).notifyDataSetChanged(); // 通知适配器数据已更改
                    })
                    .setNegativeButton("取消", (dialog, which) -> {
                        // 用户选择取消，不做任何操作
                        dialog.dismiss();
                    });

            // 创建并显示对话框
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            // 返回true表示长按事件已经被处理
            return true;
        });
    }
}