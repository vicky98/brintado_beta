package project.com.brintadoandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        Toolbar tb = (Toolbar) findViewById(R.id.reg_toolbar);
        tb.setTitle("Registration");
        setSupportActionBar(tb);

        final EditText etUsername = (EditText) findViewById(R.id.reg_username);
        final EditText etPass1 = (EditText) findViewById(R.id.reg_pass_1);
        final EditText etPass2 = (EditText) findViewById(R.id.reg_pass_2);
        final EditText etEmail = (EditText) findViewById(R.id.reg_email);
        final Button regButton = (Button) findViewById(R.id.reg_button);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String pass1 = etPass1.getText().toString();
                final String pass2 = etPass2.getText().toString();
                final String email = etEmail.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success && (pass1.equals(pass2)) && (!username.equals("")) &&
                                    (!pass1.equals("")) && (!email.equals(""))) {
                                Toast.makeText(RegisterActivity.this, "Регистрацията е успешна!",
                                        Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(RegisterActivity.this, Home.class);
                                startActivity(intent);
                            } else {
                                if (!success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setTitle("Brintado");
                                    builder.setMessage("Неуспешна регистрация.");
                                    builder.setNegativeButton("Опитай пак", null);
                                    builder.create();
                                    builder.show();
                                }

                                if ((username.equals("")) || (pass1.equals("")) || (pass2.equals(""))
                                        || (email.equals(""))) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setTitle("Brintado");
                                    builder.setMessage("Моля, попълнете всички полета.");
                                    builder.setNegativeButton("Добре", null);
                                    builder.create();
                                    builder.show();
                                }

                                if (!pass1.equals(pass2)) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setTitle("Brintado");
                                    builder.setMessage("Паролите не съвпадат.");
                                    builder.setNegativeButton("Опитай пак", null);
                                    builder.create();
                                    builder.show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(username, pass1, email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Register Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}