package com.example.android.NavSite;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.android.NavSite.Crawler.CrawlerDB;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;

/**
 * Created by hamma on 06/05/2018.
 */


public class itemRecycleViewActivity extends AppCompatActivity {

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


    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents ");
        if (getIntent().hasExtra("SummaryFromDB") && getIntent().hasExtra("isInDB") && getIntent().hasExtra("id")) {
            String summary = getIntent().getStringExtra("SummaryFromDB");
            String id = getIntent().getStringExtra("id");
            setTxtData("id:" + id + "<br><br><br>summary:" + summary);
        } else {
            printCrawledEntriesFromDb();
        }

        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("address")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");

            // ArrayList
            String id = getIntent().getStringExtra("id");
            String name = getIntent().getStringExtra("name");

            String address = getIntent().getStringExtra("address");

            String summary = "summary should be here";


            setTxtData("name:" + name + "\naddress:" + address + "\n\nSummary\n:" + summary);


        }
//        if(getIntent().hasExtra("data")){
//            setTxtData("data:"+data);
//        }

        // https://www.youtube.com/watch?v=ZXoGG2XTjzU

    }

    public void setTxtData(String str) {
        Log.d(TAG, "setTxtData: setting data to textBox");

        TextView txtView = (TextView) findViewById(R.id.txtData);

        txtView.setText(Html.fromHtml("" + str + ""));
        txtView.setClickable(true);
        txtView.setMovementMethod(LinkMovementMethod.getInstance());
//        Log.i(TAG, "summary summary: " + txtView.getText());

        //TODO: only after crawling
        //   File file= new File("C:\\Users\\hamma\\AndroidStudioProjects\\AdvancedAndroid_Shushme-develop_branch\\app\\src\\main\\java\\com\\example\\android\\NavSite\\textSummarizer\\textToSummarize.txt");

        Summarizer sum = new Summarizer();
        Log.i(TAG, "setTxtData: txtView.getText().toString()" + txtView.getText().toString());
        String finalSum = sum.Summarize(txtView.getText().toString(), 4);
        txtView.setText(finalSum);
    }

    public void setTxtData2(String str) {
        TextView txtView = (TextView) findViewById(R.id.txtData2);

        txtView.setText(Html.fromHtml("" + str + ""));
        txtView.setClickable(true);
        txtView.setMovementMethod(LinkMovementMethod.getInstance());
//        txtView.setText(str);
    }


    protected int printCrawledEntriesFromDb() {
        int count = 0;
        CrawlerDB mCrawlerDB = new CrawlerDB(this);
        SQLiteDatabase db = mCrawlerDB.getReadableDatabase();

        Cursor mCursor = db.query(CrawlerDB.TABLE_NAME, null, null, null, null,
                null, null);
        if (mCursor != null && mCursor.getCount() > 0) {
            count = mCursor.getCount();
            mCursor.moveToFirst();
            int columnIndex = mCursor
                    .getColumnIndex(CrawlerDB.COLUMNS_NAME.CRAWLED_URL);
            int columnIndex2 = mCursor
                    .getColumnIndex(CrawlerDB.COLUMNS_NAME.CRAWLED_PAGE_CONTENT);
            String content = "";
            String content2 = "Refer<br>";
            int j = 0;
            for (int i = 0; i < count; i++) {
                Log.d("AndroidSRC_Crawler",
                        "Crawled Url " + mCursor.getString(columnIndex));

                if (i != 0) {
                    if (!mCursor.getString(columnIndex).contains("translate")) {
                        String html = mCursor.getString(columnIndex2);
                        org.jsoup.nodes.Document doc = Jsoup.parse(html);
                        String text = doc.body().select("div p, div>p, p, p>span,h1").text();
//                        if(text!=null && text.length()>=100){
                        if (false) {
                            text = text.substring(0, 100) + "...";
                        } else {
                            text += ". ";
                        }
                        j++;
                        content2 += "<a href=\"" + mCursor.getString(columnIndex) + "\">" + "link" + j + "</a><br>";
                        content += "<br>" + text + "<br>\n";
                    }
                }
                mCursor.moveToNext();
            }

            setTxtData(content);
            setTxtData2(content2);


        }

        return count;
    }


}
