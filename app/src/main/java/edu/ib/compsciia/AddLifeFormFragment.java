package edu.ib.compsciia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;

import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Pet;

public class AddLifeFormFragment extends Fragment {
    private static final String default_name = "Name";
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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onSave(View view) {
        AppCompatEditText name = view.findViewById(R.id.lifeFormName);
        String nameValue = name.getText().toString();
        boolean hasError = false;
        if(nameValue.length() == 0 || nameValue.equals(default_name))
        {
            name.setError("Required");
            hasError = true;
        }
        if(!hasError)
        {
            //IF PET
            Pet pet = new Pet();
            pet.setName(nameValue);
            LifeFormManager.getManager().addLifeForm(pet);
        }
    }

}