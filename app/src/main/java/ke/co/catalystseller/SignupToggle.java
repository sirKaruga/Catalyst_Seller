package ke.co.catalystseller;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SignupToggle extends Activity {


    private static final int CHOOSE_IMAGE_REQUEST = 1;
    private Uri uri;
    Button btnUpload, btnChoseImg;
    EditText meditName;
    ImageView mImageView;
    ProgressBar mProgressBar;

    private DatabaseHelper myDb;

    private StorageReference mStorageReference;
    private DatabaseReference mDatabaseReference;
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.seller_activity);

        myDb = new DatabaseHelper(this);
        btnUpload = (Button)findViewById(R.id.upload_img_btn);
        btnChoseImg = (Button)findViewById(R.id.choose_img);
        meditName = (EditText)findViewById(R.id.edit_image_name);
        mImageView = (ImageView)findViewById(R.id.upload_image_view);
        mProgressBar = (ProgressBar)findViewById(R.id.upload_progress_bar);

        mStorageReference = FirebaseStorage.getInstance().getReference("Uploads");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Uploads");

        selectImg();
        loadUploadBtn();
    }

    public void loadUploadBtn(){
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(SignupToggle.this,"Upload in progress",Toast.LENGTH_LONG).show();
                }else {
                    uploadFile();
                }

            }
        });
    }

    public void selectImg(){
        btnChoseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, CHOOSE_IMAGE_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CHOOSE_IMAGE_REQUEST && resultCode==RESULT_OK && data !=null && data.getData()!=null){
            uri = data.getData();
            Picasso.with(this).load(uri).into(mImageView);
        }
    }

    public String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    public void uploadFile(){
        //upload work here
        if (uri !=null){
            StorageReference fileReferemce = mStorageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));

            mUploadTask = fileReferemce.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            },500);
                            Toast.makeText(SignupToggle.this,"Upload Successful",Toast.LENGTH_LONG).show();

                            Cursor all = myDb.getAllData();
                            String uName = new String();
                            String email = new String();
                            String contact  = new String();
                            while (all.moveToNext()){
                                 uName = all.getString(1);
                                 email = all.getString(2);
                                 contact = all.getString(3);
                            }
                            Uploads uploads = new Uploads(meditName.getText().toString().trim(),
                                    taskSnapshot.getStorage().getDownloadUrl().toString(),uName.trim(), email.trim(),contact.trim());
                            String uploadId = mDatabaseReference.push().getKey();
                            mDatabaseReference.child(uploadId).setValue(uploads);
                            ///test



                            ///test
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int)progress);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignupToggle.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

        }else{
            Toast.makeText(SignupToggle.this,"You Didn't choose an Image",Toast.LENGTH_LONG).show();
        }
    }
}
