package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject sandwichobj = new JSONObject(json);
            JSONObject name = sandwichobj.getJSONObject("name");
            sandwich.setMainName(name.getString("mainName"));
            sandwich.setDescription(sandwichobj.getString("description"));
            sandwich.setImage(sandwichobj.getString("image"));
            sandwich.setPlaceOfOrigin(sandwichobj.getString("placeOfOrigin"));
            List<String> ingredients = parseJsonArray(sandwichobj.getJSONArray("ingredients"));
            List<String> knownas = parseJsonArray(name.getJSONArray("alsoKnownAs"));
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
