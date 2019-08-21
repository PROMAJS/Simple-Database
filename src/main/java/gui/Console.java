package gui;

import materials.MaterialsDataBase;
import orders.OrderDataBase;
import services.ClientService;
import services.MaterialService;
import services.OrderService;

import javax.swing.*;

public class Console implements ConsoleInferface {
    static Console console = new Console();
    ClientService clientService = new ClientService();
    MaterialService materialService = new MaterialService();
    OrderService orderService = new OrderService();
    JOptionMethods jOptionMethods = new JOptionMethods();
    private int pallets;
    private String type;
    private String response;
    private JFrame frame;
    private String firstName;
    private String surName;
    private String pesel;
    private String numberOfOrder;

    public static void main(String[] args) {
        console.showMenu();
    }


    @Override
    public void showMenu() {
        frame = new JFrame();
        String message = JOptionPane.showInputDialog(frame, "W celu wybrania opcji wpisz numer jej odpowiadajacy\n" +
                "1 - Stan produktow\n" +
                "2 - Złozenie zamówienia\n" +
                "3 - Zwrot produktów\n" +
                "4 - Rozliczenie zamówienia\n" +
                "5 - Informacje o klientach\n" +
                "6 - Usun zamowienie z bazy - dostęp szyfrowany\n" +
                "7 - Dodaj nowy materiał do bazy - dostęp szyfrowany\n" +
                "8 - Lista zamówień - dostep szyfrowany\n" +
                "9 - Dodaj istniejacy material do stanu - dostęp szyfrowany\n" +
                "0 - Wyjście z aplikacji", "Wybierz opcje", 3);
        if (message == null) {
            System.exit(0);
        }
        if (!message.equals("")) {
            int opcja = Integer.parseInt(message);
            enterAnOption(opcja);
        } else {
            System.exit(0);
        }
    }

    public void enterAnOption(int number) {
        switch (number) {
            case 1: {
                jOptionMethods.showInformation(MaterialsDataBase.getInstance().showStatusOfMaterials());
                showMenu();
                break;
            }
            case 2: {
                try {
                    firstName = clientService.getInformationAboutFirstName();
                    if (firstName.equals("null")) {
                        showMenu();
                    }
                    surName = clientService.getInformationAboutSurName();
                    if (surName.equals("null")) {
                        showMenu();
                    }
                    pesel = clientService.getInformationAboutPesel();
                    while (pesel.equals("Niepoprawny") || pesel.equals("null")) {
                        if (pesel.equals("null")) {
                            showMenu();
                        } else {
                            jOptionMethods.showInformation("Wprowadziles niepoprawny pesel sprobuj ponownie");
                            pesel = jOptionMethods.askForPesel("Wprowadz pesel");
                        }
                    }
                    type = jOptionMethods.choiceOneMaterial(materialService.listAsArray());
                    if (type.equals("null")) {
                        showMenu();
                    }
                    pallets = jOptionMethods.askForNumber("Podaj liczbe palet");
                    if (pallets == 0) {
                        showMenu();
                    }
                    while (pallets < 0) {
                        jOptionMethods.showInformation("Podana liczba palet nie moze byc ujemna!");
                        pallets = jOptionMethods.askForNumber("Podaj liczbe palet od 1 w góre");
                    }
                    materialService.isOrderAvailable(type, pallets, firstName, surName, pesel);
                    showMenu();
                } catch (IllegalArgumentException e) {
                    jOptionMethods.showInformation("Nieporawna wartosc. Prosze uzywac tylko liter bez polskich znakow");
                    enterAnOption(2);
                }
            }

            case 3: {
                materialService.validateAndAddExistingTypeOfMaterial(jOptionMethods.choiceOneMaterial(
                        (MaterialsDataBase.getInstance().listMaterialstoObject())));
                showMenu();
                break;
            }
            case 4: {
                numberOfOrder = orderService.getNumberOfOrder();
                orderService.getInformationAboutOrder(numberOfOrder);
                clientService.deleteClientOrder(numberOfOrder);
                showMenu();
                break;
            }
            case 5: {
                jOptionMethods.showInformation(clientService.showInformationAboutClient());
                showMenu();
                break;
            }
            case 6: {
                clientService.deleteClientOrder(jOptionMethods.askForString("Podaj numer zamówienia"));
                showMenu();
                break;
            }
            case 7: {
                if (materialService.validatePassword()) {
                    materialService.addNewTypeOfMaterial(jOptionMethods.askForString("Podaj nowy material"));
                    showMenu();
                } else {
                    response = jOptionMethods.askForString("Podales zle hasło, kliknij OK aby sprobowac ponownie lub Cancel aby wrocic do MENU");
                    if (response.equals("")) {
                        enterAnOption(7);
                    } else {
                        showMenu();
                    }
                }
            }
            case 8: {
                if (materialService.validatePassword()) {
                    jOptionMethods.showInformation("Zamowienia : \n" + OrderDataBase.getInstance().showOrdersStatus());
                } else {
                    jOptionMethods.showInformation("Podaleś złe hasło sprobuj ponownie");
                    enterAnOption(8);
                }
                showMenu();
                break;
            }
            case 0: {
                System.exit(0);
            }
            case 9: {
                if (materialService.validatePassword()) {
                    materialService.validateAndAddExistingTypeOfMaterial(jOptionMethods.askForString("Podaj rodzaj materiału"));
                    enterAnOption(number);
                } else {
                    response = jOptionMethods.askForString("Podales zle hasło, kliknij OK aby sprobowac ponownie lub Cancel aby wrocic do MENU");
                    if (response.equals("")) {
                        enterAnOption(9);
                    } else {
                        showMenu();
                    }
                }
            }
            default: {
                System.out.println("Wybrales niepoprawna opcje, sprobuj ponownie");
                showMenu();
            }
        }

    }

}




