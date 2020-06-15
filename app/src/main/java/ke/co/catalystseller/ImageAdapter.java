package ke.co.catalystseller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Uploads> mUploads;

    public ImageAdapter(Context context, List<Uploads> uploads){
        mContext = context;
        mUploads = uploads;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public ImageView imageView;
        public TextView vendor;
        public TextView vendorContact;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.item_name);
            vendor = itemView.findViewById(R.id.seller_name);
            vendorContact = itemView.findViewById(R.id.ucontact);
            imageView = itemView.findViewById(R.id.m_image_view);
        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Uploads uploadCurrent = mUploads.get(position);
        System.out.println(uploadCurrent.getImageUrl());
        holder.textViewName.setText(uploadCurrent.getName());
        holder.vendor.setText(uploadCurrent.getUser());
        holder.vendorContact.setText(uploadCurrent.getContact());
        Picasso.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }
}
