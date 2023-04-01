package edu.ib.compsciia;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.print.PrintHelper;
import androidx.recyclerview.widget.GridLayoutManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Schedule;
import edu.ib.compsciia.businesslogic.ScheduleRunDate;


public class CalendarFragmentPrint extends Fragment {
    private TextView txtAdditionalDirections;
    private TextView txtSchedules;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_calendar_print, container, false);
        txtAdditionalDirections = (TextView) view.findViewById(R.id.txtAdditionalDirections);
        txtSchedules = (TextView) view.findViewById(R.id.txtSchedules);
        txtAdditionalDirections.setText(LifeFormManager.getManager().getAdditionalInstructions());
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.WEEK_OF_YEAR,2);
        String buildDisplay = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        List<ScheduleRunDate> schedules = LifeFormManager.getManager().getScheduleRunDates(startDate,endDate);
        for(ScheduleRunDate srd: schedules)
        {
            buildDisplay += sdf.format(srd.getDate().getTime()) + "\t" + srd.getSchedule().toString() + "\n";
        }
        txtSchedules.setText(buildDisplay);
        return view;
    }
    private void ShowPrint()
    {
        View v = this.getView();
        Bitmap bmp = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        v.draw(c);

        PrintHelper photoPrinter = new PrintHelper(getActivity());
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        photoPrinter.printBitmap("layout.png", bmp);

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
       // ShowPrint();
    }



}