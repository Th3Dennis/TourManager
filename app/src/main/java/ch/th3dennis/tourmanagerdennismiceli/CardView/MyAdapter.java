package ch.th3dennis.tourmanagerdennismiceli.CardView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ch.th3dennis.tourmanagerdennismiceli.R;

/**
 * Adapter to create a ViewHolder
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<CardViewModel> models;


    public MyAdapter(Context c, ArrayList<CardViewModel> models) {
        this.c = c;
        this.models = models;
    }

    /**
     * Inflates the holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null); //Inflates the row

        return new MyHolder(view);
    }

    /**
     * Binds the elements to the Cards
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.mTitle.setText(models.get(position).getTitle());
        holder.mDescription.setText(models.get(position).getDescription());
        holder.mImageView.setImageResource(models.get(position).getImg());

    }

    /**
     * @return size of the Arraylist
     */
    @Override
    public int getItemCount() {
        return models.size();
    }
}
