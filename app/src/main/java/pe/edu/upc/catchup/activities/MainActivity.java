package pe.edu.upc.catchup.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.adapters.SourcesAdapter;
import pe.edu.upc.catchup.backend.models.Source;
import pe.edu.upc.catchup.backend.network.NewsApi;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity {
    RecyclerView mSourcesRecyclerView;
    SourcesAdapter mSourcesAdapter;
    RecyclerView.LayoutManager mSourcesLayoutManager;
    List<Source> mSources;
    private static String TAG = "CatchUp";
    //private int mSpanCount = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSourcesRecyclerView = (RecyclerView) findViewById(R.id.sourcesRecyclerView);
        mSources = new ArrayList<>();
        mSourcesLayoutManager = new LinearLayoutManager(this);
        mSourcesAdapter = new SourcesAdapter();
        mSourcesAdapter.setSources(mSources);
        mSourcesRecyclerView.setLayoutManager(mSourcesLayoutManager);
        mSourcesRecyclerView.setAdapter(mSourcesAdapter);
        updateSources();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@Override
    //public void onConfigurationChanged(Configuration newConfig) {
    //  super.onConfigurationChanged(newConfig);
    //    mSpanCount = (newConfig.orientation == ORIENTATION_PORTRAIT) ? 2 : 3;
    //    ((GridLayoutManager) mSourcesLayoutManager).setSpanCount(mSpanCount);
    //}

    private void updateSources() {
        AndroidNetworking.get(NewsApi.SOURCES_URL)
                .addQueryParameter("language", "en")
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String mStatus = response.getString("status");
                            if(mStatus.equalsIgnoreCase("error")) {
                                String mMessage = response.getString("message");
                                Log.d(TAG, mMessage);
                                return;
                            }
                            JSONArray mJsonSources = response.getJSONArray("sources");
                            mSources = Source.build(mJsonSources);
                            mSourcesAdapter.setSources(mSources);
                            mSourcesAdapter.notifyDataSetChanged();
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
