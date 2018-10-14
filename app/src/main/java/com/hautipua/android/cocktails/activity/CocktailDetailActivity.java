package com.hautipua.android.cocktails.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hautipua.android.cocktails.R;
import com.hautipua.android.cocktails.model.Cocktail;

public class CocktailDetailActivity extends AppCompatActivity {

    private static final String NAME_DATABASE = "Cocktails";
    private Button btnCocktailDetailMenu;
    private ImageButton btnCocktailDetailBack;
    private Button btnCocktailDetailFavourite;
    private ImageView ivCocktailDetailPhoto;
    private TextView tvCocktailDetailName;
    private TextView tvCocktailDetailIngredients;
    private TextView tvCocktailDetailDirections;
    private static String ACTION_FAVOURITE_BUTTON = "add";
    private final String ACTION_ADD = "add";
    private final String ACTION_REMOVE = "remove";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_detail);

        btnCocktailDetailMenu = findViewById(R.id.btnCocktailDetailMenu);
        btnCocktailDetailBack = findViewById(R.id.btnCocktailDetailBack);
        btnCocktailDetailFavourite = findViewById(R.id.btnCocktailDetailFavourite);

        Bundle cocktailData = getIntent().getExtras();

        final Cocktail cocktail = (Cocktail) cocktailData.getSerializable("objectCocktail");

        this.fillCocktailDetail(cocktail);

        this.setButtonFavourite(cocktail.getId(), btnCocktailDetailFavourite);

        btnCocktailDetailMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        btnCocktailDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CocktailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        btnCocktailDetailFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase cocktailsDB = openOrCreateDatabase(NAME_DATABASE, MODE_PRIVATE, null);

                if(ACTION_FAVOURITE_BUTTON == ACTION_ADD)
                {
                    cocktailsDB.execSQL("INSERT INTO favourites (id) VALUES (" + cocktail.getId() + ")");
                    ACTION_FAVOURITE_BUTTON = ACTION_REMOVE;
                    btnCocktailDetailFavourite.setBackgroundResource(R.drawable.remove_favourite_button);
                }
                else
                {
                    cocktailsDB.execSQL("DELETE FROM favourites WHERE id = " + cocktail.getId());

                    ACTION_FAVOURITE_BUTTON = ACTION_ADD;
                    btnCocktailDetailFavourite.setBackgroundResource(R.drawable.add_favourite_button);
                }

                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });
    }

    private void fillCocktailDetail(Cocktail cocktail)
    {
        ivCocktailDetailPhoto = findViewById(R.id.ivCocktailDetailPhoto);
        tvCocktailDetailName = findViewById(R.id.tvCocktailDetailName);
        tvCocktailDetailIngredients = findViewById(R.id.tvCocktailDetailIngredients);
        tvCocktailDetailDirections = findViewById(R.id.tvCocktailDetailDirections);

        int _photoId = getApplicationContext().getResources().getIdentifier(cocktail.getPhotoId().replace(".png", ""), "drawable", getApplicationContext().getPackageName());
        ivCocktailDetailPhoto.setImageResource(_photoId);

        tvCocktailDetailName.setText(cocktail.getName());
        tvCocktailDetailIngredients.setText(cocktail.getIngredients().replace(";", "\n"));
        tvCocktailDetailDirections.setText(cocktail.getDirections().replace(";", "\n"));
    }

    private void setButtonFavourite(int idCocktail, Button btnFavourite)
    {
        SQLiteDatabase cocktailsDB = openOrCreateDatabase(NAME_DATABASE, MODE_PRIVATE, null);

        String countDrinkFavourite = "SELECT id FROM favourites WHERE id = " + idCocktail;
        Cursor cursorDrinkFavourite = cocktailsDB.rawQuery(countDrinkFavourite, null);
        cursorDrinkFavourite.moveToFirst();

        int cursorDrinkFavouriteCount = cursorDrinkFavourite.getCount();

        if(cursorDrinkFavouriteCount == 0)
        {
            ACTION_FAVOURITE_BUTTON = ACTION_ADD;
            btnFavourite.setBackgroundResource(R.drawable.add_favourite_button);
        }
        else
        {
            ACTION_FAVOURITE_BUTTON = ACTION_REMOVE;
            btnFavourite.setBackgroundResource(R.drawable.remove_favourite_button);
        }
    }

    // Methods to make Activity Fullscreen
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
