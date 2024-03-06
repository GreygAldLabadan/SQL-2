package com.example.sqlintegrationlabadan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;

    ArrayList<Note> notes;

    RecyclerView recyclerView;

    NoteAdapter Noteadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = findViewById(R.id.imagebutton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewInput = inflater.inflate(R.layout.note_input,null, false);
                EditText edtItem = viewInput.findViewById(R.id.edit_Item);

                new AlertDialog.Builder(MainActivity.this)
                        .setView(viewInput)
                        .setTitle("Add Items")
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String title = edtItem.getText().toString();

                                Note note = new Note (title);

                                boolean isInserted = new NoteHandler(MainActivity.this).create(note);

                                if(isInserted){
                                    Toast.makeText(MainActivity.this, "Note Saved", Toast.LENGTH_SHORT).show();
                                    loadNotes();
                                }else {
                                    Toast.makeText(MainActivity.this, "Not Saved", Toast.LENGTH_SHORT).show();
                                }
                                dialog.cancel();
                            }
                        }).show()

            }
        });
    }
}