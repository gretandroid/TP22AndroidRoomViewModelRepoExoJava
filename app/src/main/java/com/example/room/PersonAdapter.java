package com.example.room;

import static android.R.drawable.checkbox_off_background;
import static android.R.drawable.checkbox_on_background;
import static android.view.LayoutInflater.from;
import static com.example.room.R.id.person_date;
import static com.example.room.R.id.person_name;
import static com.example.room.R.id.selectPersonImage;
import static com.example.room.R.layout.row_person;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.room.database.PersonneEntity;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends
        Adapter<PersonAdapter.PersonViewHolder> {

    private final List<PersonneEntity> persons;
    private final List<PersonneEntity> selectedPersons = new ArrayList<>();
    private PersonAdapterImageClickEventListener adapterListener;

    public PersonAdapter(List<PersonneEntity> persons,
                         PersonAdapterImageClickEventListener adapterListener) {
        this.persons = persons;
        this.adapterListener = adapterListener;
    }

    public interface PersonAdapterImageClickEventListener {
        void onImageClickEventListener(View view,
                                       PersonneEntity person);
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
        PersonneEntity selectedPerson = persons.get(position);
        holder.nameText.setText(persons.get(position).getNom());
        holder.dateText.setText(persons.get(position).getDate().toString());

        holder.selectPersonImg.setOnClickListener(
                view -> {
                    //utiliser un tag pour ranger l etat de selected
                    //est ce que un item parmis toutes les row a été selectionné?
                    if (view.getTag() == TRUE) {
                        ((ImageView) view).setImageResource(checkbox_off_background);
                        view.setTag(FALSE);
                        selectedPersons.add(persons.get(position));
                    } else {
                        ((ImageView) view).setImageResource(checkbox_on_background);
                        view.setTag(TRUE);
                        if (!selectedPersons.isEmpty()) {
                            selectedPersons.remove(position);
                            Toast.makeText(view.getContext(), selectedPersons.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    //notifie vers l'appelant de l'implementation
                    adapterListener.onImageClickEventListener(view, selectedPerson);
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
