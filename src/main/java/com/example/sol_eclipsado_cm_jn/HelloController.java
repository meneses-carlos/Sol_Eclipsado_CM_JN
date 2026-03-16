package com.example.sol_eclipsado_cm_jn;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("BIENVENIDOS GRAN HIJUEPUTAS");
    }
}
