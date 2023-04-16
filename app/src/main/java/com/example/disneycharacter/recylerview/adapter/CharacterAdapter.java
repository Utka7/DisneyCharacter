package com.example.disneycharacter.recylerview.adapter;

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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disneycharacter.entity.Character;
import com.example.disneycharacter.activity.FactsActivity;
import com.example.disneycharacter.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<Character> characters;
    private final Context mContext;

    public CharacterAdapter(Context context, List<Character> characters) {
        this.characters = characters;
        this.inflater = LayoutInflater.from(context);
        mContext = context;
    }
    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new CharacterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Character character = characters.get(position);

        Picasso.get().load(character.getImageUrl()).into(holder.iconView);

        holder.nameView.setText(character.getName());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + characters.get(position).getName());

                Intent intent = new Intent(mContext, FactsActivity.class);
                intent.putExtra("character", character);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView iconView;
        final TextView nameView;
//        final TextView contentView;
        final ConstraintLayout parentLayout;
        ViewHolder(View view){
            super(view);
            iconView = view.findViewById(R.id.icon);
            nameView = view.findViewById(R.id.name);
//            contentView = view.findViewById(R.id.content);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
    
}
