package com.fti.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MovieModel> movieModels;

    public MovieAdapter(ArrayList<MovieModel> listData, Context context) {
        this.movieModels = listData;
        this.context = context;
    }

    public ArrayList<MovieModel> getMovieModels() {
        return movieModels;
    }

    public void setMovieModels(ArrayList<MovieModel> movieModels) {
        this.movieModels = movieModels;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie,viewGroup,false);
        return new ViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i){
        Glide.with(context).load(getMovieModels().get(i).getImageMovie()).into(viewHolder.ivImageMovie);
        viewHolder.tvNameMovie.setText(getMovieModels().get(i).getNameMovie());
        viewHolder.tvDirectorMovie.setText(getMovieModels().get(i).getDirectorMovie());
        viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(context, DetailActivity.class);
                moveIntent.putExtra("nameMovie", getMovieModels().get(i).getNameMovie());
                moveIntent.putExtra("imageMovie", getMovieModels().get(i).getImageMovie());
                moveIntent.putExtra("descMovie", getMovieModels().get(i).getDescMovie());
                moveIntent.putExtra("directorMovie", getMovieModels().get(i).getDirectorMovie());
                context.startActivity(moveIntent);
            }
        });

        viewHolder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String shareSubject = getMovieModels().get(i).getNameMovie();
                String shareBody = getMovieModels().get(i).getNameMovie()+"\nDirector : "+ getMovieModels().get(i).getDirectorMovie()+"\n" + getMovieModels().get(i).getDescMovie();
                share.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                share.putExtra(Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(share, "Share Using"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return getMovieModels().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivImageMovie;
        private TextView tvNameMovie,tvDirectorMovie;
        private Button btnDetail, btnShare;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ivImageMovie = itemView.findViewById(R.id.ivImageMovie);
            tvNameMovie = itemView.findViewById(R.id.tvNameMovie);
            tvDirectorMovie = itemView.findViewById(R.id.tvDirectorMovie);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            btnShare = itemView.findViewById(R.id.btnShare);
        }

    }
}
