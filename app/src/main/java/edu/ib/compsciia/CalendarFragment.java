package edu.ib.compsciia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class CalendarFragment extends Fragment {


    private ActivityViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_calendar, container, false);
        Button btnLifeForm = (Button) view.findViewById(R.id.btnAddLifeForm);
        Button btnSchedule = (Button) view.findViewById(R.id.btnAddSchedule);
        btnLifeForm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onAddLifeFormClick(view);
            }
        });
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onAddScheduleClick(view);
            }
        });
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void onAddLifeFormClick(View view) {
        Navigation.findNavController(view).navigate(R.id.AddLifeFormFragment);
    }
    public void onAddScheduleClick(View view) {
        Navigation.findNavController(view).navigate(R.id.calendarListFragment);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}