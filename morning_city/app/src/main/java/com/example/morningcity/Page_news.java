package com.example.morningcity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class Page_news extends AppCompatActivity {
    private static final String TAG = "";
    private RecyclerView recyclerView;
    private ParseItemAdapter parseItemAdapter;
    private List<ParseItemModel> parseItemModelList = new ArrayList<>();

    Element data;
    Document document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_news);
        recyclerView = findViewById(R.id.recyclerView2_id);
        //WebView webView = findViewById(R.id.webview);

        Content content = new Content();
        content.execute();
    }
    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            parseItemAdapter = new ParseItemAdapter((ArrayList<ParseItemModel>) parseItemModelList,Page_news.this);
            recyclerView.setAdapter(parseItemAdapter);
            parseItemAdapter.notifyDataSetChanged();


        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                String url = "https://www.bbc.com/zhongwen/trad/topics/c83plve5vmjt";
                org.jsoup.nodes.Document document = Jsoup.connect(url).get();
                Elements listItems = document.select("ul.bbc-1kz5jpr > li.bbc-t44f9r");
                String dataString = listItems.text();
                //               Log.e(TAG, "GET Data: " + dataString);

                int size = listItems.size();

                for (int i = 0; i < size; i++) {
                    String title = listItems.get(i).select("div").select("div.promo-text").select("h2").text();

                    String timetext = listItems.get(i).select("div").select("div.promo-text").select("time").text();

                    String imagelink = listItems.get(i).select("div").select("div.promo-image").select("div.bbc-1qfus8v.e5q9uf21").select("div").select("picture").select("img").attr("src");

                    String websiteUrl = listItems.get(i).select("div").select("div.promo-text").select("h2.bbc-10m3zrw.e47bds20").select("a").attr("href");
                    //Log.e(TAG, "GET Data: " + websiteUrl);
                    parseItemModelList.add(new ParseItemModel(title, timetext, imagelink, websiteUrl));
                    //parseItemModelList.add(new ParseItemModel(title,timetext,imagelink));

                    //
                    //finaltext += (i+1) +"." + title + "\n";
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return null;
        }
    }

}