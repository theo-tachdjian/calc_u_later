module calc_u_later.calc_u_later {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens calc_u_later.calc_u_later to javafx.fxml;
    exports calc_u_later.calc_u_later;
}