package in.koinnbit.listener;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import in.koinnbit.R;
import in.koinnbit.datastore.DataStore;
import in.koinnbit.model.ExchangeData;
import in.koinnbit.rest.KoinexRequestTask;

public class ExchangeSpinListener implements
        AdapterView.OnItemSelectedListener {

    private AppCompatActivity activity;

    public ExchangeSpinListener(AppCompatActivity activity){
        this.activity = activity;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        try{
        Spinner currency =  activity.findViewById(R.id.currency);
        EditText currencyData = activity.findViewById(R.id.currencyData);
        EditText dreamValueInput = activity.findViewById(R.id.dreamValue);
        ImageButton refreshButton = activity.findViewById(R.id.refreshButton);
        Button notifyButton = activity.findViewById(R.id.currencySubscribe);

        switch (DataStore.getInstance().getExchangeNames()[i]) {
            case "--Select Exchange--":
                currency.setVisibility(View.INVISIBLE);
                currencyData.setVisibility(View.INVISIBLE);
                dreamValueInput.setVisibility(View.INVISIBLE);
                refreshButton.setVisibility(View.INVISIBLE);
                notifyButton.setVisibility(View.INVISIBLE);
                break;
            case "Koinex":
                new KoinexRequestTask(activity).execute();

                currency.setVisibility(View.VISIBLE);
                ExchangeData exchangeData = DataStore.getInstance().getKoinexTicker();

                String[] currencyNames = {"--Select Exchange first--"};
                if (exchangeData != null) {
                    currencyNames = exchangeData.getCurrencyNames();
                }
                ArrayAdapter aa = new ArrayAdapter(activity, android.R.layout.simple_spinner_item, currencyNames);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                currency.setAdapter(aa);

                break;
            case "Bitbns":
                break;
            default:
                break;
        }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
