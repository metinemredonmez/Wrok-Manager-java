package com.metinemre.workmanagerjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dıger trafa data gonderecegız

        Data data= new Data.Builder().putInt("intkey",1).build();
        //Datayı gonderdık !

        // work manager kullanalım ! veriyi aktardık !
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(false)
                .build();

        //  work rewuest iş istegı lazım


        /*
        WorkRequest workRequest =  new OneTimeWorkRequest.Builder(refreshDatabase.class)
                .setConstraints(constraints)
                .setInputData(data)
                .setInitialDelay(5, TimeUnit.MINUTES)
                .addTag("myTag")
                .build();

        // son olaral work manager ısleme almak ıcın cagır

        WorkManager.getInstance(this).enqueue(workRequest);


         */
        // periyodik çalışma

        WorkRequest workRequest =  new PeriodicWorkRequest.Builder(refreshDatabase.class,15,TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setInputData(data)
                .setInputData(data)
                .setInitialDelay(15, TimeUnit.MINUTES)
                .addTag("myTag")
                .build();

        WorkManager.getInstance(this).enqueue(workRequest);



        // bunlar degıstıgın bılsırım ore ıslem yapma
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if (workInfo.getState() == WorkInfo.State.RUNNING) {
                    System.out.println("running");
                }
                else if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                    System.out.println("succeded");
                }else if(workInfo.getState()== WorkInfo.State.FAILED) {
                    System.out.println("failed");
                }
            }
        });

        // iptal edelim fail edınce ornek
     // WorkManager.getInstance(this).cancelAllWork();

        // chaining zincirleme olayı  periyokdık olaın ıs ısteıkkerını rıbırn baglaymayız !  bır defa olanları baglarız ! arka arkaya olan ısler
        /*

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(refreshDatabase.class)
                .setInputData(data)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(this).beginWith(oneTimeWorkRequest)
                .then(oneTimeWorkRequest)
                .then(oneTimeWorkRequest)
                .enqueue();
        // zincirleme arka arkaya çalıştırma !!

         */




    }
}





























