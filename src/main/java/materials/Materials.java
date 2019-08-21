package materials;

import lombok.Data;

@Data
public class Materials {

    public Materials(String type, int pallets) {
        this.type = type;
        this.pallets = pallets;
    }

    private String type;
    private int pallets;

    void makeAnOrder(String type, int pallets) {
        MaterialsDataBase.getInstance().deleteFromBase(type, pallets);
    }


}
