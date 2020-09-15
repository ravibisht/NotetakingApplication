package com.stark.jarvis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton createNote;
    private RecyclerView allNotes;
    DAO dao=new DAO(this);
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("                            Your  Jarvis");

        initViews();
         notesAdapter=new NotesAdapter(this);

        allNotes.setAdapter(notesAdapter);
        allNotes.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onStart() {
        super.onStart();
        notesAdapter.setNotes(dao.getAllNotes());

    }



    private void initViews() {
        createNote=findViewById(R.id.create_note);
        createNote.setOnClickListener(createNoteListener);
        allNotes=findViewById(R.id.all_notes);
    }

    View.OnClickListener createNoteListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           startActivity(new Intent(MainActivity.this,CreateNote.class));
        }
    };
}