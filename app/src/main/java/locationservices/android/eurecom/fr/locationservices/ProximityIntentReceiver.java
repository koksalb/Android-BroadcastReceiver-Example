package locationservices.android.eurecom.fr.locationservices;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

public class ProximityIntentReceiver extends BroadcastReceiver {

    String notificationTitle;
    String notificationContent;
    String tickerMessage;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(getClass().getSimpleName(),"entered");
        Intent i = intent;
        String key = LocationManager.KEY_PROXIMITY_ENTERING;
        Boolean entering = intent.getBooleanExtra(key, false);
        String id = i.getStringExtra("ido ");
        if (id!=null){
            Log.i(getClass().getSimpleName(),id);

        }else {
            Log.i(getClass().getSimpleName(), "id is null");
        }
        if (entering) {
            Log.i(getClass().getSimpleName(), "entering");
            notificationTitle="Proximity - Entry";
            notificationContent="Entered the region";
            tickerMessage = "Entered the region";
        } else {
            Log.i(getClass().getSimpleName(),"exiting");
            notificationTitle="Proximity - Exit";
            notificationContent="Exited the region";
            tickerMessage = "Exited the region";


        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Log.i(getClass().getSimpleName(),"exiting");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(), 0);

        Notification notification = createNotification(context);
        notificationManager.notify((int) System.currentTimeMillis(), notification);


    }


    private Notification createNotification(Context context) {
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setWhen(System.currentTimeMillis())
                .setContentText(notificationContent)
                .setContentTitle(notificationTitle)
                .setSmallIcon(R.drawable.common_signin_btn_icon_dark)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS)
                .setTicker(tickerMessage);

        return notificationBuilder.build();
    }
}