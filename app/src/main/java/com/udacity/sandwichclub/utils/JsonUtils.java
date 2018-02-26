package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        final String NAME = "name";
        final String MAIN_NAME = "mainName";
        final String AKA = "alsoKnownAs";
        final String PLACE_OF_ORIGIN = "placeOfOrigin";
        final String DESCRIPTION = "description";
        final String IMAGE_URL = "image";
        final String INGREDIENTS = "ingredients";

        try {
            JSONObject rootObject = new JSONObject(json);
            JSONObject nameObject = rootObject.optJSONObject(NAME);

            String mainName = nameObject.optString(MAIN_NAME);
            String origin = rootObject.optString(PLACE_OF_ORIGIN);
            String description = rootObject.optString(DESCRIPTION);
            String image = rootObject.optString(IMAGE_URL);

            JSONArray akaArray = nameObject.optJSONArray(AKA);
            List<String> akaList = new ArrayList<>();
            for (int i = 0, j = akaArray.length(); i < j; i++) {
                akaList.add(akaArray.optString(i));
            }

            JSONArray ingredientsArray = rootObject.optJSONArray(INGREDIENTS);
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0, j = ingredientsArray.length(); i < j; i++) {
                ingredientsList.add(ingredientsArray.optString(i));
            }

            return new Sandwich(mainName, akaList, origin, description, image, ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
