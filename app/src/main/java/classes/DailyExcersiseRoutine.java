package classes;

import java.util.ArrayList;

/**
 * Created by Gautam on 18-11-2017.
 */

public class DailyExcersiseRoutine {
    private String day;
    private boolean isRestDay;
    private ArrayList<String> exerciseList;

    public DailyExcersiseRoutine(){

    }
    public DailyExcersiseRoutine(String day, Boolean isRestDay, ArrayList<String> exerciseList){
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

    public ArrayList<String> getExcersiseList() {
        return exerciseList;
    }

    public void setExcersiseList(ArrayList<String> exerciseList) {
        this.exerciseList = exerciseList;
    }
}

