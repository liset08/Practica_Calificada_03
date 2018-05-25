package com.cayhualla.practica_calificada_03.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cayhualla.practica_calificada_03.Activities.MainActivity;
import com.cayhualla.practica_calificada_03.ApiService;
import com.cayhualla.practica_calificada_03.R;
import com.cayhualla.practica_calificada_03.models.Denuncias;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LISET on 20/05/2018.
 */

public class DenunciasAdapter extends RecyclerView.Adapter<DenunciasAdapter.ViewHolder> {


        private List<Denuncias> denuncias;
    private Activity activity;
    public DenunciasAdapter(Activity activity){

        this.denuncias = new ArrayList<>();
        this.activity = activity;
    }


    public void setDenuncias(List<Denuncias> denuncias){
        this.denuncias = denuncias;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView fotoImage;
        public TextView nombreText;
        public TextView denuncianteText;
        public TextView ubicaText;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoImage = itemView.findViewById(R.id.foto_image);
            nombreText = itemView.findViewById(R.id.nombre_text);
            denuncianteText = itemView.findViewById(R.id.denunciante_text);
            ubicaText = itemView.findViewById(R.id.ubicacion_text);



        }}
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_denuncia, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(DenunciasAdapter.ViewHolder viewHolder, int position) {
        Denuncias denuncias = this.denuncias.get(position);

        viewHolder.nombreText.setText(denuncias.getTitulo());
        viewHolder.denuncianteText.setText(denuncias.getDescripcion());
        viewHolder.ubicaText.setText(denuncias.getUbicacion());


        String url = ApiService.API_BASE_URL + "/pfoto/" + denuncias.getPfoto();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);

    }



    @Override
    public int getItemCount() {
        return this.denuncias.size();
    }


    }

