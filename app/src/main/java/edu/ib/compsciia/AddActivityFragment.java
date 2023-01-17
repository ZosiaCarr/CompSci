package edu.ib.compsciia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import edu.ib.compsciia.businesslogic.Activity;
import edu.ib.compsciia.businesslogic.AppViewModel;
import edu.ib.compsciia.businesslogic.LifeForm;
import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Schedule;

public class AddActivityFragment extends Fragment {
    private Activity editItem;
    private AppViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_activity, container, false);
        ChipGroup chipGroup =  (ChipGroup) view.findViewById(R.id.cLifeForms);
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
        for(LifeForm lf : LifeFormManager.getManager().getLifeForms()) {
            Chip chip = new Chip(getContext());
            chip.setText(lf.getName());
            //chip.setChipBackgroundColorResource(R.color.colorAccent);
            chip.setCloseIconVisible(true);
            chip.setTextColor(getResources().getColor(R.color.white));

            chipGroup.addView(chip);

        }

        LiveData<Activity> cachedEditItem = viewModel.getSelectedActivity();
        editItem = cachedEditItem.getValue();

        if(editItem != null) {
            SetValuesFromEditForm(view);
            viewModel.selectLifeform(null);
        }
        else
        {
            editItem = new Activity();
        }

        Button btnSaveSchedule = (Button) view.findViewById(R.id.btnSaveActivity);

        btnSaveSchedule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSave(view);
            }
        });

        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    public void onSave(View view) {
        EditText txtDescription = (EditText)view.findViewById(R.id.txtDescription);
        boolean hasError = false;

        //Adds changes to Schedule, the schedule's job to persist
        if (!hasError) {
            editItem.setDescription(txtDescription.getText().toString());
            Schedule mySchedule = viewModel.getSelectedSchedule().getValue();
            if(!mySchedule.getActivities().contains(editItem))
            {
                mySchedule.addActivity(editItem);
            }
            Navigation.findNavController(view).navigate(R.id.addScheduleFragment);
        }
    }

    //For editing
    public void SetValuesFromEditForm(View view) {
    }

}