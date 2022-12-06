package edu.ib.compsciia;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.ib.compsciia.businesslogic.LifeForm;
import edu.ib.compsciia.placeholder.PlaceholderContent.PlaceholderItem;
import edu.ib.compsciia.databinding.LifeFormItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyLifeFormRecyclerViewAdapter extends RecyclerView.Adapter<MyLifeFormRecyclerViewAdapter.ViewHolder> {

    private final List<LifeForm> mValues;

    public MyLifeFormRecyclerViewAdapter(List<LifeForm> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LifeFormItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public LifeForm mItem;

        public ViewHolder(LifeFormItemBinding binding) {
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