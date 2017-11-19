package Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import com.gautamnaik1994.gymtracker.R;

import java.util.ArrayList;
import java.util.List;

import classes.DailyExcersiseRoutine;

import static android.content.ContentValues.TAG;

/**
 * Created by Gautam on 19-11-2017.
 */

public class DailyExerciseRoutineAdapter extends RecyclerView.Adapter<DailyExerciseRoutineAdapter.MyViewHolder> {
    private List<DailyExcersiseRoutine> dailyExcersiseRoutineList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView day;
        public Switch restDaySwitch;
        public CheckBox chest, back, biceps, forearms, shoulder, legs, abs, cardio, other;

        public MyViewHolder(View view) {
            super(view);
            day = view.findViewById(R.id.day);
            restDaySwitch = view.findViewById(R.id.restDaySwitch);
            chest = view.findViewById(R.id.chest);
            back = view.findViewById(R.id.back);
            biceps = view.findViewById(R.id.biceps);
            forearms = view.findViewById(R.id.forearms);
            shoulder = view.findViewById(R.id.shoulder);
            legs = view.findViewById(R.id.legs);
            abs = view.findViewById(R.id.abs);
            cardio = view.findViewById(R.id.cardio);
            other = view.findViewById(R.id.other);
        }
    }

    public DailyExerciseRoutineAdapter(List<DailyExcersiseRoutine> dailyExcersiseRoutineList) {
        this.dailyExcersiseRoutineList = dailyExcersiseRoutineList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DailyExcersiseRoutine dailyExcersiseRoutine = dailyExcersiseRoutineList.get(position);
        holder.day.setText(dailyExcersiseRoutine.getDay());

        holder.restDaySwitch.setChecked(dailyExcersiseRoutine.isRestDay());
        Log.d(TAG, "onBindViewHolderfgdfgfd: "+ dailyExcersiseRoutine.getExcersiseList().keySet());
    }

    @Override
    public int getItemCount() {
        return dailyExcersiseRoutineList.size();
    }


}
