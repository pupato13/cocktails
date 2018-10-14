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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hautipua.android.cocktails.R;
import com.hautipua.android.cocktails.adapter.AdapterCocktailList;
import com.hautipua.android.cocktails.helper.RecyclerItemClickListener;
import com.hautipua.android.cocktails.model.Cocktail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CocktailsActivity extends AppCompatActivity {

    private static final String NAME_DATABASE = "Cocktails";
    private RecyclerView rvCocktailsList;
    private List<Cocktail> cocktailsList = new ArrayList<>();
    private Button btnCocktailsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails);

        btnCocktailsMenu = findViewById(R.id.btnCocktailsMenu);

        rvCocktailsList = findViewById(R.id.rvCocktailsList);

        this.populateCocktailsList();

        AdapterCocktailList adapterCocktailList = new AdapterCocktailList(cocktailsList, getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvCocktailsList.setLayoutManager(layoutManager);
        rvCocktailsList.setHasFixedSize(true);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        rvCocktailsList.addItemDecoration(itemDecorator);

        rvCocktailsList.setAdapter(adapterCocktailList);

        rvCocktailsList.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        rvCocktailsList,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Cocktail cocktail = cocktailsList.get(position);

                                Intent intent = new Intent(getApplicationContext(), CocktailDetailActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.putExtra("objectCocktail", (Serializable) cocktail);

                                startActivity(intent);
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

        btnCocktailsMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    public void populateCocktailsList()
    {
        SQLiteDatabase cocktailsDB = openOrCreateDatabase(NAME_DATABASE, MODE_PRIVATE, null);

        String countDrinksExists = "SELECT count(*) FROM drinks";
        Cursor cursorDrinksExists = cocktailsDB.rawQuery(countDrinksExists, null);
        cursorDrinksExists.moveToFirst();

        int iCountDrinksExists = cursorDrinksExists.getInt(0);

        if(iCountDrinksExists > 0)
        {
            String queryAllCocktails = "SELECT d.id AS id, d.name AS name, d.ingredients AS ingredients, d.directions AS directions, d.photoID AS photoID, s.name AS spirit FROM drinks d INNER JOIN spirits s ON d.spiritId = s.id";

            Cursor cursorAllCocktails = cocktailsDB.rawQuery(queryAllCocktails, null);

            int indexCocktailId = cursorAllCocktails.getColumnIndex("id");
            int indexName = cursorAllCocktails.getColumnIndex("name");
            int indexIngredients = cursorAllCocktails.getColumnIndex("ingredients");
            int indexDirections = cursorAllCocktails.getColumnIndex("directions");
            int indexPhotoID = cursorAllCocktails.getColumnIndex("photoID");
            int indexSpirit = cursorAllCocktails.getColumnIndex("spirit");

            cursorAllCocktails.moveToFirst();
            int cursorAllCocktailsCount = cursorAllCocktails.getCount();

            while (cursorAllCocktails != null
                    && cursorAllCocktailsCount != cocktailsList.size()) {
                int cocktailId = Integer.parseInt(cursorAllCocktails.getString(indexCocktailId));
                String cocktailName = cursorAllCocktails.getString(indexName);
                String cocktailIngredients = cursorAllCocktails.getString(indexIngredients);
                String cocktailDirections = cursorAllCocktails.getString(indexDirections);
                String cocktailPhotoId = cursorAllCocktails.getString(indexPhotoID);
                String cocktailSpirit = cursorAllCocktails.getString(indexSpirit);

                Cocktail cocktail = new Cocktail(cocktailId, cocktailName, cocktailIngredients, cocktailDirections, cocktailPhotoId, cocktailSpirit);

                cocktailsList.add(cocktail);

                cursorAllCocktails.moveToNext();
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
