package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookID";
    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite, btnLink;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

      /*
      * get the book Id using intent
      * check if the intent is not null
      * check if the book Id does exist
      * check if the method returns !=null
      * set the data
      * */
      Intent intent = getIntent();
      if(intent != null){
          int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
          if(bookId != -1){
              Book incomingBook = Utils.getInstance(this).getBookById(bookId);
              if(incomingBook != null){
                  setData(incomingBook);

                  handleAlreadyRead(incomingBook);
                  handleWantToReadBooks(incomingBook);
                  handleCurrentlyReadingBooks(incomingBook);
                  handleFavoritesBooks(incomingBook);
                  handleLink(incomingBook);
              }
          }
      }
    }

    //this method works on providing a link for the user to learn more info about a book
    private void handleLink(Book incomingBook){

        btnLink.setOnClickListener((e)->{
                if(incomingBook.getId() == 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("caution");
                    builder.setMessage("Are you sure you want to be redirected to this website click yes if you do");
                    builder.setPositiveButton("Yes",(dialogInterface, i) -> {
                        Intent intentWeb = new Intent(BookActivity.this, WebsiteActivity.class);
                        intentWeb.putExtra("url","https://readingraphics.com/book-summary-the-laws-of-human-nature/");
                        startActivity(intentWeb);

                    });
                    builder.setNegativeButton("No",(dialogInterface, i) -> {});

                    builder.create().show();

                }else if(incomingBook.getId()==2){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("caution");
                    builder.setMessage("Are you sure you want to be redirected to this website click yes if you do");
                    builder.setPositiveButton("Yes",(dialogInterface, i) -> {
                        Intent intentWeb = new Intent(BookActivity.this, WebsiteActivity.class);
                        intentWeb.putExtra("url","https://www.google.com/");
                        startActivity(intentWeb);

                    });
                    builder.setNegativeButton("No",(dialogInterface, i) -> {});

                    builder.create().show();
                }else{
                    Toast.makeText(this, "okay", Toast.LENGTH_SHORT).show();
                }
        });
    }
    private void handleFavoritesBooks(Book incomingBook) {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existsInFavoriteBooks = false;
        for(Book b : favoriteBooks){
            if(b.getId() == incomingBook.getId()){
                existsInFavoriteBooks = true;
            }
        }

        if(existsInFavoriteBooks){
            btnAddToFavorite.setEnabled(false);
        }else {
            btnAddToFavorite.setOnClickListener(e->{
                if(Utils.getInstance(this).addToFavorites(incomingBook)){ // the method ArrayList.add returns true if the item is added and false if not
                    Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                    btnAddToFavorite.setEnabled(false);
                    //navigating the user
                    Intent intent = new Intent(BookActivity.this, FavoriteBookActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "Something Went wrong Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void handleCurrentlyReadingBooks(Book incomingBook) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existsCurrentlyReadingBooks = false;
        for(Book b : currentlyReadingBooks){
            if(b.getId() == incomingBook.getId()){
                existsCurrentlyReadingBooks = true;
            }
        }

        if(existsCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }else {
            btnAddToCurrentlyReading.setOnClickListener(e->{
                if(Utils.getInstance(this).addToCurrentlyRead(incomingBook)){ // the method ArrayList.add returns true if the item is added and false if not
                    Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                    btnAddToCurrentlyReading.setEnabled(false);
                    //navigating the user
                    Intent intent = new Intent(BookActivity.this, CurrentlyReadBookActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "Something Went wrong Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book incomingBook) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();

        boolean existsInWantToReadBooks = false;
        for(Book b : wantToReadBooks){
            if(b.getId() == incomingBook.getId()){
                existsInWantToReadBooks = true;
            }
        }

        if(existsInWantToReadBooks){
            btnAddToWantToRead.setEnabled(false);
        }else {
            btnAddToWantToRead.setOnClickListener(e->{
                if(Utils.getInstance(this).addToWantToRead(incomingBook)){ // the method ArrayList.add returns true if the item is added and false if not
                    Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                    btnAddToWantToRead.setEnabled(false);
                    //navigating the user
                    Intent intent = new Intent(BookActivity.this, WantToReadBookActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "Something Went wrong Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /*
    * Enable and Disable Button
    * Add the book to Already Read Book ArrayList
    * */
    private void handleAlreadyRead(Book incomingBook) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existsInAlreadyReadBooks = false;
        for(Book b : alreadyReadBooks){
            if(b.getId() == incomingBook.getId()){
                existsInAlreadyReadBooks = true;
            }
        }

        if(existsInAlreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false);
        }else {
            btnAddToAlreadyRead.setOnClickListener(e->{
                if(Utils.getInstance(this).addToAlreadyRead(incomingBook)){ // the method ArrayList.add returns true if the item is added and false if not
                    Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                    btnAddToAlreadyRead.setEnabled(false);
                    //navigating the user
                    Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "Something Went wrong Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void initViews(){
        txtAuthor = findViewById(R.id.txtTheAuthor);
        txtBookName = findViewById(R.id.txtTheName);
        txtPages = findViewById(R.id.txtThePages);
        txtDescription = findViewById(R.id.txtLongDesc);

        btnAddToAlreadyRead = findViewById(R.id.AddToAlreadyRead);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);

        bookImage = findViewById(R.id.bookImage);

        btnLink = findViewById(R.id.btnLink);
    }

    private void setData(Book book){
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(bookImage);

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
}