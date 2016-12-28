package project.com.brintadoandroid;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest  extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "";
    private Map<String, String> parameters;

    public RegisterRequest (String username, String password, String email, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        parameters = new HashMap<>();

        parameters.put("username", username);
        parameters.put("password", password);
        parameters.put("email", email);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
