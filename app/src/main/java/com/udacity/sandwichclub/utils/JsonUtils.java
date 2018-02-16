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
            JSONObject nameObject = rootObject.getJSONObject(NAME);

            String mainName = nameObject.getString(MAIN_NAME);
            String origin = rootObject.getString(PLACE_OF_ORIGIN);
            String description = rootObject.getString(DESCRIPTION);
            String image = rootObject.getString(IMAGE_URL);

            JSONArray akaArray = nameObject.getJSONArray(AKA);
            List<String> akaList = new ArrayList<>();
            for (int i = 0, j = akaArray.length(); i < j; i++) {
                akaList.add(akaArray.getString(i));
            }

            JSONArray ingredientsArray = rootObject.getJSONArray(INGREDIENTS);
            List<String> ingredientsList = new ArrayList<>();
            for (int i = 0, j = ingredientsArray.length(); i < j; i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }

            return new Sandwich(mainName, akaList, origin, description, image, ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
