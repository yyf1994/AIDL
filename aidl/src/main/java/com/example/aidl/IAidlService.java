package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class IAidlService extends Service {
    public IAidlService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return iBinder;
    }

    private IBinder iBinder = new IMyAidlInterface.Stub()
    {
        @Override
        public int add(int num1, int num2) throws RemoteException {
            return num1+num2;
        }
    };

}
