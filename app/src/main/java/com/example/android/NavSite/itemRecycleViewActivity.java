package com.example.android.NavSite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.android.NavSite.Crawler.CrawlerDB;

import java.util.ArrayList;

/**
 * Created by hamma on 06/05/2018.
 */


public class itemRecycleViewActivity extends AppCompatActivity{

    private static final String TAG = "itemRecycleViewActivity";
//    private Context mContext;
//
//
//
//    public itemRecycleViewActivity(Context context){
//        this.mContext=context;
//    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_item_recycler_view);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();


    }




    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents ");

        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("address") ){
            Log.d(TAG, "getIncomingIntent: found intent extras");

           // ArrayList
            String id = getIntent().getStringExtra("id");
            String name = getIntent().getStringExtra("name");

            String address = getIntent().getStringExtra("address");

        String summary = "The building was built in 1978-1974 , The plan for a tall, narrow tower towering over a wide horizontal surface was proposed by the architect Oscar Niemeyer. It was designed by Prof. Shlomo Gilad . has 30 floors and is 102 meters high. It was named after the third prime minister , Levi Eshkol.";



            setTxtData("name:"+name +"\naddress:"+address +"\n\nSummary\n:"+summary );





        }

        // https://www.youtube.com/watch?v=ZXoGG2XTjzU

    }
     public void setTxtData(String str){
         Log.d(TAG, "setTxtData: setting data to textBox");

         TextView txtView =(TextView)findViewById(R.id.txtData);
         txtView.setText(str);

     }

}