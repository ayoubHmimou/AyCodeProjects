package com.example.mylibrary;

import static com.example.mylibrary.BookActivity.BOOK_ID_KEY;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecViewAdapter";
    private ArrayList<Book> books = new ArrayList<>();
    private Context context;
    private String parentActivity;

    public BookRecViewAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtBookName.setText(books.get(position).getName());

        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);

        holder.parent.setOnClickListener(e->{
            Intent intent = new Intent(context, BookActivity.class);
            intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
            context.startActivity(intent);
        });

        holder.author.setText(books.get(position).getAuthor());
        holder.shortDesc.setText((books.get(position).getShortDesc()));

        TransitionManager.beginDelayedTransition(holder.parent);
        if(books.get(position).isExpanded()){
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            // logic for the delete button
            if(parentActivity.equals("allBooks")){
                holder.btnDelete.setVisibility(View.GONE);
            }else if(parentActivity.equals("alreadyRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                String book = books.get(position).getName();
                holder.btnDelete.setOnClickListener((e)->{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete this book ");
                    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                        if(Utils.getInstance(context).removeFromAlreadyRead(books.get(position))){
                            Toast.makeText(context, book+" has been removed from already read books successfully", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, "Something Went Wrong Try Again Please", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                });
            }else if(parentActivity.equals("wantToRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                String book = books.get(position).getName();
                holder.btnDelete.setOnClickListener((e)->{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete this book ");
                    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                        if(Utils.getInstance(context).removeFromWantToRead(books.get(position))){
                            Toast.makeText(context, book+" has been removed from want to read books successfully", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, "Something Went Wrong Try Again Please", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                });

            }else if(parentActivity.equals("currentlyReading")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                String book = books.get(position).getName();
                holder.btnDelete.setOnClickListener((e)->{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete this book ");
                    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                        if(Utils.getInstance(context).removeFromCurrentlyRead(books.get(position))){
                            Toast.makeText(context, book+" has been removed from currently reading books successfully", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, "Something Went Wrong Try Again Please", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                });

            }else{
                holder.btnDelete.setVisibility(View.VISIBLE);
                String book = books.get(position).getName();
                holder.btnDelete.setOnClickListener((e)->{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete this book ");
                    builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                        if(Utils.getInstance(context).removeFromFavorites(books.get(position))){
                            Toast.makeText(context, book+" has been removed from favorite books successfully", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, "Something Went Wrong Try Again Please", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", (dialogInterface, i) -> {

                    });
                    builder.create().show();
                });

            }
        }else{
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private MaterialCardView parent;
        private ImageView imgBook;
        private TextView txtBookName;
        private ImageView downArrow, upArrow;
        private RelativeLayout expandedRelLayout;
        private TextView author, shortDesc;
        private Button btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtBookName = itemView.findViewById(R.id.txtBookName);

            downArrow = itemView.findViewById(R.id.btndownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            author = itemView.findViewById(R.id.author);
            shortDesc = itemView.findViewById(R.id.shortDesc);

            btnDelete = itemView.findViewById(R.id.btnDelete);

            downArrow.setOnClickListener(e->{
                Book book = books.get(getAdapterPosition());// getting the postion of the book we're looking at
                book.setExpanded(!book.isExpanded());
                notifyItemChanged(getAdapterPosition());

            });

            upArrow.setOnClickListener(e->{
                Book book = books.get(getAdapterPosition());
                book.setExpanded(!book.isExpanded());
                notifyItemChanged(getAdapterPosition());
            });

        }
    }
}
