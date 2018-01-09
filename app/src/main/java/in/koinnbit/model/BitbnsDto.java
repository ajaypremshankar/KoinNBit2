package in.koinnbit.model;

/**
 * Created by AJ on 1/10/2018.
 */

public class BitbnsDto {
    private Double sellPrice;
    private Double buyPrice;
    private Double lastTradePrice;

    public Double getSellPrice() {
        return sellPrice;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getLastTradePrice() {
        return lastTradePrice;
    }

    public void setLastTradePrice(Double lastTradePrice) {
        this.lastTradePrice = lastTradePrice;
    }
}
