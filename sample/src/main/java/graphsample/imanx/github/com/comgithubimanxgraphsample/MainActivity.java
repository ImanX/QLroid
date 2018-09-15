package graphsample.imanx.github.com.comgithubimanxgraphsample;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.imanx.QLroid.Mutation;
import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.argument.Arg;
import com.github.imanx.QLroid.callback.Callback;
import com.github.imanx.QLroid.http.Argument;
import com.github.imanx.QLroid.http.Header;
import com.github.imanx.QLroid.http.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private String baseUrl = "http://192.168.66.115/api/graphql/my";
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txt = findViewById(R.id.txt);

        baseUrl = "http://91.239.55.205:8080/api/graphql/my";

        this.uri = Uri.parse(baseUrl);

        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFmMjAxZjEzMjYxYjhkN2NjNGY3MGNmOTEyOWMwZWJmMzcwYzU5ZTc4OWZmMTI0MmY0YjUwZDY2NmFhZTMyOGQyZDkxMDA5MmExMWE2Y2EwIn0.eyJhdWQiOiIxIiwianRpIjoiYWYyMDFmMTMyNjFiOGQ3Y2M0ZjcwY2Y5MTI5YzBlYmYzNzBjNTllNzg5ZmYxMjQyZjRiNTBkNjY2YWFlMzI4ZDJkOTEwMDkyYTExYTZjYTAiLCJpYXQiOjE1MzYzOTA0OTAsIm5iZiI6MTUzNjM5MDQ5MCwiZXhwIjoxNTY3OTI2NDg5LCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.bYsMikHMgLwF0jCBwrvy75jGLRSr4hE-eBdgleefdIB4l566KdpXuQeZGcYiCT_3zYRLa818EN0poYAPsl57oHJsDJ6gG4u1HmulJ6W83CH3CdL9PNR2pEBJ3r9B7-Ma28FKRm7dAIEIe8tBNZYfnemPvSI13qzMsRNC7qAnJ9tW2MWwvYp0D4T3yvyKr4bzjP28t1iae_4EJWLHfUSAgqzC9swXJZHJnvMs7Oe-HI7sCVmK79XZt-Tn-DMSficthU2T7U8JMWuMqgc7dMpJzkeVB4d4rptuC_rFUltK0LlwNis7LkJCMQW4CKPblZSndDKJ8l69QcF9OWgRgGGtDiHPAazPS5u-MxkceKX9f5usaBCTChFD3PQ1FwnXvkrGO_ZDQP2IFNCZSTXCwVdlXmvsEsM1vDYfElfLdA2LyUTE81nuX-4Ps3KJ-RXH3lscHRjjHpIETzhZaCoUbXSYi7I3EC789KKFdj5MNLSPtpdcuxxzFlMZ9pHIo77AUgo8az0HhKpAqlZ5o5rIcf-dn6M1V_4fHptrPzwfJgyuzIhdzBaD4S7TYdOWY-A_x75w8TG6UXR9Paffe4ZjITkOmbsNdlVpcLd6qaoaQX0nskw-92xVfDkRm48HT-K9JZf0dfHY4ROfuiLeSr4KTyJyCmTs6iFRBK3xPR1BUr2aQ_U";

        Header header = new Header();
        header.append(
                "Authorization", token);

        getMutation().setHeader(header)
                .setTimeout(10)
                .build()
                .enqueue(new Callback() {
                    @Override
                    public void onResponse(final String response) {

                        txt.setText("response : " + response);
                    }

                    @Override
                    public void onFailure(final int httpCode, final String data) {
                        txt.setText("response failure " + httpCode + " || " + data);
                    }
                });
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
