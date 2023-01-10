package edu.ib.compsciia;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import edu.ib.compsciia.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class LifeFormFragment extends Fragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private AppViewModel viewModel;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LifeFormFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static LifeFormFragment newInstance(int columnCount) {
        LifeFormFragment fragment = new LifeFormFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.life_form_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyLifeFormRecyclerViewAdapter(LifeFormManager.getManager().getLifeForms(),this));
        }
        return view;
    }
}