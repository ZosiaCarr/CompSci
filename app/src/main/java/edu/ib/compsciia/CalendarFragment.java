package edu.ib.compsciia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class CalendarFragment extends Fragment {


    private ActivityViewModel mViewModel;

    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void onAddLifeFormClick(View view) {
        Navigation.findNavController(view).navigate(R.id.AddLifeFormFragment);
    }
    public void onAddScheduleClick(View view) {
        Navigation.findNavController(view).navigate(R.id.addScheduleFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}