package com.sinch.messagingtutorialskeleton;

import android.app.Activity;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.messagingtutorialskeleton.R;
import com.sinch.android.rtc.messaging.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwelch on 8/7/14.
 */
public class MessageAdapter extends BaseAdapter {

    public static final int DIRECTION_OUTGOING = 1;
    public static final int DIRECTION_INCOMING = 0;

    private List<Pair<Message, Integer>> messages;
    private LayoutInflater layoutInflater;

    public MessageAdapter(Activity activity) {
        layoutInflater = activity.getLayoutInflater();
        messages = new ArrayList<Pair<Message, Integer>>();
    }

    public void addMessage(Message message, int direction) {
        messages.add(new Pair<Message, Integer>(message, direction));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int direction = getItemViewType(position);
        if (convertView == null) {
            int res = 0;
            if (direction == DIRECTION_INCOMING) {
                res = R.layout.message_left;
            } else if (direction == DIRECTION_OUTGOING) {
                res = R.layout.message_right;
            }
            convertView = layoutInflater.inflate(res, parent, false);
        } else if (convertView != null) {
            Log.d("sinch", "not null");
        }

        Message message = messages.get(position).first;

        TextView txtmessage = (TextView)convertView.findViewById(R.id.txtMessage);
        txtmessage.setText(message.getTextBody());

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int i) {
        return messages.get(i).second;
    }
}
