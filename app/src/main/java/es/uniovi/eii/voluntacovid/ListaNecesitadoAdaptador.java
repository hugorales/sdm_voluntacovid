package es.uniovi.eii.voluntacovid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import es.uniovi.eii.voluntacovid.modelo.Ayuda;

public class ListaNecesitadoAdaptador extends RecyclerView.Adapter<ListaNecesitadoAdaptador.ViewHolderDatos>
            implements View.OnClickListener {

    List<Ayuda> listaNecesitado;
    private View.OnClickListener listener;

    public ListaNecesitadoAdaptador(List<Ayuda> listaNecesitado) {
        this.listaNecesitado = listaNecesitado;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listaNecesitado.get(position).getTitulo(),listaNecesitado.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listaNecesitado.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!= null){
            listener.onClick(v);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView descripcion;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.txTitulo);
            descripcion = itemView.findViewById(R.id.txDescripcion);
        }

        public void asignarDatos(String tituloo,String descripcionn) {
            titulo.setText(tituloo);
            descripcion.setText(descripcionn);
        }
    }
}
