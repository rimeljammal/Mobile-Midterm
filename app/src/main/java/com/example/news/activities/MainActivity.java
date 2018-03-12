package com.example.news.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.api.ApiManager;
import com.example.news.models.ApiResponse;
import com.example.news.models.ArticleItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private Spinner types, countries;
    private Button search;
    private String genre, location;
    private ArrayAdapter<CharSequence> types_adapter, countries_adapter;
    private RecyclerView articlesRecyclerView;
    private ApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiManager = new ApiManager();

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

                if (type == 0)
                    genre = "entertainment";
                if (country == 0)
                    location = "gb";

                if (type == 1)
                    genre = "sports";
                if (country == 1)
                    location = "us";

                if (type == 2)
                    genre = "general";
                if (country == 2)
                    location = "fr";

                if (type == 3)
                    genre = "health";
                if (country == 3)
                    location = "it";

                if (type == 4)
                    genre = "science";

                if (type == 5)
                    genre = "technology";

                if (type == 6)
                    genre = "business";

                getArticles(location, genre);
            }
        };

        search = findViewById(R.id.search);
        search.setOnClickListener(listener);

    }

    public void getArticles(String country, String category) {
        apiManager.getArticles(country, category).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse articles = (ApiResponse) response.body();
                    if (articles != null) {
                        showArticles(articles);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

    private void showArticles(ApiResponse info) {
        List<ArticleItem> items = info.getArticles();
        ArticlesListAdapter adapter = new ArticlesListAdapter(items);
        articlesRecyclerView.setAdapter(adapter);
    }

    public static class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> {

        private List<ArticleItem> items;

        public ArticlesListAdapter(List<ArticleItem> items) {
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Context context = holder.itemView.getContext();
            ArticleItem articleItem = items.get(position);
            String title = String.valueOf(articleItem.getTitle());
            holder.title.setText(title);
            String description = String.valueOf(articleItem.getDescription());
            holder.description.setText(description);
            String link = String.valueOf(articleItem.getURL());
            holder.link.setText(link);
            String iconUrl = articleItem.getImageURL();
            Picasso.with(context).load(iconUrl).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView image;
            TextView title, description, link;

            public ViewHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.imageView);
                title = itemView.findViewById(R.id.textView2);
                description = itemView.findViewById(R.id.textView3);
                link = itemView.findViewById(R.id.textView4);

            }
        }

    }
}