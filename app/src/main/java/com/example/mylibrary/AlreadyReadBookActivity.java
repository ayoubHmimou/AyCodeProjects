package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class AlreadyReadBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.booksRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this, "alreadyRead");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Checking if the already read books list is empty if so we fill with an example
        if(Utils.getInstance(this).getAlreadyReadBooks().isEmpty()){
            Toast.makeText(this, "You have no books added to this list", Toast.LENGTH_LONG).show();
        }else {
            adapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /* a method used to when the back button is pressed in this case if the user clicks on that button
       while on the already read books activity we will navigate him the the main activity
    */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AlreadyReadBookActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//the use of the this line is to clear the history of the stacks because if we don't and the user clicks on the back button while on the main activity it will bring him back to the already read books activity
        startActivity(intent);
    }
}