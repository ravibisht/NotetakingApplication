package com.stark.jarvis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreateNote extends AppCompatActivity {

    Button saveNote;
    TextView title, noteContent;

    DAO dao = new DAO(this);

    boolean status = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        getSupportActionBar().setTitle("Write Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        saveNote = findViewById(R.id.save_note);
        title = findViewById(R.id.title);
        noteContent = findViewById(R.id.note_text);

        title.setText(getIntent().getStringExtra("title"));
        noteContent.setText(getIntent().getStringExtra("noteContent"));

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
                CreateNote.super.onBackPressed();
            }
        });


    }

    private void saveNote() {

        String titleText = title.getText().toString();
        String noteText = noteContent.getText().toString();
        int id = getIntent().getIntExtra("id", 0);

        if (!noteText.equals("")) {

            if (titleText.equals("") && (!noteText.trim().equals(""))) {
                titleText = noteText.split("\n")[0];
            }

            status = dao.insertOrUpdateNote(id, titleText, noteText);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (!status)
            saveNote();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!status)
            saveNote();
        finishActivity(444);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}