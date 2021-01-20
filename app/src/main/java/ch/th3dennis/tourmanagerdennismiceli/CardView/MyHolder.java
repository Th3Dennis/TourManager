package ch.th3dennis.tourmanagerdennismiceli.CardView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ch.th3dennis.tourmanagerdennismiceli.R;

/**
 * Holder Class for the CardView
 */
public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mTitle, mDescription;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.imageIV);
        this.mTitle = itemView.findViewById(R.id.titleTV);
        this.mDescription = itemView.findViewById(R.id.descriptionTV);

    }
}
