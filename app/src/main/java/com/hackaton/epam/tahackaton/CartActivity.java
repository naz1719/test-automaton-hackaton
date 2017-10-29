package com.hackaton.epam.tahackaton;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class CartActivity extends AppCompatActivity {

    private TextView qtyText;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initWebViewForNestedIFrame();

        ImageView cartMinus = findViewById(R.id.cartMinus);
        cartMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtyText = findViewById(R.id.cartQty);
                Integer qty = Integer.parseInt(qtyText.getText().toString());

                if (qty > 1) {
                    qty--;
                    qtyText.setText(qty.toString());
                } else {
                }
            }
        });

        ImageView cartPlus = findViewById(R.id.cartPlus);
        cartPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtyText = findViewById(R.id.cartQty);
                Integer qty = Integer.parseInt(qtyText.getText().toString());

                if (qty < 10) {
                    qty++;
                    qtyText.setText(qty.toString());
                } else {
                }
            }
        });

        ImageView b = findViewById(R.id.cartBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, YoutubePlayerActivity.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initWebViewForNestedIFrame() {
        WebView webView = findViewById(R.id.map_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setHorizontalScrollBarEnabled(false);

        try (InputStream fin = getAssets().open("index1.html")) {
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer);
            webView.loadData(new String(buffer), "text/html", "UTF-8");
        } catch (IOException e) {
            System.out.println("Error occured while reading *.html file: "+e);
        }
    }
}
