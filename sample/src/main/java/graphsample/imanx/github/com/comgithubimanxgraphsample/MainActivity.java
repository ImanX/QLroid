package graphsample.imanx.github.com.comgithubimanxgraphsample;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.github.imanx.QLroid.Mutation;
import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.argument.Arg;
import com.github.imanx.QLroid.callback.Callback;
import com.github.imanx.QLroid.request.Argument;
import com.github.imanx.QLroid.request.Header;
import com.github.imanx.QLroid.request.Request;
import com.github.imanx.qlroidx.GraphCallback;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private String baseUrl = "http://192.168.66.115/api/graphql/my";
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        com.github.imanx.qlroidx.Query query = new com.github.imanx.qlroidx.Query("Me", new Me());
        new com.github.imanx.qlroidx.Request(baseUrl, query)
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjdjMDJmMGJjMDU0MDg2YjkyZjUyMDI1NzNlNGIzOWNmYTIwNTFmM2RlNmRlYjQ1YjA1ZWUxYmZlOGUwMGY4M2M5OWM1ZGY1ZGE5YWM3MmMyIn0.eyJhdWQiOiIxIiwianRpIjoiN2MwMmYwYmMwNTQwODZiOTJmNTIwMjU3M2U0YjM5Y2ZhMjA1MWYzZGU2ZGViNDViMDVlZTFiZmU4ZTAwZjgzYzk5YzVkZjVkYTlhYzcyYzIiLCJpYXQiOjE1Mzc0NjMxNDgsIm5iZiI6MTUzNzQ2MzE0OCwiZXhwIjoxNTY4OTk5MTQ4LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.p5Py07yami1SDeJxhcpMVQ776CNKqxe3ykoYLwthnuayIpz4ED36eaQWgmHywW61v9wOXATBvriBKouZWWpk4VhrKe08NseahvQoCXnosjsHh9-JEFJKmqYZUb9PCSIhIq-MVRehwXwv_EJQho0XA0nZuwbIX_Y3SzwmnXf72jb0KoHf0xkckHqXeRW0vGFezd_PRAS0IFaClrtqyJ8oPT3NpTic0wnRIIBuYgLOnbi6kiamgxgq5h8Ib4rlTgPSw2h46R4JtMttJUXl6xRfBmO6TedRMTmcjGWz6MYYUqwfg3AkgAedbMdyg7OVkIGpKplUu_nKPtL4oseHZeK9_yirrhyhCm3WaRLM7AYdJt_m7Trd76f58b7jiWQGRdNhp0y9KleF_uDAm0or_YuYqzaV7bzZSKFhrXkglQxWC2YnvVDJ7-0rnftQiZyhV47_ddbfbqgjW5B0INml7e7MZaTu6f9baVg50swf0XDlKmwCjy-Wa3JaN-5gyaUzAuCkYGjNVr7seMz5MbO5RUfH1g5X2j4b48JWVrLEKurilEpCNCbqTwdX7BXHavdr10DWlMhN3-zDcmCCDjFIeZ9Ux89fKgfvk_NAr6GdKiDYEZdNZKBtGZ0ycceNxRV75DfqGJ_mQScp5wxunQkbQcYhmD3hc08QUQ1mo789-KwbjA4")
                .setTimeout(10)
                .enqueue(new GraphCallback<Me>() {
                    @Override
                    public void onResponse(com.github.imanx.qlroidx.Request request, String response, Me me) {
                        Log.i(TAG, "onResponse: " + me.getEmail());
                    }

                    @Override
                    public void onFailure(com.github.imanx.qlroidx.Request request, String response, int status, Exception ex) {

                    }

                    @Override
                    public Type getType() {
                        return new TypeToken<Me>() {
                        }.getType();
                    }
                });

