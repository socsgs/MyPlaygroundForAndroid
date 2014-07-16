package com.socs.myplayground.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by SocsDrive on 7/11/2014.
 */
public class MainActivity extends Activity implements IMainView{
    private final static String TAG = "MainActivity";
    private EditText emailAddressText;
    private EditText passwordText;
    IMainPresenter mainPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter = new MainPresenter(this);

        Log.d(TAG, "onCreate");
        setContentView(R.layout.main_activity);

        emailAddressText = (EditText) findViewById(R.id.email_text);
        passwordText = (EditText) findViewById(R.id.password_text);
        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.loginClicked();
            }
        });
    }

    @Override
    public String getEmailAddress() {
        return emailAddressText != null && emailAddressText.getText() != null ? emailAddressText.getText().toString() : "";
    }

    @Override
    public String getPassword() {
        return passwordText != null && passwordText.getText() != null ? passwordText.getText().toString() : "";
    }

    @Override
    public void showLoginSuccess() {
        new AlertDialog.Builder(this)
                .setTitle("Login")
                .setMessage("Successfully logged in")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    @Override
    public void showLoginFailure() {
        new AlertDialog.Builder(this)
                .setTitle("Login")
                .setMessage("Login unsuccessful, either user id or password is wrong")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void showUserNotFound() {
        new AlertDialog.Builder(this)
                .setTitle("Login")
                .setMessage("User not found")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
