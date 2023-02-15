package edu.ib.compsciia;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.ib.compsciia.businesslogic.Schedule;
import edu.ib.compsciia.businesslogic.ScheduleRunDate;
import edu.ib.compsciia.placeholder.PlaceholderContent.PlaceholderItem;
import edu.ib.compsciia.databinding.FragmentCalendarListBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<ScheduleRunDate> mValues;

    public MyItemRecyclerViewAdapter(List<ScheduleRunDate> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentCalendarListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(sdf.format(mValues.get(position).getDate().getTime()));
        holder.mContentView.setText(mValues.get(position).getSchedule().toString());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public ScheduleRunDate mItem;

        public ViewHolder(FragmentCalendarListBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}