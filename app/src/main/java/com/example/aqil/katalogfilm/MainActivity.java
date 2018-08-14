package com.example.aqil.katalogfilm;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>> {
    private static final String TAG = "TAG";
    MovieAdapter adapter;
    Button btnSearch;
    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(myListener);
        edtSearch = findViewById(R.id.edt_search);
        RecyclerView recyclerViewList = findViewById(R.id.rv_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        Bundle bundle = new Bundle();
        adapter = new MovieAdapter(this);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerViewList.setAdapter(adapter);
        getLoaderManager().initLoader(0, bundle, this);


    }

    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int id, Bundle args) {
        String mNamaMovie = null;
        if (args != null) {
            mNamaMovie = args.getString(EXTRAS_SEARCH);
        }
        return new QueryUtils(this, mNamaMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Movie>> loader, ArrayList<Movie> data) {

        adapter.setListMovie(data);
        adapter.notifyDataSetChanged();
        Log.d(TAG, "onLoadFinished: " + data.size());

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Movie>> loader) {
        adapter.setListMovie(null);
    }

    private String EXTRAS_SEARCH = "pencarian";
    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String pencarian = edtSearch.getText().toString();
            String fixPencarian = null;

            fixPencarian = pencarian.replace(" ", "%20");

            if (TextUtils.isEmpty(pencarian)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_SEARCH, fixPencarian);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };

}










