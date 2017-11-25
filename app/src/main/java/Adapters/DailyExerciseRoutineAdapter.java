package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.gautamnaik1994.gymtracker.R;

import java.util.List;

import Classes.DailyExcersiseRoutine;
import Constants.ExcerciseBodyGroup;
import Interfaces.ExcersizeRoutineRestSwitchClickListener;
import Utils.Utils;

/**
 * Created by Gautam on 19-11-2017.
 */

public class DailyExerciseRoutineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_VIEW = 1;
    private static final int LIST_ITEM_VIEW = 2;
    private static final int FOOTER_VIEW = 3;
    private List<DailyExcersiseRoutine> dailyExcersiseRoutineList;
    private ExcersizeRoutineRestSwitchClickListener excersizeRoutineRestSwitchClickListener;


    public DailyExerciseRoutineAdapter(List<DailyExcersiseRoutine> dailyExcersiseRoutineList, ExcersizeRoutineRestSwitchClickListener excersizeRoutineRestSwitchClickListener) {
        this.dailyExcersiseRoutineList = dailyExcersiseRoutineList;
        this.excersizeRoutineRestSwitchClickListener = excersizeRoutineRestSwitchClickListener;
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
                final int pos = position - 1;
                 ListItemViewHolder myViewHolder = (ListItemViewHolder) holder;
                 DailyExcersiseRoutine dailyExcersiseRoutine = dailyExcersiseRoutineList.get(pos);
                boolean isRestDay = dailyExcersiseRoutine.isRestDay();


                myViewHolder.day.setText(dailyExcersiseRoutine.getDay());
                myViewHolder.restDaySwitch.setOnCheckedChangeListener(null);
                myViewHolder.restDaySwitch.setChecked(isRestDay);
                myViewHolder.gridHolder.setVisibility(View.GONE);
                myViewHolder.restDaySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        excersizeRoutineRestSwitchClickListener.onClick(compoundButton, pos);
                    }
                });
                Context mContext = myViewHolder.day.getContext();

                if (!isRestDay) {
                    myViewHolder.gridHolder.setVisibility(View.VISIBLE);
                    myViewHolder.excerciseGroupHolder.removeAllViews();
                    for (ExcerciseBodyGroup bodyGroup : ExcerciseBodyGroup.values()) {
                        CheckBox checkBox = new CheckBox(mContext);
                        checkBox.setId(bodyGroup.ordinal() * 555 + 55);
                        checkBox.setText(Utils.capitalizeFirstLetter(bodyGroup.toString()));
                        checkBox.setChecked(dailyExcersiseRoutine.getExcersiseList().get(bodyGroup.ordinal()));
                        GridLayout.LayoutParams param = new GridLayout.LayoutParams(GridLayout.spec(
                                GridLayout.UNDEFINED, GridLayout.FILL, 1f),
                                GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f));
                        myViewHolder.excerciseGroupHolder.addView(checkBox, param);
                    }
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

        }

        if (listSize > 0) {
            return 1 + listSize;
        } else {
            return 0;
        }

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

    public class ListItemViewHolder extends RecyclerView.ViewHolder {

        public TextView day;
        public Switch restDaySwitch;
        public GridLayout excerciseGroupHolder;
        public LinearLayout gridHolder;

        public ListItemViewHolder(View view) {
            super(view);
            day = view.findViewById(R.id.day);
            restDaySwitch = view.findViewById(R.id.restDaySwitch);
            excerciseGroupHolder = view.findViewById(R.id.excerciseGroupHolder);//grid layout
            gridHolder = view.findViewById(R.id.gridHolder);
        }
    }

    public class HeaderItemHolder extends RecyclerView.ViewHolder {

        public HeaderItemHolder(View view) {
            super(view);
        }
    }

}
