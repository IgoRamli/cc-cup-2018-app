package org.osiskanisius.cccup.cccup2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView mDescription;
    private String textTitle = "Unknown Lomba";
    private String textDesc = "Unknown Detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //BEGIN get data from Intent
        Intent fromMain = getIntent();
        if(fromMain.hasExtra(Intent.EXTRA_TITLE)){
            textTitle = fromMain.getStringExtra(Intent.EXTRA_TITLE);
        }
        if(fromMain.hasExtra(Intent.EXTRA_TEXT)){
            textDesc = fromMain.getStringExtra(Intent.EXTRA_TEXT);
        }
        //END get data from Intent

        mToolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        mDescription = (TextView) findViewById(R.id.tv_detail);
        mToolbar.setTitle(textTitle);
        mDescription.setText(textDesc);
    }
}
