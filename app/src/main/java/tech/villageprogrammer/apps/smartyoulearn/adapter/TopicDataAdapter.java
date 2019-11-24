package tech.villageprogrammer.apps.smartyoulearn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import java.util.List;

import tech.villageprogrammer.apps.smartyoulearn.R;
import tech.villageprogrammer.apps.smartyoulearn.model.TopicData;

import static tech.villageprogrammer.apps.smartyoulearn.R.id.text;

public class TopicDataAdapter extends BaseAdapter {
    private Context appicationContext;
    private List<TopicData> topicData;
    private TextView textView;
    private ViewHolder viewHolder;
    private class ViewHolder{
        TextView textView;
    }

    public TopicDataAdapter(Context applicationContext, List<TopicData> topicData) {
        this.appicationContext = applicationContext;
        this.topicData = topicData;
    }

    @Override
    public int getCount() {
        return topicData.size();
    }

    @Override
    public Object getItem(int i) {
        return topicData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount(){
        return getCount();
    }
    @Override
    public int getItemViewType(int position){
        return position;
    }

    @Override
    public View getView(int i, View view,@NonNull ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) appicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.topic_list_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.text2);
            TopicData newtopicData = topicData.get(i);
            viewHolder.textView.setText(newtopicData.getTitle());
            view.setTag(viewHolder);

        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        return view;


    }
}
