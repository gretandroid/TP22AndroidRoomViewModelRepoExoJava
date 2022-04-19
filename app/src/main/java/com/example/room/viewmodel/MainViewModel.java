package com.example.room.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.room.AppRepository;
import com.example.room.database.PersonneEntity;

import java.util.List;

//Nous avons besoin d'un context dans le constructeur
//pour cela on étend AndroidViewModel
public class MainViewModel extends AndroidViewModel {

    private AppRepository mRepository;
    public LiveData<List<PersonneEntity>> mPersons;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mPersons = mRepository.mPersons;
    }

    public void addAllPersons(List<PersonneEntity> persons) {
        mRepository.addAllPersons(persons);
    }

    public void deleteAllPersons() {
        mRepository.deleteAllPersons();
    }
}
