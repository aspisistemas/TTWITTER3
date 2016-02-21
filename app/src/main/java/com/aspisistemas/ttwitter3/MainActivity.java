package com.aspisistemas.ttwitter3;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


//import com.parse.LogInCallback;
//import com.parse.ParseACL;
//import com.parse.ParseAnalytics;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseUser;
//import com.parse.SignUpCallback;


import java.text.ParseException;

public class MainActivity extends AppCompatActivity

{
    private EditText usuario;
    private EditText senha;
    private Button login;
   // private Log log;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        usuario = (EditText) findViewById(R.id.edtUsuario);
        senha = (EditText) findViewById(R.id.edtSenha);
        login = (Button) findViewById(R.id.btnLogin);


        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null)

        {
            //mostraListaUsuario();
        }
    }




    public void loginOuSignup(View view)

    {
        ParseUser.logInInBackground(String.valueOf(usuario.getText()), String.valueOf(senha.getText()), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {

                    Log.i("AppInfo", "Logged in");

                    // mostraListaUsuario();
                } else {

                    ParseUser newUser = new ParseUser();
                    //newUser.setUsername(String.valueOf(usuario.getText()));
                    //newUser.setPassword(String.valueOf(senha.getText()));

                    newUser.signUpInbackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                Log.i("AppInfo", "Sinned Up");
                                // mostraListaUsuario();
                            } else {
                                Toast.makeText(getApplication(), "Não foi posiivel realizar o ligin ou sign up - por favor tente novamente.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.aspisistemas.ttwitter3/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.aspisistemas.ttwitter3/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
