package graphsample.imanx.github.com.comgithubimanxgraphsample;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.imanx.QLroid.Callback;
import com.github.imanx.QLroid.Header;
import com.github.imanx.QLroid.Query;
import com.github.imanx.QLroid.Request;

public class MainActivity extends AppCompatActivity implements Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Header header = new Header();
        header.append(
                "Authorization",
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjFjMTc0Y2U1Zjg5MWNmNjc5ZDk0ZDcwZWNhYWU0NWU5ZDAyN2Y2MDViOWY1ZDBiZDRlMTc2ZjRhOGY5OTUyNWUyZmUwNzg4YzliOWVjMGM4In0.eyJhdWQiOiIxIiwianRpIjoiMWMxNzRjZTVmODkxY2Y2NzlkOTRkNzBlY2FhZTQ1ZTlkMDI3ZjYwNWI5ZjVkMGJkNGUxNzZmNGE4Zjk5NTI1ZTJmZTA3ODhjOWI5ZWMwYzgiLCJpYXQiOjE1MzMxMDc4NDQsIm5iZiI6MTUzMzEwNzg0NCwiZXhwIjoxNTY0NjQzODQ0LCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.h6MMHTmF36Syru8IRCLJwE947zvIOv2WdXcdw1gf0odI_ANwHzZyZu6ZUhJPUO_FtLIFGymlgumEo_XRAPjdxCVlM-sepkWIqjsavZHwbdcj5bhW32CUqBpoXSh4tfLNc1YdjrbnlXjExlL3UsOmYkN_er5WQNiXLYH8QYDelGLHCF6eZWE6YvDHB8QzDcY6pjFEmWvXgpBuoR2OXkNDibPhOQC7N4sdvozQ-5adDuEeOfD_e39ZyiaV3WIFYYotXef-pNAk7RIJVRlk077fGYmVKL-oUQnNfLca7w_QE5v3XDYUm0hJceXSNdmiMQ9_YjZOKOKUBh_wwpVDthQzw8c34bsGDasVmYW7BCyDYa-0hqsK2hR0F6RczwqDJi33vyVPN2NCZN2B1exu8O4Cu2GAohw81YfEFsqLrmukbF1PFyRcChPQmNgfDMLjT-jqi23xwS4A9MQFhafv3ZC106pUwUI088qmAtTDMG2A8YEkMgVXbH7GlPFWGF6qkDUC0EcRJNRUTLNKBujllRvMaRbgi_3hDpthh-t8WRlsgs_j3kJFyv2LKO8eNhGvJ0fjM9Al-Ght7a3uIyTz15-x1aval7Ib_YrF52XVefBfmWZ5xXa9twL_hstD1lYgzSEvsQOlZNtfe374VCRdFwq0veOO7DJk2NG2zGHavsgM4FE");

        new Request.Builder(this, Uri.parse("http://192.168.66.115/api/graphql"), new Query() {

            @Override
            public String[] getResponseFields() {
                return new String[]{"id", "birthday", "username", "avatar"};
            }

            @Override
            public String getOperationName() {
                return "Me";
            }


        })
                .setHeader(header)
                .setTimeout(10)
                .build()
                .enqueue(this);

    }


    @Override
    public void onResponse(String response) {
        Log.i("TAG", "onResponse: " + response);
    }

    @Override
    public void onFailure() {

    }
}
