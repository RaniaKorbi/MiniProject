package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;


import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.miniproject.databinding.ActivityCinemaBinding;

public class CinemaActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCinemaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCinemaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    }



}