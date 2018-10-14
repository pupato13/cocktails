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
import com.hautipua.android.cocktails.adapter.AdapterCocktailList;
import com.hautipua.android.cocktails.helper.RecyclerItemClickListener;
import com.hautipua.android.cocktails.model.Cocktail;
import com.hautipua.android.cocktails.model.Spirit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CocktailsBySpiritActivity extends AppCompatActivity {

    private static final String NAME_DATABASE = "Cocktails";
    private RecyclerView rvCocktailsBySpiritList;
    private List<Cocktail> cocktailsBySpiritList = new ArrayList<>();
    private Button btnCocktailsBySpiritMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails_by_spirit);

        btnCocktailsBySpiritMenu = findViewById(R.id.btnCocktailsBySpiritMenu);

        Bundle spiritData = getIntent().getExtras();

        final Spirit spirit = (Spirit) spiritData.getSerializable("objectSpiritSelected");

        rvCocktailsBySpiritList = findViewById(R.id.rvCocktailsBySpiritList);

        this.populateCocktailsBySpiritList(spirit.getId());

        AdapterCocktailList adapterCocktailList = new AdapterCocktailList(cocktailsBySpiritList, getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvCocktailsBySpiritList.setLayoutManager(layoutManager);
        rvCocktailsBySpiritList.setHasFixedSize(true);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        rvCocktailsBySpiritList.addItemDecoration(itemDecorator);

        rvCocktailsBySpiritList.setAdapter(adapterCocktailList);

        rvCocktailsBySpiritList.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        rvCocktailsBySpiritList,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Cocktail cocktail = cocktailsBySpiritList.get(position);

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

        btnCocktailsBySpiritMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    public void populateCocktailsBySpiritList(int spiritId)
    {
        SQLiteDatabase cocktailsDB = openOrCreateDatabase(NAME_DATABASE, MODE_PRIVATE, null);

        String countDrinksBySpiritExists = "SELECT count(*) FROM drinks";
        Cursor cursorDrinksBySpiritExists = cocktailsDB.rawQuery(countDrinksBySpiritExists, null);
        cursorDrinksBySpiritExists.moveToFirst();

        int iCountDrinksBySpiritExists = cursorDrinksBySpiritExists.getInt(0);

        if(iCountDrinksBySpiritExists > 0)
        {
            String queryAllCocktailsBySpirit = "SELECT d.id AS id, d.name AS name, d.ingredients AS ingredients, d.directions AS directions, d.photoID AS photoID, s.name AS spirit FROM drinks d INNER JOIN spirits s ON d.spiritId = s.id WHERE d.spiritId = " + spiritId;

            Cursor cursorAllCocktailsBySpirit = cocktailsDB.rawQuery(queryAllCocktailsBySpirit, null);

            int indexCocktailId = cursorAllCocktailsBySpirit.getColumnIndex("id");
            int indexName = cursorAllCocktailsBySpirit.getColumnIndex("name");
            int indexIngredients = cursorAllCocktailsBySpirit.getColumnIndex("ingredients");
            int indexDirections = cursorAllCocktailsBySpirit.getColumnIndex("directions");
            int indexPhotoID = cursorAllCocktailsBySpirit.getColumnIndex("photoID");
            int indexSpirit = cursorAllCocktailsBySpirit.getColumnIndex("spirit");

            cursorAllCocktailsBySpirit.moveToFirst();
            int cursorAllCocktailsBySpiritCount = cursorAllCocktailsBySpirit.getCount();

            while (cursorAllCocktailsBySpirit != null
                    && cursorAllCocktailsBySpiritCount != cocktailsBySpiritList.size()) {
                int cocktailId = Integer.parseInt(cursorAllCocktailsBySpirit.getString(indexCocktailId));
                String cocktailName = cursorAllCocktailsBySpirit.getString(indexName);
                String cocktailIngredients = cursorAllCocktailsBySpirit.getString(indexIngredients);
                String cocktailDirections = cursorAllCocktailsBySpirit.getString(indexDirections);
                String cocktailPhotoId = cursorAllCocktailsBySpirit.getString(indexPhotoID);
                String cocktailSpirit = cursorAllCocktailsBySpirit.getString(indexSpirit);

                Cocktail cocktail = new Cocktail(cocktailId, cocktailName, cocktailIngredients, cocktailDirections, cocktailPhotoId, cocktailSpirit);

                cocktailsBySpiritList.add(cocktail);

                cursorAllCocktailsBySpirit.moveToNext();
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
