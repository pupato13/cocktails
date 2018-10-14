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
import android.widget.Toast;

import com.hautipua.android.cocktails.R;
import com.hautipua.android.cocktails.adapter.AdapterCocktailList;
import com.hautipua.android.cocktails.adapter.AdapterSpiritList;
import com.hautipua.android.cocktails.helper.RecyclerItemClickListener;
import com.hautipua.android.cocktails.model.Cocktail;
import com.hautipua.android.cocktails.model.Spirit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpiritActivity extends AppCompatActivity {

    private static final String NAME_DATABASE = "Cocktails";
    private RecyclerView rvSpiritList;
    private List<Spirit> spiritList = new ArrayList<>();
    private Button btnSpiritMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spirit);

        btnSpiritMenu = findViewById(R.id.btnSpiritMenu);

        rvSpiritList = findViewById(R.id.rvSpiritList);

        this.populateSpiritList();
        AdapterSpiritList adapterSpiritList = new AdapterSpiritList(spiritList, getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvSpiritList.setLayoutManager(layoutManager);
        rvSpiritList.setHasFixedSize(true);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        rvSpiritList.addItemDecoration(itemDecorator);

        rvSpiritList.setAdapter(adapterSpiritList);

        rvSpiritList.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        rvSpiritList,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Spirit spirit = spiritList.get(position);

                                Intent intent = new Intent(getApplicationContext(), CocktailsBySpiritActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.putExtra("objectSpiritSelected", (Serializable) spirit);

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

        btnSpiritMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    public void populateSpiritList()
    {
        SQLiteDatabase cocktailsDB = openOrCreateDatabase(NAME_DATABASE, MODE_PRIVATE, null);

        String countSpiritsExists = "SELECT count(*) FROM spirits";
        Cursor cursorSpiritsExists = cocktailsDB.rawQuery(countSpiritsExists, null);
        cursorSpiritsExists.moveToFirst();

        int iCountSpiritsExists = cursorSpiritsExists.getInt(0);

        if(iCountSpiritsExists > 0)
        {
            String queryAllSpirits = "SELECT id, name, quantity FROM spirits";

            Cursor cursorAllSpirits = cocktailsDB.rawQuery(queryAllSpirits, null);

            int indexSpiritId = cursorAllSpirits.getColumnIndex("id");
            int indexName = cursorAllSpirits.getColumnIndex("name");
            int indexQuantity = cursorAllSpirits.getColumnIndex("quantity");

            cursorAllSpirits.moveToFirst();
            int cursorAllSpiritsCount = cursorAllSpirits.getCount();

            while (cursorAllSpirits != null
                    && cursorAllSpiritsCount != spiritList.size()) {

                int spiritId = Integer.parseInt(cursorAllSpirits.getString(indexSpiritId));
                String spiritName = cursorAllSpirits.getString(indexName);
                int spiritQuantity = Integer.parseInt(cursorAllSpirits.getString(indexQuantity));

                Spirit spirit = new Spirit(spiritId, spiritName, spiritQuantity);

                spiritList.add(spirit);

                cursorAllSpirits.moveToNext();
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
