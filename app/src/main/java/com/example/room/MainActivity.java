package com.example.room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.room.database.PersonneEntity;
import com.example.room.database.TestData;
import com.example.room.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<PersonneEntity> arrayAdapter;
    private MainViewModel mViewModel;
    private final List<PersonneEntity> personsData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        mViewModel = new ViewModelProvider(this)
                .get(MainViewModel.class);
        mViewModel.mPersons.observe(
                this, persons -> {
                    //on doit utiliser une collection
                    //qui wrappe les données pour manipuler le view model
                    //on commence par la clear le notify ne prend
                    // en compte les modifications sur l'adapter
                    personsData.clear();
                    personsData.addAll(persons);
                    if (arrayAdapter == null) {
                        arrayAdapter = new ArrayAdapter<>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                personsData
                        );
                        listView.setAdapter(arrayAdapter);
                    } else arrayAdapter.notifyDataSetChanged();
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