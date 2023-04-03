package edu.ib.compsciia;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.ib.compsciia.businesslogic.AppViewModel;
import edu.ib.compsciia.businesslogic.LifeFormManager;
import edu.ib.compsciia.businesslogic.Schedule;
import edu.ib.compsciia.businesslogic.ScheduleEventHandler;
import edu.ib.compsciia.businesslogic.ScheduleRunDate;
import edu.ib.compsciia.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ScheduleEventHandler {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private LifeFormManager lifeFormManager;
    private AppViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        this.lifeFormManager = LifeFormManager.load(this.getApplicationContext());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        this.lifeFormManager.addListener(this);
        LifeFormManager.getManager().addListener(this);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        scheduleNotificationRun();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void scheduleNotificationRun()
    {
        List<ScheduleRunDate> notifications = LifeFormManager.getManager().getNextScheduleRunDate();
        for(ScheduleRunDate run : notifications)
        {
            addNotification(run);
        }
    }
    public void addNotification(ScheduleRunDate run)
    {
        Intent notifyIntent = new Intent(this, MyReceiver.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        notifyIntent.putExtra("notification",buildNotification(run.getSchedule()));
        notifyIntent.putExtra("notification_id",run.getSchedule().getUniqueId());
        Calendar scheduleRunDate = run.getDate();
        scheduleRunDate.add(Calendar.MINUTE,run.getSchedule().getAlertTimeBefore() * -1);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this.getBaseContext(), run.getSchedule().getUniqueId(), notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getBaseContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                run.getDate().getTimeInMillis(), pendingIntent);

    }
    public void CancelNotification(int id)
    {
        NotificationManager notifManager= (NotificationManager) this.getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancel(id);
    }
    private Notification buildNotification(Schedule schedule)
    {
        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this,"PT232");
        }
        else
        {
            builder = new Notification.Builder(this);
        }
        builder.setContentTitle("Scheduled Life Form Time!");
        builder.setContentText("It is almost time for your Life Form's care: " + schedule.toString());
        builder.setSmallIcon(R.drawable.ic_baseline_pets_24);
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        return builder.build();
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
            navController.navigate(R.id.lifeFormListContainer);
            return true;
        }
        if (id == R.id.action_schedules) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main); // replace "nav_host_fragment" with the id of your navHostFragment in activity layout
            navController.navigate(R.id.scheduleListContainer);
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

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("PT232", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void ScheduleRemoved(int id) {
        CancelNotification(id);
    }

    @Override
    public void ScheduleNextEvent(ScheduleRunDate rd) {
        addNotification(rd);
    }
}