package com.example.funkitapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class UserInfoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_info_view, container, false);
        ListView recipeListView = (ListView) v.findViewById(R.id.userRecipeListView);
//        List<Recipe> recipeList = getRecipeList();

        UserInfo user = new UserInfo((float) 4.5, "Eason", "Profession");// temp init

        RatingBar ratingBar = (RatingBar)v.findViewById(R.id.userInfoStars);
        ratingBar.setRating(user.getStar());

        TextView ratingStarNumber = (TextView)v.findViewById(R.id.ratingStarNumber);
        ratingStarNumber.setText("(" + String.valueOf(user.getStar()) + ")");

        TextView userInfoName = (TextView)v.findViewById(R.id.userInfoName);
        userInfoName.setText("Name: " + user.getName());

        TextView userInfoLevel = (TextView)v.findViewById(R.id.userInfoLevel);
        userInfoLevel.setText("Chef level: " + String.valueOf(user.getLevel()));

        ListView userRecipeListView = (ListView) v.findViewById(R.id.userRecipeListView);
//        userRecipeListView.setAdapter(new UserRecipeAdapter(getActivity(), recipeList));

//        recipeListView.setAdapter(new RecipeAdapter(getActivity(), recipeList));
        return v;
    }

    public static UserInfoFragment newInstance() {

        UserInfoFragment f = new UserInfoFragment();

        return f;
    }

//    public List<Recipe> getRecipeList() {
//        List<Recipe> memberList = new ArrayList<>();
//        memberList.add(new Recipe("Eason", "easy", R.drawable.mapo));
//        memberList.add(new Recipe("Tom", "easy", R.drawable.mapo));
//        memberList.add(new Recipe("Jason", "difficult", R.drawable.mapo));
//        memberList.add(new Recipe("Tina", "easy", R.drawable.mapo));
//        memberList.add(new Recipe("Amy", "easy", R.drawable.mapo));
//        return memberList;
//    }
}
