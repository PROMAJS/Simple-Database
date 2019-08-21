package services;

import clients.ClientsDataBase;
import gui.Console;
import gui.JOptionMethods;
import materials.Materials;
import materials.MaterialsDataBase;
import materials.TypesOfMaterials;
import orders.OrderDataBase;

import java.util.Optional;
import java.util.Scanner;

public class MaterialService {
    Scanner scanner = new Scanner(System.in);
    static Console console = new Console();
    private JOptionMethods jOptionMethods = new JOptionMethods();
    private static final int ADMIN_PASSWORD = 1001;
    private String type = "";
    private int pallets;
    private int password;
    private String numberOfOrder;

    public void isOrderAvailable(String type, int pallets, String firstName, String surName, String pesel) {
        Optional<TypesOfMaterials> material = checkAndGetOneMaterial(type);
        if (material.isPresent()) {
            if (material.get().getCountInPallets() > 0 && material.get().getCountInPallets() > pallets) {
                MaterialsDataBase.getInstance().deleteFromBase(type, pallets);
                numberOfOrder = OrderDataBase.getInstance().numberOfOrder();
                OrderDataBase.getInstance().addOrder(numberOfOrder, type, pallets);
                ClientsDataBase.getInstance().addClient(firstName, surName, pesel, firstName.charAt(0) + surName, numberOfOrder);
                jOptionMethods.showInformation("Zamówienie zostanie zrealizowane");

            } else if (material.get().getCountInPallets() == 0) {
                jOptionMethods.showInformation("Niestety nie mamy juz tego materialu");
            } else {
                jOptionMethods.showInformation("Nie mamy tyle palet na stanie, sprawdz stan i sprobuj ponownie");
            }

        } else {
            jOptionMethods.showInformation("Nie mamy takiego materialu na stanie, sprawdz stan i sprobuj ponownie");
        }

    }

    public void validateAndAddExistingTypeOfMaterial(String type) {
        if (checkAndGetOneMaterial(type).isPresent()) {
            pallets = jOptionMethods.askForNumber("Podaj liczbe palet");
            MaterialsDataBase.getInstance().addExistingMaterial(new Materials(type, pallets));
        } else {
            type = String.valueOf(jOptionMethods.askForString("Podales złe materiał, sprobuj ponownie lub wcisnij cancel aby wyjsc do menu"));
            if (type.equals("0")) {
                console.showMenu();
            } else {
                validateAndAddExistingTypeOfMaterial(type);
            }
        }
    }

    public void addNewTypeOfMaterial(String type) {
        pallets = jOptionMethods.askForNumber("Podaj liczbe palet do dodania na stan");
        int prizePerPallet = jOptionMethods.askForNumber("Podaj cene jednej palety");
        MaterialsDataBase.getInstance().addNewMaterial(type, pallets, prizePerPallet);
    }


    public Optional<TypesOfMaterials> checkAndGetOneMaterial(String type) {
        Optional<TypesOfMaterials> material = MaterialsDataBase.getInstance().typesOfMaterials().stream()
                .filter(e -> e.getTypeOfMaterial().equals(type))
                .findAny();

        return material;

    }

    public boolean validatePassword() throws IllegalArgumentException {
        password = jOptionMethods.askForPassword();
        if (password == ADMIN_PASSWORD) {
            return true;
        }
        return false;

    }

    public Object[] listAsArray() {
        return MaterialsDataBase.getInstance().listMaterialstoObject();
    }


}