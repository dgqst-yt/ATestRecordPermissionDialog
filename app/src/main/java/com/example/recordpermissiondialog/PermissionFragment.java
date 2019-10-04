package com.example.recordpermissiondialog;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PermissionFragment extends Fragment {

    static final int REQUEST_CODE = 1000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if(shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO))
            {
                // show an epla
            }else {
                Log.v("MainActivity", "API Level = " + Build.VERSION.SDK_INT + ": パーミッションが付与されていない");
                // パーミッションが付与されていない場合、
                // パーミッションを要求する（ユーザに許可を求めるダイアログを表示する）
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE);
            }
        }else{
            // API Level 23未満(Marshmallow)は、問答無用でこのelseブロックになります
            Log.v("MainActivity", "API Level = " + Build.VERSION.SDK_INT + ": パーミッションが付与されている");
            // パーミッションが付与されているので、画面遷移します
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_permission, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //開始
                    Log.v("requestpermission", "permission ");
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.v("requestpermission", "no permission ");
                }
                return;
            }
            default:
            {
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
            }
            // other 'switch' lines to check for other
            // permissions this app might request
        }
    }

}
