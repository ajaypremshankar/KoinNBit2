package in.koinnbit.rest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.koinnbit.datastore.DataStore;
import in.koinnbit.model.BitbnsDto;
import in.koinnbit.model.ExchangeData;

public class BitBnsRequestTask extends AsyncTask<Void, Void, ExchangeData> {

    private AppCompatActivity activity;

    public BitBnsRequestTask(AppCompatActivity activity){
        this.activity = activity;
    }

        @Override
        protected ExchangeData doInBackground(Void... params) {
            try {
                DataStore.getInstance().setBitBnsTicker(null);
                final String url = "https://bitbns.com/order/getTickerAll";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Map<String, BitbnsDto>[] result = restTemplate.getForObject(url, Map[].class);
                Date now = new Date();
                String key = "Bitbns";
                List<String> currencyNamesList  = new ArrayList<>();
                Map<String, String> prices = new HashMap<>();
                for (int i = 0; i< result.length; i++){
                    Map<String, BitbnsDto> record = result[i];
                    for(Map.Entry<String, BitbnsDto> entry: record.entrySet()){
                        currencyNamesList.add(entry.getKey());
                        prices.put(entry.getKey(), entry.getValue().getLastTradePrice().toString());
                    }
                }
                String [] currencyNames = currencyNamesList.toArray(new String[currencyNamesList.size()]);
                return new ExchangeData(key, currencyNames, prices, now);
            } catch (Exception e) {
                Log.e("ExchangeSpinListener", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(ExchangeData result) {
            DataStore.getInstance().setBitBnsTicker(result);
        }

    }