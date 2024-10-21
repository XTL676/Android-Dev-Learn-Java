package org.xlyo.notepad.bean;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class NoteBean implements Serializable {
    private static final long serialVersionUID = 6211836506847293811L;
    private String title;
    private String content;
    private String time;

    public NoteBean() {
    }

    public NoteBean(String title, String content, String time) {
        this.title = title;
        this.content = content;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteBean noteBean = (NoteBean) o;
        return Objects.equals(title, noteBean.title) &&
                Objects.equals(content, noteBean.content) &&
                Objects.equals(time, noteBean.time);
    }

    @NonNull
    @Override
    public String toString() {
        return "NoteBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, time);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
