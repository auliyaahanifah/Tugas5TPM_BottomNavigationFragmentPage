package com.fti.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNameMovie,tvDesc, tvDirector;
    private ImageView ivCoverMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivCoverMovie = findViewById(R.id.ivCoverMovie);
        tvNameMovie = findViewById(R.id.tvNameMovie);
        tvDirector = findViewById(R.id.tvDirectorMovie);
        tvDesc = findViewById(R.id.tvDescMovie);

        int iCover = getIntent().getIntExtra("imageMovie",0);
        String sName = getIntent().getStringExtra("nameMovie");
        String sDirector= "" + getIntent().getStringExtra("directorMovie");
        String sDesc = getIntent().getStringExtra("descMovie");

        Glide.with(this).load(iCover).into(ivCoverMovie);
        tvNameMovie.setText(sName);
        tvDesc.setText(sDesc);
        tvDirector.setText(sDirector);

    }
}