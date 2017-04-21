package pe.edu.upc.catchup.backend.models;

import com.orm.SugarRecord;

/**
 * Created by ricardo on 4/12/17.
 */

public class Bookmark extends SugarRecord {
    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mUrl;
    private String mUrlToImage;
    private String mPublishedAt;
    private String mSourceId;

    public Bookmark(){}

    public Bookmark(String mAuthor, String mTitle, String mDescription, String mUrl,
                        String mUrlToImage, String mPublishedAt, String mSourceId) {
        this.mAuthor = mAuthor;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mUrl = mUrl;
        this.mUrlToImage = mUrlToImage;
        this.mPublishedAt = mPublishedAt;
        this.mSourceId = mSourceId;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public Bookmark setAuthor(String author) {
        this.mAuthor = author;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public Bookmark setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public Bookmark setDescription(String mDescription) {
        this.mDescription = mDescription;
        return this;
    }

    public String getUrl() {
        return mUrl;
    }

    public Bookmark setUrl(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    public String getUrlToImage() {
        return mUrlToImage;
    }

    public Bookmark setUrlToImage(String mUrlToImage) {
        this.mUrlToImage = mUrlToImage;
        return this;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public Bookmark setPublishedAt(String mPublishedAt) {
        this.mPublishedAt = mPublishedAt;
        return this;
    }

    public String getSourceId() {
        return mSourceId;
    }

    public Bookmark setSourceId(String mSourceId) {
        this.mSourceId = mSourceId;
        return this;
    }

    public static Bookmark build(Article article){
        return new Bookmark()
                .setAuthor(article.getAuthor())
                .setDescription(article.getDescription())
                .setPublishedAt(article.getPublishedAt())
                .setTitle(article.getTitle())
                .setUrl(article.getUrl())
                .setUrlToImage(article.getUrlToImage())
                .setSourceId(article.getSource().getId());
    }
}
