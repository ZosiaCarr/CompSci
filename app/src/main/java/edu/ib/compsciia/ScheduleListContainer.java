package edu.ib.compsciia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import edu.ib.compsciia.businesslogic.AppViewModel;


public class ScheduleListContainer extends Fragment {
    private AppViewModel viewModel;

    public ScheduleListContainer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule_list_container, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
        Button btnAddSchedule = (Button) view.findViewById(R.id.btnAddSchedule);
        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewModel.setSelectedSchedule(null);
                Navigation.findNavController(view).navigate(R.id.addScheduleFragment);
            }
        });

        return view;
    }
}