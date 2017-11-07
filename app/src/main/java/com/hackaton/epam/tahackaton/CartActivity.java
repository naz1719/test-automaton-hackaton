package com.hackaton.epam.tahackaton;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hackaton.epam.tahackaton.util.SendMessage;

import java.io.IOException;
import java.io.InputStream;

public class CartActivity extends AppCompatActivity {

    private TextView qtyText;
    private String email;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initWebViewForNestedIFrame();
        Intent cart = getIntent();
        String image = cart.getStringExtra("image");
        email = cart.getStringExtra("username");
        setProductImage(image);
        final TextView errorView = findViewById(R.id.error_count);

        ImageView cartMinus = findViewById(R.id.cartMinus);
        cartMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qtyText = findViewById(R.id.cartQty);
                Integer qty = Integer.parseInt(qtyText.getText().toString());

                if (qty > 1) {
                    qty--;
                    qtyText.setText(qty.toString());
                    errorView.setError(null);
                    errorView.setText("");
                } else {
                    errorView.setError("Yeah! Like you can have less!");
                    errorView.setText("Yeah! Like you can have less!");
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
                    errorView.setError(null);
                    errorView.setText("");
                    qtyText.setText(qty.toString());
                } else {
                    errorView.setError("10 products remaining");
                    errorView.setText("10 products remaining!");
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

        TextView contactUs = findViewById(R.id.contactUs);
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });

        Button payNow = findViewById(R.id.payNow);
        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String[] pieces = LoginActivity.DUMMY_CREDENTIALS.get(0).split(":");
                            new SendMessage().sendFromGmail(pieces[0], pieces[1], new String[]{email}, "Congratulations", "You bought new Kanken Kids backpack.");
                        } catch (Throwable e) {
                            System.out.println("Didn't send any email." + e);
                        }
                    }
                }).start();
                Context context = getApplicationContext();
                CharSequence text = "Congratulations! Check email for more information";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
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
            System.out.println("Error occured while reading *.html file: " + e);
        }
    }

    private void setProductImage(String image) {
        ImageView prod = findViewById(R.id.cart_product);
        int[] images = {R.drawable.basket_activity_first_product, R.drawable.basket_activity_second_product, R.drawable.basket_activity_third_product, R.drawable.basket_activity_fourth_product};
        switch (image) {
            case "first":
                prod.setImageResource(images[0]);
                break;
            case "second":
                prod.setImageResource(images[1]);
                break;
            case "third":
                prod.setImageResource(images[2]);
                break;
            case "fourth":
                prod.setImageResource(images[3]);
                break;
            default:
                prod.setImageResource(images[0]);
        }
    }
}
