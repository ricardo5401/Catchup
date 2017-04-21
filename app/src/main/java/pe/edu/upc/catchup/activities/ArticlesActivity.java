package pe.edu.upc.catchup.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.catchup.CatchUpApp;
import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.adapters.ArticlesAdapter;
import pe.edu.upc.catchup.backend.models.Article;
import pe.edu.upc.catchup.backend.models.Source;
import pe.edu.upc.catchup.backend.network.NewsApi;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class ArticlesActivity extends AppCompatActivity {
    RecyclerView mArticlesRecyclerView;
    ArticlesAdapter mArticlesAdapter;
    RecyclerView.LayoutManager mArticlesLayoutManager;
    List<Article> mArticles;
    private int mSpanCount = 2;
    private static String TAG = "CatchUp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mArticlesLayoutManager = new GridLayoutManager(this, mSpanCount);
        mArticlesRecyclerView = (RecyclerView) findViewById(R.id.articlesRecyclerView);
        mArticles = new ArrayList<>();
        mArticlesAdapter = new ArticlesAdapter();
        mArticlesAdapter.setArticles(mArticles);
        mArticlesRecyclerView.setLayoutManager(mArticlesLayoutManager);
        mArticlesRecyclerView.setAdapter(mArticlesAdapter);
        updateArticles();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mSpanCount = (newConfig.orientation == ORIENTATION_PORTRAIT) ? 2 : 3;
        ((GridLayoutManager) mArticlesLayoutManager).setSpanCount(mSpanCount);
    }

    private void updateArticles() {
        final Source mCurrentSource = CatchUpApp.getInstance().getCurrentSource();
        AndroidNetworking.get(NewsApi.ARTICLES_URL)
                .addQueryParameter("apiKey", getString(R.string.news_api_key))
                .addQueryParameter("source", mCurrentSource.getId())
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equalsIgnoreCase("error")) {
                                Log.d(TAG, response.getString("message"));
                                return;
                            }
                            mArticles = Article.build(response.getJSONArray("articles"), mCurrentSource);
                            mArticlesAdapter.setArticles(mArticles);
                            mArticlesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getErrorBody());
                    }
                });


    }

}
