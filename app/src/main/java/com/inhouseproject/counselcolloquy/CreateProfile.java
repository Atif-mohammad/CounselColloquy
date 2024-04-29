package com.inhouseproject.counselcolloquy;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.StorageReference;


public class CreateProfile extends AppCompatActivity{

    EditText etname,etBio, etProfession,etEmail,etWeb;
    Button button;
    ImageView imageView;
    ProgressBar progressbar;
    Uri uri;
    UploadTask uploadTask;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    FirebaseFirestore db;
    DocumentReference r;


    private static final int PIC_IMAGE = 1;




    @Override
    protected void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);

    }
}
