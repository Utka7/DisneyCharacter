package com.example.disneycharacter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<ItemElement> elements;

    ElementAdapter(Context context, List<ItemElement> elements) {
        this.elements = elements;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ElementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ElementAdapter.ViewHolder holder, int position) {
        ItemElement element = elements.get(position);
        holder.iconView.setImageResource(element.getIcon());
        holder.nameView.setText(element.getName());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView iconView;
        final TextView nameView;
        ViewHolder(View view){
            super(view);
            iconView = view.findViewById(R.id.icon);
            nameView = view.findViewById(R.id.name);

        }
    }
}
