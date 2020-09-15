package com.stark.jarvis;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Random;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>{

    private static final String TAG = "NotesAdapter";
    Context context;

    ArrayList<Note> notes;

    public  NotesAdapter(Context context){
        this.context=context;
    }




    public void setNotes(ArrayList<Note> notes){
        this.notes=notes;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_notes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.noteTitle.setText(notes.get(position).getTitle());
        holder.noteContent.setText(notes.get(position).getNoteContent());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,CreateNote.class);
                intent.putExtra("title",notes.get(position).getTitle());
                intent.putExtra("noteContent",notes.get(position).getNoteContent());
                intent.putExtra("id",notes.get(position).getId());
                context.startActivity(intent);

            }
        });

       /* Random rand=new Random();
       int color= rand.nextInt(3)+1;

       int arr[] ={R.color.color1,R.color.color2,R.color.color3,R.color.color4};


        holder.cardView.setBackgroundResource(arr[color]);*/

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Are you want to delete ? ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                DAO dao=new DAO(context);
                                dao.deleteNote(notes.get(holder.getAdapterPosition()).getId());
                                notes.remove(holder.getAdapterPosition());
                              notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null)
                        .create()
                        .show();

                return false;
            }
        });

    }




    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView noteTitle,noteContent;
        private MaterialCardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle=itemView.findViewById(R.id.show_title);
            noteContent=itemView.findViewById(R.id.notes_content);
            cardView=itemView.findViewById(R.id.cardVieww);
        }
    }
}