//        final TextView txt = findViewById(R.id.txt);
//
//        baseUrl = "http://91.239.55.205:8080/api/graphql/my";
//
//        this.uri = Uri.parse(baseUrl);
//
//        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFmMjAxZjEzMjYxYjhkN2NjNGY3MGNmOTEyOWMwZWJmMzcwYzU5ZTc4OWZmMTI0MmY0YjUwZDY2NmFhZTMyOGQyZDkxMDA5MmExMWE2Y2EwIn0.eyJhdWQiOiIxIiwianRpIjoiYWYyMDFmMTMyNjFiOGQ3Y2M0ZjcwY2Y5MTI5YzBlYmYzNzBjNTllNzg5ZmYxMjQyZjRiNTBkNjY2YWFlMzI4ZDJkOTEwMDkyYTExYTZjYTAiLCJpYXQiOjE1MzYzOTA0OTAsIm5iZiI6MTUzNjM5MDQ5MCwiZXhwIjoxNTY3OTI2NDg5LCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.bYsMikHMgLwF0jCBwrvy75jGLRSr4hE-eBdgleefdIB4l566KdpXuQeZGcYiCT_3zYRLa818EN0poYAPsl57oHJsDJ6gG4u1HmulJ6W83CH3CdL9PNR2pEBJ3r9B7-Ma28FKRm7dAIEIe8tBNZYfnemPvSI13qzMsRNC7qAnJ9tW2MWwvYp0D4T3yvyKr4bzjP28t1iae_4EJWLHfUSAgqzC9swXJZHJnvMs7Oe-HI7sCVmK79XZt-Tn-DMSficthU2T7U8JMWuMqgc7dMpJzkeVB4d4rptuC_rFUltK0LlwNis7LkJCMQW4CKPblZSndDKJ8l69QcF9OWgRgGGtDiHPAazPS5u-MxkceKX9f5usaBCTChFD3PQ1FwnXvkrGO_ZDQP2IFNCZSTXCwVdlXmvsEsM1vDYfElfLdA2LyUTE81nuX-4Ps3KJ-RXH3lscHRjjHpIETzhZaCoUbXSYi7I3EC789KKFdj5MNLSPtpdcuxxzFlMZ9pHIo77AUgo8az0HhKpAqlZ5o5rIcf-dn6M1V_4fHptrPzwfJgyuzIhdzBaD4S7TYdOWY-A_x75w8TG6UXR9Paffe4ZjITkOmbsNdlVpcLd6qaoaQX0nskw-92xVfDkRm48HT-K9JZf0dfHY4ROfuiLeSr4KTyJyCmTs6iFRBK3xPR1BUr2aQ_U";
//
//        Header header = new Header();
//        header.append(
//                "Authorization", token);
//
//        getMutation().setHeader(header)
//                .setTimeout(10)
//                .build()
//                .enqueue(new Callback() {
//                    @Override
//                    public void onResponse(final String response) {
//
//                        txt.setText("response : " + response);
//                    }
//
//                    @Override
//                    public void onFailure(final int httpCode, final String data) {
//                        txt.setText("response failure " + httpCode + " || " + data);
//                    }
//                });
    }

    public Request.Builder getQuery() {
        return new Request.Builder(this, uri, new Query() {
            @Nullable
            @Override
            public String getOperationName() {
                return "Cards";
            }

            @Override
            public String[] getResponseFields() {
                return new String[]{"id", "pan"};
            }
        });
    }

    // get Mutation Request Builder
    public Request.Builder getMutation() {

        return new Request.Builder(this, uri, new Mutation(new Me()) {

            @Override
            public String getOperationName() {
                return "PreferencesEdit";
            }

            @Override
            public String[] getResponseFields() {
                return new String[]{"id"};
            }

            @Override
            public Argument getArgument() {
                Argument argument = new Argument();
                argument.add(new Arg("username", "farshid_zp", String.class)
                        , new Arg("email", "farshid@email.com", String.class));


                try {
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


                    argument.add(new Arg("notification_preferences", array, UserNotificationPreferencesInput[].class));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return argument;
            }
        });
    }
}
