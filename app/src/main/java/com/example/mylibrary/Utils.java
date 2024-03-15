package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "allready_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_boos";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";
    private static Utils instance;
    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> favoriteBooks;
    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if(getAllBooks() == null){
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if(getAlreadyReadBooks() == null){
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(getCurrentlyReadingBooks() == null){
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(getWantToReadBooks() == null){
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(getFavoriteBooks() == null){
            editor.putString(FAVORITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }
    private void initData() {
        ArrayList<Book> books = new ArrayList();
        books.add(new Book(1, "The Laws Of Human Nature", "Robert Greene", 690, "https://www.tirryaq.com/wp-content/uploads/2022/04/robert-G.jpg",
                "The Laws of Human Nature helps you understand why people do what they do and how you can use both your own psychological flaws and those of others to your advantage at work, in relationships, and in life",
                "Long Description..."));
        books.add(new Book(2, "In Search of Lost Time", "Marcel Proust", 4215, "https://kbimages1-a.akamaihd.net/0e77a0f9-14f7-4a97-9784-053bc1691e87/353/569/90/False/in-search-of-lost-time-volumes-1-to-7-8.jpg",
                "In Search of Lost Time follows the narrator's recollections of childhood and experiences into adulthood in the late 19th-century and early 20th-century high-society France, while reflecting on the loss of time and lack of meaning in the world.",
                "Long Description..."));
        books.add(new Book(3, "Ulysses", "James Joyce", 	732, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/JoyceUlysses2.jpg/330px-JoyceUlysses2.jpg",
                "Ulysses, novel by Irish writer James Joyce, first published in book form in 1922. Stylistically dense and exhilarating, it is generally regarded as a masterpiece and has been the subject of numerous volumes of commentary and analysis. The novel is constructed as a modern parallel to Homer's Odyssey.",
                "Long Description..."));

        /*
        * to presist our data without using a "real" DB we can use Sharedpreferences
        * shared prefrences takes a string but we have an object so we used the gson library
        * to convert our object to a string then stored it in shared preferences
        * */

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY ,gson.toJson(books));
        editor.commit();
    }

    //Singleton Pattern
    public static Utils getInstance(Context context) {
        if (instance != null){
            return instance;
        }else {
            instance = new Utils(context);
            return instance;
        }
    }
    public ArrayList<Book> getAllBooks() {
        //getting our books from our "sharedPrefrences" and converting it to an arrayList
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }
    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
        return books;
    }
    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return books;
    }
    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
        return books;
    }
    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS, null), type);
        return books;
    }

    /*
    * method used to check if the id passed in the argument exists in the allBooks arrayList
    * */
    public Book getBookById(int id){
        ArrayList<Book> books = getAllBooks();
        if(books != null){
            for(Book b : books){
                if(b.getId()==id){
                    return b;
                }
            }
        }
        return null;
    }

    /*
    * a method that returns a boolean if a book object is successfully added to the alreadyReadBooks ArrayList
    * */
    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(books != null){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }
    public boolean addToWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if(books != null){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }
    public boolean addToCurrentlyRead(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(books != null){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }
    public boolean addToFavorites(Book book){
        ArrayList<Book> books = getFavoriteBooks();
        if(books != null){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            for (Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public boolean removeFromWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null) {
            for (Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public boolean removeFromCurrentlyRead(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null) {
            for (Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public boolean removeFromFavorites(Book book){
        ArrayList<Book> books = getFavoriteBooks();
        if (books != null) {
            for (Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
