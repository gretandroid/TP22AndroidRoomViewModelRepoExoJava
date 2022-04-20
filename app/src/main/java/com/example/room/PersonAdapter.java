package com.example.room;

import static android.R.drawable.checkbox_off_background;
import static android.R.drawable.checkbox_on_background;
import static android.view.LayoutInflater.from;
import static com.example.room.R.id.selectPersonImage;
import static com.example.room.R.id.person_date;
import static com.example.room.R.id.person_name;
import static com.example.room.R.layout.row_person;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.room.database.PersonneEntity;

import java.util.List;

public class PersonAdapter extends
        Adapter<PersonAdapter.PersonViewHolder> {
    Boolean isCurrentPersonSelected = false;

    private List<PersonneEntity> persons;

    public PersonAdapter(List<PersonneEntity> data) {
        persons = data;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType) {
        return new PersonViewHolder(from(parent.getContext()).inflate(row_person,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder,
                                 int position) {

        holder.nameText.setText(persons.get(position).getNom());
        holder.dateText.setText(persons.get(position).getDate().toString());
        holder.selectPersonImg.setOnClickListener(
                view -> {
                    if (!isCurrentPersonSelected) {
                        holder.selectPersonImg.setImageResource(checkbox_on_background);
                        isCurrentPersonSelected = true;
                    } else {
                        holder.selectPersonImg.setImageResource(checkbox_off_background);
                        isCurrentPersonSelected = false;
                    }
                    //utiliser un tag pour ranger l etat de selected
                    //est ce que un item parmis toutes les row a été selectionné?

                }
        );
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        ImageView selectPersonImg;
        TextView nameText;
        TextView dateText;


        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            selectPersonImg = itemView.findViewById(selectPersonImage);
            nameText = itemView.findViewById(person_name);
            dateText = itemView.findViewById(person_date);

        }
    }
}
