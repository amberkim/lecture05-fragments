package edu.uw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
            implements MoviesFragment.OnMovieSelectedListener, SearchFragment.OnSearchListener {

    private static final String TAG = "MainActivity";

    private ViewPager vp;
    private SearchFragment searchFragment;
    private MoviesFragment moviesFragment;
    private DetailFragment detailFragment;
    private PagerAdapter pa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.viewPager);
        pa = new MoviePagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pa);
        searchFragment = searchFragment.newInstance();
    }

    public class MoviePagerAdapter extends FragmentStatePagerAdapter {

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return searchFragment;
            }
            if (position == 1) {
                return moviesFragment;
            }
            if (position == 2) {
                return detailFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            if (moviesFragment == null) {
                return 1;
            } else if (detailFragment == null) {
                return 2;
            }
            return 3;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

//    //respond to search button clicking
//    public void handleSearchClick(View v){
//        EditText text = (EditText)findViewById(R.id.txtSearch);
//        String searchTerm = text.getText().toString();
//
//        //add a new results fragment to the page
//        MoviesFragment fragment = MoviesFragment.newInstance(searchTerm);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.container, fragment, "MoviesFragment");
//        ft.addToBackStack(null); //remember for the back button
//        ft.commit();
//    }

    @Override
    public void onMovieSelected(Movie movie) {
        detailFragment = DetailFragment.newInstance(movie.toString(), movie.imdbId);
        pa.notifyDataSetChanged();
        vp.setCurrentItem(2);
    }

    public void onSearchSubmitted(String searchTerm) {
        moviesFragment = MoviesFragment.newInstance(searchTerm);
        pa.notifyDataSetChanged();
        vp.setCurrentItem(1);
    }

}
