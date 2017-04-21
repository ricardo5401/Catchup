package pe.edu.upc.catchup.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.upc.catchup.CatchUpApp;
import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.activities.ArticleActivity;
import pe.edu.upc.catchup.backend.models.Article;

/**
 * Created by operador on 4/6/17.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private List<Article> mArticles;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_article, parent, false);
        ViewHolder mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitleTextView.setText(mArticles.get(position).getTitle());
        holder.mPictureImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.mPictureImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.mPictureImageView.setImageUrl(mArticles.get(position).getUrlToImage());
        holder.mArticleCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatchUpApp.getInstance().setCurrentArticle(mArticles.get(position));
                Intent mArticleIntent = new Intent(v.getContext(), ArticleActivity.class);
                ActivityOptionsCompat mOptions =
                        ActivityOptionsCompat
                                .makeSceneTransitionAnimation((Activity) v.getContext(),
                                        holder.mPictureImageView, "picture");

                v.getContext().startActivity(mArticleIntent, mOptions.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public List<Article> getArticles() {
        return mArticles;
    }

    public void setArticles(List<Article> mArticles) {
        this.mArticles = mArticles;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView mArticleCardView;
        ANImageView mPictureImageView;
        TextView mTitleTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mArticleCardView = (CardView) itemView.findViewById(R.id.articleCardView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            mPictureImageView = (ANImageView) itemView.findViewById(R.id.pictureANImageView);
        }
    }
}
