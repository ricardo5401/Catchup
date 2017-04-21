package pe.edu.upc.catchup.backend.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by operador on 3/30/17.
 */

public class Source {
    private String mId;
    private String mName;
    private String mDescription;
    private String mUrl;
    private String mCategory;
    private String mLanguage;
    private String mCountry;
    private Map<String, String> mUrlsToLogos;
    private List<String> mSortBysAvailable;

    public Source() {
    }

    public Source(String id, String name, String description, String url, String category, String language, String country, Map<String, String> urlsToLogos, List<String> sortBysAvailable) {
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mUrl = url;
        this.mCategory = category;
        this.mLanguage = language;
        this.mCountry = country;
        this.mUrlsToLogos = urlsToLogos;
        this.mSortBysAvailable = sortBysAvailable;
    }

    public String getId() {
        return mId;
    }

    public Source setId(String id) {
        this.mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Source setName(String name) {
        this.mName = name;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public Source setDescription(String description) {
        this.mDescription = description;
        return this;
    }

    public String getUrl() {
        return mUrl;
    }

    public Source setUrl(String url) {
        this.mUrl = url;
        return this;
    }

    public String getCategory() {
        return mCategory;
    }

    public Source setCategory(String category) {
        this.mCategory = category;
        return this;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public Source setLanguage(String language) {
        this.mLanguage = language;
        return this;
    }

    public String getCountry() {
        return mCountry;
    }

    public Source setCountry(String country) {
        this.mCountry = country;
        return this;
    }

    public Map<String, String> getUrlsToLogos() {
        return mUrlsToLogos;
    }

    public String getUrlToSmallLogo() {
        return mUrlsToLogos.get("small");
    }

    public String getUrlToMediumLogo() {
        return mUrlsToLogos.get("medium");
    }

    public String getUrlToLargeLogo() {
        return mUrlsToLogos.get("large");
    }

    public Source setUrlsToLogos(Map<String, String> urlsToLogos) {
        this.mUrlsToLogos = urlsToLogos;
        return this;
    }

    public List<String> getSortBysAvailable() {
        return mSortBysAvailable;
    }

    public Source setSortBysAvailable(List<String> sortBysAvailable) {
        this.mSortBysAvailable = sortBysAvailable;
        return this;
    }

    public static Source build(JSONObject jsonSource) {
        if(jsonSource == null) return null;
        Source mSource = new Source();
        try {
            Map<String, String> mUrlsToLogos = new HashMap<>();
            mUrlsToLogos.put("small", jsonSource.getJSONObject("urlsToLogos").getString("small"));
            mUrlsToLogos.put("medium", jsonSource.getJSONObject("urlsToLogos").getString("medium"));
            mUrlsToLogos.put("large", jsonSource.getJSONObject("urlsToLogos").getString("large"));
            List<String> mSortBysAvailable = new ArrayList<>();
            int mLength = jsonSource.getJSONArray("sortBysAvailable").length();
            for(int i = 0; i < mLength; i++) {
                mSortBysAvailable.add(jsonSource.getJSONArray("sortBysAvailable").getString(i));
            }
            mSource.setId(jsonSource.getString("id"))
                    .setName(jsonSource.getString("name"))
                    .setDescription(jsonSource.getString("description"))
                    .setUrl(jsonSource.getString("url"))
                    .setCategory(jsonSource.getString("category"))
                    .setLanguage(jsonSource.getString("language"))
                    .setCountry(jsonSource.getString("country"))
                    .setUrlsToLogos(mUrlsToLogos)
                    .setSortBysAvailable(mSortBysAvailable);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mSource;
    }

    public static List<Source> build(JSONArray jsonSources) {
        if(jsonSources == null) return null;
        List<Source> mSources = new ArrayList<>();
        int mLength = jsonSources.length();
        for(int i = 0; i < mLength; i++) {
            try {
                mSources.add(Source.build(jsonSources.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mSources;
    }

}
