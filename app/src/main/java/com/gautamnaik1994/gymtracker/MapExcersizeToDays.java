package com.gautamnaik1994.gymtracker;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Adapters.DailyExerciseRoutineAdapter;
import Constants.DaysOfWeek;
import Constants.ExcerciseBodyGroup;
import Interfaces.ExcerciseRoutineExCheckboxListener;
import Interfaces.ExcersizeRoutineRestSwitchClickListener;
import Models.DailyExcersiseRoutine;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.content.ContentValues.TAG;

public class MapExcersizeToDays extends AppCompatActivity {
    private Realm dailyExcerciseRoutineRealm;
    private List<DailyExcersiseRoutine> dailyExcersiseRoutineList = new ArrayList<>();
    private RecyclerView dayItemHolder;
    private DailyExerciseRoutineAdapter dailyExerciseRoutineAdapter;
    private ExcerciseRoutineExCheckboxListener excerciseRoutineExCheckboxListener;
    private ExcersizeRoutineRestSwitchClickListener excersizeRoutineRestSwitchClickListener;

    private Toolbar appBar;
    private Button saveBtn;
    private Random random;
    private Context context;

    public MapExcersizeToDays() {
        random = new Random();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_excersize_to_day);
        appBar = findViewById(R.id.appBar);
        setSupportActionBar(appBar);
        dailyExcerciseRoutineRealm = Realm.getDefaultInstance();
        dayItemHolder = findViewById(R.id.dayItemHolder);//Recycler View
        saveBtn = findViewById(R.id.saveBtn);
        initListeners();
        dailyExerciseRoutineAdapter = new DailyExerciseRoutineAdapter(dailyExcersiseRoutineList, excersizeRoutineRestSwitchClickListener, excerciseRoutineExCheckboxListener);
        dailyExerciseRoutineAdapter.setHasStableIds(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return true;
            }
        };

        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        dayItemHolder.setLayoutManager(mLayoutManager);
        dayItemHolder.setItemAnimator(new DefaultItemAnimator());
        dayItemHolder.setAdapter(dailyExerciseRoutineAdapter);
        populateExcerciseBodyGroupList();

    }

    private void initListeners() {
        excerciseRoutineExCheckboxListener = new ExcerciseRoutineExCheckboxListener() {
            @Override
            public void onClick(View view, int position, int bodyGroupIndex, boolean boolValue) {

                dailyExcersiseRoutineList.get(position).setExerciseBoolValues(bodyGroupIndex, boolValue);
                dailyExerciseRoutineAdapter.notifyItemChanged(position);
            }
        };
        excersizeRoutineRestSwitchClickListener = new ExcersizeRoutineRestSwitchClickListener() {
            @Override
            public void onClick(View view, int position, boolean boolValue) {
                Toast.makeText(view.getContext(), "Item Clicked " + dailyExcersiseRoutineList.get(position).getDay().toString() + " " + position + " " + boolValue, Toast.LENGTH_SHORT).show();
                dailyExcersiseRoutineList.get(position).setRestDay(boolValue);
                dailyExerciseRoutineAdapter.notifyItemChanged(position);

            }
        };

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void saveData() {
        dailyExcerciseRoutineRealm.beginTransaction();
//        DailyExcersiseRoutine dailyExcersiseRoutine = dailyExcerciseRoutineRealm.createObject(DailyExcersiseRoutine.class);
        dailyExcerciseRoutineRealm.insertOrUpdate(dailyExcersiseRoutineList);
        dailyExcerciseRoutineRealm.commitTransaction();
        Log.d(TAG, "realm path: " + dailyExcerciseRoutineRealm.getPath());
        Toast.makeText(this, "SavebtnClicked ", Toast.LENGTH_SHORT).show();
    }

    public boolean getRandomBoolean() {
        return random.nextBoolean();
    }


    private void populateExcerciseBodyGroupList() {

        // RealmResults<DailyExcersiseRoutine> dailyExcersiseRoutineFullData = dailyExcerciseRoutineRealm.where(DailyExcersiseRoutine.class).findAll();
        // if (dailyExcersiseRoutineFullData == null) {
        Toast.makeText(this, "NO DATA FOUND", Toast.LENGTH_LONG).show();
        for (DaysOfWeek day : DaysOfWeek.values()) {
            RealmList<Boolean> sampleData = new RealmList<>();
            for (ExcerciseBodyGroup bodyGroup : ExcerciseBodyGroup.values()) {
                sampleData.add(getRandomBoolean());
            }
            DailyExcersiseRoutine dailyExcersiseRoutine = new DailyExcersiseRoutine(day.toString(), false, sampleData);
            dailyExcersiseRoutineList.add(dailyExcersiseRoutine);
        }
//        } else {
//            Toast.makeText(this, "DATA FOUND", Toast.LENGTH_LONG).show();
//            for (DaysOfWeek day : DaysOfWeek.values()) {
//                RealmList boolValues = dailyExcersiseRoutineFullData.get(day.ordinal()).getExcersiseList();
//                DailyExcersiseRoutine dailyExcersiseRoutine = new DailyExcersiseRoutine(day.toString(), dailyExcersiseRoutineFullData.get(day.ordinal()).isRestDay(), boolValues);
//                dailyExcersiseRoutineList.add(dailyExcersiseRoutine);
//            }
        //}
        dailyExerciseRoutineAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dailyExcerciseRoutineRealm.close();
    }
}
