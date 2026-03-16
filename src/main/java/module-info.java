module com.example.sol_eclipsado_cm_jn
{
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.sol_eclipsado_cm_jn to javafx.fxml;
    opens com.example.sol_eclipsado_cm_jn.controller to javafx.fxml;

    exports com.example.sol_eclipsado_cm_jn;
    exports com.example.sol_eclipsado_cm_jn.model;
    exports com.example.sol_eclipsado_cm_jn.view;
    exports com.example.sol_eclipsado_cm_jn.controller;
}