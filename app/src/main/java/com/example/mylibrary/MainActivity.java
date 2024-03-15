package com.example.mylibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks, btnAlreadyRead, btnWantToRead, btnCurrentlyReading, btnFavorite, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnAllBooks.setOnClickListener(e->{
            Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
            startActivity(intent);
        });

        btnAlreadyRead.setOnClickListener(e->{
            Intent intent = new Intent(MainActivity.this, AlreadyReadBookActivity.class);
            startActivity(intent);
        });

        btnCurrentlyReading.setOnClickListener(e->{
            Intent intent = new Intent(MainActivity.this, CurrentlyReadBookActivity.class);
            startActivity(intent);
        });

        btnWantToRead.setOnClickListener(e->{
            Intent intent = new Intent(MainActivity.this, WantToReadBookActivity.class);
            startActivity(intent);
        });

        btnFavorite.setOnClickListener(e->{
            Intent intent = new Intent(MainActivity.this, FavoriteBookActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(e->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.app_name));
            builder.setMessage("Designed and Developed by Ayoub at AyCode.org\n" +
                    "Check my website for more awesome applications:");
            builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                    intent.putExtra("url","https://twitter.com/Ayoub_KOP");
                    startActivity(intent);
                }
            });

            builder.create().show();
        });

        //creating an instance of the Utils class so we avoid redundancy(don't have to use the get instance every time we call an arraylist) and null pointer exception
        Utils.getInstance(this);
    }

    private void initViews() {
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWantToRead = findViewById(R.id.btnWantToRead);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnAbout = findViewById(R.id.btnAbout);
    }
}