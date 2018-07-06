package com.pdmproyect.ifmsaelsalvador.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdmproyect.ifmsaelsalvador.R;
import com.pdmproyect.ifmsaelsalvador.database.entities.Committee;

import java.util.List;

public class CommitteeAdapter extends RecyclerView.Adapter<CommitteeAdapter.ViewHolder> {

    private List<Committee> committees;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_comites,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Committee aux=committees.get(position);
        bindViews(aux, holder);
    }

    /**
     * @param aux committee that will be displayed into {@link android.support.v7.widget.CardView}
     * @param holder view holder that contains widgets to show
     */
    private void bindViews(Committee aux, ViewHolder holder){
        /*TODO bind views*/
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
