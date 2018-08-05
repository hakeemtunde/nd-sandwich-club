package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @BindView(R.id.also_known_tv)
    public TextView alsoKnownAs_tv;
    @BindView(R.id.origin_tv)
    public TextView placeOfOrigin_tv;
    @BindView(R.id.description_tv)
    public TextView description_tv;
    @BindView(R.id.ingredients_tv)
    public TextView ingredients_tv;
    @BindView(R.id.image_iv)
    public ImageView ingredientsIv;

    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable

            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        alsoKnownAs_tv.setText(convertToString(sandwich.getAlsoKnownAs()));
        placeOfOrigin_tv.setText(sandwich.getPlaceOfOrigin());
        description_tv.setText(sandwich.getDescription());
        ingredients_tv.setText(convertToString(sandwich.getIngredients()));
    }

    private String convertToString(List<String> stringList) {

        if (stringList.size() == 0) return "";

        StringBuilder stringBuilder = new StringBuilder();

        for(String stringItem : stringList) {
            stringBuilder.append(stringItem);
            stringBuilder.append(",");
        }

        String string = stringBuilder.toString();

        string = string.substring(0, string.length() - 1);

        return string;
    }

}
