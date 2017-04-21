package pe.edu.upc.catchup;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.orm.SugarApp;

import pe.edu.upc.catchup.backend.models.Article;
import pe.edu.upc.catchup.backend.models.Source;
import pe.edu.upc.catchup.backend.network.NewsApi;

/**
 * Created by operador on 4/5/17.
 */

public class CatchUpApp extends SugarApp {
    private static CatchUpApp mCatchApp;
    private NewsApi mNewsApi = new NewsApi();

    public CatchUpApp() {
        super();
        mCatchApp = this;
    }

    // Singleton Pattern implementation
    public static CatchUpApp getInstance() {
        return mCatchApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }

    // Delegate Pattern implementation
    public Source getCurrentSource() {
        return mNewsApi.getCurrentSource();
    }

    public void setCurrentSource(Source source) {
        mNewsApi.setCurrentSource(source);
    }

    public Article getCurrentArticle() { return mNewsApi.getCurrentArticle(); }

    public void setCurrentArticle(Article article) { mNewsApi.setCurrentArticle(article); }
}
