package Models;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Gautam on 18-11-2017.
 */

public class DailyExcersiseRoutine {
    private String day;
    private boolean isRestDay;
    private ArrayList<Boolean> exerciseBoolValues;

    public DailyExcersiseRoutine(){

    }
    public DailyExcersiseRoutine(String day, Boolean isRestDay,ArrayList<Boolean> exerciseBoolValues){
        this.day=day;
        this.isRestDay=isRestDay;
        this.exerciseBoolValues = exerciseBoolValues;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isRestDay() {
        return isRestDay;
    }

    public void setRestDay(boolean restDay) {
        isRestDay = restDay;
    }

    public ArrayList<Boolean> getExcersiseList() {
        return exerciseBoolValues;
    }

    public void setExcersiseList(ArrayList<Boolean> exerciseBoolValues) {
        this.exerciseBoolValues = exerciseBoolValues;
    }
    public void setExerciseBoolValues(int position, boolean value){
       this.exerciseBoolValues.set(position,value);
    }



}

