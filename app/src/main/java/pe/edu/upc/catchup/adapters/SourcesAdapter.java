package pe.edu.upc.catchup.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.catchup.CatchUpApp;
import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.activities.ArticlesActivity;
import pe.edu.upc.catchup.backend.models.Source;

/**
 * Created by operador on 4/5/17.
 */

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {
    private List<Source> mSources;

    @Override
    public SourcesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_source, parent, false);
        ViewHolder mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(SourcesAdapter.ViewHolder holder, final int position) {
        holder.mNameTextView.setText(mSources.get(position).getName());
        holder.mDescriptionTextView.setText(mSources.get(position).getDescription());
        holder.mSourceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatchUpApp.getInstance().setCurrentSource(mSources.get(position));
                v.getContext().startActivity(new Intent(v.getContext(), ArticlesActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSources.size();
    }

    public List<Source> getSources() {
        return mSources;
    }

    public void setSources(List<Source> mSources) {
        this.mSources = mSources;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView mSourceCardView;
        private TextView mNameTextView;
        private TextView mDescriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mSourceCardView = (CardView) itemView.findViewById(R.id.sourceCardView);
            mNameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
