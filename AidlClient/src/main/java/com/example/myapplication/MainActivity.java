package com.example.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{

    private EditText edit_num1;
    private EditText edit_num2;
    private EditText edit_result;
    private Button btn_add;
    IMyAidlInterface iMyaidl;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyaidl = IMyAidlInterface.Stub.asInterface(iBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iMyaidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        IbindService();

    }

    private void initView() {
        edit_num1 = (EditText) findViewById(R.id.edit_num1);
        edit_num2 = (EditText) findViewById(R.id.edit_num2);
        edit_result = (EditText) findViewById(R.id.edit_result);
        btn_add =(Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int num1 = Integer.parseInt(edit_num1.getText().toString());
        int num2 = Integer.parseInt(edit_num2.getText().toString());
        try {
            int result = iMyaidl.add(num1,num2);
            edit_result.setText(result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private void IbindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.myapplication","com.example.myapplication.IMyAidlInterface"));
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
