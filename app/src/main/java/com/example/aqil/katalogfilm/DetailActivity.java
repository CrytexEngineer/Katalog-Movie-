package com.example.aqil.katalogfilm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    ImageView img_detail;
    TextView detaiLDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        img_detail = findViewById(R.id.img_detail);
        detaiLDescription = findViewById(R.id.description);
        Movie currentMovie = getIntent().getParcelableExtra(MovieAdapter.EXTRA_MOVIE);
        Glide.with(this).load(currentMovie.getPosterPath()).into(img_detail);
        detaiLDescription.setText(currentMovie.getDescription());

    }
}
