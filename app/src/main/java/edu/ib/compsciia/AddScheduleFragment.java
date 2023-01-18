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
    //Gets the fields from the form
    //Get Radio Buttons
    CheckBox cbMonday = null;
    CheckBox cbTuesday = null;
    CheckBox cbWednesday = null;
    CheckBox cbThursday = null;
    CheckBox cbFriday = null;
    CheckBox cbSaturday = null;
    CheckBox cbSunday = null;
    EditText txtTime = null;
    EditText txtDescription = null;
    TextView lblActivity = null;
    TextView lblDays = null;

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
        //Gets the fields from the form
        //Get Radio Buttons
        cbMonday = (CheckBox) view.findViewById(R.id.cbMonday);
        cbTuesday = (CheckBox) view.findViewById(R.id.cbTuesday);
         cbWednesday = (CheckBox) view.findViewById(R.id.cbWednesday);
         cbThursday = (CheckBox) view.findViewById(R.id.cbThursday);
         cbFriday = (CheckBox) view.findViewById(R.id.cbFriday);
         cbSaturday = (CheckBox) view.findViewById(R.id.cbSaturday);
         cbSunday = (CheckBox) view.findViewById(R.id.cbSunday);
         txtTime = (EditText)view.findViewById(R.id.txtScheduleTime);
         txtDescription = (EditText)view.findViewById(R.id.txtShortDescription);
         lblActivity = (TextView)view.findViewById(R.id.lblAtivitiesText);
         lblDays = (TextView) view.findViewById(R.id.lblDays);

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


        //Gets the needed values
        String DescriptionValue = txtDescription.getText().toString();
        String timeValue = txtTime.getText().toString();

        //Valadates all feilds have been filled in
        //Gives an error if the user does not input a description of the schedule
        boolean hasError = false;
        if (DescriptionValue.length() == 0) {
            txtDescription.setError("Required");
            hasError = true;
        }

        //Gives an error if the user does not input the time for the schedule
        if (timeValue.length() == 0) {
            txtTime.setError("Required");
            hasError = true;
        }
        //Gives an error if the user does not add any activities
        if (editItem.getActivities().size() == 0) {
            lblActivity.setError("You need at least one activity");
            hasError = true;
        }

        //Gives an error if the user does not select at least one day
        if (!cbMonday.isChecked() && !cbTuesday.isChecked() && !cbWednesday.isChecked() &&
                !cbThursday.isChecked() && !cbFriday.isChecked() &&
                !cbSaturday.isChecked() && !cbSunday.isChecked()) {
            lblDays.setError("You need at least one day");
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

        //Gets the needed values
        EditText txtDescription = (EditText)view.findViewById(R.id.txtShortDescription);
        txtDescription.setText(editItem.getShortDescription());
    }

}