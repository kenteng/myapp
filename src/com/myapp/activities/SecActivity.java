package com.myapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.example.myapp.R;
import com.myapp.adapter.FruitAdapter;
import com.myapp.controller.ActivityCollector;
import com.myapp.objects.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by megatron on 15/2/18.
 */
public class SecActivity extends Activity{
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.secactivity);
        ActivityCollector.activityList.add(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Toast.makeText(SecActivity.this,"Got name:"+name,Toast.LENGTH_SHORT).show();

        //list view
       /* String[] data = {"Apple","SamSung","Nokia"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) findViewById(R.id.sec_listView);
        listView.setAdapter(arrayAdapter);*/
        initFruits();

        FruitAdapter fruitAdapter = new FruitAdapter(this,R.layout.fruit_item,fruitList);
        ListView listView = (ListView) findViewById(R.id.sec_listView);
        listView.setAdapter(fruitAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fruit = fruitList.get(i);
                Toast.makeText(SecActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        Button bt = (Button) findViewById(R.id.button_2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initFruits() {
        Fruit apple = new Fruit("Apple", R.drawable.apple_pic); fruitList.add(apple);
        Fruit banana = new Fruit("Banana", R.drawable.banana_pic); fruitList.add(banana);
        Fruit orange = new Fruit("Orange", R.drawable.orange_pic); fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic); fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear", R.drawable.pear_pic); fruitList.add(pear);
        Fruit grape = new Fruit("Grape", R.drawable.grape_pic); fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic); fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic); fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic); fruitList.add(cherry);
        Fruit mango = new Fruit("Mango", R.drawable.mango_pic); fruitList.add(mango);
    }
}
