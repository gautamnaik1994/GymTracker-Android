package Models;

import java.util.ArrayList;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.converter.PropertyConverter;

/**
 * Created by Gautam on 18-11-2017.
 */

@Entity
public class DailyExcersiseRoutine {
    @Id(assignable = true)
    private long id;
    private String day;
    private boolean isRestDay;
    @Convert(converter = ExerciseBoolValuesConverter.class, dbType = String.class)
    private ArrayList<Boolean> exerciseBoolValues;

    public DailyExcersiseRoutine() {

    }

    public DailyExcersiseRoutine(long id, String day, Boolean isRestDay, ArrayList<Boolean> exerciseBoolValues) {
        this.id=id;
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

    public ArrayList<Boolean> getExcersiseList() {
        return exerciseBoolValues;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Boolean> getExerciseBoolValues() {
        return exerciseBoolValues;
    }

    public void setExerciseBoolValues(ArrayList<Boolean> exerciseBoolValues) {
        this.exerciseBoolValues = exerciseBoolValues;
    }

    public void setExcersiseList(ArrayList<Boolean> exerciseBoolValues) {

        this.exerciseBoolValues = exerciseBoolValues;
    }

    public void setExerciseBoolValues(int position, boolean value) {
        this.exerciseBoolValues.set(position, value);
    }


    public static class ExerciseBoolValuesConverter implements PropertyConverter<ArrayList<Boolean>, String> {

        @Override
        public ArrayList<Boolean> convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) return null;

            String[] strings = databaseValue.split(",");
            ArrayList<Boolean> booleanArrayList =new ArrayList<>();
            for(int i=0;i<strings.length;i++){
                booleanArrayList.add(Boolean.parseBoolean(strings[i]));
            }

            return booleanArrayList;
        }

        @Override
        public String convertToDatabaseValue(ArrayList<Boolean> entityProperty) {
            if (entityProperty == null) return "";
            if (entityProperty.isEmpty()) return "";
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < entityProperty.size(); i++) {
                stringBuilder.append(entityProperty.get(i)).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }
    }
}

