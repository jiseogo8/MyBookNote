package com.example.my_book_notev4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressLint("Registered")
public class loginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        /*Button login = findViewById(R.id.login);
        login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Intent intent = new Intent(loginPage.this, Maingaesipan.class);

                startActivity(intent);
            }
        });*/
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                final EditText idEditText = (EditText) findViewById(R.id.editText);
                final EditText idEditText2 = (EditText) findViewById(R.id.editText2);
                final Map<String, Object> user = new HashMap<>();
                db.collection("users")
                        .whereEqualTo("first",String.valueOf(idEditText.getText()))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().isEmpty()) {
                                        user.put("first", String.valueOf(idEditText.getText()));
                                        user.put("pw", String.valueOf(idEditText2.getText()));
                                        db.collection("users")
                                                .add(user)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.d("TAG", Objects.requireNonNull(e.getMessage()));
                                                    }
                                                });
                                        Intent intent = new Intent(loginPage.this, Basic.class);


                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(),R.string.ID_DUPLICATION_TOAST_MESSAGE, Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Log.d("TAG", "Error getting documents: ", task.getException());
                                }
                            }
                        });

            }
        });


        final Button login = findViewById(R.id.login);
        login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                final EditText idEditText = (EditText) findViewById(R.id.editText);
                final EditText idEditText2 = (EditText) findViewById(R.id.editText2);
                db.collection("users")
                        .whereEqualTo("first",String.valueOf(idEditText.getText()))
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                        String pass = (String) document.getData().get("pw");
                                        Log.d("TAG", document.getId() + " => " + document.getData());
                                        if (String.valueOf(idEditText2.getText()).equals(pass)) {
                                            Intent intent = new Intent(loginPage.this, Maingaesipan.class);


                                            startActivity(intent);
                                        }
                                    }
                                }
                                    else {
                                    Log.d("TAG", "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        });

        }
    }

