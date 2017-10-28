package com.hackaton.epam.tahackaton;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    private AutoCompleteTextView secretCodeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_registration);
        TextView userName = findViewById(R.id.usernamereg);
        userName.setText("Hello, " + intent.getStringExtra("username") + ". Please, find your secret code inside your email box to complete registration.");
        secretCodeView = findViewById(R.id.secret);

        Button secretCodeBtn = findViewById(R.id.secretBrn);
        secretCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSecretCode();
            }
        });
    }

    private void attemptSecretCode() {
        secretCodeView.setError(null);
        View focusView = null;
        boolean cancel = false;
        final String secretCode = secretCodeView.getText().toString();

        if (TextUtils.isEmpty(secretCode) || secretCode.length() < 9) {
            secretCodeView.setError(getString(R.string.error_secret_short));
            focusView = secretCodeView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else if (LoginActivity.winNumber.equals(secretCode)) {
            Intent intent = new Intent(RegistrationActivity.this, BasketActivity.class);
            startActivity(intent);
            finish();
        } else {
            secretCodeView.setError(null);
            secretCodeView.setError(getString(R.string.error_incorrect_secret_code));
        }
    }
}
