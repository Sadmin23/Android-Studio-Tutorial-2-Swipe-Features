package com.example.androidswipegesturetutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.RecyclerViewHolder> {

    // creating a variable for our array list and context.
    private ArrayList<CategoryData> categoryDataArrayList;
    private Context mcontext;

    // creating a constructor class.
    public CategoryAdapter(ArrayList<CategoryData> recyclerDataArrayList, Context mcontext) {
        this.categoryDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        CategoryData recyclerData = categoryDataArrayList.get(position);
        holder.categoryName.setText(recyclerData.getCategoryName());
    }

    @Override
    public int getItemCount() {

        return categoryDataArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryName;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.idCategoryName);
        }
    }
}
