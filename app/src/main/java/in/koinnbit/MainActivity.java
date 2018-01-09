package in.koinnbit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import in.koinnbit.datastore.DataStore;
import in.koinnbit.listener.CurrencySpinListener;
import in.koinnbit.listener.ExchangeSpinListener;
import in.koinnbit.listener.RefreshOnClickListener;
import in.koinnbit.rest.KoinexRequestTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateExchageData();

        Spinner exchangeSpin =  findViewById(R.id.exchange);
        exchangeSpin.setOnItemSelectedListener(new ExchangeSpinListener(this));

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, DataStore.getInstance().getExchangeNames());
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        exchangeSpin.setAdapter(aa);

        ImageButton refreshButton = findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new RefreshOnClickListener(this));

        Spinner currency =  findViewById(R.id.currency);
        currency.setOnItemSelectedListener(new CurrencySpinListener(this));


    }

    private void initiateExchageData() {
        new KoinexRequestTask(this).execute();

    }


}
