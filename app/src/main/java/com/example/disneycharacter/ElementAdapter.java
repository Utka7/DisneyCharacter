package com.example.disneycharacter;

import static android.app.PendingIntent.getActivity;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.*;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<ItemElement> elements;

    private final Context mContext;


    ElementAdapter(Context context, List<ItemElement> elements) {
        this.elements = elements;
        this.inflater = LayoutInflater.from(context);
        mContext = context;
    }
    @Override
    public ElementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ElementAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ItemElement element = elements.get(position);
        holder.iconView.setImageResource(element.getIcon());
        holder.nameView.setText(element.getName());
//        holder.contentView.setText(element.getContent());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + elements.get(position).getName());

                Toast.makeText(mContext, elements.get(position).getName(), Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(mContext, FactsActivity.class);
                intent.putExtra("icon", elements.get(position).getIcon());
                intent.putExtra("name", elements.get(position).getName());
                intent.putExtra("content", elements.get(position).getContent());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView iconView;
        final TextView nameView;
        final TextView contentView;

        final ConstraintLayout parentLayout;
        ViewHolder(View view){
            super(view);
            iconView = view.findViewById(R.id.icon);
            nameView = view.findViewById(R.id.name);
            contentView = view.findViewById(R.id.content);

            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
