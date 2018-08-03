package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String INGREDIENTS = "ingredients";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject sandwichobj = new JSONObject(json);
            JSONObject name = sandwichobj.getJSONObject(NAME);
            sandwich.setMainName(name.getString(MAIN_NAME));
            sandwich.setDescription(sandwichobj.getString(DESCRIPTION));
            sandwich.setImage(sandwichobj.getString(IMAGE));
            sandwich.setPlaceOfOrigin(sandwichobj.getString(PLACE_OF_ORIGIN));
            List<String> ingredients = parseJsonArray(sandwichobj.getJSONArray(INGREDIENTS));
            List<String> knownas = parseJsonArray(name.getJSONArray(ALSO_KNOWN_AS));
            sandwich.setIngredients(ingredients);
            sandwich.setAlsoKnownAs(knownas);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

    public static List<String> parseJsonArray(JSONArray jsonarray) throws JSONException {

        List<String> list = new ArrayList<>();

        for (int i=0; i<jsonarray.length(); i++) {

            list.add(jsonarray.getString(i));
        }

        return list;
    }
}
