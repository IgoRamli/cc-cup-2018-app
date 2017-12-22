package org.osiskanisius.cccup.cccup2018.internet;

import android.os.AsyncTask;

import org.osiskanisius.cccup.cccup2018.JadwalJsonParser;
import org.osiskanisius.cccup.cccup2018.jadwal.JadwalContract;

import java.net.URL;

public class FetchDataTask extends AsyncTask<URL, Void, String[]> {
    private JadwalContract.Presenter mPresenter;

    public FetchDataTask(JadwalContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onPreExecute() {
        mPresenter.onPreExecute();
    }

    @Override
    public String[] doInBackground(URL... url) {
        URL request = url[0];
        try {
            String result;
            result = NetworkUtil.getResponse(request);
            return JadwalJsonParser.parseSimpleJadwal(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onPostExecute(String[] hasilAkhir) {
        mPresenter.onPostExecute(hasilAkhir);
    }
}