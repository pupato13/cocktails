package com.hautipua.android.cocktails.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
// Import to get the correct 'R'
import com.hautipua.android.cocktails.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String NAME_DATABASE = "Cocktails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.createDatabase();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, 3000);
    }

    private void createDatabase()
    {
        try
        {
            SQLiteDatabase cocktailsDB = openOrCreateDatabase(NAME_DATABASE, MODE_PRIVATE, null);

            cocktailsDB.execSQL("CREATE TABLE IF NOT EXISTS spirits (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, quantity INT(3) NOT NULL)");
            cocktailsDB.execSQL("CREATE TABLE IF NOT EXISTS drinks (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, ingredients TEXT NOT NULL, directions TEXT NOT NULL, photoID TEXT NOT NULL, spiritId INTEGER NOT NULL, FOREIGN KEY(spiritId) REFERENCES spirits(id))");
            cocktailsDB.execSQL("CREATE TABLE IF NOT EXISTS favourites (id NOT NULL)");

            String count = "SELECT count(*) FROM drinks";

            Cursor dCursor = cocktailsDB.rawQuery(count, null);
            dCursor.moveToFirst();

            int iCount = dCursor.getInt(0);

            if(iCount == 0) {

                String querySpiritCognacINSERT = "INSERT INTO spirits (name, quantity) VALUES ('Cognac', 6)";
                cocktailsDB.execSQL(querySpiritCognacINSERT);

                String querySpiritOthersINSERT = "INSERT INTO spirits (name, quantity) VALUES ('Others', 15)";
                cocktailsDB.execSQL(querySpiritOthersINSERT);

                String querySpiritGinINSERT = "INSERT INTO spirits (name, quantity) VALUES ('Gin', 19)";
                cocktailsDB.execSQL(querySpiritGinINSERT);

                String querySpiritRumINSERT = "INSERT INTO spirits (name, quantity) VALUES ('Rum', 11)";
                cocktailsDB.execSQL(querySpiritRumINSERT);

                String querySpiritVodkaINSERT = "INSERT INTO spirits (name, quantity) VALUES ('Vodka', 15)";
                cocktailsDB.execSQL(querySpiritVodkaINSERT);

                String querySpiritWhiskyINSERT = "INSERT INTO spirits (name, quantity) VALUES ('Whisky', 7)";
                cocktailsDB.execSQL(querySpiritWhiskyINSERT);

                String querySpiritTequilaINSERT = "INSERT INTO spirits (name, quantity) VALUES ('Tequila', 4)";
                cocktailsDB.execSQL(querySpiritTequilaINSERT);

                String querySpiritCognacSELECT = "SELECT id FROM spirits where name = 'Cognac'";
                Cursor cursorSpiritCognacSELECT = cocktailsDB.rawQuery(querySpiritCognacSELECT, null);
                int indexSpiritCognacId = cursorSpiritCognacSELECT.getColumnIndex("id");
                cursorSpiritCognacSELECT.moveToFirst();
                int spiritCognacID = Integer.parseInt(cursorSpiritCognacSELECT.getString(indexSpiritCognacId));

                String querySpiritOthersSELECT = "SELECT id FROM spirits where name = 'Others'";
                Cursor cursorSpiritOthersSELECT = cocktailsDB.rawQuery(querySpiritOthersSELECT, null);
                int indexSpiritOthersId = cursorSpiritOthersSELECT.getColumnIndex("id");
                cursorSpiritOthersSELECT.moveToFirst();
                int spiritOthersID = Integer.parseInt(cursorSpiritOthersSELECT.getString(indexSpiritOthersId));

                String querySpiritGinSELECT = "SELECT id FROM spirits where name = 'Gin'";
                Cursor cursorSpiritGinSELECT = cocktailsDB.rawQuery(querySpiritGinSELECT, null);
                int indexSpiritGinId = cursorSpiritGinSELECT.getColumnIndex("id");
                cursorSpiritGinSELECT.moveToFirst();
                int spiritGinID = Integer.parseInt(cursorSpiritGinSELECT.getString(indexSpiritGinId));

                String querySpiritRumSELECT = "SELECT id FROM spirits where name = 'Rum'";
                Cursor cursorSpiritSELECT = cocktailsDB.rawQuery(querySpiritRumSELECT, null);
                int indexSpiritId = cursorSpiritSELECT.getColumnIndex("id");
                cursorSpiritSELECT.moveToFirst();
                int spiritRumID = Integer.parseInt(cursorSpiritSELECT.getString(indexSpiritId));

                String querySpiritVodkaSELECT = "SELECT id FROM spirits where name = 'Vodka'";
                Cursor cursorSpiritVodkaSELECT = cocktailsDB.rawQuery(querySpiritVodkaSELECT, null);
                int indexSpiritVodkaId = cursorSpiritVodkaSELECT.getColumnIndex("id");
                cursorSpiritVodkaSELECT.moveToFirst();
                int spiritVodkaID = Integer.parseInt(cursorSpiritVodkaSELECT.getString(indexSpiritVodkaId));

                String querySpiritWhiskySELECT = "SELECT id FROM spirits where name = 'Whisky'";
                Cursor cursorSpiritWhiskySELECT = cocktailsDB.rawQuery(querySpiritWhiskySELECT, null);
                int indexSpiritWhiskyId = cursorSpiritWhiskySELECT.getColumnIndex("id");
                cursorSpiritWhiskySELECT.moveToFirst();
                int spiritWhiskyID = Integer.parseInt(cursorSpiritWhiskySELECT.getString(indexSpiritWhiskyId));

                String querySpiritTequilaSELECT = "SELECT id FROM spirits where name = 'Tequila'";
                Cursor cursorSpiritTequilaSELECT = cocktailsDB.rawQuery(querySpiritTequilaSELECT, null);
                int indexSpiritTequilaId = cursorSpiritTequilaSELECT.getColumnIndex("id");
                cursorSpiritTequilaSELECT.moveToFirst();
                int spiritTequilaID = Integer.parseInt(cursorSpiritTequilaSELECT.getString(indexSpiritTequilaId));

                String queryAlexander = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Alexander', '30ml Cognac;30ml Créme de Cacao;30ml Fresh Cream;1 sprinkle nutmeg', 'Shake and strain into a chilled cocktail glass.;Sprinkle with fresh ground nutmeg.', 'alexander.png', " + spiritCognacID + ")";
                cocktailsDB.execSQL(queryAlexander);

                String queryAmericano = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Americano', '30ml Campari;30ml Vermouth;Splash Soda Water', 'Mix the ingredients directly in a rocks glass filled with ice cubes.;Add a splash of soda water and garnish with half an orange slice.', 'americano.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryAmericano);

                String queryAngelFace = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Angel Face', '30ml Calvados;30ml Gin;30ml Apricot Brandy', 'Pour all ingredients into a shaker with ice.;Shake and strain into a cocktail glass.', 'angel_face.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryAngelFace);

                String queryAviation = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Aviation', '45ml Gin;15ml Maraschino Liqueur;15ml Lemon Juice;1tsp Crème de Violette (optional)', 'Shake and strain into a chilled cocktail glass.', 'aviation.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryAviation);

                String queryB52 = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('B52', '20ml Grand Marnier;20ml Baileys Irish Cream;20ml Kahlúa', 'Layer the ingredients one at a time starting with Kahlúa, followed by the Baileys and then Grand Marnier.;Set the Grand Marnier alight, and serve while the flame is still on with a straw. Drink quickly.', 'b52.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryB52);

                String queryBacardi = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Bacardi', '45ml Bacardi White Rum;20ml Lime Juice;10ml Grenadine', 'Pour all of the ingredients into a shaker with ice cubes.;Shake well and strain into a chilled cocktail glass.', 'bacardi.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryBacardi);

                String queryBarracuda = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Barracuda', '45ml Gold Rum;15ml Galliano;60ml Pineapple Juice;60ml Prosecco;1 dash Lime Juice', 'Combine the ingredients in the glass, stir gently and serve.', 'barracuda.png', " + spiritRumID + ")";
                cocktailsDB.execSQL(queryBarracuda);

                String queryBellini = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Bellini', '100ml Prosecco;50ml fresh Peach Puree', 'Pour the peach puree into a chilled glass and add sparkling wine before stirring gently.', 'bellini.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryBellini);

                String queryBetweenTheSheets = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Between The Sheets', '30ml Cognac;30ml White Rum;30ml Triple Sec;20ml Lemon Juice', 'Pour all of the ingredients into a shaker with ice cubes.;Shake, and strain into a chilled cocktail glass.', 'between_the_sheets.png', " + spiritCognacID + ")";
                cocktailsDB.execSQL(queryBetweenTheSheets);

                String queryBlackRussian = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Black Russian', '50ml Vodka;20ml Coffee Liqueur', 'Pour the ingredients into a rocks glass filled with ice cubes before stirring gently.;To make a White Russian, float 20ml fresh Cream on the top and stir gently.', 'black_russian.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryBlackRussian);

                String queryBloodyMary = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Bloody Mary', '45ml Vodka;90ml Tomato Juice;15ml Lemon Juice;2 dashes of Worcestershire Sauce;1 dash Tabasco;1 pinch Celery Salt;1 pinch Pepper', 'Stir gently and pour all of the ingredients into a highball glass rimmed with celery salt. ;Garnish with the end of a celery stick.', 'bloody_mary.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryBloodyMary);

                String queryBramble = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Bramble', '40ml Gin;10ml Sugar Syrup;15ml Lemon Juice;15ml Blackberry Liqueur', 'Build the gin, syrup and lemon juice over crushed ice in a rocks glass.;Stir, then pour the blackberry liqueur over the top of the drink in a circular fashion.;Garnish with a lemon slice, and two blackberries.', 'bramble.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryBramble);

                String queryCaipirinha = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Caipirinha', '50ml Cachaça;4 fresh Lime wedges;2 teaspoons sugar', 'Place the lime and sugar into a rocks glass and muddle.;Fill the glass with ice and Cachaça. ;For a Caipiroska, use Vodka instead of Cachaça.', 'caipirinha.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryCaipirinha);

                String queryCasino = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Casino', '40ml Old Tom Gin;10ml Maraschino Liqueur;10ml Orange Bitters;10ml Lemon Juice', 'Pour all of the ingredients into shaker with ice cubes and shake well.;Strain into a chilled cocktail glass and garnish with a lemon twist and a maraschino cherry.', 'casino.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryCasino);

                String queryChampagneCocktail = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Champagne Cocktail', '90ml chilled Champagne;10ml Cognac;2 dashes Angostura Bitters;1 sugar cube;1 maraschino cherry', 'Soak the sugar cube in bitters and gently place it in a champagne flute.;Add the cognac and gently pour the champagne. Garnish with a maraschino cherry.', 'champagne_cocktail.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryChampagneCocktail);

                String queryCloverClub = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Clover Club', '45ml Gin;15ml Raspberry Syrup;15ml Lemon Juice;Few drops Egg White', 'Pour all of the ingredients into a cocktail shaker filled with ice.;Shake well and strain into a cocktail glass.', 'clover_club.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryCloverClub);

                String queryCosmopolitan = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Cosmopolitan', '40ml Lemon Vodka (substitute normal Vodka);15ml Cointrea;;30ml Cranberry Juice;15ml Lime Juice', 'Shake the ingredients over ice and strain into a cocktail glass.;Garnish with a slice of lime.', 'cosmopolitan.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryCosmopolitan);

                String queryCubaLibre = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Cuba Libre', '50ml White Rum;120ml Cola;10ml Lime Juice', 'Build all of the ingredients in a highball glass filled with ice.;Garnish with a lime wedge.', 'cuba_libre.png', " + spiritRumID + ")";
                cocktailsDB.execSQL(queryCubaLibre);

                String queryDaiquiri = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Daiquiri', '45ml White rum;15ml Simple syrup;25ml Fresh lime juice', 'Shake and strain into a cocktail glass.', 'daiquiri.png', " + spiritRumID + ")";
                cocktailsDB.execSQL(queryDaiquiri);

                String queryDark_n_Stormy = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Dark n Stormy', '60ml Dark Rum;100ml Ginger Beer', 'Pour straight into a highball glass filled with ice.;Garnish with a lime wedge.', 'dark_n_stormy.png', " + spiritRumID + ")";
                cocktailsDB.execSQL(queryDark_n_Stormy);

                String queryDerby = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Derby', '60ml Gin;2 drops Peach Bitters;2 fresh Mint Leaves', 'Pour all of the ingredients into a mixing glass with ice.;Stir and strain into a cocktail glass. Garnish with fresh mint leaves.', 'derby.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryDerby);

                String queryDirtyMartini = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Dirty Martini', '60ml Vodka;10ml Dry Vermouth;10ml Olive Juice', 'Pour all of the ingredients into a mixing glass with ice cubes and stir well.;Strain into a chilled cocktail glass and garnish with a green olive.', 'dirty_martini.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryDirtyMartini);

                String queryDryMartini = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Dry Martini', '60ml Gin;10ml Dry Vermouth', 'Pour all of the ingredients into a mixing glass with ice cubes.;Stir well. Strain into a chilled cocktail glass. Garnish with an olive.', 'dry_martini.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryDryMartini);

                String queryEspressoMartini = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Espresso Martini', '50ml Vodka;10ml Kahlúa;25ml Strong Espresso;Sugar Syrup to taste', 'Shake and strain into a chilled cocktail glass.', 'espresso_martini.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryEspressoMartini);

                String queryFrench75 = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('French 75', '30ml Gin;15ml Lemon Juice;2 dashes Sugar Syrup;60ml Champagne', 'Pour the Gin, Lemon Juice and Syrup into a shaker and shake.;Strain into a Champagne flute, and top with Champagne. Stir gently.', 'french_75.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryFrench75);

                String queryFrenchConnection = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('French Connection', '35ml Cognac;35ml Amaretto', 'Pour all of the ingredients directly into a rocks glass filled with ice cubes.;Stir gently.', 'french_connection.png', " + spiritCognacID + ")";
                cocktailsDB.execSQL(queryFrenchConnection);

                String queryFrenchMartini = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('French Martini', '45ml Vodka;15ml Raspberry Liqueur;15ml Pineapple Juice', 'Pour all of the ingredients into a shaker with ice.;Shake and strain into a chilled cocktail glass.', 'french_martini.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryFrenchMartini);

                String queryGinFizz = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Gin Fizz', '45ml Gin;10ml Sugar syrup;30ml Lemon Juice;80ml Soda Water', 'Shake the gin, syrup and lemon juice with ice. Pour into a glass, and top with soda water.;Garnish with a lemon slice.', 'gin_fizz.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryGinFizz);

                String queryGodfather = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Godfather', '35ml Scotch;35ml Amaretto', 'Pour all of the ingredients directly into a rocks glass filled with ice cubes.;Stir gently.', 'godfather.png', " + spiritWhiskyID + ")";
                cocktailsDB.execSQL(queryGodfather);

                String queryGodmother = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Godmother', '35ml Vodka;35ml Amaretto', 'Pour all of the ingredients directly into a rocks glass filled with ice cubes.;Stir gently.', 'godmather.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryGodmother);

                String queryGoldenDream = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Golden Dream', '20ml Galliano;20ml Triple Sec;20ml fresh Orange Juice;10ml fresh Cream', 'Pour all of the ingredients into a shaker filled with ice.;Shake briskly for few seconds.;Strain into a chilled cocktail glass.', 'golden_dream.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryGoldenDream);

                String queryGrasshopper = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Grasshopper', '30ml Crème de Cacao;30ml Crème de Menthe;30ml fresh Cream', 'Pour all of the ingredients into a shaker filled with ice.;Shake briskly for few seconds.;Strain into a chilled cocktail glass.', 'grasshopper.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryGrasshopper);

                String queryHarveyWallbanger = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Harvey Wallbanger', '45ml Vodka;90ml Orange Juice;15ml Galliano', 'Pour the vodka and orange juice into a highball glass filled with ice.;Stir gently and float the Galliano on top.;Garnish with orange slices and a cherry.', 'harvey_wallbanger.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryHarveyWallbanger);

                String queryHemingwaySpecial = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Hemingway Special', '60ml White Rum;15ml Maraschino Liqueur;40ml Grapefruit Juice;15ml Lime Juice', 'Pour all of the ingredients into a shaker with ice and shake.;Strain into a cocktail glass.', 'hemingway_special.png', " + spiritRumID + ")";
                cocktailsDB.execSQL(queryHemingwaySpecial);

                String queryHorsesNeck = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Horses Neck', '40ml Brandy;120ml Ginger Ale;1 dash of Angostura bitters', 'Pour the brandy and ginger ale directly into a highball glass filled with ice.;Stir gently, and add a dash of bitters.;Garnish with a lemon spiral.', 'horse_s_neck.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryHorsesNeck);

                String queryIrishCoffee = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Irish Coffee', '40ml Irish Whiskey;90ml Hot Coffee;30ml Fresh Cream;1 teaspoon brown sugar', 'Warm the Irish whiskey over a flame.;Pour the whiskey into the glass with the coffee, and add the sugar. Float the cream on top.', 'irish_coffee.png', " + spiritWhiskyID + ")";
                cocktailsDB.execSQL(queryIrishCoffee);

                String queryJohnCollins = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('John Collins', '45ml Gin;15ml Sugar syrup;30ml Lemon Juice;60ml Soda Water;1 dash Angostura bitters', 'Pour all of the ingredients directly into a highball glass filled with ice. Stir gently.;Garnish with a lemon slice and a maraschino cherry. Add a dash of Angostura bitters.;(Note: for a Tom Collins, use Old Tom Gin)', 'john_collins.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryJohnCollins);

                String queryKamikaze = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Kamikaze', '30ml Vodka;30ml Triple Sec;30ml Lime Juice', 'Shake and strain into a chilled cocktail glass.', 'kamikaze.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryKamikaze);

                String queryKir = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Kir', '90ml Dry White Wine;10ml Crème de Cassis', 'Pour the Crème de Cassis into a glass and top up with white wine.;For a Kir Royal: Use champagne instead of white wine.', 'kir.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryKir);

                String queryLemonDrop = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Lemon Drop', '25ml Lemon Vodka;20ml Triple Sec;15ml Lemon Juice', 'Shake and strain into a chilled cocktail glass rimmed with sugar, and garnish with a slice of lemon.', 'lemon_drop.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryLemonDrop);

                String queryLongIslandIcedTea = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Long Island Iced Tea', '15ml Gin;15ml Tequila;15ml Vodka;15ml White Rum;15ml Triple Sec;30ml Sugar Syrup;25ml Lemon Juice;Top with Cola', 'Pour the ingredients into a highball glass filled with ice. Add the cola and stir gently. Garnish with lemon and a straw.', 'long_island_iced_tea.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryLongIslandIcedTea);

                String queryMaiTai = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Mai-Tai', '40ml White Rum;20ml Dark Rum;15ml Orange Curaćao;15ml Orgeat Syrup;10ml Lime Juice', 'Shake and strain into a highball glass. ;Garnish with a pineapple spear, mint leaves, lime peel and a straw.', 'mai_tai.png', " + spiritRumID + ")";
                cocktailsDB.execSQL(queryMaiTai);

                String queryManhattan = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Manhattan', '50ml Rye Whiskey;20ml Red Vermouth;1 dash Angostura bitters', 'Pour all of the ingredients into a mixing glass with ice cubes.;Stir well and strain into a chilled cocktail glass. Garnish with a maraschino cherry.', 'manhattan.png', " + spiritWhiskyID + ")";
                cocktailsDB.execSQL(queryManhattan);

                String queryMargarita = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Margarita', '35ml Tequila;20ml Cointreau;15ml Lime Juice', 'Pour all of the ingredients into a shaker with ice.;Shake well and strain into a cocktail glass rimmed with salt.', 'margarita.png', " + spiritTequilaID + ")";
                cocktailsDB.execSQL(queryMargarita);

                String queryMaryPickford = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Mary Pickford', '60ml White Rum;10ml Maraschino Liqueur;10ml Grenadine Syrup;60ml fresh Pineapple Juice', 'Shake and strain into a chilled cocktail glass.', 'mary_pickford.png', " + spiritRumID + ")";
                cocktailsDB.execSQL(queryMaryPickford);

                String queryMimosa = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Mimosa', '75ml Champagne;75ml fresh Orange Juice', 'Pour the orange juice into a Champagne flute and add the Champagne.;Stir gently.', 'mimosa.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryMimosa);

                String queryMintJulep = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Mint Julep', '60ml Bourbon Whiskey;4 fresh mint sprigs;1 teaspoon powdered sugar;2 teaspoons water', 'Gently muddle the mint, sugar and water in a highball glass.;Fill the glass with cracked ice, add the Bourbon and stir well until water starts to condense on the glass.;Garnish with a mint spring.;(Note - this drink is traditionally served in a silver metal cup)', 'mint_julep.png', " + spiritWhiskyID + ")";
                cocktailsDB.execSQL(queryMintJulep);

                String queryMojito = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Mojito', '40ml White Cuban Rum;30ml Lime Juice;6 Mint Sprigs;2 teaspoons white sugar;Top with Soda Water', 'Muddle the mint springs with sugar and lime juice.;Add the rum and fill the glass with crushed ice.;Top with soda water.;Garnish with a spring of mint leaves and a lemon slice. Serve with a straw.', 'mojito.png', " + spiritRumID + ")";
                cocktailsDB.execSQL(queryMojito);

                String queryMonkeyGland = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Monkey Gland', '50ml Gin;30ml Orange juice;2 drops Absinthe;2 drops Grenadine', 'Shake and strain into a chilled cocktail glass.', 'monkey_gland.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryMonkeyGland);

                String queryMoscowMule = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Moscow Mule', '45ml Vodka;120ml Ginger beer;5ml Lime juice;1 lime slice', 'Mix the ingredients in a glass with crushed ice.;Garnish with a lime slice.;(Note - this drink is traditionally served in a copper mug)', 'moscow_mule.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryMoscowMule);

                String queryNegroni = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Negroni', '30ml Gin;30ml Campari;30ml Sweet Red Vermouth', 'Pour all of the ingredients directly into a rocks glass filled with ice.;Stir gently and garnish with a half orange slice.', 'negroni.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryNegroni);

                String queryOldFashioned = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Old Fashioned', '45ml Bourbon or Rye whiskey;2 dashes Angostura bitters;1 sugar cube;Few dashes plain water', 'Place a sugar cube in a rocks glass and saturate it with bitters.;Add a dash of plain water, and muddle until dissolved.;Fill the glass with ice cubes and add the whiskey.;Garnish with an orange slice and a cocktail cherry.', 'old_fashioned.png', " + spiritWhiskyID + ")";
                cocktailsDB.execSQL(queryOldFashioned);

                String queryParadise = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Paradise', '35ml Gin;20ml Apricot Brandy;15ml Orange Juice', 'Pour all of the ingredients into a cocktail shaker filled with ice. ;Shake and strain into a chilled cocktail glass.', 'paradise.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryParadise);

                String queryPinaColada = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Pina Colada', '30ml White Rum;90ml Pineapple juice;30ml Coconut cream', 'Blend all of the ingredients with ice in an electric blender, pour into a large wide glass.;Garnish with a slice of pineapple and a cocktail cherry. Serve with a straw.', 'pina_colada.png', " + spiritRumID + ")";
                cocktailsDB.execSQL(queryPinaColada);

                String queryPiscoSour = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Pisco Sour', '45ml Pisco;20ml Sugar Syrup;30ml Lemon Juice;1 small Egg White;1 dash Angostura bitters', 'Shake and strain into a chilled glass.;Dash some Angostura bitters on top.', 'pisco_sour.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryPiscoSour);

                String queryPlantersPunch = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Planters Punch', '45ml Dark rum;35ml Orange Juice;35ml fresh Pineapple Juice;20ml Lemon Juice;10ml Grenadine;10ml Sugar syrup;3 dashes Angostura bitters', 'Pour all of the ingredients except the bitters into a shaker filled with ice and shake.;Pour into a large glass filled with ice. Dash the bitters on top.;Garnish with a cocktail cherry and a pineapple spear.', 'planter_s_punch.png', " + spiritRumID + ")";
                cocktailsDB.execSQL(queryPlantersPunch);

                String queryPortoFlip = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Porto Flip', '15ml Brandy;45ml Red Port;10ml Egg Yolk', 'Pour all of the ingredients into a cocktail shaker filled with ice and shake well.;Strain into a cocktail glass and sprinkle with ground nutmeg.', 'porto_flip.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryPortoFlip);

                String queryRamosGinFizz = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Ramos Gin Fizz', '45ml Gin;30ml Sugar Syrup;15ml Lime Juice;15ml Lemon Juice;60ml Cream;1 Egg White;3 dashes Orange Flower Water;2 drops Vanilla Extract;Top with Soda Water', 'Pour all of the ingredients except the soda in a mixing glass, shake with no ice for two minutes, and then add ice and shake hard for another minute.;Strain into a highball glass without ice and top with soda.', 'ramos_gin_fizz.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryRamosGinFizz);

                String queryRose = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Rose', '20ml Kirsch;40ml Dry Vermouth;3 dashes Strawberry Syrup', 'Stir all of the ingredients with ice and strain into a cocktail glass.', 'rose.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(queryRose);

                String queryRussianSpringPunch = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Russian Spring Punch', '25ml Vodka;15ml Créme de Cassis;10ml Sugar Syrup;25ml Lemon Juice;75ml Champagne or Prosecco', 'Shake all of the ingredients and pour into highball glass filled with ice. Top with Champagne or prosecco.;Garnish with a lemon slice and a blackberry.', 'russian_spring_punch.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryRussianSpringPunch);

                String queryRustyNail = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Rusty Nail', '45ml Scotch whisky;25ml Drambuie', 'Pour all of the ingredients directly into a rocks glass filled with ice.;Stir gently and garnish with a lemon twist.', 'rusty_nail.png', " + spiritWhiskyID + ")";
                cocktailsDB.execSQL(queryRustyNail);

                String querySazerac = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Sazerac', '50ml Cognac (substitute Rye Whiskey);10ml Absinthe;1 sugar cube;2 dashes Peychaud’s bitters', 'Rinse a chilled rocks glass with the absinthe, add crushed ice and set it aside.;In a mixing glass, add the sugar cube and saturate it with the bitters. Add the cognac and stir with ice.;Discard the ice and absinthe from the rocks glass, and strain the drink into the glass.;Garnish with a lemon peel.', 'sazerac.png', " + spiritCognacID + ")";
                cocktailsDB.execSQL(querySazerac);

                String queryScrewdriver = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Screwdriver', '50ml Vodka;100ml Orange Juice', 'Pour all of the ingredients into a highball glass filled with ice. ;Stir gently and garnish with an orange slice.', 'screwdriver.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(queryScrewdriver);

                String querySeaBreeze = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Sea Breeze', '40ml Vodka;120ml Cranberry juice;30ml Grapefruit Juice;1 lime wedge', 'Build all of the ingredients in a rocks glass filled with ice.;Garnish with a lime wedge.', 'sea_breeze.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(querySeaBreeze);

                String querySexOnTheBeach = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Sex On The Beach', '40ml Vodka;20ml Peach schnapps;40ml Cranberry Juice;40ml Orange Juice', 'Build all of the ingredients into a highball glass filled with ice.;Garnish with an orange slice.', 'sex_on_the_beach.png', " + spiritVodkaID + ")";
                cocktailsDB.execSQL(querySexOnTheBeach);

                String querySidecar = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Sidecar', '50ml Cognac;20ml Triple Sec;20ml Lemon Juice', 'Pour all of the ingredients into a cocktail shaker filled with ice.;Shake well and strain into a cocktail glass rimmed with sugar.', 'sidecar.png', " + spiritCognacID + ")";
                cocktailsDB.execSQL(querySidecar);

                String querySingaporeSling = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Singapore Sling', '30ml Gin;15ml Cherry liqueur;7.5ml Cointreau;7.5ml DOM Bénédictine;10ml Grenadine;120ml Pineapple juice;15ml Lime juice;1 dash Angostura bitters', 'Pour all of the ingredients into cocktail shaker filled with ice cubes.;Shake well and strain into a highball glass.;Garnish with a pineapple slice and a cocktail cherry.', 'singapore_sling.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(querySingaporeSling);

                String querySpritzVeneziano = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Spritz Veneziano', '60ml Prosecco;40ml Aperol;Splash soda water', 'Build into a rocks  glass filled with ice.;Top with a splash of soda water.;Garnish with half orange slice.', 'spritz_veneziano.png', " + spiritOthersID + ")";
                cocktailsDB.execSQL(querySpritzVeneziano);

                String queryStinger = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Stinger', '50ml Cognac;20ml white Crème de Menthe', 'Pour all of the ingredients into a mixing glass with ice.;Stir and strain into a cocktail glass.', 'stinger.png', " + spiritCognacID + ")";
                cocktailsDB.execSQL(queryStinger);

                String queryTequilaSunrise = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Tequila Sunrise', '45ml Tequila;90ml Orange juice;15ml Grenadine', 'Pour the tequila and the orange juice directly into a highball glass with ice cubes.;Add a splash of grenadine to create a chromatic effect (sunrise), and do not stir.;Garnish with an orange slice and a cherry.', 'tequila_sunrise.png', " + spiritTequilaID + ")";
                cocktailsDB.execSQL(queryTequilaSunrise);

                String queryTommysMargarita = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Tommys Margarita', '45ml Tequila;15ml Lime Juice;10ml Agave Nectar', 'Shake and strain into a chilled cocktail glass.', 'tommy_s_margarita.png', " + spiritTequilaID + ")";
                cocktailsDB.execSQL(queryTommysMargarita);

                String queryTuxedo = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Tuxedo', '30ml Old Tom Gin;30ml Dry Vermouth;1/2 tsp Maraschino;1/4 tsp Absinthe;3 dashes Orange Bitters', 'Stir all of the ingredients with ice and strain into a cocktail glass.;Garnish with a cocktail cherry and a lemon twist.', 'tuxedo.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryTuxedo);

                String queryVampiro = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Vampiro', '50ml Tequila;70ml Tomato Juice;30ml Orange Juice;10ml Lime Juice;1 teaspoon clear Honey;1 half slice finely chopped Onion;3 slices red hot chilli peppers;3 drops Worcestershire sauce;Salt to taste', 'Shake ingredients over ice and strain into a highball glass. Garnish with a chilli pepper.', 'vampiro.png', " + spiritTequilaID + ")";
                cocktailsDB.execSQL(queryVampiro);

                String queryVesper = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Vesper', '60ml Gin;15ml Vodka;7.5ml Lillet Blanc', 'Shake and strain into a chilled cocktail glass.;Garnish with a lemon twist.', 'vesper.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryVesper);

                String queryWhiskeySour = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Whiskey Sour', '45ml Bourbon Whiskey;15ml Sugar Syrup;30ml fresh Lemon Juice;Dash Egg White (traditional but optional)', 'Pour all of the ingredients into a cocktail shaker filled with ice. ;Shake well and strain into a rocks glass with ice.;Garnish with a half orange slice and a maraschino cherry.', 'whiskey_sour.png', " + spiritWhiskyID + ")";
                cocktailsDB.execSQL(queryWhiskeySour);

                String queryWhiteLady = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('White Lady', 'White Lady;40ml Gin;30ml Triple Sec;20ml fresh Lemon Juice', 'Add all of the ingredients into a cocktail shaker filled with ice.;Shake well and strain into a cocktail glass.', 'white_lady.png', " + spiritGinID + ")";
                cocktailsDB.execSQL(queryWhiteLady);

                String queryYellowBird = "INSERT INTO drinks (name, ingredients, directions, photoID, spiritId) " +
                        "VALUES ('Yellow Bird', '30ml White Rum;15ml Galliano;15ml Triple Sec;15ml Lime Juice', 'Shake and strain into a chilled cocktail glass.', 'yellow_bird.png', " + spiritRumID + ")";
                cocktailsDB.execSQL(queryYellowBird);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
