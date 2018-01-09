package in.koinnbit.rest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import in.koinnbit.R;
import in.koinnbit.datastore.DataStore;
import in.koinnbit.model.ExchangeData;

public class KoinexRequestTask extends AsyncTask<Void, Void, ExchangeData> {

    private AppCompatActivity activity;

    public KoinexRequestTask(AppCompatActivity activity){
        this.activity = activity;
    }

        @Override
        protected ExchangeData doInBackground(Void... params) {
            try {
                DataStore.getInstance().setKoinexTicker(null);
                final String url = "https://koinex.in/api/ticker";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Map<String, Map<String, String>> result = restTemplate.getForObject(url, Map.class);
                Date now = new Date();
                String key = "Koinex";
                String[] currencyNames = result.get("prices").keySet().toArray(new String[result.get("prices").keySet().size()]);
                Map<String, String> prices = result.get("prices");
                return new ExchangeData(key, currencyNames, prices, now);
            } catch (Exception e) {
                Log.e("ExchangeSpinListener", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(ExchangeData result) {
            DataStore.getInstance().setKoinexTicker(result);
        }

    }