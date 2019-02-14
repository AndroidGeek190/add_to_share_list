package com.add_to_share_list;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ImageView imageFromSendIntent;
TextView textFromSendIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            } else if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        } else {
            // Handle other intents, such as being started from the home screen
        }
    }

    private void initViews() {
        imageFromSendIntent=findViewById(R.id.image_from_send_intent);
        textFromSendIntent=findViewById(R.id.text_from_send_intent);
    }

    /*method for handling text from another app*/
    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            Log.e("Intent--","Text--"+sharedText);
            textFromSendIntent.setText(sharedText);
        }
    }

    /*method for handling image from another app*/
    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            Log.e("Intent--","Image--"+imageUri);
            Glide.with(this) .load(imageUri).into(imageFromSendIntent);
        }
    }
}
