package com.example.funkitapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserRecipeAdapter extends BaseAdapter {
    Context context;
    List<Recipe> recipeList;

    UserRecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @Override
    public int getCount(){ return recipeList.size(); }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        if(itemView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            itemView = layoutInflater.inflate(R.layout.user_recipe_item_view, parent, false);
        }

        Recipe recipe = recipeList.get(position);
        ImageView recipeImage = (ImageView) itemView.findViewById(R.id.userRecipeImage);
//        recipeImage.setImageResource(recipe.getImage());

        TextView recipeDifficulty = (TextView) itemView.findViewById(R.id.userRecipeDifficulty);
        recipeDifficulty.setText(String.valueOf(recipe.getDifficulty()));
        return itemView;
    }

    @Override
    public Object getItem(int position) { return recipeList.get(position); }

    @Override
    public long getItemId(int position) { return recipeList.get(position).getId();}
}
