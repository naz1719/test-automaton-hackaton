package com.hackaton.epam.tahackaton;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class BasketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        final Animation translate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        ImageView bskImgFirst = findViewById(R.id.bsk_img_first);
        ImageView bskImgSec = findViewById(R.id.bsk_img_second);
        ImageView bskImgThr = findViewById(R.id.bsk_img_third);
        ImageView bskImgFourth = findViewById(R.id.bsk_img_fourth);

        bskImgFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(translate);
                createBasketActivity("first");
            }
        });
        bskImgSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(translate);
                createBasketActivity("second");
            }
        });
        bskImgThr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(translate);
                createBasketActivity("third");
            }
        });
        bskImgFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(translate);
                createBasketActivity("fourth");
            }
        });
    }

    private void createBasketActivity() {
        Intent cart = new Intent(BasketActivity.this, CartActivity.class);
        startActivity(cart);
    }

    private void createBasketActivity(String image) {
        Intent cart = new Intent(BasketActivity.this, CartActivity.class);
        cart.putExtra("image", image);
        startActivity(cart);
    }
}
