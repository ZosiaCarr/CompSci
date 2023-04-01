package edu.ib.compsciia;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.ib.compsciia.businesslogic.Schedule;
import edu.ib.compsciia.databinding.ScheduleItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyScheduleRecyclerViewAdapter extends RecyclerView.Adapter<MyScheduleRecyclerViewAdapter.ViewHolder> {

    private final List<Schedule> mValues;
    private final ScheduleFragment fragment;
    public MyScheduleRecyclerViewAdapter(List<Schedule> items, ScheduleFragment fragment) {
        mValues = items;
        this.fragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(ScheduleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        final public ImageView mDelete;
        final public ImageView mEdit;
        public Schedule mItem;

        public ViewHolder(ScheduleItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            mDelete = binding.delete;
            mEdit = binding.edit;
            mDelete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    fragment.onDelete(v, mItem);
                }
            });
            mEdit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    fragment.onEdit(v, mItem);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}