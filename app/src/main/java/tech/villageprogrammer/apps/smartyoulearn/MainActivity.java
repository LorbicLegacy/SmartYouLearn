package tech.villageprogrammer.apps.smartyoulearn;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tech.villageprogrammer.apps.smartyoulearn.adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {
    private String[]data = {"C","C++","Java","C#","Python",
            "Django","Flask","Numpy","Pandas","Matplotlib","Scipy",
            "Seaborn","HTML","CSS","JavaScript","XML","PHP",
            "Machine Learning","Cyber Security","Computer Hardware"};
    private String share_msg, app_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView mListView;
        ImageButton share, about;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.share_msg = getResources().getString(R.string.share_msg);
        this.app_url = getResources().getString(R.string.app_url);
        share = findViewById(R.id.action_share);
        about = findViewById(R.id.action_about);
        final AlphaAnimation clickEffect = new AlphaAnimation(2F,0.2F);
        mListView = findViewById(R.id.ListView);

        ArrayList <String> arrayList = new ArrayList<>();
        for (String mdata :data){
            arrayList.add(mdata);
        }
        MyAdapter adapter = new MyAdapter(this,R.layout.list_item,arrayList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                view.startAnimation(clickEffect);
                Intent intent = new Intent(MainActivity.this, TopicDataActivity.class);
                intent.putExtra("Tutorial",text);
                startActivity(intent, ActivityOptions.makeScaleUpAnimation(view,200,500,200,200).toBundle());
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share_it();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this,AboutActivity.class);
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
