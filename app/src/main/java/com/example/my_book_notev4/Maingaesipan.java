package com.example.my_book_notev4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Maingaesipan extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maingaesipan);
        ImageView recomand = findViewById(R.id.recomand);
        recomand.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Intent intent = new Intent(Maingaesipan.this, recomand_abook.class) ;

                startActivity(intent) ;
            }
        });
        ImageView note = findViewById(R.id.note);
        note.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Intent intent = new Intent(Maingaesipan.this, notess.class) ;

                startActivity(intent) ;
            }
        });
        ImageView propil =  findViewById(R.id.propil);
        propil.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Intent intent = new Intent(Maingaesipan.this, propil.class) ;

                startActivity(intent) ;
            }
        });

        db.collection("gaesipan").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot doc : task.getResult()) {
                    TextView tv = new TextView(Maingaesipan.this);
                    tv.setTextSize(24f);
                    String title = ((String) doc.get("title")) + "\n" + ((String) doc.get("neyong"));
                    tv.setText(title);
                }
            }
        });
    }
}
