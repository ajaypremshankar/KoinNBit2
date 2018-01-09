package in.koinnbit.rest;

import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import in.koinnbit.R;
import in.koinnbit.datastore.DataStore;
import in.koinnbit.model.ExchangeData;

/**
 * Created by AJ on 1/10/2018.
 */

public class RequestCompletionHandler {


    private AppCompatActivity activity;
    public RequestCompletionHandler(AppCompatActivity activity){
        this.activity = activity;
    }

    public void handle(){
        Spinner exchangeSpin =  activity.findViewById(R.id.exchange);
        Spinner currency =  activity.findViewById(R.id.currency);

        ExchangeData exchangeData = null;
        switch (exchangeSpin.getSelectedItem().toString()){
            case "Koinex":
                exchangeData = DataStore.getInstance().getKoinexTicker();
                break;
            case "Bitbns":
                exchangeData = DataStore.getInstance().getBitBnsTicker();
                break;
            default:
        }



        if (exchangeData != null) {
            String[] currencyNames = exchangeData.getCurrencyNames();
            ArrayAdapter aa = new ArrayAdapter(activity, android.R.layout.simple_spinner_item, currencyNames);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            currency.setAdapter(aa);
        }


    }

}
