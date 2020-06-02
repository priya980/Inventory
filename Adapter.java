package com.example.inventory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Model> modelList;
    private Context context;
    private FirebaseFirestore db;



    public Adapter(List<Model> modelList, Context context, FirebaseFirestore db) {
        this.modelList = modelList;
        this.context = context;
        this.db = db;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);

        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        final int itemPosition = position;
        final Model model=modelList.get(itemPosition);

        holder.barcodeNumber.setText(model.getBarcodeNumber());
        holder.productName.setText(model.getProductName());
        holder.price.setText(model.getPrice());
        holder.date.setText(model.getDate());


//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateNote(note);
//            }
//        });
//
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteNote(note.getId(), itemPosition);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView barcodeNumber,productName,price,date;


        ViewHolder(View view) {
            super(view);
            barcodeNumber = view.findViewById(R.id.barcode_text);
           productName = view.findViewById(R.id.product_name);
           price=view.findViewById(R.id.product_price);
           date=view.findViewById(R.id.product_date);


        }
    }
}
