package ru.gb.lesson4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class PrefsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout layoutLogin;
    private Button saveLogin;

    private SharedPreferences prefs;

    public static final String CUSTOM = "CUSTOM";
    public static final String LOGIN = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);

        prefs = getSharedPreferences(CUSTOM, MODE_PRIVATE);

        layoutLogin = findViewById(R.id.layout_login);
        saveLogin = findViewById(R.id.save_login);

        String login = prefs.getString(LOGIN, "");

        layoutLogin.getEditText().setText(login);
        saveLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String login = layoutLogin.getEditText().getText().toString();
        if(isLoginIsValid(login))
        {
            layoutLogin.setError(null);
            prefs.edit().putString(LOGIN, login).apply();
        }
        else {
            layoutLogin.setError("Login is invalid!");
        }
    }

    private boolean isLoginIsValid(String login) {
        return login.matches(".*dima.*");
    }
}