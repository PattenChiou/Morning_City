package com.example.morningcity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Page_breakfast extends AppCompatActivity {
    TextView break1,break2,break3;
    Button renew,find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_breakfast);

        break1 = findViewById(R.id.break1);
        break2 = findViewById(R.id.break2);
        break3 = findViewById(R.id.break3);

        renew = findViewById(R.id.button_renew);
        find = findViewById(R.id.button_find);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri res_IntentUri = Uri.parse("geo:0,0?q=breakfast");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, res_IntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        renew.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String eatinglist = "香雞漢堡,豬肉漢堡,鮪魚漢堡,歐姆漢堡,培根漢堡,檸檬雞漢堡,勳雞漢堡,黑胡椒豬排漢堡,卡茲雞腿漢堡,超厚牛肉起司漢堡,超厚雞肉起司漢堡," +
                        "香雞湯種,豬肉湯種,鮪魚湯種,歐姆湯種,培根湯種,檸檬雞湯種,勳雞湯種,黑胡椒豬排湯種,卡茲雞腿湯種,超厚牛肉起司湯種,超厚雞肉起司湯種," +
                        "香雞可颂,豬肉可颂,鮪魚可颂,歐姆可颂,培根可颂,檸檬雞可颂,勳雞可颂,黑胡椒豬排可颂,卡茲雞腿可颂,超厚牛肉起司可颂,超厚雞肉起司可颂," +
                        "香雞丹麥堡,豬肉丹麥堡,鮪魚丹麥堡,歐姆丹麥堡,培根丹麥堡,檸檬雞丹麥堡,勳雞丹麥堡,黑胡椒豬排丹麥堡,卡茲雞腿丹麥堡,超厚牛肉起司丹麥堡,超厚雞肉起司丹麥堡," +
                        "香雞滿分堡,豬肉滿分堡,鮪魚滿分堡,歐姆滿分堡,培根滿分堡,檸檬雞滿分堡,勳雞滿分堡,黑胡椒豬排滿分堡,卡茲雞腿滿分堡,超厚牛肉起司滿分堡,超厚雞肉起司滿分堡," +
                        "原味蛋餅,鮪魚蛋餅,鮮蔬玉米蛋餅,薯餅蛋餅,培根蛋餅,豬排蛋餅,蘿蔔糕,手工蔥抓蛋餅,黃金泡菜蔥抓餅,黃金泡菜酥炸餃,黃金泡菜蘿蔔糕," +
                        "黑胡椒鐵板麵,蘑菇鐵板麵,三杯雞丁鐵板麵,宮保雞丁鐵板麵,眷村麻醬鐵板麵,川味麻辣鐵板麵," +
                        "起司雙重奏握蛋捲餅,鮪魚握蛋捲餅,培根握蛋捲餅,燻雞握蛋捲餅,德腸握蛋捲餅,豬排握蛋捲餅," +
                        "顆粒花生湯種,顆粒花生丹麥,顆粒花生布里歐皇厚吐司,草莓湯種,草莓丹麥,草莓布里歐皇厚吐司,巧克力湯種,巧克力丹麥,巧克力布里歐皇厚吐司,香蒜湯種,香蒜丹麥,香蒜布里歐皇厚吐司," +
                        "鮮蛋沙拉,燻雞沙拉,地瓜沙拉,嫩雞沙拉,超厚蔬肉沙拉,超厚蔬肉地瓜特餐," +
                        "鮮蔬漢堡,鮮蔬湯種,鮮蔬丹麥堡,鮮蔬可颂超厚蔬肉漢堡,超厚蔬肉湯種,超厚蔬肉丹麥堡,超厚蔬肉可颂超厚蔬肉滿分堡,鮮蛋沙拉漢堡,鮮蛋沙拉湯種,鮮蛋沙拉丹麥堡,鮮蛋沙拉可颂,";

                String drinklist = "中冰錫蘭紅茶,中溫錫蘭紅茶,中冰釀綠茶,中冰無糖豆漿,中溫無糖豆漿,中冰奶茶,中溫奶茶," +
                        "大冰錫蘭紅茶,大溫錫蘭紅茶,大冰釀綠茶,大冰無糖豆漿,大溫無糖豆漿,大冰奶茶,大溫奶茶," +
                        "超大冰錫蘭紅茶,超大溫錫蘭紅茶,超大冰釀綠茶,超大冰無糖豆漿,超大溫無糖豆漿,超大冰奶茶,超大溫奶茶," +
                        "冰山藥薏仁漿,冰柳橙汁,冰紅茶拿鐵,冰可可牛奶,冰紐西蘭牛奶,冰金萱包種茶,冰橙柚茶,冰橙柚綠茶,冰美式咖啡,冰拿鐵咖啡,冰焦糖拿鐵,冰榛果拿鐵," +
                        "溫山藥薏仁漿,溫紅茶拿鐵,溫可可牛奶,溫紐西蘭牛奶,溫金萱包種茶,溫美式咖啡,溫拿鐵咖啡,溫焦糖拿鐵,溫榛果拿鐵," +
                        "奶油玉米濃湯,巧達濃湯";

                String[] foodarray = eatinglist.split(",");
                String[] drinkarray = drinklist.split(",");

                List<String> randomFoods = new ArrayList<>();
                Random random = new Random();
                while(randomFoods.size()<3){
                    int randomIndex = random.nextInt(foodarray.length);
                    String food = foodarray[randomIndex];
                    if(!randomFoods.contains(food)){
                        randomFoods.add(food);
                    }

                }

                List<String> randomDrinks = new ArrayList<>();
                Random rand = new Random();
                while(randomDrinks.size()<3){
                    int randomIndex = rand.nextInt(drinkarray.length);
                    String drink = drinkarray[randomIndex];
                    if(!randomDrinks.contains(drink)){
                        randomDrinks.add(drink);
                    }

                }

                break1.setText("1." + randomFoods.get(0) + " + " + randomDrinks.get(0));
                break2.setText("2." + randomFoods.get(1) + " + " + randomDrinks.get(1));
                break3.setText("3." + randomFoods.get(2) + " + " + randomDrinks.get(2));

            }
        });
    }
}