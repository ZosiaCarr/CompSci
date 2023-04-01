package edu.ib.compsciia;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.ib.compsciia.businesslogic.ScheduleRunDate;
import edu.ib.compsciia.databinding.FragmentCalendarListBinding;

import java.text.SimpleDateFormat;
import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<ScheduleRunDate> mValues;
    private final CalendarListFragment fragment;

    public MyItemRecyclerViewAdapter(List<ScheduleRunDate> items, CalendarListFragment f) {
        mValues = items;
        fragment = f;
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
        public final ImageView mView;

        public ViewHolder(FragmentCalendarListBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            mView = binding.view;

            mView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    fragment.onView(v, mItem.getSchedule());
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}