package edu.uw.fragmentdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private OnSearchListener callback;

    public interface OnSearchListener {
        void onSearchSubmitted(String searchTerm);
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnSearchListener)context;
        }catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSearchListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_search, container, false);

        Button button = (Button) rootview.findViewById(R.id.btnSearch);
        final EditText text = (EditText) rootview.findViewById(R.id.txtSearch);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String searchTerm = text.getText().toString();
                callback.onSearchSubmitted(searchTerm);
            }
        });

        return rootview;
    }

}
