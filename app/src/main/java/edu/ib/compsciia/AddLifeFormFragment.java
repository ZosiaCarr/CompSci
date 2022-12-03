package edu.ib.compsciia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Pet;
import edu.ib.compsciia.businesslogic.Plant;

public class AddLifeFormFragment extends Fragment {
    private static final String default_name = "Name";
    private static final String default_species = "Species";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_life_form, container, false);
        Button btnLifeForm = (Button) view.findViewById(R.id.btnSaveLifeForm);
        btnLifeForm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSave(view);
            }
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnPet = (RadioButton) view.findViewById(R.id.radioBtnPet);
        Button btnPlant = (RadioButton) view.findViewById(R.id.radioBtnPlant);




    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onSave(View view) {
        //Gives an error if the user does not input the name of their life form
        AppCompatEditText name = view.findViewById(R.id.txtLFName);
        String nameValue = name.getText().toString();
        boolean hasError = false;
        if(nameValue.length() == 0 || nameValue.equals(default_name))
        {
            name.setError("Required");
            hasError = true;
        }

        //Gives an error if the user does not input the species of their life form
        AppCompatEditText species = view.findViewById(R.id.txtSpecies);
        String speciesValue = species.getText().toString();
        if(speciesValue.length() == 0 || speciesValue.equals(default_species))
        {
            species.setError("Required");
            hasError = true;
        }




        //Saves life form name
        if(!hasError)
        {
            //IF PET
            Pet pet = new Pet();
            pet.setName(nameValue);
            LifeFormManager.getManager().addLifeForm(pet);

            //IF PLANT I DON'T THINK THIS SHOULD BE HERE
            Plant plant = new Plant();
            plant.setName(nameValue);
            LifeFormManager.getManager().addLifeForm(plant);
        }
    }
}