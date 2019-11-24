package tech.villageprogrammer.apps.smartyoulearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;


public class LoaderActivity extends AppCompatActivity {
    private WebView loader;
    private ImageButton share, about;
    private String share_msg, app_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        loader = findViewById(R.id.webview);
        TextView title = findViewById(R.id.title);
        String text;
        String url;
        Intent i = getIntent();
        text = i.getStringExtra("title");
        url = i.getStringExtra("location");
        this.share_msg = getResources().getString(R.string.share_msg);
        this.app_url = getResources().getString(R.string.app_url);
        share = findViewById(R.id.action_share);
        about = findViewById(R.id.action_about);
        WebSettings webSettings = loader.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        title.setText(text);
        loader.setWebChromeClient(new WebChromeClient());
        loader.loadUrl(url);
        share.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
            share_it();
        }
    });
        about.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoaderActivity.this,AboutActivity.class);
            startActivity(intent);
        }
    });

    }
    public void share_it(){
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String body = share_msg;
        String shareUrl = app_url;
        body = body + shareUrl;
        String sub = "SmartYou.Learn Shared Post";
        myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
        myIntent.putExtra(Intent.EXTRA_TEXT,body);
        startActivity(Intent.createChooser(myIntent, "Share Using"));
    }
}
