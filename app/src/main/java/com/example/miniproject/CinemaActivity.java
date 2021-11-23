package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.miniproject.Entities.CinemaEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import androidx.lifecycle.Observer;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniproject.databinding.ActivityCinemaBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CinemaActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private AppBarConfiguration appBarConfiguration;
    private ActivityCinemaBinding binding;
    private CinemaEventViewModel cinemaEventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityCinemaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecyclerView recyclerView = findViewById(R.id.cinema_recyclerview);
        final CinemaEventListAdapter adapter = new CinemaEventListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cinemaEventViewModel = ViewModelProviders.of(this).get(CinemaEventViewModel.class);
        cinemaEventViewModel.getAllWords().observe(this, new Observer<List<CinemaEvent>>() {
            @Override
            public void onChanged(@Nullable final List<CinemaEvent> events) {
                // Update the cached copy of the words in the adapter.
                adapter.setEvents(events);
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(CinemaActivity.this, NewEventActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        CinemaEvent event = adapter.getEventAtPosition(position);
                        Toast.makeText(CinemaActivity.this, "Deleting " +
                                event.getMCinemaEvent(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        cinemaEventViewModel.deleteWord(event);
                    }
                });

        helper.attachToRecyclerView(recyclerView);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            CinemaEvent word = new CinemaEvent(data.getStringExtra(NewEventActivity.EXTRA_REPLY));
            cinemaEventViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}