package com.gautamnaik1994.gymtracker;

import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Adapters.DailyExerciseRoutineAdapter;
import Interfaces.ExcerciseRoutineExCheckboxListener;
import Models.DailyExcersiseRoutine;
import Constants.DaysOfWeek;
import Constants.ExcerciseBodyGroup;
import Interfaces.ExcersizeRoutineRestSwitchClickListener;
import Models.DailyExcersiseRoutine_;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.QueryBuilder;


public class MapExcersizeToDays extends AppCompatActivity {
    private static final String TAG = "TAG";
    private List<DailyExcersiseRoutine> dailyExcersiseRoutineList = new ArrayList<>();
    private RecyclerView dayItemHolder;
    private DailyExerciseRoutineAdapter dailyExerciseRoutineAdapter;
    private ExcerciseRoutineExCheckboxListener excerciseRoutineExCheckboxListener;
    private ExcersizeRoutineRestSwitchClickListener excersizeRoutineRestSwitchClickListener;
    private Toolbar appBar;
    private Button saveBtn;
    private Random random;
    private Box<DailyExcersiseRoutine> dailyExcersiseRoutineBox;
    private final String dataIsSaved ="DATA_IS_SAVED";
    private SharedPreferences pref;
   private SharedPreferences.Editor editor;

    public MapExcersizeToDays() {
        random = new Random();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_excersize_to_day);
        initViews();
        initListeners();
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
         editor = pref.edit();
        dailyExerciseRoutineAdapter = new DailyExerciseRoutineAdapter(dailyExcersiseRoutineList, excersizeRoutineRestSwitchClickListener, excerciseRoutineExCheckboxListener);
        dailyExerciseRoutineAdapter.setHasStableIds(true);
        BoxStore boxStore = ((App) getApplication()).getBoxStore();
        dailyExcersiseRoutineBox = boxStore.boxFor(DailyExcersiseRoutine.class);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return true;
            }
        };
        if(!pref.getBoolean(dataIsSaved,false)){
            populateExcerciseBodyGroupList();
        }
        else {
            getExerciseData();
        }
        dayItemHolder.setLayoutManager(mLayoutManager);
        dayItemHolder.setItemAnimator(new DefaultItemAnimator());
        dayItemHolder.setAdapter(dailyExerciseRoutineAdapter);




    }

    private void getExerciseData() {
        QueryBuilder<DailyExcersiseRoutine> builder = dailyExcersiseRoutineBox.query();
        List<DailyExcersiseRoutine> routine = builder.build().find();

        for (DaysOfWeek day : DaysOfWeek.values()) {
            ArrayList<Boolean> sampleData = new ArrayList<>();
            for (ExcerciseBodyGroup bodyGroup : ExcerciseBodyGroup.values()) {
                sampleData.add(routine.get(day.ordinal()).getExerciseBoolValues().get(bodyGroup.ordinal()));
            }
            DailyExcersiseRoutine dailyExcersiseRoutine = new DailyExcersiseRoutine(day.ordinal() + 1, day.toString(), routine.get(day.ordinal()).isRestDay() , sampleData);
            dailyExcersiseRoutineList.add(dailyExcersiseRoutine);
        }
        dailyExerciseRoutineAdapter.notifyDataSetChanged();
    }


    private void initViews(){
        appBar = findViewById(R.id.appBar);
        setSupportActionBar(appBar);
        dayItemHolder = findViewById(R.id.dayItemHolder);//Recycler View
        saveBtn=findViewById(R.id.saveBtn);
    }

    private void initListeners() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
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
            DailyExcersiseRoutine dailyExcersiseRoutine = new DailyExcersiseRoutine(day.ordinal() + 1, day.toString(), getRandomBoolean(), sampleData);
            dailyExcersiseRoutineList.add(dailyExcersiseRoutine);
        }
        dailyExerciseRoutineAdapter.notifyDataSetChanged();
    }

    private void saveData() {

        Log.d(TAG, "saveData: Clicked Done");
        dailyExcersiseRoutineBox.put(dailyExcersiseRoutineList);
        boolean dataSaved=pref.getBoolean(dataIsSaved, false);
        if(!dataSaved) {
            editor.putBoolean(dataIsSaved, true);
            editor.commit();
        }
    }
    private void Toatdata(){
        QueryBuilder<DailyExcersiseRoutine> builder = dailyExcersiseRoutineBox.query();
//        builder.equal(DailyExcersiseRoutine_.id,2);
       //DailyExcersiseRoutine dailyExcersiseRoutine=new DailyExcersiseRoutine();
        List<DailyExcersiseRoutine> routine = builder.build().find();

        Log.d(TAG, "Toatdata: Day"+ routine.get(0).getDay());
        Log.d(TAG, "Toatdata: Day"+ routine.get(2).getDay());
        Log.d(TAG, "Toatdata: List"+ routine.get(1).getExerciseBoolValues());
        Log.d(TAG, "Toatdata: List"+ routine.get(2).getExerciseBoolValues());
    }
}
