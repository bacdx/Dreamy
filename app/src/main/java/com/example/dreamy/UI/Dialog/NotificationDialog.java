package com.example.dreamy.UI.Dialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.dreamy.R;


public class NotificationDialog extends AlertDialog {
    private TextView tvTitle,tvContent;
    private ImageView imageView;
    private String title,content;
    private Drawable icon;

    public NotificationDialog(@NonNull Context context, @NonNull String title,@NonNull String content, Drawable icon) {
        super(context);
        this.title = title;
        this.content = content;
        this.icon = icon;
    }

    public NotificationDialog(@NonNull Context context,@NonNull String title, @NonNull String content) {
        super(context);
        this.title = title;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        tvContent=findViewById(R.id.tv_content_dialogal);
        tvTitle=findViewById(R.id.tv_tilte_dialogal);
        imageView=findViewById(R.id.imv_img_dialogal);

        tvTitle.setText(title);
        tvContent.setText(content);
        if (icon!=null){

        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    dismiss();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}
