package classes;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Gautam on 18-11-2017.
 */

public class DailyExcersiseRoutine {
    private String day;
    private boolean isRestDay;
    private Hashtable<String,Boolean> exerciseList;

    public DailyExcersiseRoutine(){

    }
    public DailyExcersiseRoutine(String day, Boolean isRestDay,Hashtable<String,Boolean> exerciseList){
        this.day=day;
        this.isRestDay=isRestDay;
        this.exerciseList=exerciseList;
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

    public Hashtable<String,Boolean> getExcersiseList() {
        return exerciseList;
    }

    public void setExcersiseList(Hashtable<String,Boolean> exerciseList) {
        this.exerciseList = exerciseList;
    }
}

