package edu.ib.compsciia;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ib.compsciia.businesslogic.AppViewModel;
import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Schedule;


/**
 * A fragment representing a list of Items.
 */
public class ScheduleFragment extends Fragment {


    private AppViewModel viewModel;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ScheduleFragment() {
    }

    private RecyclerView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schedule_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            listView = (RecyclerView) view;
            bindList();
        }

        return view;
    }

    private void bindList()
    {
        Context context = listView.getContext();
        RecyclerView recyclerView = (RecyclerView) listView;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.setAdapter(new MyScheduleRecyclerViewAdapter(LifeFormManager.getManager().getSchedules(), this));

    }

    public void onEdit(View v, Schedule s)
    {
        viewModel.setSelectedSchedule(s);
        Navigation.findNavController(v).navigate(R.id.addScheduleFragment);
    }
    public void onDelete(View v, Schedule s)    
    {
        LifeFormManager.getManager().removeSchedule(s);
        LifeFormManager.getManager().persist();
        bindList();
    }

}