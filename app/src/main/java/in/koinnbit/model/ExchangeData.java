package in.koinnbit.model;

import java.util.Date;
import java.util.Map;

/**
 * Created by AJ on 1/10/2018.
 */

public class ExchangeData {
    private String key;
    private String[] currencyNames;
    private Map<String, String> prices;
    private Date updatedTime;

    public ExchangeData(String key, String[] currencyNames, Map<String, String> prices, Date updatedTime) {
        this.key = key;
        this.currencyNames = currencyNames;
        this.prices = prices;
        this.updatedTime = updatedTime;
    }

    public String getKey() {
        return key;
    }

    public String[] getCurrencyNames() {
        return currencyNames;
    }

    public Map<String, String> getPrices() {
        return prices;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }
}
