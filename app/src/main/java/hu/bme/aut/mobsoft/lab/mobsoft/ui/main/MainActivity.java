package hu.bme.aut.mobsoft.lab.mobsoft.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.lab.mobsoft.MobSoftApplication;
import hu.bme.aut.mobsoft.lab.mobsoft.R;
import hu.bme.aut.mobsoft.lab.mobsoft.ui.recipes.RecipesActivity;

public class MainActivity extends AppCompatActivity implements MainScreen {

    @Inject
    MainPresenter mainPresenter;

    boolean login = false;
    int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobSoftApplication.injector.inject(this);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                login = true;

                EditText userNameEditText  = (EditText)findViewById(R.id.usernameEditText);
                EditText passwordEditText  = (EditText)findViewById(R.id.passwordEditText);
                String username =userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                mainPresenter.login(username, password);
            }
        });

        TextView continueTextView = (TextView) findViewById(R.id.continueTextView);
        continueTextView.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                login = false;
                mainPresenter.continueWithoutLogin();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
        checkPermission();
    }

    //forrás: http://stackoverflow.com/questions/32599132/securityexception-permission-denial-reading-only-on-emulator
    private void checkPermission(){
        int writeStoragePermissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int internetPermissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        if (writeStoragePermissionCheck != PackageManager.PERMISSION_GRANTED ||
                internetPermissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (!(requestCode == REQUEST_CODE && (grantResults.length > 1) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)
        && (grantResults[1] == PackageManager.PERMISSION_GRANTED))) {
            showMessage("You cannot use the app without granting permissions.");
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public void navigateToRecipes() {
        Intent intent = new Intent(getApplicationContext(), RecipesActivity.class);
        intent.putExtra("Login", login);
        startActivity(intent);
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
