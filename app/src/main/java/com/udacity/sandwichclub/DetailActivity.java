package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    //private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

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
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage()).fit().centerCrop()
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView tvOrigin, tvdescription, tvingredients, tvAlias;
        tvAlias = findViewById(R.id.alias_tv);
        tvOrigin = findViewById(R.id.origin_tv);
        tvdescription = findViewById(R.id.description_tv);
        tvingredients = findViewById(R.id.ingredients_tv);
        tvAlias.setText(retrievStringFromArray(sandwich.getAlsoKnownAs()));
        tvOrigin.setText(sandwich.getPlaceOfOrigin());
        tvdescription.setText(sandwich.getDescription());
        tvingredients.setText(retrievStringFromArray(sandwich.getIngredients()));

        if (tvOrigin.getText().toString().matches("")){

            tvOrigin.setText("No place of origin found");
            //tvdescription.setText("No description found");

            //tvingredients.setText("No ingredients were found");
        } else {

        }

        if (tvAlias.getText().toString().matches("")){
            tvAlias.setText("No alias name found");
        }else {

        }
    }
    /**
     * This method creates strings from a given List of type String
     * @param stringList
     * @return
     */
    private String retrievStringFromArray(List<String> stringList) {
        if (stringList.isEmpty()) return null;
        return TextUtils.join(", ", stringList);
    }


}
