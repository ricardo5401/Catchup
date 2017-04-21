package pe.edu.upc.catchup.backend.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by operador on 3/30/17.
 */

public class Article {
    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mUrl;
    private String mUrlToImage;
    private String mPublishedAt;
    private Source mSource;

    public Article() {
    }

    public Article(String mAuthor, String title, String description, String url, String urlToImage, String publishedAt, Source source) {
        this.mAuthor = mAuthor;
        this.mTitle = title;
        this.mDescription = description;
        this.mUrl = url;
        this.mUrlToImage = urlToImage;
        this.mPublishedAt = publishedAt;
        this.mSource = source;
    }

    public String getAuthor() {
        return mAuthor.equalsIgnoreCase("null") ? "" : mAuthor;
    }

    public Article setAuthor(String author) {
        this.mAuthor = author;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public Article setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public Article setDescription(String description) {
        this.mDescription = description;
        return this;
    }

    public String getUrl() {
        return mUrl;
    }

    public Article setUrl(String url) {
        this.mUrl = url;
        return this;
    }

    public String getUrlToImage() {
        return mUrlToImage;
    }

    public Article setUrlToImage(String urlToImage) {
        this.mUrlToImage = urlToImage;
        return this;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public Article setPublishedAt(String publishedAt) {
        this.mPublishedAt = publishedAt;
        return this;
    }

    public Source getSource() {
        return mSource;
    }

    public Article setSource(Source source) {
        this.mSource = source;
        return this;
    }

    public static Article build(JSONObject jsonArticle, Source source) {
        if(jsonArticle == null || source == null ) return null;
        Article mArticle = new Article();
        try {
            mArticle.setSource(source)
                    .setAuthor(jsonArticle.optString("author"))
                    .setTitle(jsonArticle.getString("title"))
                    .setDescription(jsonArticle.getString("description"))
                    .setUrl(jsonArticle.getString("url"))
                    .setUrlToImage(jsonArticle.getString("urlToImage"))
                    .setPublishedAt(jsonArticle.getString("publishedAt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mArticle;
    }

    public static List<Article> build(JSONArray jsonArticles, Source source) {
        if(jsonArticles == null || source == null) return null;
        List<Article> mArticles = new ArrayList<>();
        int mLength = jsonArticles.length();
        for(int i = 0; i < mLength; i++) {
            try {
                mArticles.add(Article.build(jsonArticles.getJSONObject(i), source));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mArticles;
    }
}
