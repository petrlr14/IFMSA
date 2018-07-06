package com.pdmproyect.ifmsaelsalvador.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdmproyect.ifmsaelsalvador.R;
import com.pdmproyect.ifmsaelsalvador.database.entities.Project;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder>{

    private List<Project> projectList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_proyectos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Project aux=projectList.get(position);
        bindViews(aux, holder);
    }

    /**
     * @param aux project that will be displayed into {@link android.support.v7.widget.CardView}
     * @param holder view holder that contains widgets to show
     */
    private void bindViews(Project aux, ViewHolder holder){
        /*TODO bind views*/
    }

    @Override
    public int getItemCount() {
        return projectList==null?0:projectList.size();
    }


    /**
     * @param projectList list of projects that {@link RecyclerView} will show
     */
    public void setProjectList(List<Project> projectList){
        this.projectList=projectList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView committeeImage;
        TextView textViewProjectName, textViewPlace, textViewDate, textViewHour;
        public ViewHolder(View itemView) {
            super(itemView);
            committeeImage=itemView.findViewById(R.id.ImageView_foto_proyecto);
            textViewProjectName=itemView.findViewById(R.id.TextView_NombreProyecto_proyecto);
            textViewPlace=itemView.findViewById(R.id.TextView_LugarProyecto_Proyecto);
            textViewDate=itemView.findViewById(R.id.TextView_fecha_proyecto);
            textViewHour=itemView.findViewById(R.id.TextView_hora_proyecto);
        }
    }
}
