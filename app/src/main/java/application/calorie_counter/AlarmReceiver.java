package application.calorie_counter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    NotificationsController nc = new NotificationsController();
    int id=0;

    @Override
    public void onReceive(Context context, Intent intent) {
        nc.notify(context, context.getString(R.string.notification_string), id);
        id++;    }
}
