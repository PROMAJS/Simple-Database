package clients;

import lombok.Data;

@Data
public class Clients {

    public Clients(String firstName, String surName, String pesel, String clientLogin, String numberOfOrder) {
        this.firstName = firstName;
        this.surName = surName;
        this.pesel = pesel;
        this.clientLogin = clientLogin;
        this.numberOfOrder = numberOfOrder;
    }

    private String firstName;
    private String surName;
    private String pesel;
    private String clientLogin;
    private String numberOfOrder;

}

