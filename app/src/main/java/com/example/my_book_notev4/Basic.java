package com.example.my_book_notev4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.my_book_notev4.Maingaesipan;
import com.example.my_book_notev4.R;

public class Basic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);Button complete = (Button) findViewById(R.id.complete);
        complete.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Intent intent = new Intent(Basic.this, Maingaesipan.class) ;

                startActivity(intent) ;
            }
        });

        Button skip = (Button) findViewById(R.id.skip);
        skip.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Intent intent = new Intent(Basic.this,Maingaesipan.class) ;

                startActivity(intent) ;
            }
        });
    }
}
