package com.pdmproyect.ifmsaelsalvador.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdmproyect.ifmsaelsalvador.database.entities.Committee;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private List<Committee> committees;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*View view= LayoutInflater.from(parent.getContext()).inflate(,parent, false);
        return new ViewHolder(view);*/
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return committees==null?0:committees.size();
    }


    /**
     * @param committees list of committees that the {@link RecyclerView} will show
     *
     **/
    public void setCommittees(List<Committee> committees) {
        this.committees = committees;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
