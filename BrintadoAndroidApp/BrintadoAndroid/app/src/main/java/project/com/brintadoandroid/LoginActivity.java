package project.com.brintadoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar tb = (Toolbar) findViewById(R.id.login_toolbar);
        tb.setTitle("Log in");
        setSupportActionBar(tb);

        final EditText etName = (EditText) findViewById(R.id.login_username);
        final EditText etPass = (EditText) findViewById(R.id.login_pass);
        final Button bLogin = (Button) findViewById(R.id.login_button);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etName.getText().toString();
                final String pass = etPass.getText().toString();
            }
        });
    }
}
