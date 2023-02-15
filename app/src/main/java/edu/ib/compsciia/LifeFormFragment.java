package edu.ib.compsciia;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ib.compsciia.businesslogic.AppViewModel;
import edu.ib.compsciia.businesslogic.LifeForm;
import edu.ib.compsciia.businesslogic.LifeFormManager;

/**
 * A fragment representing a list of Items.
 */
public class LifeFormFragment extends Fragment {

    private AppViewModel viewModel;
    private RecyclerView listContainerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LifeFormFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
    }
    public void onEdit(View v, LifeForm lf)
    {
        viewModel.selectLifeform(lf);
        Navigation.findNavController(v).navigate(R.id.AddLifeFormFragment);
    }
    public void onDelete(View v, LifeForm lf)
    {
        LifeFormManager.getManager().removeLifeForm(lf);
        LifeFormManager.getManager().persist();
        bindListData(listContainerView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.life_form_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            listContainerView = (RecyclerView) view;
            bindListData(view);
        }
        return view;
    }
    private void bindListData(View view)
    {
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MyLifeFormRecyclerViewAdapter(LifeFormManager.getManager().getLifeForms(),this));

    }
}