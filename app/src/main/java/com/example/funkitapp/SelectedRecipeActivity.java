package com.example.funkitapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectedRecipeActivity extends AppCompatActivity {
    private String author, difficulty, name, steps, material;
    private Bitmap image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_recipe);

        Bundle bundle = getIntent().getExtras();
        author = bundle.getString("author");
        difficulty = bundle.getString("difficulty");
        name = bundle.getString("name");
        steps = bundle.getString("steps");
        material = bundle.getString("material");
        image = bundle.getParcelable("image");

        TextView authorAndMaterialView = findViewById(R.id.selected_recipe_material_and_author);
        TextView stepsView = findViewById(R.id.selected_recipe_steps);
        ImageView imageView = findViewById(R.id.selected_recipe_image);

        authorAndMaterialView.setText("作者: " + author + "難度: " + difficulty + "\n所需食材: " + material);
        imageView.setImageBitmap(image);
        String[] step = steps.split("/");
        String stepText = "";
        for(int i=0;i < step.length;i++){
            stepText += "步驟" + String.valueOf(i+1) + ": " + step[i] + "\n";
        }
        stepsView.setText(stepText);
    }

    public void resume(View view) {
        finish();
    }

    public void onClickStart(View view) {
        // package all values to next page
        Intent intent = new Intent(this, BlueToothMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("steps", steps);
//        bundle.putParcelable("image", image);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
