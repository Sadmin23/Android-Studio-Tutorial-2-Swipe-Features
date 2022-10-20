package com.example.androidswipegesturetutorial;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // creating a variable for recycler view,
    // array list and adapter class.
    private RecyclerView CategoryRV;
    private ArrayList<CategoryData> CategoryList;
    private CategoryAdapter CategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our variables.
        CategoryRV = findViewById(R.id.idCategory);

        // creating new array list.
        CategoryList = new ArrayList<>();

        // in below line we are adding data to our array list.
        CategoryList.add(new CategoryData("DSA Course"));
        CategoryList.add(new CategoryData("DSA Course"));
        CategoryList.add(new CategoryData("DSA Course"));
        CategoryList.add(new CategoryData("DSA Course"));

        // initializing our adapter class with our array list and context.
        CategoryAdapter = new CategoryAdapter(CategoryList, this);

        // below line is to set layout manager for our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);

        // setting layout manager for our recycler view.
        CategoryRV.setLayoutManager(manager);

        // below line is to set adapter
        // to our recycler view.
        CategoryRV.setAdapter(CategoryAdapter);

        // on below line we are creating a method to create item touch helper
        // method for adding swipe to delete functionality.
        // in this we are specifying drag direction and position to right
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // this method is called
                // when the item is moved.
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
    }
}
