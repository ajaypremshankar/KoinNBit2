package in.koinnbit.listener;

import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import in.koinnbit.R;
import in.koinnbit.datastore.DataStore;
import in.koinnbit.model.ExchangeData;
import in.koinnbit.rest.KoinexRequestTask;

public class CurrencySpinListener implements
        AdapterView.OnItemSelectedListener {

    private AppCompatActivity activity;
    public CurrencySpinListener(AppCompatActivity activity){
        this.activity = activity;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        EditText currencyData = activity.findViewById(R.id.currencyData);
        EditText dreamValueInput = activity.findViewById(R.id.dreamValue);
        ImageButton refreshButton = activity.findViewById(R.id.refreshButton);
        Button notifyButton = activity.findViewById(R.id.currencySubscribe);

        ExchangeData exchangeData = DataStore.getInstance().getKoinexTicker();
        if(exchangeData != null) {

            currencyData.setVisibility(View.VISIBLE);
            dreamValueInput.setVisibility(View.VISIBLE);
            refreshButton.setVisibility(View.VISIBLE);
            notifyButton.setVisibility(View.VISIBLE);

            StringBuffer data = new StringBuffer("Rs. <span style='font-size: 20px; font-weight: bolder'>");
            Spinner currency = activity.findViewById(R.id.currency);

            String selectedCurrency = currency.getSelectedItem().toString();
            String price = exchangeData.getPrices().get(selectedCurrency);
            data.append(price).append("</span> INR <br><br>");
            currencyData.setText(Html.fromHtml(data.toString()));

            Double priceValue = Double.valueOf(price);
            dreamValueInput.setText(String.valueOf(priceValue*1.2));

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
