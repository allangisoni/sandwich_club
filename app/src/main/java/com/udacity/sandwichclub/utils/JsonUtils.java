package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName() ;

    public static Sandwich parseSandwichJson(final String json) {
        /*
        **
        Sandwich information. Each sandwich's info is an element of the "sandwich_detail" array
        **
        */
        final String SW_NAME = "name";
        final String SW_MAIN_NAME = "mainName";
        final String SW_STRING_ALIAS = "alsoKnownAs";
        final List<String> SW_ALIAS = new ArrayList<>();
        final String SW_PLACE_OF_ORIGIN = "placeOfOrigin";
        final String SW_DESCRIPTION = "description";
        final String SW_IMAGE = "image";
        final List<String> SW_INGREDIENTS = new ArrayList<>();
        final String SW_STRING_INGREDIENTS = "ingredients";

        Sandwich sandwich =new Sandwich();

            try {
                JSONObject sandwichJsonObject = new JSONObject(json);


                JSONObject sandwichName = sandwichJsonObject.getJSONObject(SW_NAME);

                String mainName = sandwichName.getString(SW_MAIN_NAME);;

                if(sandwichName.has(SW_STRING_ALIAS)){
                    JSONArray aliasArray= sandwichName.getJSONArray(SW_STRING_ALIAS);


                    for(int i=0;i<aliasArray.length();i++){

                          SW_ALIAS.add(aliasArray.getString(i));
                    }
                }

                String placeofOrigin = sandwichJsonObject.getString(SW_PLACE_OF_ORIGIN);

                String description = sandwichJsonObject.getString(SW_DESCRIPTION);

                String image = sandwichJsonObject.getString(SW_IMAGE);

                if(sandwichJsonObject.has(SW_STRING_INGREDIENTS)){
                    JSONArray ingredientsArray= sandwichJsonObject.getJSONArray(SW_STRING_INGREDIENTS);

                    for(int i=0;i<ingredientsArray.length();i++){
                        SW_INGREDIENTS.add(ingredientsArray.getString(i));
                    }
                }
                sandwich.setMainName(mainName);
                sandwich.setAlsoKnownAs(SW_ALIAS);
                sandwich.setPlaceOfOrigin(placeofOrigin);
                sandwich.setDescription(description);
                sandwich.setImage(image);
                sandwich.setIngredients(SW_INGREDIENTS);


              return sandwich;


            } catch (final JSONException e){

                Log.e(TAG, "Json parsing error: " + e.getMessage());

            }


        return null;
    }


}
