package com.gautamnaik1994.gymtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Adapters.DailyExerciseRoutineAdapter;
import Interfaces.ExcerciseRoutineExCheckboxListener;
import Models.DailyExcersiseRoutine;
import Constants.DaysOfWeek;
import Constants.ExcerciseBodyGroup;
import Interfaces.ExcersizeRoutineRestSwitchClickListener;

public class MapExcersizeToDays extends AppCompatActivity {
    private List<DailyExcersiseRoutine> dailyExcersiseRoutineList = new ArrayList<>();
    private RecyclerView dayItemHolder;
    private DailyExerciseRoutineAdapter dailyExerciseRoutineAdapter;
    private ExcerciseRoutineExCheckboxListener excerciseRoutineExCheckboxListener;
    private ExcersizeRoutineRestSwitchClickListener excersizeRoutineRestSwitchClickListener;
    private Toolbar appBar;
    private Button saveBtn;
    private Random random;

    public MapExcersizeToDays() {
        random = new Random();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_excersize_to_day);
        appBar = findViewById(R.id.appBar);
        setSupportActionBar(appBar);
        dayItemHolder = findViewById(R.id.dayItemHolder);//Recycler View
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
    }

    public boolean getRandomBoolean() {
        return random.nextBoolean();
    }


    private void populateExcerciseBodyGroupList() {
        for (DaysOfWeek day : DaysOfWeek.values()) {
            ArrayList<Boolean> sampleData = new ArrayList<>();
            for (ExcerciseBodyGroup bodyGroup : ExcerciseBodyGroup.values()) {
                sampleData.add(getRandomBoolean());
            }
            DailyExcersiseRoutine dailyExcersiseRoutine = new DailyExcersiseRoutine(day.toString(), getRandomBoolean(), sampleData);
            dailyExcersiseRoutineList.add(dailyExcersiseRoutine);
        }
        dailyExerciseRoutineAdapter.notifyDataSetChanged();
    }
}
