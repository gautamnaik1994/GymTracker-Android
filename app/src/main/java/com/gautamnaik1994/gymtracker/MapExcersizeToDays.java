package com.gautamnaik1994.gymtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapters.DailyExerciseRoutineAdapter;
import classes.DailyExcersiseRoutine;
import constants.DaysOfWeek;

public class MapExcersizeToDays extends AppCompatActivity {
    private List<DailyExcersiseRoutine> dailyExcersiseRoutineList = new ArrayList<>();
    private RecyclerView dayItemHolder;
    private DailyExerciseRoutineAdapter dailyExerciseRoutineAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_excersize_to_day);
        dayItemHolder=findViewById(R.id.dayItemHolder);

        dailyExerciseRoutineAdapter=new DailyExerciseRoutineAdapter(dailyExcersiseRoutineList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        dayItemHolder.setLayoutManager(mLayoutManager);
        dayItemHolder.setItemAnimator(new DefaultItemAnimator());
        dayItemHolder.setAdapter(dailyExerciseRoutineAdapter);
        populateExcerciseBodyGroupList();
    }

    private void populateExcerciseBodyGroupList(){

        for (DaysOfWeek day : DaysOfWeek.values()) {
            ArrayList<String> sampleData =new ArrayList<String>();
            sampleData.add("Chest");
            sampleData.add("Back");
            sampleData.add("Biceps");
            DailyExcersiseRoutine dailyExcersiseRoutine=new DailyExcersiseRoutine(day.toString(),false,sampleData);
            dailyExcersiseRoutineList.add(dailyExcersiseRoutine);
        }
        dailyExerciseRoutineAdapter.notifyDataSetChanged();
    }
}
