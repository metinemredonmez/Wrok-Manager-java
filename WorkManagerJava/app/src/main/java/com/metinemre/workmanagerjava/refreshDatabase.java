package com.metinemre.workmanagerjava;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class refreshDatabase  extends Worker {

    Context myContext;



    public refreshDatabase(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.myContext = context;
    }




    @NonNull
    @Override
    public Result doWork() {
        // işlemleri burada yapıyoruz work manager ıcın

        Data data = getInputData();
        // intkEY ANAHTARI SAYESINDEN DGIER TARAFTAKI VERIYI  ALALIM
        int myNumber =  data.getInt("intKey",0);

        refreshDatabase(myNumber);
        return Result.success();

    }

    private void refreshDatabase(int myNumber) {

        // resresh database
        SharedPreferences sharedPreferences = myContext.getSharedPreferences("com.metinemre.workmanagerjava",Context.MODE_PRIVATE);
        int mySavedNumber =  sharedPreferences.getInt("myNumber",0);
        mySavedNumber =  myNumber + 1;
        sharedPreferences.edit().putInt("myNumber",mySavedNumber).apply();


    }

}


















