package tech.villageprogrammer.apps.smartyoulearn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tech.villageprogrammer.apps.smartyoulearn.adapter.TopicDataAdapter;
import tech.villageprogrammer.apps.smartyoulearn.model.TopicData;

public class TopicDataActivity extends AppCompatActivity {
    List<TopicData> topicData;
    TopicData mtopicData;
    private String[] files;
    private String share_msg, app_url;
    String TAG = "names";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_data);
        String tutorial_location = "";
        String selected_tut;
        String[] title = getResources().getStringArray(R.array.cpp_tutorial);
        String[] location = getResources().getStringArray(R.array.cpp_tutorial);
        this.share_msg = getResources().getString(R.string.share_msg);
        this.app_url = getResources().getString(R.string.app_url);
        ImageButton share = findViewById(R.id.action_share);
        ImageButton about = findViewById(R.id.action_about);

        ListView topicList = findViewById(R.id.topic_list);
        final AlphaAnimation clickEffect = new AlphaAnimation(2F,0.2F);
        topicData  = new ArrayList<TopicData>();
        Intent intent1 = getIntent();
        selected_tut = intent1.getStringExtra("Tutorial");
        switch (selected_tut)
        {
            case "C++":
                tutorial_location = "file:///android_asset/cpp_tutorial/";
                title = getResources().getStringArray(R.array.cpp_tutorial);
                location = getResources().getStringArray(R.array.cpp_tutorial);
                break;
            case "C":
                tutorial_location = "file:///android_asset/c_tutorial/";
                title = getResources().getStringArray(R.array.c_tutorial);
                location = getResources().getStringArray(R.array.c_tutorial);
                break;
            case "Java":
                tutorial_location = "file:///android_asset/java_tutorial/";
                title = getResources().getStringArray(R.array.java_tutorial);
                location = getResources().getStringArray(R.array.java_tutorial);
                break;
            default:
                tutorial_location = "";

        }


        for(int i = 0; i< title.length; i++){
           String s = tutorial_location + title[i] + ".html";
           location[i] = s;

        }

        for(int i = 0; i< title.length; i++) {
            mtopicData = new TopicData(title[i],location[i]);
            topicData.add(mtopicData);
        }
        TopicDataAdapter topicDataAdapter = new TopicDataAdapter(getApplicationContext(),
                this.topicData);

        topicList.setAdapter(topicDataAdapter);

        topicList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mtopicData= topicData.get(i);
                String mtitle = mtopicData.getTitle();
                String mlocation = mtopicData.getLocation();
                Toast.makeText(TopicDataActivity.this,mtitle,Toast.LENGTH_SHORT).show();
                view.startAnimation(clickEffect);
                Intent intent = new Intent(TopicDataActivity.this, LoaderActivity.class);
                intent.putExtra("title",mtitle);
                intent.putExtra("location",mlocation);
                startActivity(intent, ActivityOptions.makeScaleUpAnimation(view,200,500,
                        200,200).toBundle());
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
                Intent intent = new Intent(TopicDataActivity.this,AboutActivity.class);
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
//    public boolean list_files(String path){
//        String[] listOfFiles = {};
//        String[] list = {};
//        try {
//            listOfFiles = getAssets().list("");
//            if (listOfFiles.length > 0) {
//                String TAG = "MYFILE";
//                for (String file:listOfFiles) {
//                    if(!list_files(path+"/"+file)) {
//                        return false;
//                    }
//                    else{
//                        Log.v(TAG,file);
//
////                        for(int i=0;i<listOfFiles.length;i++){
////                            this.files[i] = listOfFiles[i];
////                        }
//                    }
//                }
//            }
//        }
//        catch (IOException e){
//            System.out.println("No files !!!!");
//        }
//        return false;
//    }
}
