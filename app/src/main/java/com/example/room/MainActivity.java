package com.example.room;

import static com.example.room.R.id.rcView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.database.PersonneEntity;
import com.example.room.database.TestData;
import com.example.room.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MainViewModel mViewModel;
    private final List<PersonneEntity> personsData = new ArrayList<>();
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = new ViewModelProvider(this)
                .get(MainViewModel.class);
        mViewModel.mPersons.observe(
                this, persons -> {
                    personsData.clear();
                    personsData.addAll(persons);
                    if (personAdapter == null) {
                        personAdapter = new PersonAdapter(personsData);
                        recyclerView = findViewById(rcView);
                        recyclerView.setAdapter(personAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    } else personAdapter.notifyDataSetChanged();
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void onClickAddAllData(MenuItem item) {
        mViewModel.addAllPersons(TestData.getAll());
    }

    public void onClickDeleteAllData(MenuItem item) {
        mViewModel.deleteAllPersons();
    }
}