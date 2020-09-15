package com.stark.jarvis;

public class Note  {
    int id;
    String title;
    String NoteContent;

    public Note(int id,String title, String noteContent) {
        this.id=id;
        this.title = title;
        NoteContent = noteContent;
    }

    public Note(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteContent() {
        return NoteContent;
    }

    public void setNoteContent(String noteContent) {
        NoteContent = noteContent;
    }
}
