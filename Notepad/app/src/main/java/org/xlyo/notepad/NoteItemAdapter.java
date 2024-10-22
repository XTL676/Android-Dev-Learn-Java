package org.xlyo.notepad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.xlyo.notepad.bean.NoteBean;

import java.time.Instant;
import java.util.List;

public class NoteItemAdapter extends BaseAdapter {
    private final List<NoteBean> noteList;

    public NoteItemAdapter() {
        this.noteList = MainActivity.noteDbHelper.getAllNotes();
    }

    public void AddNote(NoteBean note) {
        noteList.add(note);
        int index = noteList.lastIndexOf(note);
        MainActivity.noteDbHelper.addNote(
                Integer.toString(index),
                note.getTitle(),
                note.getContent(),
                note.getTime()
        );
    }

    public void DelNote(NoteBean note) {
        int index = noteList.lastIndexOf(note);
        noteList.remove(note);
        MainActivity.noteDbHelper.deleteNote(Integer.toString(index));
        if (noteList.isEmpty()) MainActivity.noteDbHelper.deleteAllNotes();
    }

    public void UpdateNote(String id, NoteBean note) {
        MainActivity.noteDbHelper.updateNote(
                id,
                note.getTitle(),
                note.getContent(),
                note.getTime());
        NoteBean bean = noteList.get(Integer.parseInt(id));
        bean.setTitle(note.getTitle());
        bean.setContent(note.getContent());
        bean.setTime(note.getTime());
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return noteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_note_item, null);
        NoteBean note = (NoteBean) getItem(i);
        ((TextView) view.findViewById(R.id.note_title)).setText(note.getTitle());
        ((TextView) view.findViewById(R.id.note_desc)).setText(note.getContent());
        ((TextView) view.findViewById(R.id.note_date)).setText(note.getTime());
        return view;
    }
}
