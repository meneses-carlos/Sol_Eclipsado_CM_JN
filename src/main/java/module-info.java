module com.example.sol_eclipsado_cm_jn {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sol_eclipsado_cm_jn to javafx.fxml;
    exports com.example.sol_eclipsado_cm_jn;
}