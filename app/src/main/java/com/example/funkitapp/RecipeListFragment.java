package com.example.funkitapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeListFragment extends Fragment {
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private ListView recipeListView;
    private String encodeImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_fragment, container, false);
        TextView recipeTitleText = (TextView) v.findViewById(R.id.recipeViewTitle);
        recipeListView = (ListView) v.findViewById(R.id.recipeListView);
        String url = "https://21qozgfde9.execute-api.us-east-1.amazonaws.com/default/final_get_recipe?target=all";
        httpRequestToTextView(url, recipeTitleText);

//         upload recipe
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.resize);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
//        byte[] bytes = baos.toByteArray();
//        encodeImage = Base64.encodeToString(bytes, Base64.DEFAULT);
//        encodeImage = encodeImage.replace('/', '.');
//        encodeImage = encodeImage.replace('=', '_');
//        encodeImage = encodeImage.replace('+', '-');
//        encodeImage = encodeImage.replace("\n", "");
//        int imageLength = encodeImage.length();
//        String uploadUrl = "https://w99csuw80l.execute-api.us-east-1.amazonaws.com/default/recipe_upload_to_db";
//        int i = 0, imgNum = 0;
//        while(i < imageLength){
//            if(i + 350000 > imageLength){
//                httpRequestUploadRecipe(uploadUrl, encodeImage.substring(i, imageLength), "Adam", "hard", "2-"+Integer.toString(imgNum), Integer.toString(imageLength/350000+1));
//            }
//            else{
//                httpRequestUploadRecipe(uploadUrl, encodeImage.substring(i, i+350000), "Adam", "hard", "2-"+Integer.toString(imgNum), Integer.toString(imageLength/350000+1));
//            }
//            i += 350000;
//            imgNum++;
//        }

        return v;
    }

    public static RecipeListFragment newInstance() {

        RecipeListFragment f = new RecipeListFragment();

        return f;
    }


    public void httpRequestUploadRecipe(final String url, String image, String author, String difficulty, String ID, String imgSize) {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("author", author)
                .add("image", image)
                .add("difficulty", difficulty)
                .add("ID", ID)
                .add("imgSize", imgSize)
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    final String myResponse = response.body().string();
                }
            }
        });
    }

    public void httpRequestToTextView(final String url,final TextView id) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    try {
                        jsonObject = new JSONObject(myResponse);
                        jsonArray = jsonObject.getJSONArray("Items");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setRecipeList(jsonArray);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public boolean isInImageArray(String[][] array, String target){
        for(int i = 0;i < 100;i++){
            if(array[i][0].equals(target) == true){
                return true;
            }
        }
        return false;
    }

    public void setRecipeList(JSONArray jsonArray) {
        String[][] imageArray = new String[100][100];
        for(int i = 0;i < 100 ;i++){
            for(int j = 0;j < 100;j++){
                imageArray[i][j] = "";
            }
        }
        List<Recipe> recipeList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject recipeObject = jsonArray.getJSONObject(i);
                String imageId = recipeObject.getJSONObject("food_id").getString("S").split("-")[0];
                if( !isInImageArray(imageArray, imageId)) {
                    int nullIndex = 0;
                    while(imageArray[nullIndex][0] != ""){
                        nullIndex++;
                    }
                    imageArray[nullIndex][0] = imageId;
                }
                for(int j = 0;j < 100;j++){
                    if(imageArray[j][0].equals(imageId)){
                        int imgPos = Integer.parseInt(recipeObject.getJSONObject("food_id").getString("S").split("-")[1]);
                        imageArray[j][imgPos+1] = recipeObject.getJSONObject("image").getString("S");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String preRecipe = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject recipeObject = null;
            try {
                recipeObject = jsonArray.getJSONObject(i);
                String author = recipeObject.getJSONObject("author").getString("S");
                String difficulty = recipeObject.getJSONObject("difficulty").getString("S");
                String imageId = recipeObject.getJSONObject("food_id").getString("S").split("-")[0];
                String name = recipeObject.getJSONObject("name").getString("S");
                String steps = recipeObject.getJSONObject("steps").getString("S");
                String material = recipeObject.getJSONObject("material").getString("S");

                // prevent doing again
                if(preRecipe.equals(imageId)){
                    continue;
                }
                preRecipe = imageId;

                String image64 = "";
                for(int j = 0;j < 100;j++){
                    if(imageArray[j][0].equals(imageId)){
                        for(int k = 1;k < 100;k++){
                            image64 += imageArray[j][k];
                        }
                    }
                }
                image64 = image64.replace('.', '/');
                image64 = image64.replace('_', '=');
                image64 = image64.replace('-', '+');
                byte[] decodeString = Base64.decode(image64, Base64.DEFAULT);
                Bitmap image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                recipeList.add(new Recipe(author, difficulty, image, steps, name, material));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        recipeListView.setAdapter(new RecipeAdapter(getActivity(), recipeList));
    }
}
