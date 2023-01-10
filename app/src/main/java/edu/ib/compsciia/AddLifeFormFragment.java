package edu.ib.compsciia;

import java.util.Date;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import edu.ib.compsciia.businesslogic.AppViewModel;
import edu.ib.compsciia.businesslogic.LifeForm;
import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Pet;
import edu.ib.compsciia.businesslogic.Plant;

public class AddLifeFormFragment extends Fragment {
    private static final String default_name = "Name";
    private static final String default_species = "Species";
    private static final String default_description = "Species";
    private AppViewModel viewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private LifeForm editItem = null;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);

        LiveData<LifeForm> editForm = viewModel.getSelectedItem();
        editItem = editForm.getValue();;
        View view =  inflater.inflate(R.layout.fragment_add_life_form, container, false);
        if(editItem != null) {
            SetValuesFromEditForm(view);
            viewModel.selectLifeform(null);
        }

        Button btnLifeForm = (Button) view.findViewById(R.id.btnSaveLifeForm);
        Button rbPet = (Button) view.findViewById(R.id.radioBtnPet);
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.birthDate);
        Button rbPlant = (Button) view.findViewById(R.id.radioBtnPlant);

        calendarView.setDate(new java.util.Date().getTime());
        //Wire up events
        btnLifeForm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSave(view);
            }
        });
        rbPet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onPetClick(view);
            }
        });
        rbPlant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onPlantClick(view);
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
    public void onPetClick(View view) {
        CalendarView birthDay = (CalendarView) view.findViewById(R.id.birthDate);
        birthDay.setVisibility(View.VISIBLE);

    }
    public void onPlantClick(View view) {
        CalendarView birthDay = (CalendarView) view.findViewById(R.id.birthDate);
        birthDay.setVisibility(View.GONE);
    }
    public void onSave(View view) {
        //Gets the fields from the form
        RadioButton rbPlant = (RadioButton) view.findViewById(R.id.radioBtnPlant);
        RadioButton rbPet = (RadioButton) view.findViewById(R.id.radioBtnPet);
        AppCompatEditText nameField = view.findViewById(R.id.txtLFName);
        AppCompatEditText speciesField = view.findViewById(R.id.txtSpecies);
        AppCompatEditText descriptionField = view.findViewById(R.id.txtDescripton);
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.birthDate);

        //Gets the needed values
        String nameValue = nameField.getText().toString();
        String speciesValue = speciesField.getText().toString();
        String descriptionValue = descriptionField.getText().toString();

        //Gives an error if the user does not input the name of their life form
        boolean hasError = false;
        if(nameValue.length() == 0 || nameValue.equals(default_name))
        {
            nameField.setError("Required");
            hasError = true;
        }

        //Gives an error if the user does not input the species of their life form
        if(speciesValue.length() == 0 || speciesValue.equals(default_species))
        {
            speciesField.setError("Required");
            hasError = true;
        }

        //Gives an error if the user does not input a description of their life form
        if(descriptionValue.length() == 0 || descriptionValue.equals(default_description))
        {
            descriptionField.setError("Required");
            hasError = true;
        }

        //Saves life form
        if(!hasError)
        {
            if(editItem == null) {
                //If plant
                if (rbPlant.isChecked()) {
                    editItem = new Plant();
                }
                //If pet
                else {

                    editItem = new Pet();
                    editItem.setBirthDay(new Date(calendarView.getDate()));
                }
                LifeFormManager.getManager().addLifeForm(editItem);
            }
            else
            {
                if(editItem instanceof Pet)
                {
                    editItem.setBirthDay(new Date(calendarView.getDate()));
                }
            }
            //Saves the name of the life form
            editItem.setName(nameValue);
            //Saves the description of the life form
            editItem.setDescription(descriptionValue);
            //Saves the species of the life form
            editItem.setSpecies(speciesValue);
            //Adds the life form to the life form manager
            LifeFormManager.getManager().persist();
            Navigation.findNavController(view).navigate(R.id.lifeFormFragment);
        }
    }
    public void SetValuesFromEditForm(View view) {
        //Gets the fields from the form
        RadioButton rbPlant = (RadioButton) view.findViewById(R.id.radioBtnPlant);
        RadioButton rbPet = (RadioButton) view.findViewById(R.id.radioBtnPet);
        AppCompatEditText nameField = view.findViewById(R.id.txtLFName);
        AppCompatEditText speciesField = view.findViewById(R.id.txtSpecies);
        AppCompatEditText descriptionField = view.findViewById(R.id.txtDescripton);
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.birthDate);

        //Gets the needed values
        nameField.setText(editItem.getName());
        speciesField.setText(editItem.getSpecies());
        descriptionField.setText(editItem.getDescription());

        if(editItem instanceof Pet)
        {
            rbPet.setChecked(true);
            onPetClick(view);
            calendarView.setDate(editItem.getBirthDay().getTime());
        }
        else
        {
            rbPlant.setChecked(true);
            onPlantClick(view);
        }
    }

}