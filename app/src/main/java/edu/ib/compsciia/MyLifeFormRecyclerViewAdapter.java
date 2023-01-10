package edu.ib.compsciia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ib.compsciia.businesslogic.LifeForm;
import edu.ib.compsciia.databinding.LifeFormItemBinding;
import edu.ib.compsciia.placeholder.PlaceholderContent.PlaceholderItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyLifeFormRecyclerViewAdapter extends RecyclerView.Adapter<MyLifeFormRecyclerViewAdapter.ViewHolder> {

    private final List<LifeForm> mValues;
    private final LifeFormFragment fragment;
    public MyLifeFormRecyclerViewAdapter(List<LifeForm> items,LifeFormFragment fragment ) {
        mValues = items;
        this.fragment= fragment;
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

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public final TextView mIdView;
        final public TextView mContentView;
        final public ImageView mDelete;
        final public ImageView mEdit;
        public LifeForm mItem;

        public ViewHolder(LifeFormItemBinding binding) {
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

        @Override
        public void onClick(View view) {

        }
    }
}