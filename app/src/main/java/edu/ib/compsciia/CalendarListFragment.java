package edu.ib.compsciia;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Dictionary;
import java.util.List;

import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Schedule;
import edu.ib.compsciia.businesslogic.ScheduleRunDate;
import edu.ib.compsciia.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class CalendarListFragment extends Fragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CalendarListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_list_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
                //recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            endDate.add(Calendar.WEEK_OF_YEAR,2);
            List<ScheduleRunDate> schedules = LifeFormManager.getManager().getScheduleRunDates(startDate,endDate);
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(schedules));
        }
        return view;
    }
}