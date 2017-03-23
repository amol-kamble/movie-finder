package com.aml.moviefinder.ui.moviefinder.list;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aml.moviefinder.R;
import com.aml.moviefinder.core.utils.ui.OnItemClickListener;
import com.aml.moviefinder.ui.moviefinder.model.Movie;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by owner on 17/11/16.
 */

public class MovieListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<Movie> movies;
    OnItemClickListener<Movie> onItemClickListener;


    public MovieListAdapter(List<Movie> movies) {
        this.movies = movies;
    }


    public OnItemClickListener<Movie> getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<Movie> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public MovieListHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        final View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.adap_movie_item, viewGroup, false);

        return new MovieListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        MovieListHolder viewHolder = (MovieListHolder) holder;
        viewHolder.txtTitle.setText(movie.getTitle());
        Picasso.with(viewHolder.imgPoster.getContext()).load(movie.getPoster()).into(viewHolder.imgPoster);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getOnItemClickListener()!=null){
                    getOnItemClickListener().onItemClick(movie);
                }
            }
        });
    }

    public static class MovieListHolder extends RecyclerView.ViewHolder {

        protected TextView txtTitle;
        protected ImageView imgPoster;
        public MovieListHolder(View v) {
            super(v);
            txtTitle = (TextView) v.findViewById(R.id.txt_title);
            imgPoster = (ImageView) v.findViewById(R.id.img_poster);

        }
    }



}
