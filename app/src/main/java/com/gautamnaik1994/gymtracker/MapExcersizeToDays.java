package com.gautamnaik1994.gymtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Adapters.DailyExerciseRoutineAdapter;
import Classes.DailyExcersiseRoutine;
import Constants.DaysOfWeek;
import Constants.ExcerciseBodyGroup;
import Interfaces.ExcersizeRoutineRestSwitchClickListener;

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
        dailyExerciseRoutineAdapter = new DailyExerciseRoutineAdapter(dailyExcersiseRoutineList, new ExcersizeRoutineRestSwitchClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(view.getContext(), "Item Clicked"+ dailyExcersiseRoutineList.get(position).getDay().toString() , Toast.LENGTH_SHORT).show();
                boolean isRestday=dailyExcersiseRoutineList.get(position).isRestDay();
                dailyExcersiseRoutineList.get(position).setRestDay(!isRestday);
                dailyExerciseRoutineAdapter.notifyDataSetChanged();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        dayItemHolder.setLayoutManager(mLayoutManager);
        dayItemHolder.setItemAnimator(new DefaultItemAnimator());
        dayItemHolder.setAdapter(dailyExerciseRoutineAdapter);
        populateExcerciseBodyGroupList();
    }
    private Random random;
    public MapExcersizeToDays(){
        random = new Random();
    }
    public boolean getRandomBoolean() {
        return random.nextBoolean();
    }


    private void populateExcerciseBodyGroupList() {

        for (DaysOfWeek day : DaysOfWeek.values()) {
            ArrayList<Boolean> sampleData = new ArrayList<>();

            for(ExcerciseBodyGroup bodyGroup:ExcerciseBodyGroup.values()){
                sampleData.add(getRandomBoolean());
            }

            DailyExcersiseRoutine dailyExcersiseRoutine = new DailyExcersiseRoutine(day.toString(), getRandomBoolean(), sampleData);
            dailyExcersiseRoutineList.add(dailyExcersiseRoutine);
        }
        dailyExerciseRoutineAdapter.notifyDataSetChanged();
    }
}
