package in.koinnbit.listener;

import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import in.koinnbit.R;
import in.koinnbit.datastore.DataStore;
import in.koinnbit.model.ExchangeData;
import in.koinnbit.rest.KoinexRequestTask;

/**
 * Created by AJ on 1/10/2018.
 */

public class RefreshOnClickListener implements View.OnClickListener {

    private AppCompatActivity activity;

    public RefreshOnClickListener(AppCompatActivity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        try {
            new KoinexRequestTask(activity).execute();
            EditText currencyData = activity.findViewById(R.id.currencyData);
            StringBuffer data = new StringBuffer("Rs. <span style='font-size: 20px; font-weight: bolder'>");
            Spinner currency = activity.findViewById(R.id.currency);

            String selectedCurrency = currency.getSelectedItem().toString();

            ExchangeData exchangeData = DataStore.getInstance().getKoinexTicker();

            while (exchangeData == null) {
                Thread.sleep(10);
                exchangeData = DataStore.getInstance().getKoinexTicker();
            }

            String price = exchangeData.getPrices().get(selectedCurrency);
            data.append(price).append("</span> INR <br><br>");
            currencyData.setText(Html.fromHtml(data.toString()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
