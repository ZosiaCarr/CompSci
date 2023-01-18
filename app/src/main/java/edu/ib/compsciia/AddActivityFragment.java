package edu.ib.compsciia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import edu.ib.compsciia.businesslogic.Activity;
import edu.ib.compsciia.businesslogic.AppViewModel;
import edu.ib.compsciia.businesslogic.LifeForm;
import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Schedule;

public class AddActivityFragment extends Fragment {
    private Activity editItem;
    private AppViewModel viewModel;
    private List<checkboxAndValue> lifeFormsCheckboxes = new ArrayList<checkboxAndValue>();
    //form fields
    EditText txtDescription;


    class checkboxAndValue  {
        public checkboxAndValue(CheckBox cb, LifeForm lf)
        {
            this.checkBox = cb;
            this.lifeForm = lf;
        }
        public CheckBox getCheckBox() {
            return checkBox;
        }

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public LifeForm getLifeForm() {
            return lifeForm;
        }

        public void setLifeForm(LifeForm lifeForm) {
            this.lifeForm = lifeForm;
        }

        private CheckBox checkBox;
        private LifeForm lifeForm;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_activity, container, false);
        //get the data we are editing
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);

        //get the fields
        txtDescription = (EditText)view.findViewById(R.id.txtDescription);

        //build the checkboxes
        for(LifeForm lf : LifeFormManager.getManager().getLifeForms()) {
            addCheckBox(view,lf);

        }

        //get the edit data from the view model
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

    public void addCheckBox(View view,LifeForm lf)
    {
        LinearLayout layout = view.findViewById(R.id.root_element);
        CheckBox cb = new CheckBox(getContext());
        cb.setText(lf.getName());
        layout.addView(cb,1);
        lifeFormsCheckboxes.add(new checkboxAndValue(cb,lf));
   }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    public void onSave(View view) {

        boolean hasError = false;
        //TODO: Add validation
        //Adds changes to Schedule, the schedule's job to persist
        if (!hasError) {
            editItem.setDescription(txtDescription.getText().toString());

            for(checkboxAndValue cvb : lifeFormsCheckboxes)
            {
               if(cvb.checkBox.isChecked())
               {
                   editItem.addLifeForm(cvb.lifeForm);
               }
               else
               {
                   editItem.removeLifeForm(cvb.lifeForm);
               }
            }

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
        txtDescription.setText(editItem.getDescription());

        for(checkboxAndValue cvb : lifeFormsCheckboxes)
        {
            if(editItem.getLifeForms().contains(cvb.lifeForm))
            {
                cvb.checkBox.setChecked(true);
            }
        }
    }

}