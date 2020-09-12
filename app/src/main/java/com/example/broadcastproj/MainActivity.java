package com.example.broadcastproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    public Reciever locaListener;
  //  public final TextView text;
    public static final String BROADCAST_ACTION ="Broadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.button){
                    BackgroundService.startAction(getApplicationContext());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        locaListener = new Reciever();
        IntentFilter intentFilter=new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(locaListener,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(locaListener);

    }


   public class Reciever extends BroadcastReceiver {
       TextView text=findViewById(R.id.txt);
        @Override
        public void onReceive(Context context, Intent intent) {


             String currentText=text.getText().toString();
             String message =intent.getStringExtra("value");
             currentText+="\nRecieved "+ message;
             text.setText(currentText);
        }
    }


}