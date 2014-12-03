package com.xcv58.broadpacks;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.JoulerPolicy;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {
    private JoulerPolicy joulerPolicy;
    private List<String> emptyList = new ArrayList<String>();

    public final static String TAG = "DelayedTask";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        joulerPolicy = (android.os.JoulerPolicy)getSystemService(JOULER_SERVICE);
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
    public void sendBad(View view) {
        Log.d(TAG, "send bad");
        joulerPolicy.broadcastAlertIntent(this.getPacks(), emptyList, emptyList);
    }
    public void sendOkay(View view) {
        Log.d(TAG, "send okay");
        joulerPolicy.broadcastAlertIntent(emptyList, this.getPacks(), emptyList);
    }
    public void sendGood(View view) {
        Log.d(TAG, "send good");
        joulerPolicy.broadcastAlertIntent(emptyList, emptyList, this.getPacks());
    }

    private List<String> getPacks() {
        List<String> packList = new ArrayList<String>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        // for(PackageInfo pack : packs)
        // {
        //     packList.add(pack.packageName);
        // }
        packList.add("com.xcv58.broadpacks");
        return packList;
//        joulerPolicy.broadcastAlertIntent(goodPacks, null, null);
    }
}
