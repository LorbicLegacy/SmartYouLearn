package tech.villageprogrammer.apps.smartyoulearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutActivity extends AppCompatActivity {
    private String share_msg, app_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        String aboutUrl = getResources().getString(R.string.about_file);
        final String github_url = getResources().getString(R.string.github_url);
        final String website_url  =getResources().getString(R.string.website_url);
        this.share_msg = getResources().getString(R.string.share_msg);
        this.app_url = getResources().getString(R.string.app_url);
        final AlphaAnimation clickEffect = new AlphaAnimation(2F,0.2F);

        WebView about_loader = findViewById(R.id.about_webview);
        ImageButton share_btn = findViewById(R.id.action_share);
        ImageButton about_btn = findViewById(R.id.action_about);
        Button github_btn = findViewById(R.id.github_btn);
        Button website_btn = findViewById(R.id.website_btn);

        github_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(github_url));
                startActivity(i);
                v.startAnimation(clickEffect);
            }
        });
        website_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(website_url));
                startActivity(i);
                v.startAnimation(clickEffect);

            }
        });

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_it();
            }
        });
        about_btn.setVisibility(View.INVISIBLE);
        about_loader.loadUrl(aboutUrl);
        WebSettings settings = about_loader.getSettings();
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
