package edu.ib.compsciia;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.ib.compsciia.businesslogic.Activity;
import edu.ib.compsciia.databinding.ActivityListItemBinding;

import java.util.List;

public class MyActivityListRecyclerViewAdapter extends RecyclerView.Adapter<MyActivityListRecyclerViewAdapter.ViewHolder> {

    private final List<Activity> mValues;
    private ActivityListFragment fragment;

    public MyActivityListRecyclerViewAdapter(List<Activity> items, ActivityListFragment fragment) {
        this.mValues = items;
        this.fragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(ActivityListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).ToString());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mContentView;
        public Activity mItem;
        final public ImageView mDelete;
        final public ImageView mEdit;


        public ViewHolder(ActivityListItemBinding binding) {
            super(binding.getRoot());
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