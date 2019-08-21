package materials;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TypesOfMaterials {


    public TypesOfMaterials(String typeOfMaterial, int countInPallets, double priceForOnePallets) {
        this.typeOfMaterial = typeOfMaterial;
        this.countInPallets = countInPallets;
        this.priceForOnePallets = priceForOnePallets;

    }

    private String typeOfMaterial;
    private int countInPallets;
    private double priceForOnePallets;


}
