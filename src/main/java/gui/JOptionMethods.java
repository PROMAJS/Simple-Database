package gui;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


public class JOptionMethods {

    private static final Collection<String> ALLOWED_CHARS = List.of(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "W", "Y", "Z", "Ż", "Ź",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "w", "y", "z", "ż");
    private JFrame frame = new JFrame();

    public String askForString(String message) {
        frame = new JFrame();
        String clientMessage = JOptionPane.showInputDialog(frame, message);
        if (clientMessage == null) {
            return "null";
        }
        clientMessage.trim().chars()
                .mapToObj(c -> String.valueOf((char) c))
                .forEach(c -> {
                    if (ALLOWED_CHARS.contains(c)) {
                    } else throw new IllegalArgumentException();
                });
        if (clientMessage.length() == 0) {
            showInformation("Wprowadz poprawna wartosc - pole nie moze byc puste, jezeli chcesz wyjsc kliknij cancel");
            return askForString(message);
        } else return clientMessage;
    }


    public int askForNumber(String message) {
        frame = new JFrame();
        try {
            String clientMessage = JOptionPane.showInputDialog(frame, message, "");
            if (clientMessage == null) {
                return 0;
            } else if (clientMessage.length() == 0) {
                showInformation("Wprowadz poprawna wartosc - pole nie moze byc puste, jezeli chcesz wyjac kliknij cancel");
                return askForNumber(message);
            } else if (Integer.parseInt(clientMessage) < 0) {
                return -1;
            } else return Integer.parseInt(clientMessage);
        } catch (NumberFormatException e) {
            showInformation("Wprowadzona liczba jest niepoprawna");

            return askForNumber("Podaj poprawna liczbe palet");
        }
    }


    public String askForPesel(String message) {
        frame = new JFrame();
        String clientMessage = JOptionPane.showInputDialog(frame, message);
        if (clientMessage == null) {
            return "null";
        } else if (clientMessage.length() == 0) {
            showInformation("Wprowadz poprawna wartosc - pole nie moze byc puste, jezeli chcesz wyjac kliknij cancel");
            return askForPesel(message);
        } else if (clientMessage.length() < 11) {
            return "Niepoprawny";
        } else return clientMessage;
    }


    public void showInformation(String message) {
        frame = new JFrame("JOption Pane shwMessageDialog");
        JOptionPane.showMessageDialog(frame, message);
    }

    public int askForPassword() {
        frame = new JFrame();
        String message = JOptionPane.showInputDialog(frame, "Podaj hasło dostępu");
        if (message == null) {
            return -1;
        } else if (message.length() == 0) {
            showInformation("Wprowadz poprawna wartosc - pole nie moze byc puste, jezeli chcesz wyjac kliknij cancel");
            return askForPassword();
        } else return Integer.parseInt(message);
    }


    public String choiceOneMaterial(Object[] list) {
        Object selectedValue = JOptionPane.showInputDialog(null, "Choose One Material",
                "Input", JOptionPane.INFORMATION_MESSAGE, null, list, list[0]);
        if (selectedValue == null) {
            return "null";
        } else return selectedValue.toString();
    }


}