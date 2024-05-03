package com.inhouseproject.counselcolloquy;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


public class CreateProfile extends AppCompatActivity {

    EditText etname, etBio, etProfession, etEmail, etWeb;
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

//    private void uploadData() {
//        String name = etname.getText().toString();
//        String bio = etBio.getText().toString();
//        String Web= etWeb.getText().toString();
//        String prof= etProfession.getText().toString();
//        String Email= etEmail.getText().toString();
//        if(!TextUtils.isEmpty(name)||!TextUtils.isEmpty(bio)||!TextUtils.isEmpty(Web)||!TextUtils.isEmpty(prof)||!TextUtils.isEmpty(Email)||imageUri!=null){
//
//
//            progressbar.setVisibility((View.GONE));
//            final StorageReference reference = storageReference.child(System.currentTimeMillis()+"."+getFileExt(imageUri));
//            uploadTask = reference.putFile(imageUri);
//            Task<Object> urlTask;
//            urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Object>>() {
//                @Override
//                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                    if(!task.isSuccessful()){
//                        throw task.getException();
//                    }
//                    return reference.getDownloadUrl();
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                public void onComplete(@NonNull Task<Uri> task){
//                    if(task.isSuccessful()){
//                        Uri downloadUri = task.getResult();
//
//                        Map <String,String > profile = new HashMap<>();
//                        profile.put("name",name);
//                        profile.put("prof",prof);
//                        profile.put("url",downloadUri.toString());
//                        profile.put("web",Web);
//                        profile.put("email",Email);
//                        profile.put("bio",bio);
//                        profile.put("privacy","Public");
//                        member.setName(name);
//                        member.setUid(currentUserId);
//                        member.setUrl(downloadUri.toString());
//                        member.setProf(prof);
//
//                        databaseReference.child(currentUserId).setValue(member);
//                        documentreference.set(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(CreateProfile.this,"Profile Created", Toast.LENGTH_LONG).show();                            }
//                        });
//
//                    }
//                }
//            }
//            ;
//        }
//
//    }
private void uploadData() {
    String name = etname.getText().toString();
    String bio = etBio.getText().toString();
    String Web = etWeb.getText().toString();
    String prof = etProfession.getText().toString();
    String Email = etEmail.getText().toString();
    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(bio) && !TextUtils.isEmpty(Web) && !TextUtils.isEmpty(prof) && !TextUtils.isEmpty(Email) && imageUri != null) {
        progressbar.setVisibility(View.VISIBLE);
        final StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExt(imageUri));
        uploadTask = reference.putFile(imageUri);
        Task<Uri> urlTask;
        urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return reference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();

                    Map<String, String> profile = new HashMap<>();
                    profile.put("name", name);
                    profile.put("prof", prof);
                    profile.put("url", downloadUri.toString());
                    profile.put("web", Web);
                    profile.put("email", Email);
                    profile.put("bio", bio);
                    profile.put("privacy", "Public");
                    member.setName(name);
                    member.setUid(currentUserId);
                    member.setUrl(downloadUri.toString());
                    member.setProf(prof);

                    databaseReference.child(currentUserId).setValue(member);
                    documentreference.set(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(CreateProfile.this, "Profile Created", Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(CreateProfile.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    } else {
        Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            Intent intent = new Intent(CreateProfile.this,fragement1.class);
            startActivity(intent);
            }
        },2000);
    }
}

    private String getFileExt(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Initialize FirebaseAuth
        FirebaseAuth auth = FirebaseAuth.getInstance();

        // Check if the user is signed in
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            // User is not signed in, show a message or redirect to the sign-in page
            Toast.makeText(this, "Please sign in to create a profile", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        setContentView(R.layout.create_profile);
        imageView = findViewById(R.id.iv_cp);
        etBio = findViewById(R.id.et_bio_cp);
        etEmail = findViewById(R.id.et_email_cp);
        etname = findViewById(R.id.et_name_cp);
        etProfession = findViewById(R.id.et_profession_cp);
        etWeb = findViewById(R.id.et_web_cp);
        button = findViewById(R.id.btn_cp);
        progressbar = findViewById(R.id.progressbar_cp);
        member = new All_User_member();

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

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
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imageUri = data.getData();
                Picasso.get().load(imageUri).into(imageView);
            }
        } catch (NullPointerException e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}