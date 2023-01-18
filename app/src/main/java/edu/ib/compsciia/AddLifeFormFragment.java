package edu.ib.compsciia;

import java.util.Date;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import edu.ib.compsciia.businesslogic.AppViewModel;
import edu.ib.compsciia.businesslogic.LifeForm;
import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Pet;
import edu.ib.compsciia.businesslogic.Plant;

public class AddLifeFormFragment extends Fragment {
    private AppViewModel viewModel;

    private LifeForm editItem = null;

    CalendarView birthDay = null;
    Button btnLifeForm = null;
    RadioButton rbPet = null;
    RadioButton rbPlant = null;
    TextView txtBDay = null;

    AppCompatEditText nameField = null;
    AppCompatEditText speciesField = null;
    AppCompatEditText descriptionField = null;
    CalendarView calendarView = null;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);

        LiveData<LifeForm> editForm = viewModel.getSelectedLifeForm();
        editItem = editForm.getValue();;
        View view =  inflater.inflate(R.layout.fragment_add_life_form, container, false);
        if(editItem != null) {
            SetValuesFromEditForm(view);
            viewModel.selectLifeform(null);
        }

        btnLifeForm = (Button) view.findViewById(R.id.btnSaveLifeForm);
        rbPet = (RadioButton) view.findViewById(R.id.radioBtnPet);
        rbPlant = (RadioButton) view.findViewById(R.id.radioBtnPlant);
        birthDay = (CalendarView) view.findViewById(R.id.birthDate);
        txtBDay = (TextView) view.findViewById(R.id.txtBirthday);
        nameField = view.findViewById(R.id.txtLFName);
        speciesField = view.findViewById(R.id.txtSpecies);
        descriptionField = view.findViewById(R.id.txtDescripton);

        birthDay.setDate(new java.util.Date().getTime());
        birthDay.setVisibility(View.GONE);
        txtBDay.setVisibility(View.GONE);
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
    //Makes pet b-day calender visible when user selects pet
    public void onPetClick(View view) {
        birthDay.setVisibility(View.VISIBLE);
        txtBDay.setVisibility(View.VISIBLE);

    }
    //Makes pet b-day calender invisible when user selects plant
    public void onPlantClick(View view) {
        birthDay.setVisibility(View.GONE);
        txtBDay.setVisibility(View.GONE);
    }
    public void onSave(View view) {

        //Gets the needed values from user inputs
        String nameValue = nameField.getText().toString();
        String speciesValue = speciesField.getText().toString();
        String descriptionValue = descriptionField.getText().toString();

        //Gives an error if the user does not input the name of their life form
        boolean hasError = false;
        if(nameValue.length() == 0)
        {
            nameField.setError("Required");
            hasError = true;
        }

        //Gives an error if the user does not input the species of their life form
        if(speciesValue.length() == 0)
        {
            speciesField.setError("Required");
            hasError = true;
        }

        //Gives an error if the user does not input a description of their life form
        if(descriptionValue.length() == 0)
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