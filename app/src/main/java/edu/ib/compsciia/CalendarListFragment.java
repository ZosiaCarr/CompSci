package edu.ib.compsciia;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.List;

import edu.ib.compsciia.businesslogic.AppViewModel;
import edu.ib.compsciia.businesslogic.LifeForm;
import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Schedule;
import edu.ib.compsciia.businesslogic.ScheduleRunDate;

/**
 * A fragment representing a list of Items.
 */
public class CalendarListFragment extends Fragment {

    private AppViewModel viewModel;

    public CalendarListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);

    }

    public void onView(View v, Schedule s)
    {
        viewModel.setSelectedSchedule(s);
        Navigation.findNavController(v).navigate(R.id.addScheduleFragment);
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
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(schedules, this));
        }
        return view;
    }
}