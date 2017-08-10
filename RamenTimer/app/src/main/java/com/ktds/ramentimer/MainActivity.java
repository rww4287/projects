package com.ktds.ramentimer;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ktds.ramentimer.common.ListViewSimpleAdapter;
import com.ktds.ramentimer.model.Ramen;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_ramen_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_ramen_list = (ListView) findViewById(R.id.lv_ramen_list);

        List<Ramen> ramens = getRamenList();
        lv_ramen_list.setAdapter(new ListViewSimpleAdapter(MainActivity.this, R.layout.item_main, ramens) {
            @Override
            public Holder createHolder(View view) {
                ItemHolder itemHolder = new ItemHolder(view);
                return itemHolder;
            }

            @Override
            public void setContent(Holder holder, int position) {
                final Ramen ramen = (Ramen) getItem(position);
                ItemHolder itemHolder = (ItemHolder) holder;
                itemHolder.tv_ramen_name.setText(ramen.getRamenName());
                if( ramen.getTime() % 60 != 0 ) {
                    itemHolder.tv_ramen_time.setText(ramen.getTime() / 60 + "분" + ramen.getTime() % 60 + "초");
                } else {
                    itemHolder.tv_ramen_time.setText(ramen.getTime() / 60 + "분");
                }
                itemHolder.lv_ramen_img.setImageDrawable(ramen.getImg());

                itemHolder.lv_ramen_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("TIME",ramen.getTime());
                        intent.putExtra("Name",ramen.getRamenName());
                        startActivity(intent);
                    }
                });


            }
            class ItemHolder extends Holder {
                public TextView tv_ramen_name;
                public TextView tv_ramen_time;
                public ImageView lv_ramen_img;

                public ItemHolder(View v) {
                    super(v);
                    tv_ramen_name = (TextView) v.findViewById(R.id.tv_ramen_name);
                    tv_ramen_time = (TextView) v.findViewById(R.id.tv_ramen_time);
                    lv_ramen_img = (ImageView) v.findViewById(R.id.lv_ramen_img);
                }
            }
        });

    }



    public List<Ramen> getRamenList(){
        List<Ramen> ramens = new ArrayList<>();
        Ramen ramen = new Ramen();

        ramen.setRamenName("너구리");
        ramen.setTime(180);
        ramen.setImg(getResources().getDrawable(R.drawable.nu));
        ramens.add(ramen);

        ramen = new Ramen();
        ramen.setRamenName("참깨라면");
        ramen.setTime(210);
        ramen.setImg(getResources().getDrawable(R.drawable.cham));
        ramens.add(ramen);

        ramen = new Ramen();
        ramen.setRamenName("진짬뽕");
        ramen.setTime(240);
        ramen.setImg(getResources().getDrawable(R.drawable.jin));
        ramens.add(ramen);

        return ramens;
    }
}
