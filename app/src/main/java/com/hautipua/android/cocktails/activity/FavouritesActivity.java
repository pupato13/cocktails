package com.hautipua.android.cocktails.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import com.hautipua.android.cocktails.R;
import com.hautipua.android.cocktails.adapter.AdapterFavouritesList;
import com.hautipua.android.cocktails.helper.RecyclerItemClickListener;
import com.hautipua.android.cocktails.model.Cocktail;
import com.hautipua.android.cocktails.model.Favourite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    private static final String NAME_DATABASE = "Cocktails";
    private RecyclerView rvFavouritesList;
    private List<Favourite> favouritesList = new ArrayList<>();
    private Button btnFavouritesMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        btnFavouritesMenu = findViewById(R.id.btnFavouritesMenu);

        rvFavouritesList = findViewById(R.id.rvFavouritesList);

        this.populateFavouritesList();

        AdapterFavouritesList adapterFavouritesList = new AdapterFavouritesList(favouritesList, getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvFavouritesList.setLayoutManager(layoutManager);
        rvFavouritesList.setHasFixedSize(true);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        rvFavouritesList.addItemDecoration(itemDecorator);

        rvFavouritesList.setAdapter(adapterFavouritesList);

        rvFavouritesList.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        rvFavouritesList,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Favourite favourite = favouritesList.get(position);

                                SQLiteDatabase cocktailsDB = openOrCreateDatabase(NAME_DATABASE, MODE_PRIVATE, null);
                                String queryCocktailByFavourites = "SELECT d.id AS id, d.name AS name, d.ingredients AS ingredients, d.directions AS directions, d.photoID AS photoID, s.name AS spirit FROM drinks d INNER JOIN spirits s ON d.spiritId = s.id WHERE d.id = " + favourite.getId();

                                Cursor cursorAllCocktailByFavourites = cocktailsDB.rawQuery(queryCocktailByFavourites, null);

                                int indexCocktailId = cursorAllCocktailByFavourites.getColumnIndex("id");
                                int indexName = cursorAllCocktailByFavourites.getColumnIndex("name");
                                int indexIngredients = cursorAllCocktailByFavourites.getColumnIndex("ingredients");
                                int indexDirections = cursorAllCocktailByFavourites.getColumnIndex("directions");
                                int indexPhotoID = cursorAllCocktailByFavourites.getColumnIndex("photoID");
                                int indexSpirit = cursorAllCocktailByFavourites.getColumnIndex("spirit");

                                cursorAllCocktailByFavourites.moveToFirst();
                                int cursorAllCocktailByFavouritesCount = cursorAllCocktailByFavourites.getCount();

                                if(cursorAllCocktailByFavouritesCount == 1)
                                {
                                    int cocktailId = Integer.parseInt(cursorAllCocktailByFavourites.getString(indexCocktailId));
                                    String cocktailName = cursorAllCocktailByFavourites.getString(indexName);
                                    String cocktailIngredients = cursorAllCocktailByFavourites.getString(indexIngredients);
                                    String cocktailDirections = cursorAllCocktailByFavourites.getString(indexDirections);
                                    String cocktailPhotoId = cursorAllCocktailByFavourites.getString(indexPhotoID);
                                    String cocktailSpirit = cursorAllCocktailByFavourites.getString(indexSpirit);

                                    Cocktail cocktail = new Cocktail(cocktailId, cocktailName, cocktailIngredients, cocktailDirections, cocktailPhotoId, cocktailSpirit);

                                    Intent intent = new Intent(getApplicationContext(), CocktailDetailActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.putExtra("objectCocktail", (Serializable) cocktail);

                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        btnFavouritesMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    public void populateFavouritesList()
    {
        SQLiteDatabase cocktailsDB = openOrCreateDatabase(NAME_DATABASE, MODE_PRIVATE, null);

        String countFavouritesExists = "SELECT count(*) FROM favourites";
        Cursor cursorFavouritesExists = cocktailsDB.rawQuery(countFavouritesExists, null);
        cursorFavouritesExists.moveToFirst();

        int iCountFavouritesExists = cursorFavouritesExists.getInt(0);

        if(iCountFavouritesExists > 0)
        {
            String queryAllFavourites = "SELECT d.id AS id, d.name AS name FROM drinks d WHERE d.id IN (SELECT id FROM favourites)";

            Cursor cursorAllFavourites = cocktailsDB.rawQuery(queryAllFavourites, null);

            int indexFavouriteId = cursorAllFavourites.getColumnIndex("id");
            int indexName = cursorAllFavourites.getColumnIndex("name");

            cursorAllFavourites.moveToFirst();
            int cursorAllFavouritesCount = cursorAllFavourites.getCount();

            while (cursorAllFavourites != null
                    && cursorAllFavouritesCount != favouritesList.size()) {

                int favouriteId = Integer.parseInt(cursorAllFavourites.getString(indexFavouriteId));
                String favouriteName = cursorAllFavourites.getString(indexName);

                Favourite favourite = new Favourite(favouriteId, favouriteName);

                favouritesList.add(favourite);

                cursorAllFavourites.moveToNext();
            }
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

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
