package graphsample.imanx.github.com.comgithubimanxgraphsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.imanx.QLroid.argument.Arg;
import com.github.imanx.qlroidx.Argument;
import com.github.imanx.qlroidx.GraphCallback;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private String baseUrl = "http://91.239.55.205:8080/api/graphql/my";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        com.github.imanx.qlroidx.Query query = new com.github.imanx.qlroidx.Query("Cards", new Card());
        com.github.imanx.qlroidx.Mutation mutation =
                new com.github.imanx.qlroidx.Mutation("PreferencesEdit", getArgument(), new Me());


        new com.github.imanx.qlroidx.Request(baseUrl, mutation)
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjdjMDJmMGJjMDU0MDg2YjkyZjUyMDI1NzNlNGIzOWNmYTIwNTFmM2RlNmRlYjQ1YjA1ZWUxYmZlOGUwMGY4M2M5OWM1ZGY1ZGE5YWM3MmMyIn0.eyJhdWQiOiIxIiwianRpIjoiN2MwMmYwYmMwNTQwODZiOTJmNTIwMjU3M2U0YjM5Y2ZhMjA1MWYzZGU2ZGViNDViMDVlZTFiZmU4ZTAwZjgzYzk5YzVkZjVkYTlhYzcyYzIiLCJpYXQiOjE1Mzc0NjMxNDgsIm5iZiI6MTUzNzQ2MzE0OCwiZXhwIjoxNTY4OTk5MTQ4LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.p5Py07yami1SDeJxhcpMVQ776CNKqxe3ykoYLwthnuayIpz4ED36eaQWgmHywW61v9wOXATBvriBKouZWWpk4VhrKe08NseahvQoCXnosjsHh9-JEFJKmqYZUb9PCSIhIq-MVRehwXwv_EJQho0XA0nZuwbIX_Y3SzwmnXf72jb0KoHf0xkckHqXeRW0vGFezd_PRAS0IFaClrtqyJ8oPT3NpTic0wnRIIBuYgLOnbi6kiamgxgq5h8Ib4rlTgPSw2h46R4JtMttJUXl6xRfBmO6TedRMTmcjGWz6MYYUqwfg3AkgAedbMdyg7OVkIGpKplUu_nKPtL4oseHZeK9_yirrhyhCm3WaRLM7AYdJt_m7Trd76f58b7jiWQGRdNhp0y9KleF_uDAm0or_YuYqzaV7bzZSKFhrXkglQxWC2YnvVDJ7-0rnftQiZyhV47_ddbfbqgjW5B0INml7e7MZaTu6f9baVg50swf0XDlKmwCjy-Wa3JaN-5gyaUzAuCkYGjNVr7seMz5MbO5RUfH1g5X2j4b48JWVrLEKurilEpCNCbqTwdX7BXHavdr10DWlMhN3-zDcmCCDjFIeZ9Ux89fKgfvk_NAr6GdKiDYEZdNZKBtGZ0ycceNxRV75DfqGJ_mQScp5wxunQkbQcYhmD3hc08QUQ1mo789-KwbjA4")
                .setTimeout(10)
                .enqueue(new GraphCallback<Me>() {
                    @Override
                    public void onResponse(com.github.imanx.qlroidx.Request request, String response, Me me) {
                        Log.i(TAG, "onResponse: " + me.getFirstName());
                    }

                    @Override
                    public void onFailure(com.github.imanx.qlroidx.Request request, String response, int status, Exception ex) {
                        Log.i(TAG, "onFailure: " + response + " || " + status + " || ex : " + ex.getMessage());
                    }

                    @Override
                    public Type getType() {
                        return new TypeToken<Me>() {
                        }.getType();
                    }
                });
    }


    public Argument getArgument() {

        Argument argument = new Argument();

        try {

            argument.add(new Argument.SubArg("username", "farshid_zp", String.class));
            argument.add(new Argument.SubArg("email", "farshid@email.com", String.class));

            JSONArray array = new JSONArray();

            // New Ticket

            JSONObject newTicket = new JSONObject();

            newTicket.put("type", "TICKET_NEW");

            JSONArray channels = new JSONArray();
            channels.put("MAIL");
            channels.put("PUSH");

            newTicket.put("channels", channels);


            // Replay Ticket

            JSONObject ticketReplay = new JSONObject();
            ticketReplay.put("type", "TICKET_REPLY");

            JSONArray channelsReplay = new JSONArray();
            channelsReplay.put("MAIL");
            channelsReplay.put("WEBHOOK");

            ticketReplay.put("channels", channelsReplay);


            // Add Items
            array.put(newTicket);
            array.put(ticketReplay);


            argument.add(new Argument.SubArg("notification_preferences", array.toString(), UserNotificationPreferencesInput[].class));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return argument;


    }

}
