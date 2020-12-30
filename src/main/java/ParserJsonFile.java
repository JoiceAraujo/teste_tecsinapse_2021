import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParserJsonFile {
    List<Product> products = new ArrayList<>();
    List<Product> decemberProducts = new ArrayList<>();

    public void readJson() {
        final String jsonPath = "src/main/resources/recrutamento.json";
        final JSONParser jsonParser = new JSONParser();

        try {
            final JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(jsonPath));

            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;

                products.add(new Product(jsonObject));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterAndAgroupDecemberProducts() {
        final Map<String, Product> decemberProducts = new HashMap<>();

        products.stream().filter(product -> product.getDay().contains("/12/2018")).forEach(product -> {
            if (decemberProducts.containsKey(product.item)) {
                Product decemberProduct = decemberProducts.get(product.item);
                decemberProduct.setTotal(decemberProduct.getTotal().add(product.getTotal()));
                decemberProduct.setQuantity(decemberProduct.getQuantity() + product.getQuantity());
            } else {
                decemberProducts.put(product.item, product);
            }
        });

        decemberProducts.forEach((key, product) -> this.decemberProducts.add(product));
    }

    public Product bestSellingProduct() {
        readJson();
        filterAndAgroupDecemberProducts();

        return decemberProducts.stream().reduce((productOne, productTwo) -> {
            if (productOne.getQuantity() > productTwo.getQuantity()) {
                return productOne;
            } else if (productTwo.getQuantity() > productOne.getQuantity()) {
                return productTwo;
            } else {
                int compare = productOne.getItem().compareToIgnoreCase(productTwo.getItem());
                if (compare < 0) {
                    return productTwo;
                }
                return productOne;
            }
        }).get();
    }
}
