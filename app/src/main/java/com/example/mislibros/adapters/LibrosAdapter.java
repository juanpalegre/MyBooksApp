package com.example.mislibros.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mislibros.R;
import com.example.mislibros.modelos.Libro;

import java.util.List;
//Lo comentado corresponde a la aplicación de la interfaz para escuchar los clicks del Recycler desde el propio adapter; la que fue utilizada, porque considero una mejor práctica, es la de generar una interfaz que pueda aplicarse a cualquier adapter utilizándo generics.
public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.LibrosViewHolder> {

    /* public interface OnLibroClickListener {
        void onLibroClick(Libro libro);
    }*/

    private List<Libro> libros;
    private OnItemClickListener<Libro> onItemClickListener;
    //private OnLibroClickListener onLibroClickListener;

    /*public void setOnLibroClickListener(OnLibroClickListener onLibroClickListener) {
        this.onLibroClickListener = onLibroClickListener;
    }*/

    public LibrosAdapter(List<Libro> libros) {
        this.libros = libros;
    }

    public void update(List<Libro> updateLibros ){
        libros.clear();
        libros.addAll(updateLibros);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<Libro> onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public LibrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLibroView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libros, parent, false);
        return new LibrosViewHolder(itemLibroView);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrosViewHolder holder, int position) {
        holder.bind(libros.get(position));
    }


    @Override
    public int getItemCount() {
        return libros.size();
    }

    class LibrosViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitulo;
        TextView tvAutor;

        public LibrosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tv_titulo);
            tvAutor = itemView.findViewById(R.id.tv_autor);
        }

        public void bind (final Libro libro){
            tvTitulo.setText(libro.getNombre());
            tvAutor.setText(libro.getAutor());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(libro);
                    }
                }
            });

            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onLibroClickListener != null){
                        onLibroClickListener.onLibroClick(libro);
                    }
                }
            });*/
        }
    }
}
