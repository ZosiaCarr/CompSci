package edu.ib.compsciia;

import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.ib.compsciia.businesslogic.LifeForm;
import edu.ib.compsciia.databinding.LifeFormItemBinding;


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
        holder.mContentView.setTextColor(mValues.get(position).getColor());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.lfIcon.setImageResource(mValues.get(position).getIcon());
        }
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
        final public ImageView lfIcon;
        public LifeForm mItem;

        public ViewHolder(LifeFormItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            mDelete = binding.delete;
            mEdit = binding.edit;
            lfIcon = binding.lfIcon;
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