package com.gautamnaik1994.gymtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import Adapters.DailyExerciseRoutineAdapter;
import classes.DailyExcersiseRoutine;
import constants.DaysOfWeek;
import constants.ExcerciseBodyGroup;

public class MapExcersizeToDays extends AppCompatActivity {
    private List<DailyExcersiseRoutine> dailyExcersiseRoutineList = new ArrayList<>();
    private RecyclerView dayItemHolder;
    private DailyExerciseRoutineAdapter dailyExerciseRoutineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_excersize_to_day);
        dayItemHolder = findViewById(R.id.dayItemHolder);
        dayItemHolder.setHasFixedSize(true);
//        dayItemHolder.setNestedScrollingEnabled(false);
        dailyExerciseRoutineAdapter = new DailyExerciseRoutineAdapter(dailyExcersiseRoutineList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        dayItemHolder.setLayoutManager(mLayoutManager);
        dayItemHolder.setItemAnimator(new DefaultItemAnimator());
        dayItemHolder.setAdapter(dailyExerciseRoutineAdapter);
        populateExcerciseBodyGroupList();
    }

    private void populateExcerciseBodyGroupList() {

        for (DaysOfWeek day : DaysOfWeek.values()) {
            Hashtable<String,Boolean> sampleData = new Hashtable<String, Boolean>();

            for(ExcerciseBodyGroup bodyGroup:ExcerciseBodyGroup.values()){
                sampleData.put(bodyGroup.toString(),true);
            }

            DailyExcersiseRoutine dailyExcersiseRoutine = new DailyExcersiseRoutine(day.toString(), true, sampleData);
            dailyExcersiseRoutineList.add(dailyExcersiseRoutine);
        }
        dailyExerciseRoutineAdapter.notifyDataSetChanged();
    }
}
