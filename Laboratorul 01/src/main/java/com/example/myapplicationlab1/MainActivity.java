package com.example.myapplicationlab1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// Găsim elementele din layout
        TextView textView = findViewById(R.id.textView);
        Button buttonChangeColor = findViewById(R.id.buttonChangeColor);

// Lista de culori posibile
        int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.BLACK};
        int[] index = {0}; // Variabilă pentru a reține culoarea curentă

// Eveniment la apăsarea butonului
        buttonChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Schimbă culoarea textului
                textView.setTextColor(colors[index[0]]);
// Afișează un mesaj Toast
                Toast.makeText(MainActivity.this, "Culoarea s-a schimbat!", Toast.LENGTH_SHORT).show();
// Trecem la următoarea culoare
                index[0] = (index[0] + 1) % colors.length;
            }
        });
    }
}