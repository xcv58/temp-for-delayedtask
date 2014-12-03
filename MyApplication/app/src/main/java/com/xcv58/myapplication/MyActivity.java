package com.xcv58.myapplication;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.JoulerPolicy;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {
    private JoulerPolicy joulerPolicy;
    public final static String EXTRA_MESSAGE = "com.xcv58.myapplication.MESSAGE";
    public final static String TAG = "DelayedTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        joulerPolicy = (android.os.JoulerPolicy)getSystemService(JOULER_SERVICE);
//        this.broadcast();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        TextView textView = (TextView) findViewById(R.id.text_view);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        textView.setText(editText.getText().toString());


        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        String str = ((EditText) findViewById(R.id.delayed_time)).getText().toString();
        int delay = Integer.parseInt(str);
        Log.d("TEST", " " + delay);
        PendingIntent pintent = PendingIntent.getActivity(getBaseContext(), 1,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        joulerPolicy.setDelayedTask(pintent, delay);
    }

    private void broadcast() {
        List<String> goodPacks = new ArrayList<String>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for(PackageInfo pack : packs)
        {
            Log.d(TAG, pack.packageName);
            goodPacks.add(pack.packageName);
//            ActivityInfo[] activityInfo = getPackageManager().getPackageInfo(pack.packageName, PackageManager.GET_ACTIVITIES).activities;
//            Log.i("Pranay", pack.packageName + " has total " + ((activityInfo==null)?0:activityInfo.length) + " activities");
//            if(activityInfo!=null)
//            {
//                for(int i=0; i<activityInfo.length; i++)
//                {
//                    Log.i("PC", pack.packageName + " ::: " + activityInfo[i].name);
//                }
//            }
        }
        joulerPolicy.broadcastAlertIntent(goodPacks, null, null);
    }
}
