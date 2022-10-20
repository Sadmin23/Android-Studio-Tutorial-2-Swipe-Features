package com.example.androidswipegesturetutorial;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView CategoryRV;
    private ArrayList<CategoryData> CategoryList;
    private CategoryAdapter CategoryAdapter;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoryRV = findViewById(R.id.idCategory);
        swipeRefreshLayout = findViewById(R.id.swipelayout);

        CategoryList = new ArrayList<>();

        CategoryList.add(new CategoryData("Electronics"));
        CategoryList.add(new CategoryData("Women's Clothing"));
        CategoryList.add(new CategoryData("Men's Clothing"));
        CategoryList.add(new CategoryData("Jewlery"));
        CategoryList.add(new CategoryData("Home and Lifestyle"));
        CategoryList.add(new CategoryData("Food and Groceries"));
        CategoryList.add(new CategoryData("Sports and Fitness"));
        CategoryList.add(new CategoryData("Others"));


        CategoryAdapter = new CategoryAdapter(CategoryList, this);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        CategoryRV.setLayoutManager(manager);

        CategoryRV.setAdapter(CategoryAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                CategoryData deletedCategory = CategoryList.get(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                CategoryList.remove(viewHolder.getAdapterPosition());
                CategoryAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Snackbar.make(CategoryRV, deletedCategory.getCategoryName(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CategoryList.add(position, deletedCategory);
                        CategoryAdapter.notifyItemInserted(position);
                    }
                }).show();
            }
        }).attachToRecyclerView(CategoryRV);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
