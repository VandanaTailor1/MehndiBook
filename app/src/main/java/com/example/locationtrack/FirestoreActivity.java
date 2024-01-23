package com.example.locationtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.locationtrack.databinding.ActivityFirestoreBinding;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreActivity extends AppCompatActivity {
      ActivityFirestoreBinding binding;
    FirebaseFirestore firestore;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFirestoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firestore=FirebaseFirestore.getInstance();

        binding.save.setOnClickListener(v -> {
            data=binding.name.getText().toString();
            if(TextUtils.isEmpty(data)){
                Toast.makeText(this, "Fill the data", Toast.LENGTH_SHORT).show();
            }else{
                SaveDataInFireStore(data);
          }
        });
    }
      private void SaveDataInFireStore(String name){
          CollectionReference dbCourses = firestore.collection("Courses");
          FireStoreModel courses = new FireStoreModel();
          courses.setSubject(name);
            dbCourses.add(courses)
                  .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                      @Override
                      public void onSuccess(DocumentReference documentReference) {
                          Toast.makeText(FirestoreActivity.this, "add ", Toast.LENGTH_SHORT).show();
                      }
      })
         .addOnCanceledListener(new OnCanceledListener() {
                      @Override
                      public void onCanceled() {
                          Toast.makeText(FirestoreActivity.this, "Nottt", Toast.LENGTH_SHORT).show();
                      }
                  });
      }
}