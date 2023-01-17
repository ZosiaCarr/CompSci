package edu.ib.compsciia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import edu.ib.compsciia.businesslogic.Activity;
import edu.ib.compsciia.businesslogic.AppViewModel;
import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Schedule;

public class AddScheduleFragment extends Fragment {
    private Schedule editItem;
    private AppViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_schedule, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
        LiveData<Schedule> cachedEditItem = viewModel.getSelectedSchedule();

        editItem = cachedEditItem.getValue();

        if(editItem != null) {
            SetValuesFromEditForm(view);

        }
        else
        {
            editItem = new Schedule();
            viewModel.setSelectedSchedule(editItem);
        }

        Button btnSaveSchedule = (Button) view.findViewById(R.id.btnSaveSchedule);
        ImageButton btnAddActivity = (ImageButton) view.findViewById(R.id.btnAddActivity);
        btnSaveSchedule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSave(view);
            }
        });
        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Activity a = new Activity();
                viewModel.setSelectedActivity(a);
                Navigation.findNavController(view).navigate(R.id.action_addScheduleFragment_to_addActivityFragment);
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
        //Gets the fields from the form
        //Get Radio Buttons
        CheckBox cbMonday = (CheckBox) view.findViewById(R.id.cbMonday);
        CheckBox cbTuesday = (CheckBox) view.findViewById(R.id.cbTuesday);
        CheckBox cbWednesday = (CheckBox) view.findViewById(R.id.cbWednesday);
        CheckBox cbThursday = (CheckBox) view.findViewById(R.id.cbThursday);
        CheckBox cbFriday = (CheckBox) view.findViewById(R.id.cbFriday);
        CheckBox cbSaturday = (CheckBox) view.findViewById(R.id.cbSaturday);
        CheckBox cbSunday = (CheckBox) view.findViewById(R.id.cbSunday);
        EditText txtTime = (EditText)view.findViewById(R.id.txtScheduleTime);
        EditText txtDescription = (EditText)view.findViewById(R.id.txtShortDescription);
        TextView lblActivity = (TextView)view.findViewById(R.id.lblAtivitiesText);
        TextView lblDays = (TextView) view.findViewById(R.id.lblDays);

        //Gets the needed values
        String DescriptionValue = txtDescription.getText().toString();
        String timeValue = txtTime.getText().toString();

        //Gives an error if the user does not input the name of their life form
        //Valadate all feilds have been filled in
        boolean hasError = false;
        if (DescriptionValue.length() == 0) {
            txtDescription.setError("Required");
            hasError = true;
        }

        //Gives an error if the user does not input the species of their life form
        if (timeValue.length() == 0) {
            txtTime.setError("Required");
            hasError = true;
        }
        if (editItem.getActivities().size() == 0) {
            lblActivity.setError("You need at least one Activity");
            hasError = true;
        }

        if (!cbMonday.isChecked() && !cbTuesday.isChecked() && !cbWednesday.isChecked() &&
                !cbThursday.isChecked() && !cbFriday.isChecked() &&
                !cbSaturday.isChecked() && !cbSunday.isChecked()) {
            lblDays.setError("You must have one day selected");
            hasError = true;
        }

        //Saves life form
        if (!hasError) {

            editItem.setShortDescription(DescriptionValue);
            //Saves the description of the life form

            LifeFormManager.getManager().persist();
            Navigation.findNavController(view).navigate(R.id.calendarFragment);
        }
    }

    //For editing
    public void SetValuesFromEditForm(View view) {
        //Gets the fields from the form
        CheckBox cbMonday = (CheckBox) view.findViewById(R.id.cbMonday);
        CheckBox cbTuesday = (CheckBox) view.findViewById(R.id.cbTuesday);
        CheckBox cbWednesday = (CheckBox) view.findViewById(R.id.cbWednesday);
        CheckBox cbThursday = (CheckBox) view.findViewById(R.id.cbThursday);
        CheckBox cbFriday = (CheckBox) view.findViewById(R.id.cbFriday);
        CheckBox cbSaturday = (CheckBox) view.findViewById(R.id.cbSaturday);
        CheckBox cbSunday = (CheckBox) view.findViewById(R.id.cbSunday);
        EditText time = (EditText)  view.findViewById((R.id.txtScheduleTime));


        //Gets the needed values
        EditText txtDescription = (EditText)view.findViewById(R.id.txtShortDescription);
        txtDescription.setText(editItem.getShortDescription());
    }

}