package com.example.dreamy.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dreamy.Manager.PayController;
import com.example.dreamy.Model.OderDetal;
import com.example.dreamy.R;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PayActivity extends AppCompatActivity implements PropertyChangeListener {
    private OderDetal  oderDetal ;
  PayController payController=PayController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payController.addPropertyChangeListener(this);
        setContentView(R.layout.activity_pay);
        Bundle bundle=getIntent().getBundleExtra("data");
        oderDetal=(OderDetal) bundle.getSerializable("oderDetal");
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("Pay")){

        }
    }

    @Override
    protected void onDestroy() {
        payController.removePropertyChangeListener(this);
        super.onDestroy();
    }
}