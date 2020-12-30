import org.json.simple.JSONObject;

import java.math.BigDecimal;

public class Product {
    String item;
    long quantity;
    BigDecimal total;
    String day;

    public Product(JSONObject jsonObject) {
        this.item = jsonObject.get("item").toString();
        this.quantity = (long) jsonObject.get("quantidade");
        this.total = BigDecimal.valueOf((double) jsonObject.get("total"));
        this.day = jsonObject.get("dia").toString();
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDay() {
        return day;
    }

    public String getItem() {
        return item;
    }
}
