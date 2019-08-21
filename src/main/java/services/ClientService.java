package services;

import clients.ClientsDataBase;
import gui.JOptionMethods;

public class ClientService {

    JOptionMethods jOptionMethods = new JOptionMethods();

    public String showInformationAboutClient() {

        return ClientsDataBase.getInstance().showClientsList();
    }

    public String getInformationAboutFirstName() {
        return jOptionMethods.askForString("Podaj swoje imie");

    }

    public String getInformationAboutSurName() {
        return jOptionMethods.askForString("Podaj swoje naziwsko");
    }

    public String getInformationAboutPesel() {
        String pesel = jOptionMethods.askForPesel("Podaj swoj pesel");
        if (pesel.equals("null")) {
            return "null";
        } else if (pesel.length() != 11) {
            jOptionMethods.showInformation("Podales niepoprawny pesel, sprobuj ponownie");
            return getInformationAboutPesel();
        } else return pesel;
    }

    public void deleteClientOrder(String numberOforder) {
        ClientsDataBase.getInstance().deletedClientFromDataBase(numberOforder);
        jOptionMethods.showInformation("Zamowienie usuniete z bazy danych");
    }
}
