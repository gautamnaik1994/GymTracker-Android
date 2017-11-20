package Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.gautamnaik1994.gymtracker.R;

import java.util.List;

import classes.DailyExcersiseRoutine;
import constants.ExcerciseBodyGroup;

import static android.content.ContentValues.TAG;
import static android.widget.GridLayout.*;

/**
 * Created by Gautam on 19-11-2017.
 */

public class DailyExerciseRoutineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DailyExcersiseRoutine> dailyExcersiseRoutineList;

    private static final int HEADER_VIEW = 1;
    private static final int LIST_ITEM_VIEW = 2;
    private static final int FOOTER_VIEW = 3;

    public static String capitalizeFirstLetter(@NonNull String customText) {
        int count = customText.length();
        if (count == 0) {
            return customText;
        }
        if (count == 1) {
            return customText.toUpperCase();
        }
        return customText.substring(0, 1).toUpperCase() + customText.substring(1).toLowerCase();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder {

        public TextView day;
        public Switch restDaySwitch;
        public CheckBox chest, back, biceps, forearms, shoulder, legs, abs, cardio, other;
        public GridLayout excerciseGroupHolder;

        public ListItemViewHolder(View view) {
            super(view);
            day = view.findViewById(R.id.day);
            restDaySwitch = view.findViewById(R.id.restDaySwitch);
            excerciseGroupHolder = view.findViewById(R.id.excerciseGroupHolder);

        }
    }

    public class HeaderItemHolder extends RecyclerView.ViewHolder {

        public HeaderItemHolder(View view) {
            super(view);
        }
    }

//    class ViewHolderFooter extends RecyclerView.ViewHolder {
//
//        public ViewHolderFooter(View itemView) {
//
//        }
//
//    }


    public DailyExerciseRoutineAdapter(List<DailyExcersiseRoutine> dailyExcersiseRoutineList) {
        this.dailyExcersiseRoutineList = dailyExcersiseRoutineList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case LIST_ITEM_VIEW:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item, parent, false);
                return new ListItemViewHolder(itemView);

            case HEADER_VIEW:
                View headeriItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.big_header, parent, false);
                return new HeaderItemHolder(headeriItemView);
            default:
                return null;
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case LIST_ITEM_VIEW:
                ListItemViewHolder myViewHolder = (ListItemViewHolder) holder;
                DailyExcersiseRoutine dailyExcersiseRoutine = dailyExcersiseRoutineList.get(position - 1);
                myViewHolder.day.setText(dailyExcersiseRoutine.getDay());
                myViewHolder.restDaySwitch.setChecked(dailyExcersiseRoutine.isRestDay());
                Context mContext = myViewHolder.day.getContext();
                myViewHolder.excerciseGroupHolder.removeAllViews();
                for (ExcerciseBodyGroup bodyGroup : ExcerciseBodyGroup.values()) {
                    CheckBox checkBox = new CheckBox(mContext);
                    checkBox.setText(capitalizeFirstLetter(bodyGroup.toString()));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    GridLayout.LayoutParams param = new GridLayout.LayoutParams(GridLayout.spec(
                            GridLayout.UNDEFINED, GridLayout.FILL, 1f),
                            GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f));
                    myViewHolder.excerciseGroupHolder.addView(checkBox, param);
                }

                break;

            case HEADER_VIEW:
                HeaderItemHolder headerItemHolder = (HeaderItemHolder) holder;
                break;
        }
    }

    @Override
    public int getItemCount() {
        int listSize = 0;

        if (dailyExcersiseRoutineList == null) {
            return 0;
        }

        if (dailyExcersiseRoutineList != null) {
            listSize = dailyExcersiseRoutineList.size();
            Log.d(TAG, "getItemCount: " + dailyExcersiseRoutineList.size());
        }

        if (listSize > 0) {
            return 1 + listSize;
        } else {
            return 0;
        }

//        Log.d(TAG, "getItemCount: " + dailyExcersiseRoutineList.size());
//        if (dailyExcersiseRoutineList == null) {
//            return 0;
//        }
//
//        if (dailyExcersiseRoutineList.size() == 0) {
//            //Return 1 here to show nothing
//            return 0;
//        }
//
//        // Add extra view to show the footer view
//        return dailyExcersiseRoutineList.size();

//        return dailyExcersiseRoutineList.size();
    }

    @Override
    public int getItemViewType(int position) {

        int listSize = 0;

        if (dailyExcersiseRoutineList == null)
            return super.getItemViewType(position);

        if (dailyExcersiseRoutineList != null) {
            listSize = dailyExcersiseRoutineList.size();
        }
        if (listSize > 0) {
            if (position == 0) return HEADER_VIEW;
            else return LIST_ITEM_VIEW;
        }

        return super.getItemViewType(position);
    }

}
