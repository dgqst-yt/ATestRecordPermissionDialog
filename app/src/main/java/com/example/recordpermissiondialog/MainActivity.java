package com.example.recordpermissiondialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View button = findViewById(R.id.button);
        button.setOnClickListener(new OnClickReadContactsListener());
    }

    private class OnClickReadContactsListener implements View.OnClickListener {
        private int REQUEST_CODE = 1000;
        @Override
        public void onClick(View v){

            if(ContextCompat.checkSelfPermission(getApplicationContext(),  Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.RECORD_AUDIO))
                {
                    // show an epla
                }else {
                    Log.v("MainActivity", "API Level = " + Build.VERSION.SDK_INT + ": パーミッションが付与されていない");
                    // パーミッションが付与されていない場合、
                    // パーミッションを要求する（ユーザに許可を求めるダイアログを表示する）
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE);
                }
            }else{
                // API Level 23未満(Marshmallow)は、問答無用でこのelseブロックになります
                Log.v("MainActivity", "API Level = " + Build.VERSION.SDK_INT + ": パーミッションが付与されている");
                // パーミッションが付与されているので、画面遷移します
                //transitionContactList();
            }

//            if(ContextCompat.checkSelfPermission(getApplicationContext(),  Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//                Log.v("MainActivity", "API Level = " + Build.VERSION.SDK_INT + ": パーミッションが付与されていない");
//                // パーミッションが付与されていない場合、
//                // パーミッションを要求する（ユーザに許可を求めるダイアログを表示する）
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_READ_CONTACTS);
//            } else {
//
//            }
        }
    }
}
