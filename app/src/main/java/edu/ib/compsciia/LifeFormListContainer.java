package edu.ib.compsciia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class LifeFormListContainer extends Fragment {


    public LifeFormListContainer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_life_form_list_container, container, false);
       // When Add Life Form button is pressed, go to the add life form fragment
        Button btnAddLifForm = (Button) view.findViewById(R.id.btnAddLifeForm);
        btnAddLifForm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_global_AddLifeFormFragment);
            }
        });

        return view;

    }
}