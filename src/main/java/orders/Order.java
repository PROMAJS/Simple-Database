package orders;

import lombok.Data;

@Data
public class Order {

    public Order(String numberOfOrder, String typeOfMaterial, int numberOfPallets) {
        this.numberOfOrder = numberOfOrder;
        this.typeOfMaterial = typeOfMaterial;
        this.numberOfPallets = numberOfPallets;
    }

    private String numberOfOrder;
    private String typeOfMaterial;
    private int numberOfPallets;

}
