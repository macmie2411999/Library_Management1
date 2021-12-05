package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, author_input, price_input;
    Button update_button;
    String id, title, author, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input_update);
        author_input = findViewById(R.id.author_input_update);
        price_input = findViewById(R.id.price_input_update);
        update_button = findViewById(R.id.update_button);

        getIntentData();
        update_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.updateBook(id, title, author, Integer.valueOf(price));
            }
        });
    }

    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("price")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            price = getIntent().getStringExtra("price");

            //Setting Intent Data
            title_input.setText(title);
            author_input.setText(author);
            price_input.setText(price);
        } else {
            Toast.makeText(this, "No Data.",  Toast.LENGTH_LONG).show();
        }
    }
}