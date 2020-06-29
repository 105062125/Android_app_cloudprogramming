package com.example.funkitapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecipeAdapter extends BaseAdapter {
    Context context;
    List<Recipe> recipeList;

    RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @Override
    public int getCount(){ return recipeList.size(); }

    @Override
    public View getView(final int position, View itemView, ViewGroup parent) {
        if(itemView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            itemView = layoutInflater.inflate(R.layout.recipe_item_view, parent, false);
        }

        Recipe recipe = recipeList.get(position);
        ImageView recipeImage = (ImageView) itemView.findViewById(R.id.recipeImage);
        recipeImage.setImageBitmap(recipe.getImage());

        TextView recipeAuthor = (TextView) itemView.findViewById(R.id.recipeViewAuthor);
        recipeAuthor.setText("作者: " + String.valueOf(recipe.getAuthor()) + "\n食譜名稱: " + String.valueOf(recipe.getName()));

        TextView recipeDifficulty = (TextView) itemView.findViewById(R.id.recipeViewDifficulty);
        recipeDifficulty.setText("難度: " + String.valueOf(recipe.getDifficulty()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开始传值
                Recipe recipe = recipeList.get(position);
                String author = recipe.getAuthor();
                String difficulty = recipe.getDifficulty();
                Bitmap image = recipe.getImage();
                String name = recipe.getName();
                String steps = recipe.getSteps();
                String material = recipe.getMaterial();

                // package all values to next page
                Intent intent = new Intent(context, SelectedRecipeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("author", author);
                bundle.putString("difficulty", difficulty);
                bundle.putString("name", name);
                bundle.putString("steps", steps);
                bundle.putString("material", material);
                bundle.putParcelable("image", image);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return itemView;
    }

    @Override
    public Object getItem(int position) { return recipeList.get(position); }

    @Override
    public long getItemId(int position) { return recipeList.get(position).getId();}
}
