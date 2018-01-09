package in.koinnbit.datastore;

import java.time.LocalDateTime;
import java.util.Map;

import in.koinnbit.model.ExchangeData;

/**
 * Created by AJ on 1/10/2018.
 */

public class DataStore {
    private static final DataStore ourInstance = new DataStore();


    public static DataStore getInstance() {
        return ourInstance;
    }

    private String[] exchangeNames = { "--Select Exchange--", "Koinex", "Bitbns"};
    private ExchangeData koinexTicker;
    private ExchangeData bitBnsTicker;

    private DataStore() {
    }


    public ExchangeData getKoinexTicker() {
        return koinexTicker;
    }

    public void setKoinexTicker(ExchangeData koinexTicker) {
            this.koinexTicker = koinexTicker;
    }

    public String[] getExchangeNames() {
        return exchangeNames;
    }

    public ExchangeData getBitBnsTicker() {
        return bitBnsTicker;
    }

    public void setBitBnsTicker(ExchangeData bitBnsTicker) {
        this.bitBnsTicker = bitBnsTicker;
    }
}
