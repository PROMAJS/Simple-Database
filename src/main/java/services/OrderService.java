package services;

import gui.JOptionMethods;
import materials.TypesOfMaterials;
import orders.OrderDataBase;
import orders.Order;

import javax.swing.*;
import java.util.Optional;

public class OrderService {
    private MaterialService materialService = new MaterialService();
    private JOptionMethods jOptionMethods = new JOptionMethods();
    private int numberOfOrder;
    double orderCost;

    public double countingMethodCostForOrder(String type, int pallets) {
        Optional<TypesOfMaterials> material = materialService.checkAndGetOneMaterial(type);
        orderCost = pallets * material.get().getPriceForOnePallets();
        return orderCost;
    }

    public void getInformationAboutOrder(String numberOfOrder) {
        if (numberOfOrder.equals("Brak")) {
            jOptionMethods.showInformation("Brak takiego numeru zamowienia w systemie !!!\n" +
                    "Sprobuj ponownie !");
            getNumberOfOrder();
        } else {
            Optional<Order> orders = OrderDataBase.getInstance().orders().stream()
                    .filter(e -> e.getNumberOfOrder().equals(numberOfOrder))
                    .findAny();
            jOptionMethods.showInformation("Koszt zamówienia wyniósł : " + countingMethodCostForOrder(orders.get().getTypeOfMaterial(),
                    orders.get().getNumberOfPallets()) + " zł");
        }
    }


    public String getNumberOfOrder() {
        JFrame frame = new JFrame();
        String message = JOptionPane.showInputDialog(frame, "Podaj numer zamówienia");
        numberOfOrder = Integer.parseInt(message);
        if (validateExistingOrder(numberOfOrder)) {
            return Integer.toString(numberOfOrder);
        } else {
            return "Brak";

        }
    }

    public boolean validateExistingOrder(int numberOfOrder) {
        String orderAsString = Integer.toString(numberOfOrder);
        Optional<Order> ordernumber = OrderDataBase.getInstance().orders().stream()
                .filter(e -> e.getNumberOfOrder().equals(orderAsString))
                .findAny();
        if (ordernumber.isPresent()) {
            return true;
        } else {
            return false;
        }
    }


}