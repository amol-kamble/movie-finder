package com.aml.moviefinder.ui.moviefinder.details;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aml.moviefinder.R;
import com.aml.moviefinder.ui.base.fragment.BaseFragment;
import com.aml.moviefinder.ui.moviefinder.model.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by amolkamble on 22/03/2017 AD.
 */

public class MovieDetailFragment extends BaseFragment implements MovieDetailContract.View {

    private static final String TAG = MovieDetailFragment.class.getSimpleName();
    View rootView;
    MovieDetailContract.Presenter presenter;
    ProgressBar progressBar;
    TextView txtTitle;
    TextView txtPlot;
    TextView txtGenre;
    TextView txtReleasedOn;
    TextView txtRating;
    ImageView imgPoster;
    ScrollView container;
    String imdbId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        initViews();
        initListener();
        initObjects();
        return rootView;
    }

    @Override
    public void initViews() {
        progressBar=(ProgressBar) rootView.findViewById(R.id.progress);
        txtTitle=(TextView) rootView.findViewById(R.id.txt_title);
        txtGenre=(TextView) rootView.findViewById(R.id.txt_genre);
        txtPlot=(TextView) rootView.findViewById(R.id.txt_plot);
        txtRating=(TextView) rootView.findViewById(R.id.txt_rating);
        txtReleasedOn=(TextView) rootView.findViewById(R.id.txt_released_on);
        imgPoster=(ImageView) rootView.findViewById(R.id.img_poster);
        container=(ScrollView) rootView.findViewById(R.id.movie_container);
        container.setVisibility(View.GONE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initObjects() {
        presenter.getMovie(getArguments().getString(Movie.IMDB_ID));
    }


    @Override
    public void onMovieFound(Movie movie) {
        Picasso.with(imgPoster.getContext()).load(movie.getPoster()).into(imgPoster);
        txtTitle.setText("Title:    "+movie.getTitle());
        txtRating.setText("Rating:  "+movie.getImdbRating());
        txtGenre.setText("Genre:    "+movie.getGenre());
        txtReleasedOn.setText("Released On: "+movie.getReleased());
        txtPlot.setText(movie.getPlot());
        container.setVisibility(View.VISIBLE);

    }

    @Override
    public void setPresenter(MovieDetailContract.Presenter presenter) {
        this.presenter=presenter;
    }



    @Override
    public void onError(String error) {
        showShortToast(error);
        showEmptyUI();
    }

    @Override
    public void onException(String message) {
        showShortToast(message);
        showEmptyUI();
    }
    public void showEmptyUI(){
        container.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

}
