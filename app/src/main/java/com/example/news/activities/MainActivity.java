package com.example.news.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.news.R;

public class MainActivity extends AppCompatActivity {

    Spinner types, countries;
    Button search;
    ArrayAdapter<CharSequence> types_adapter, countries_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        types = findViewById(R.id.type);
        types_adapter = ArrayAdapter.createFromResource(this, R.array.news_types, android.R.layout.simple_spinner_item);
        types_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        types.setAdapter(types_adapter);

        countries = findViewById(R.id.country);
        countries_adapter = ArrayAdapter.createFromResource(this, R.array.news_countries, android.R.layout.simple_spinner_item);
        countries_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countries.setAdapter(countries_adapter);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int type = types.getSelectedItemPosition();
                int country = countries.getSelectedItemPosition();

                Context context = getApplicationContext();

                Toast toast = Toast.makeText(context, type + " " + country, Toast.LENGTH_SHORT);
                toast.show();

                String genre, location;

                if(type == 0)
                    genre = "entertainment";
                if (country == 0)
                    location = "gb";

                if(type == 1)
                    genre = "sports";
                if (country == 1)
                    location = "us";

                if(type == 2)
                    genre = "general";
                if (country == 2)
                    location = "fr";

                if(type == 3)
                    genre = "health";
                if (country == 3)
                    location = "it";

                if(type == 4)
                    genre = "science";

                if(type == 5)
                    genre = "technology";

                if(type == 6)
                    genre = "business";
            }
        };

        search = findViewById(R.id.search);
        search.setOnClickListener(listener);

    }
}