package com.hfad.starbuzz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Administrator
 */
public class DrinkActivity extends Activity {
    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the intent
        int drinkId = getIntent().getIntExtra(EXTRA_DRINKID,0);
        Drink drink = Drink.drinks[drinkId];

        //Populate the drink name
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(drink.getName());

        //Populate the drink description
        //Populate:填充
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(drink.getDescription());

        //Populate the drink image
        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageResource(drink.getImageResourceId());
        photo.setContentDescription(drink.getName());
    }

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, DrinkActivity.class);
        intent.putExtra(EXTRA_DRINKID, id);
        return intent;
    }
}
