package Models;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Gautam on 18-11-2017.
 */

public class DailyExcersiseRoutine extends RealmObject {
    @PrimaryKey
    private String day;
    private boolean isRestDay;
    private RealmList<Boolean> exerciseBoolValues;

    public DailyExcersiseRoutine() {

    }

    public DailyExcersiseRoutine(String day, Boolean isRestDay, RealmList<Boolean> exerciseBoolValues) {
        this.day = day;
        this.isRestDay = isRestDay;
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

    public RealmList<Boolean> getExcersiseList() {
        return exerciseBoolValues;
    }

    public void setExcersiseList(RealmList<Boolean> exerciseBoolValues) {
        this.exerciseBoolValues = exerciseBoolValues;
    }

    public void setExerciseBoolValues(int position, boolean value) {
        this.exerciseBoolValues.set(position, value);
    }


}

