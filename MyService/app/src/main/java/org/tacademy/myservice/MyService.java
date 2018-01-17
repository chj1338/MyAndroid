package org.tacademy.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.d("MyService", "=============== onCreate() called!");
        super.onCreate();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<1000; i++) {
                    Log.d("MyService", "===============" + i + " called!");

                    try {
                        Thread.sleep(1000);
                    }catch (Exception e) {

                    }
                }
            }
        });

        thread.start();
    }

    @Override
    public void onDestroy() {
        Log.d("MyService", "=============== onDestroy() called!");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "=============== onStartCommand() called!");

        if(intent != null) {
            String command = intent.getStringExtra("command");

            if(command != null) {
                if(command.equals("show")) {
                    Log.d("MyService", "=============== command content : " + command);

                    String data = intent.getStringExtra("data");

                    Intent showIntent = new Intent(getApplicationContext(), ShowActivity.class);
                    showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    showIntent.putExtra("data", data);
                    startActivity(showIntent);

                    showIntent = new Intent(getApplicationContext(), ShowActivity.class);
                    showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    showIntent.putExtra("data", "걸스데이");
                    startActivity(showIntent);
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
