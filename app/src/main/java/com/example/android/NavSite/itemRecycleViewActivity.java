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
        if(getIntent().hasExtra("SummaryFromDB") && getIntent().hasExtra("isInDB")){
            String summary = "SummaryFromDB";
            setTxtData("summary:"+summary);
        }

        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("address") ){
            Log.d(TAG, "getIncomingIntent: found intent extras");

           // ArrayList
            String id = getIntent().getStringExtra("id");
            String name = getIntent().getStringExtra("name");

            String address = getIntent().getStringExtra("address");

        String summary = "summary should be here";



            setTxtData("name:"+name +"\naddress:"+address +"\n\nSummary\n:"+summary );





        }
//        if(getIntent().hasExtra("data")){
//            setTxtData("data:"+data);
//        }

        // https://www.youtube.com/watch?v=ZXoGG2XTjzU

    }
     public void setTxtData(String str){
         Log.d(TAG, "setTxtData: setting data to textBox");

         TextView txtView =(TextView)findViewById(R.id.txtData);
         txtView.setText(str);

     }

}
