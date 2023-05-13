package com.example.disneycharacter.fragment;

import static com.example.disneycharacter.activity.FactsActivity.processArray;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.disneycharacter.R;
import com.squareup.picasso.Picasso;

public class ContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facts_activity, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            com.example.disneycharacter.entity.Character
                    character = bundle.getParcelable("character");

            if (character != null) {
                // Использование данных character во фрагменте
                ImageView iconImageView = view.findViewById(R.id.icon);
                TextView nameTextView = view.findViewById(R.id.name);
                TextView filmTextView = view.findViewById(R.id.film_data);
                TextView shortFilmsTextView = view.findViewById(R.id.shortFilms_data);
                TextView tvShowsTextView = view.findViewById(R.id.tvShows_data);
                TextView videoGamesTextView = view.findViewById(R.id.videoGames_data);

                Picasso.get().load(character.getImageUrl()).into(iconImageView);
                nameTextView.setText(character.getName());
                filmTextView.setText(processArray(character.getFilms()));
                shortFilmsTextView.setText(processArray(character.getShortFilms()));
                tvShowsTextView.setText(processArray(character.getTvShows()));
                videoGamesTextView.setText(processArray(character.getVideoGames()));
            }
        }


        return view;
    }
}
