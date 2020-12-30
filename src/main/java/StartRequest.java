import java.io.IOException;

public class StartRequest {
    public static void main(String[] args) throws IOException, InterruptedException {
        ParserJsonFile parserJsonFile = new ParserJsonFile();
        RequestHttp request = new RequestHttp();

        request.getRequest();
        Product product = parserJsonFile.bestSellingProduct();

        request.postRequest(product.item + "#" + product.total);
    }
}
