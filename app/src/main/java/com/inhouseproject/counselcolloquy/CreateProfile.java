package com.inhouseproject.counselcolloquy;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


public class CreateProfile extends AppCompatActivity{

    EditText etname,etBio, etProfession,etEmail,etWeb;
    Button button;
    ImageView imageView;
    ProgressBar progressbar;
    Uri imageUri;
    UploadTask uploadTask;

    StorageReference storageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentreference;
    All_User_member member;

    String currentUserId;


    private static final int PICK_IMAGE = 1;

    private void uploadData() {

    }
    private String getFileExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));


    }


    @Override
    protected void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        imageView = findViewById(R.id.iv_cp);
        etBio = findViewById(R.id.et_bio_cp);
        etEmail = findViewById(R.id.et_email_cp);
        etname = findViewById(R.id.et_name_cp);
        etProfession = findViewById(R.id.et_profession_cp);
        etWeb = findViewById(R.id.et_web_cp);
        button = findViewById(R.id.btn_cp);
        progressbar =findViewById(R.id.progressbar_cp);
        member = new All_User_member();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        currentUserId = user.getUid();

        documentreference = db.collection("user").document(currentUserId);

        storageReference = FirebaseStorage.getInstance().getReference("Profile image ");

        databaseReference = database.getReference("All Users");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }


        });






        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivity(intent);
            }
        });
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            // Your code here
            try{
            if(requestCode== PICK_IMAGE||resultCode ==RESULT_OK || data!=null|data.getData()!=null){
            imageUri = data.getData();

                Picasso.get().load(imageUri).into(imageView);
            }
        }catch(Exception e){
                Toast.makeText(this,"Error"+e,Toast.LENGTH_SHORT).show();
            }
        }




    }

}
