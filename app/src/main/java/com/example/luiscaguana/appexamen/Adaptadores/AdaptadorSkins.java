package com.example.luiscaguana.appexamen.Adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luiscaguana.appexamen.R;
import com.example.luiscaguana.appexamen.modelo.Skin;

import java.util.ArrayList;

public class AdaptadorSkins  extends RecyclerView.Adapter<AdaptadorSkins.SkinVH>
        implements  View.OnClickListener{

    //simpre igual 1
    private ArrayList<Skin> datosSkin;
    private View.OnClickListener listener;

    //clase del Skingvh 2
    public static class SkinVH extends RecyclerView.ViewHolder{
           //de la clase layout
        private TextView tvNombre;
        private TextView tvRareza;


        public SkinVH(View itemView) {
            super(itemView);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvRareza = (TextView) itemView.findViewById(R.id.tvRareza);

        }

        public TextView getTvNombre(){
            return  tvNombre;

        }

        public  TextView getTvRareza(){
            return  tvRareza;
        }


    }

    ///clase estructura igual 3
    public AdaptadorSkins(ArrayList<Skin> datosSkin){
        this.datosSkin = datosSkin;
    }


    @NonNull
    @Override
    public SkinVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //clase  igual con diferente estructura
        View V = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skin_layout, parent, false);
        V.setOnClickListener(this);
        SkinVH vhp = new SkinVH(V);
        return  vhp;


    }

    @Override
    public void onBindViewHolder(@NonNull SkinVH holder, int position) {
        //importamos los datos que vamos a necesitar estructura es igual
        holder.tvRareza.setText(datosSkin.get(position).getName());
        holder.tvRareza.setText(datosSkin.get(position).getRarity());
    }

    @Override
    public int getItemCount() {
        //estructura igual solo cambiamos por los datos necesarios
        return datosSkin.size();
    }

    //clase cerar a mano no sale auntomatica
    public  void setOnClickListener(View.OnClickListener listener){
      this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        //misma estructura
        if (listener != null){
            listener.onClick(view);
        }

    }



}
