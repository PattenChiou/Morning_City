package com.example.morningcity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;

import java.io.IOException;

public class Page_star extends AppCompatActivity {
    private static final String TAG = "";
    TextView txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;
    Spinner spinner;
    Button button;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_star);
        //txt1 = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);
        txt3 = findViewById(R.id.textView3);
        txt4 = findViewById(R.id.textViewTodosDate);
        txt5 = findViewById(R.id.textViewTodosShowDate);
        txt6 = findViewById(R.id.textViewTodosTime);
        txt7 = findViewById(R.id.textView7TodosShowTime);
        txt8 = findViewById(R.id.textView8TodosTodos);
        txt9 = findViewById(R.id.textView9);
        txt10 = findViewById(R.id.textView10);
        image = findViewById(R.id.imageView2);
        spinner = findViewById(R.id.spinner2);
        button = findViewById(R.id.buttonTodosAdd);
        String[] starSigns = {"牡羊座", "金牛座", "雙子座", "巨蟹座", "獅子座", "處女座", "天秤座", "天蠍座", "射手座", "摩羯座", "水瓶座", "雙魚座"};
        String[] starSignUrls = {"https://astro.click108.com.tw/daily_0.php?iAstro=0",
                "https://astro.click108.com.tw/daily_1.php?iAcDay=2023-05-28&iAstro=1",
                "https://astro.click108.com.tw/daily_2.php?iAcDay=2023-05-28&iAstro=2",
                "https://astro.click108.com.tw/daily_3.php?iAcDay=2023-05-28&iAstro=3",
                "https://astro.click108.com.tw/daily_4.php?iAcDay=2023-05-28&iAstro=4",
                "https://astro.click108.com.tw/daily_5.php?iAcDay=2023-05-28&iAstro=5",
                "https://astro.click108.com.tw/daily_6.php?iAcDay=2023-05-28&iAstro=6",
                "https://astro.click108.com.tw/daily_7.php?iAcDay=2023-05-28&iAstro=7",
                "https://astro.click108.com.tw/daily_8.php?iAcDay=2023-05-28&iAstro=8",
                "https://astro.click108.com.tw/daily_9.php?iAcDay=2023-05-28&iAstro=9",
                "https://astro.click108.com.tw/daily_10.php?iAcDay=2023-05-28&iAstro=10",
                "https://astro.click108.com.tw/daily_11.php?iAcDay=2023-05-28&iAstro=11"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, starSigns);
        spinner.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPosition = spinner.getSelectedItemPosition();
                String selectedUrl = starSignUrls[selectedPosition];

                Thread thread = new Thread(new Runnable() {
                    final EbayProduct product = new EbayProduct();
                    @Override
                    public void run() {
                        try{

                            org.jsoup.nodes.Document document = Jsoup.connect(selectedUrl).get();
                            String star_txt_view = document.select("div.TODAY_CONTENT").select("h3").text();
                            String image_txt = document.select("div.TODAY_FORTUNE").select("div.STARBABY").select("img").attr("src");
                            String all_txt_view = document.select("div.TODAY_CONTENT").select("p").first().text();
                            String all_txt_txt_view = document.select("div.TODAY_CONTENT").select("p").get(1).text();
                            String love_txt_view = document.select("div.TODAY_CONTENT").select("p").get(2).text();
                            String love_txt_txt_view = document.select("div.TODAY_CONTENT").select("p").get(3).text();
                            String job_txt_view = document.select("div.TODAY_CONTENT").select("p").get(4).text();
                            String job_txt_txt_view = document.select("div.TODAY_CONTENT").select("p").get(5).text();
                            String money_txt_view = document.select("div.TODAY_CONTENT").select("p").get(6).text();
                            String money_txt_txt_view = document.select("div.TODAY_CONTENT").select("p").get(7).text();

                            product.setAll_txt(all_txt_view);
                            product.setAll_txt_txt(all_txt_txt_view);
                            product.setLove_txt(love_txt_view);
                            product.setLove_txt_txt(love_txt_txt_view);
                            product.setJob_txt(job_txt_view);
                            product.setJob_txt_txt(job_txt_txt_view);
                            product.setMoney_txt(money_txt_view);
                            product.setMoney_txt_txt(money_txt_txt_view);
                            product.setImageURL(image_txt);
                            product.setStar(star_txt_view);

                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override

                            public void run() {
                                Picasso.get().load(product.getImageURL()).into(image);
                                txt2.setText(product.getStar());
                                txt3.setText(product.getAll_txt());
                                txt4.setText(product.getAll_txt_txt());
                                txt5.setText(product.getLove_txt());
                                txt6.setText(product.getLove_txt_txt());
                                txt7.setText(product.getJob_txt());
                                txt8.setText(product.getJob_txt_txt());
                                txt9.setText(product.getMoney_txt());
                                txt10.setText(product.getMoney_txt_txt());

                            }
                        });

                    }
                });
                thread.start();
            }
        });


    }

}