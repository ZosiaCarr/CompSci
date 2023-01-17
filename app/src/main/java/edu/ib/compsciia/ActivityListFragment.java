package edu.ib.compsciia;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ib.compsciia.businesslogic.Activity;
import edu.ib.compsciia.businesslogic.AppViewModel;
import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Schedule;

/**
 * A fragment representing a list of Items.
 */
public class ActivityListFragment extends Fragment { ;

    private AppViewModel viewModel;
    private Schedule mySchedule;

    public ActivityListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_list, container, false);

        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);

        mySchedule = viewModel.getSelectedSchedule().getValue();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyActivityListRecyclerViewAdapter(mySchedule.getActivities(),this));
        }
        return view;
    }

    public void onEdit(View v, Activity a)
    {
        viewModel.setSelectedActivity(a);
        Navigation.findNavController(v).navigate(R.id.action_addScheduleFragment_to_addActivityFragment);
    }
    public void onDelete(View v, Activity a)
    {
        mySchedule.removeActivity(a);
        LifeFormManager.getManager().persist();
    }

}