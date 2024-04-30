package com.inhouseproject.counselcolloquy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

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




public class CreateProfile extends AppCompatActivity{

    EditText etname,etBio, etProfession,etEmail,etWeb;
    Button button;
    ImageView imageView;
    ProgressBar progressbar;
    Uri uri;
    UploadTask uploadTask;

    StorageReference storageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentreference;
    All_User_member member;

    String currentUserId;


    private static final int PIC_IMAGE = 1;

    private void uploadData() {
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

        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            // Your code here
        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivity(intent);
            }
        });



    }

}
