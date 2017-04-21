package pe.edu.upc.catchup.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import pe.edu.upc.catchup.CatchUpApp;
import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.backend.models.Article;

public class ArticleActivity extends AppCompatActivity {
    ANImageView mPictureANImageView;
    TextView mTitleTextView;
    TextView mAuthorTextView;
    TextView mDescriptionTextView;
    Article mCurrentArticle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPictureANImageView = (ANImageView) findViewById(R.id.pictureANImageView);
        mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        mAuthorTextView = (TextView) findViewById(R.id.authorTextView);
        mDescriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        mCurrentArticle = CatchUpApp.getInstance().getCurrentArticle();
        mPictureANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        mPictureANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        mPictureANImageView.setImageUrl(mCurrentArticle.getUrlToImage());
        mTitleTextView.setText(mCurrentArticle.getTitle());
        mAuthorTextView.setText(mCurrentArticle.getAuthor());
        mDescriptionTextView.setText(mCurrentArticle.getDescription());
    }

}
