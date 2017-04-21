package pe.edu.upc.catchup.backend.network;

import pe.edu.upc.catchup.backend.models.Article;
import pe.edu.upc.catchup.backend.models.Source;

/**
 * Created by operador on 3/30/17.
 */

public class NewsApi {
    public static String SOURCES_URL = "https://newsapi.org/v1/sources";
    public static String ARTICLES_URL = "https://newsapi.org/v1/articles";
    private Source mCurrentSource;
    private Article mCurrentArticle;

    public Source getCurrentSource() {
        return mCurrentSource;
    }

    public void setCurrentSource(Source mCurrentSource) {
        this.mCurrentSource = mCurrentSource;
    }

    public Article getCurrentArticle() {
        return mCurrentArticle;
    }

    public void setCurrentArticle(Article mCurrentArticle) {
        this.mCurrentArticle = mCurrentArticle;
    }
}
