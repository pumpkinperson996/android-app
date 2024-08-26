package com.example.toiletratingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView restroomListView;
    private RatingBar ratingBar;
    private EditText reviewEditText;
    private Button submitButton;

    private ArrayList<String> restroomList;
    private ArrayList<String> reviewsList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restroomListView = findViewById(R.id.restroomListView);
        ratingBar = findViewById(R.id.ratingBar);
        reviewEditText = findViewById(R.id.reviewEditText);
        submitButton = findViewById(R.id.submitButton);

        // Initialize the restroom list
        restroomList = new ArrayList<>();
        restroomList.add("Restroom 1 - Location A");
        restroomList.add("Restroom 2 - Location B");
        restroomList.add("Restroom 3 - Location C");

        reviewsList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, restroomList);
        restroomListView.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String review = reviewEditText.getText().toString();
                float rating = ratingBar.getRating();

                if (review.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please write a review", Toast.LENGTH_SHORT).show();
                    return;
                }

                String selectedRestroom = restroomList.get(restroomListView.getCheckedItemPosition());
                String feedback = selectedRestroom + " - Rating: " + rating + " stars\nReview: " + review;
                reviewsList.add(feedback);
                reviewEditText.setText("");
                ratingBar.setRating(0);

                Toast.makeText(MainActivity.this, "Review submitted!", Toast.LENGTH_SHORT).show();
                // Ideally, save the review to a database or cloud service
            }
        });
    }
}
