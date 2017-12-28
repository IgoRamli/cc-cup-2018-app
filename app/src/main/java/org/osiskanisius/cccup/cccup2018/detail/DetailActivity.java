package org.osiskanisius.cccup.cccup2018.detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.osiskanisius.cccup.cccup2018.R;
import org.osiskanisius.cccup.cccup2018.data.DataLomba;

public class DetailActivity extends AppCompatActivity implements DetailContract.View{
    private Toolbar mToolbar;
    private TextView mDescription;
    private String textTitle = "Unknown DataLomba";
    private String textDesc = "Unknown Detail";
    private Integer lombaID;
    private DataLomba data;

    private DetailContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        mDescription = (TextView) findViewById(R.id.tv_detail);

        mPresenter = new DetailPresenter(this);

        Intent fromMain = getIntent();
        mPresenter.setView(fromMain.getIntExtra("lombaID", 0));
    }

    @Override
    public void changeTitleView(String text) {
        mToolbar.setTitle(text);
    }

    @Override
    public void changeDescriptionView(String text) {
        mDescription.setText(text);
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}
