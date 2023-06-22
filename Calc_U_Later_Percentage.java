package calc_u_later.calc_u_later;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calc_U_Later_Percentage extends Application {

    private TextField inputField;
    private double num1, num2;
    private String operator;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculatrice de pourcentage");

        // Création des éléments de l'interface utilisateur
        inputField = new TextField();
        inputField.setEditable(false);

        Button button0 = createNumberButton("0");
        Button button1 = createNumberButton("1");
        Button button2 = createNumberButton("2");
        Button button3 = createNumberButton("3");
        Button button4 = createNumberButton("4");
        Button button5 = createNumberButton("5");
        Button button6 = createNumberButton("6");
        Button button7 = createNumberButton("7");
        Button button8 = createNumberButton("8");
        Button button9 = createNumberButton("9");

        Button buttonAdd = createOperatorButton("+");
        Button buttonSubtract = createOperatorButton("-");
        Button buttonMultiply = createOperatorButton("*");
        Button buttonDivide = createOperatorButton("/");
        Button buttonPercentage = createPercentageButton();
        Button buttonEquals = createEqualsButton();
        Button buttonClear = createClearButton();

        // Création de la disposition de la calculatrice
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(inputField, 0, 0, 4, 1);

        gridPane.add(button7, 0, 1);
        gridPane.add(button8, 1, 1);
        gridPane.add(button9, 2, 1);
        gridPane.add(buttonDivide, 3, 1);

        gridPane.add(button4, 0, 2);
        gridPane.add(button5, 1, 2);
        gridPane.add(button6, 2, 2);
        gridPane.add(buttonMultiply, 3, 2);

        gridPane.add(button1, 0, 3);
        gridPane.add(button2, 1, 3);
        gridPane.add(button3, 2, 3);
        gridPane.add(buttonSubtract, 3, 3);

        gridPane.add(button0, 0, 4);
        gridPane.add(buttonClear, 1, 4);
        gridPane.add(buttonPercentage, 2, 4);
        gridPane.add(buttonAdd, 3, 4);
        gridPane.add(buttonEquals, 0, 5, 4, 1);

        // Création de la scène principale
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createNumberButton(String number) {
        Button button = new Button(number);
        button.setOnAction(e -> {
            String currentText = inputField.getText();
            inputField.setText(currentText + number);
        });
        return button;
    }

    private Button createOperatorButton(String operator) {
        Button button = new Button(operator);
        button.setOnAction(e -> {
            num1 = Double.parseDouble(inputField.getText());
            this.operator = operator;
            inputField.clear();
        });
        return button;
    }

    private Button createPercentageButton() {
        Button button = new Button("%");
        button.setOnAction(e -> {
            num1 = Double.parseDouble(inputField.getText());
            double result = num1 / 100.0;
            inputField.setText(String.valueOf(result));
        });
        return button;
    }

    private Button createEqualsButton() {
        Button button = new Button("=");
        button.setOnAction(e -> {
            num2 = Double.parseDouble(inputField.getText());
            double result = calculateResult();
            inputField.setText(String.valueOf(result));
        });
        return button;
    }

    private Button createClearButton() {
        Button button = new Button("C");
        button.setOnAction(e -> inputField.clear());
        return button;
    }

    private double calculateResult() {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }
}
