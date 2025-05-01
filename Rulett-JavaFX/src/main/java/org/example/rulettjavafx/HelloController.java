package org.example.rulettjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label welcomeText;


    public void initialize() {
        welcomeText.setText("Köszöntjük a Roulett birodalomban!");
    }


    @FXML
    private void onRegisterButtonClick() {
        System.out.println("Regisztráció...");
        // Ide jöhet a regisztrációs logika, pl. egy új ablak nyitása
    }


    @FXML
    private void onLoginButtonClick() {
        System.out.println("Bejelentkezés...");
        // Ide jöhet a bejelentkezési logika, pl. egy új ablak nyitása
    }
}