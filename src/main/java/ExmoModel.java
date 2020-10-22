import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExmoModel {
    private String valute;
    private double ask_amount;
    private double ask_top;
    private double bid_quantity;
    private double bid_amount;
    private double bid_top;

    public double getAsk_amount() {
        return ask_amount;
    }

    public void setAsk_amount(double ask_amount) {
        this.ask_amount = ask_amount;
    }

    public double getAsk_top() {
        return ask_top;
    }

    public void setAsk_top(double ask_top) {
        this.ask_top = ask_top;
    }

    public double getBid_quantity() {
        return bid_quantity;
    }

    public void setBid_quantity(double bid_quantity) {
        this.bid_quantity = bid_quantity;
    }

    public double getBid_amount() {
        return bid_amount;
    }

    public void setBid_amount(double bid_amount) {
        this.bid_amount = bid_amount;
    }

    public double getBid_top() {
        return bid_top;
    }

    public void setBid_top(double bid_top) {
        this.bid_top = bid_top;
    }

    public String getValute() {
        return valute;
    }

    public void setValute(String valute) {
        this.valute = valute;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Valute - " + valute + "\t");
        sb.append("Ask_amount - " + ask_amount + "\t");
        sb.append("Ask_top - " + ask_top + "\t");
        sb.append("Bid_quantity - " + bid_quantity + "\t");
        sb.append("Bid_amount - " + bid_amount + "\t");
        sb.append("Bid_top - " + bid_top);
        return sb.toString();
    }
}

    class ExmoUSD {
    @JsonProperty("BTC_USD")
    private ExmoModel BTC_USD;

    public ExmoModel getBTC_USD() {
            return BTC_USD;
        }

        public void setBTC_USD(ExmoModel BTC_USD) {
            this.BTC_USD = BTC_USD;
        }
    }

    class ExmoEUR {
        @JsonProperty("BTC_EUR")
        private ExmoModel BTC_EUR;

        public ExmoModel getBTC_EUR() {
            return BTC_EUR;
        }

        public void setBTC_EUR(ExmoModel BTC_EUR) {
            this.BTC_EUR = BTC_EUR;
        }
    }

    class ExmoUAH {
        @JsonProperty("BTC_UAH")
        private ExmoModel BTC_UAH;

        public ExmoModel getBTC_UAH() {
            return BTC_UAH;
        }

        public void setBTC_UAH(ExmoModel BTC_UAH) {
            this.BTC_UAH = BTC_UAH;
        }
    }

