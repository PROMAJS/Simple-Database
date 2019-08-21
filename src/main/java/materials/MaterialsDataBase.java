package materials;

import gui.Console;
import gui.JOptionMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MaterialsDataBase {
    private JOptionMethods jOptionMethods = new JOptionMethods();
    private Console console = new Console();
    private List<TypesOfMaterials> materialsSpecyfics = new ArrayList<>();
    private List<String> listOfMaterials = new ArrayList<>();

    private static MaterialsDataBase materialsDataBase = new MaterialsDataBase();

    public static final MaterialsDataBase getInstance() {
        return materialsDataBase;
    }

    private MaterialsDataBase() {
        listOfMaterials.add("Cegła");
        listOfMaterials.add("Kamień");
        listOfMaterials.add("Krzemień");
        listOfMaterials.add("Piasek");
        listOfMaterials.add("Glina");
        materialsSpecyfics.add(new TypesOfMaterials("Cegła", 40, 850));
        materialsSpecyfics.add(new TypesOfMaterials("Kamień", 50, 900));
        materialsSpecyfics.add(new TypesOfMaterials("Krzemień", 20, 600));
        materialsSpecyfics.add(new TypesOfMaterials("Piasek", 35, 500));
        materialsSpecyfics.add(new TypesOfMaterials("Glina", 15, 400));
    }

    public List<TypesOfMaterials> typesOfMaterials() {
        return materialsSpecyfics;
    }

    public String showStatusOfMaterials() {
        String output = "";
        for (int i = 0; i < materialsSpecyfics.size(); i++) {
            String everything = (materialsSpecyfics.get(i).getTypeOfMaterial() + " " + materialsSpecyfics.get(i).getCountInPallets() + " palet\n");
            output = output + everything;
        }
        return output;
    }

    public void addExistingMaterial(Materials materialType) {
        Optional<TypesOfMaterials> materials = MaterialsDataBase.getInstance().typesOfMaterials().stream()
                .filter(e -> e.getTypeOfMaterial().equals(materialType.getType()))
                .findAny();
        materials.get().setCountInPallets(materials.get().getCountInPallets() + materialType.getPallets());
        jOptionMethods.showInformation("Material dodany poprawnie");
        console.showMenu();

    }

    public void addNewMaterial(String type, int pallets, int priceForOnePallet) {
        materialsSpecyfics.add(new TypesOfMaterials(type, pallets, priceForOnePallet));
        listOfMaterials.add(type);
        jOptionMethods.showInformation("Nowy materiał dodany poprawnie");
    }

    public void deleteFromBase(String type, int pallets) {
        Optional<TypesOfMaterials> material = MaterialsDataBase.getInstance().typesOfMaterials().stream()
                .filter(e -> e.getTypeOfMaterial().equals(type))
                .findAny();
        material.get().setCountInPallets(material.get().getCountInPallets() - pallets);
    }

    public Object[] listMaterialstoObject() {
        List<String> list = listOfMaterials;
        return list.toArray();
    }
}

