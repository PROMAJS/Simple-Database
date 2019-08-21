package orders;

import java.util.ArrayList;
import java.util.List;

public class OrderDataBase {


    private List<Order> orders = new ArrayList<>();
    private static OrderDataBase orderList = new OrderDataBase();

    public static final OrderDataBase getInstance() {
        return orderList;
    }

    public List<Order> orders() {
        return orders;
    }

    public void addOrder(String number, String type, int numberofPallets) {
        orders.add(new Order(number, type, numberofPallets));
    }

    public String numberOfOrder() {
        return Integer.toString(orders.size());
    }

    public String showOrdersStatus() {
        String output = "";
        for (int i = 0; i < orders.size(); i++) {
            String everything = (orders.get(i).getNumberOfOrder() + " " + orders.get(i).getTypeOfMaterial() + " palet" + orders.get(i).getNumberOfPallets() + "\n");
            output = output + everything;
        }
        return output;
    }
}
