package edu.ib.compsciia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.print.PrintHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import edu.ib.compsciia.businesslogic.AppViewModel;
import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.ScheduleRunDate;


public class CalendarFragment extends Fragment {
    private AppViewModel viewModel;
    private EditText additionalInformation;
    private Button btnLifeForm;
    private Button btnSchedule;
    private Button btnPrint;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_calendar, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
        btnLifeForm = (Button) view.findViewById(R.id.btnAddLifeForm);
        btnSchedule = (Button) view.findViewById(R.id.btnAddSchedule);
        btnPrint = (Button) view.findViewById(R.id.btnPrint);
        additionalInformation = (EditText) view.findViewById(R.id.txtAdditionalDirections);
        btnLifeForm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onAddLifeFormClick(view);
            }
        });
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onAddScheduleClick(view);
            }
        });
        btnPrint.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                printPage(v);
            }
        });

        return view;
    }
    public void printPage(View view)
    {;
        btnPrint.setVisibility(View.GONE);
        btnSchedule.setVisibility(View.GONE);
        btnLifeForm.setVisibility(View.GONE);
        LifeFormManager.getManager().setAdditionalInstructions(additionalInformation.getText().toString());
        View v = this.getView();
        Bitmap bmp = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        v.draw(c);
        btnPrint.setVisibility(View.VISIBLE);
        btnSchedule.setVisibility(View.VISIBLE);
        btnLifeForm.setVisibility(View.VISIBLE);

        PrintHelper photoPrinter = new PrintHelper(getActivity());
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        photoPrinter.printBitmap("layout.png", bmp);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void onAddLifeFormClick(View view) {
        Navigation.findNavController(view).navigate(R.id.AddLifeFormFragment);
    }
    public void onAddScheduleClick(View view) {
        viewModel.setSelectedSchedule(null);
        Navigation.findNavController(view).navigate(R.id.addScheduleFragment);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}