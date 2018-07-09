package com.pdmproyect.ifmsaelsalvador.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdmproyect.ifmsaelsalvador.R;
import com.pdmproyect.ifmsaelsalvador.database.entities.Committee;

import java.util.List;

public abstract class CommitteeAdapter extends RecyclerView.Adapter<CommitteeAdapter.ViewHolder> {

    private List<Committee> committees;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_comites, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Committee aux = committees.get(position);
        bindViews(aux, holder);
    }

    /**
     * @param aux    committee that will be displayed into {@link android.support.v7.widget.CardView}
     * @param holder view holder that contains widgets to show
     */
    private void bindViews(Committee aux, ViewHolder holder) {
        holder.name = aux.getName();
    }

    @Override
    public int getItemCount() {
        return committees == null ? 0 : committees.size();
    }


    /**
     * @param committees list of committees that the {@link RecyclerView} will show
     **/
    public void setCommittees(List<Committee> committees) {
        this.committees = committees;
    }

    public abstract void onCommitteeClick(String name);

    public class ViewHolder extends RecyclerView.ViewHolder {

        String name;

        ImageView imageViewLogoCommittee;
        TextView textViewNameCommittee, textViewDefinitionNameCommittee;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewLogoCommittee = itemView.findViewById(R.id.ImageView_IComite_Comite);
            textViewNameCommittee = itemView.findViewById(R.id.TextView_Acronimo_Comite);
            textViewDefinitionNameCommittee = itemView.findViewById(R.id.TextView_DefAcronimo_Comite);
            itemView.setOnClickListener(v -> onCommitteeClick(name));
        }
    }
}
