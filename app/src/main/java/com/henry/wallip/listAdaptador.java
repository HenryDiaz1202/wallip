package com.henry.wallip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class listAdaptador extends RecyclerView.Adapter<listAdaptador.listAdaptadorViewHolder> {

    private ArrayList<listaElements> mListe;
    private ArrayList<listaElements> listaOriginal;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onPlay(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class listAdaptadorViewHolder extends RecyclerView.ViewHolder{

        ImageView iconosIMG;
        CardView cvV;
        TextView nombre,numero;

        public listAdaptadorViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            iconosIMG = itemView.findViewById(R.id.iconoimg);

            nombre = itemView.findViewById(R.id.palabraTXT);
            numero = itemView.findViewById(R.id.pronTXT);
            cvV = itemView.findViewById(R.id.cv);

            cvV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onPlay(position);
                        }
                    }
                }
            });
        }
    }

    public listAdaptador(ArrayList<listaElements> listeElements){
        mListe = listeElements;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(mListe);
    }

    @Override
    public listAdaptadorViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_contactos, parent, false);
        listAdaptadorViewHolder lsh = new listAdaptadorViewHolder(v, mListener);
        return lsh;
    }

    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud==0){
            mListe.clear();
            mListe.addAll(listaOriginal);
        }
        else{
           if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
               List<listaElements> collection = mListe.stream().filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                       .collect(Collectors.toList());
               mListe.clear();
               mListe.addAll(collection);
           }else{
               for(listaElements le: listaOriginal){
                   if(le.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                       mListe.add(le);
                   }
               }
           }
           notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(listAdaptadorViewHolder holder, int position){
        listaElements currecntItem = mListe.get(position);

        holder.nombre.setText(currecntItem.getNombre());
        holder.numero.setText(currecntItem.getNumero());
    }

    @Override
    public int getItemCount(){
        return mListe.size();
    }

}
