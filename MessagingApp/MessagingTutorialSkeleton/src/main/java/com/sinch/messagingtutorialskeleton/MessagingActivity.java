package com.sinch.messagingtutorialskeleton;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.messagingtutorialskeleton.R;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.messaging.Message;
import com.sinch.android.rtc.messaging.MessageClient;
import com.sinch.android.rtc.messaging.MessageClientListener;
import com.sinch.android.rtc.messaging.MessageDeliveryInfo;
import com.sinch.android.rtc.messaging.MessageFailureInfo;

import java.util.List;


/**
 * Created by jwelch on 8/5/14.
 */
public class MessagingActivity extends Activity implements ServiceConnection, MessageClientListener {
    private String recipientId;
    private Button sendButton;
    private EditText messageBodyField;
    private String messageBody;
    private MessageService.MessageServiceInterface messageService;
    //private MessageAdapter messageAdapter;
    //private ListView messagesList;


    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.messaging);
        doBind();
        Intent intent = getIntent();
        recipientId = intent.getStringExtra("RECIPIENT_ID");

        sendButton = (Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

    }

    private void doBind() {
        Intent serviceIntent = new Intent(this, MessageService.class);
        bindService(serviceIntent, this, BIND_AUTO_CREATE);
    }

    private void sendMessage() {
        messageBodyField = (EditText)findViewById(R.id.messageBodyField);
        messageBody = messageBodyField.getText().toString();
        if (messageBody.isEmpty()) {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_LONG).show();
            return;
        }
        messageService.sendMessage(recipientId, messageBody);
        messageBodyField.setText("");
        }

    @Override
    public void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }

    @Override
    public void onIncomingMessage(MessageClient messageClient, Message message) {

    }

    @Override
    public void onMessageSent(MessageClient messageClient, Message message, String s) {
        // update UI on outgoing message
    }

    @Override
    public void onMessageFailed(MessageClient messageClient, Message message, MessageFailureInfo messageFailureInfo) {
        Toast.makeText(this, "Message failed to send", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessageDelivered(MessageClient messageClient, MessageDeliveryInfo messageDeliveryInfo) {
        // intentionally left blank
    }

    @Override
    public void onShouldSendPushData(MessageClient messageClient, Message message, List<PushPair> pushPairs) {
        // left blank
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder iBinder) {
        //Define the messaging service and add a listener
        messageService = (MessageService.MessageServiceInterface) iBinder;
        messageService.addMessageClientListener(this);

        //Notify the user if they are not connected to the Sinch client.
        //Otherwise, for example, if your app key & secret are typed
        //in wrong, the user might keep hitting the send button
        //with no feedback
        if (!messageService.isSinchClientStarted()) {
            Toast.makeText(this, "The message client did not start."
                    ,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        messageService = null;
    }
}
