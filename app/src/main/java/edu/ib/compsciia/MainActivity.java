package edu.ib.compsciia;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private LifeFormManager lifeFormManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        this.lifeFormManager = LifeFormManager.load(this.getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_calendar) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main); // replace "nav_host_fragment" with the id of your navHostFragment in activity layout
            navController.navigate(R.id.calendarFragment);
            return true;
        }
        if (id == R.id.action_calendar) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main); // replace "nav_host_fragment" with the id of your navHostFragment in activity layout
            navController.navigate(R.id.calendarFragment);
            return true;
        }
        if (id == R.id.action_lifeForms) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main); // replace "nav_host_fragment" with the id of your navHostFragment in activity layout
            navController.navigate(R.id.lifeFormFragment);
            return true;
        }
        if (id == R.id.action_schedules) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main); // replace "nav_host_fragment" with the id of your navHostFragment in activity layout
            navController.navigate(R.id.scheduleFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.lifeFormManager.save(this.getApplicationContext());
    }
}