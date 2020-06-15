//package ke.co.catalystseller;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class ImageAdapter2 extends RecyclerView.Adapter<ImageAdapter2.ImageViewHolder> {
//    private Context mContext;
//    private List<Uploads> mUploads;
//
//    public ImageAdapter2(Context context, List<Uploads> uploads){
//        mContext = context;
//        mUploads = uploads;
//    }
//
//    @NonNull
//    @Override
//    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent,false);
//        return new ImageViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
//        Uploads uploadCurrent = mUploads.get(position);
//        holder.textViewName.setText(uploadCurrent.getName());
//
//        Picasso.with(mContext)
//                .load(uploadCurrent.getImageUrl())
//                .fit()
//                .centerCrop()
//                .into(holder.imageView);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mUploads.size();
//    }
//
//    public class ImageViewHolder extends RecyclerView.ViewHolder{
//        public TextView textViewName;
//        public ImageView imageView;
//
//        public ImageViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewName = itemView.findViewById(R.id.item_name);
//            imageView = itemView.findViewById(R.id.m_image_view);
//        }
//    }
//}
