package com.example.room;

import static android.R.drawable.checkbox_on_background;
import static android.view.LayoutInflater.from;
import static com.example.room.R.id.addPersonImage;
import static com.example.room.R.id.person_date;
import static com.example.room.R.id.person_name;
import static com.example.room.R.layout.row_person;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.room.database.PersonneEntity;

import java.util.List;

public class PersonAdapter extends
        Adapter<PersonAdapter.PersonViewHolder> {

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
        final Boolean  isPersonSelected = false;
        holder.nameText.setText(persons.get(position).getNom());
        holder.dateText.setText(persons.get(position).getDate().toString());
        holder.selectPersonImg.setOnClickListener(
                imageView -> {

                    ImageView iv = (ImageView) imageView;
                    if (isPersonSelected==false) {
                        iv.setImageResource(android.R.drawable.checkbox_on_background);
                        isPersonSelected=true;

                    }else{
                        iv.setImageResource(android.R.drawable.checkbox_off_background);
                        isPersonSelected=false;
                    }
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
            selectPersonImg = itemView.findViewById(addPersonImage);
            nameText = itemView.findViewById(person_name);
            dateText = itemView.findViewById(person_date);
        }
    }
}
