package clients;

import materials.MaterialsDataBase;
import orders.Order;
import orders.OrderDataBase;

import java.util.*;

public class ClientsDataBase {

    List<Clients> clients = new ArrayList<>();
    private static ClientsDataBase clientsDataBase = new ClientsDataBase();

    public static final ClientsDataBase getInstance() {
        return clientsDataBase;
    }

    public List<Clients> clientsBase() {
        return clients;
    }


    public void addClient(String name, String surname, String pesel, String login, String numberOfOrder) {

        clients.add(new Clients(name, surname, pesel, login, numberOfOrder));

    }

    public void deletedClientFromDataBase(String numberOfOrder) {

        Optional<Clients> clientWithNumberOfOrder = ClientsDataBase.getInstance().clientsBase().stream()
                .filter(e -> e.getNumberOfOrder().equals(numberOfOrder))
                .findAny();

        Clients client = new Clients(clientWithNumberOfOrder.get().getFirstName(), clientWithNumberOfOrder.get().getSurName(),
                clientWithNumberOfOrder.get().getPesel(), clientWithNumberOfOrder.get().getClientLogin(),
                clientWithNumberOfOrder.get().getNumberOfOrder());

        clientsBase().remove(client);
    }

    public List<Clients> sortList() {
        clients.sort((o1, o2) -> o1.getSurName().charAt(0) - o2.getSurName().charAt(0));
        return clients;
    }

    public String showClientsList() {
        String output = "Lista klientów\n" +
                "Imie     Nazwisko    Numer zamowienia\n";
        sortList();
        for (int i = 0; i < clientsBase().size(); i++) {
            String everything = (clientsBase().get(i).getSurName() + " " + clientsBase().get(i).getFirstName() + " " + clientsBase().get(i).getNumberOfOrder() + "\n");
            output = output + everything;
        }
        return output;
    }

}




