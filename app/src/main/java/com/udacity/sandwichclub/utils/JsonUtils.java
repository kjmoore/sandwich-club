package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {
        Log.d(TAG, "Parsing Json: [" + json + "]");
        try {
            final JSONObject sandwich  = new JSONObject(json);

            final JSONObject names = sandwich.getJSONObject("name");
            final String mainName = names.getString("mainName");

            final JSONArray otherNamesArray = names.getJSONArray("alsoKnownAs");
            final List<String> otherNames = new ArrayList<>();
            for (int i = 0; i < otherNamesArray.length(); i++) {
                otherNames.add(otherNamesArray.getString(i));
            }


            final String description = sandwich.getString("description");
            final String placeOfOrigin = sandwich.getString("placeOfOrigin");
            final String image = sandwich.getString("image");

            final JSONArray ingredientsArray = sandwich.getJSONArray("ingredients");
            final List<String> ingredients = new ArrayList<>();
            for(int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i));
            }


            return new Sandwich(mainName, otherNames, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            Log.e(TAG, "Unable to parse JSON: [" + json + "]", e);
        }
        return null;
    }
}
