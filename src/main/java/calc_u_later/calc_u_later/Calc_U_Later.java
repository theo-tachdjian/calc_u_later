package calc_u_later.calc_u_later;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calc_U_Later extends Application {

    private final Font globalFont = new Font("Arial", 24);
    private final Font memoryFont = new Font("Arial", 20);

    private TextField inputField;
    private double num1, num2;
    private String operator;
    private boolean isResult = false;
    private double memoryValue;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calc_U_Later");
        primaryStage.setResizable(false);

        // Création des éléments de l'interface utilisateur

        // champs pour afficher le calcul
        inputField = new TextField();
        inputField.setFont(globalFont);
        inputField.setEditable(false);
        inputField.setDisable(true);
        // met l'opacité au maximum car la fonction setDisable() réduit par défaut l'opacité
        inputField.setOpacity(1);

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

        Button buttonComma = createCommaButton();

        Button buttonAdd = createOperatorButton("+", 64, 128);
        Button buttonSubtract = createOperatorButton("-");
        Button buttonMultiply = createOperatorButton("*");
        Button buttonDivide = createOperatorButton("/");
        Button buttonPercentage = createPercentageButton();
        Button buttonEquals = createEqualsButton();
        Button buttonClear = createClearButton();

        // boutons pour la gestion de la mémoire
        Button buttonMemClear = createMemoryRelatedButton("MC", e -> this.memoryValue = 0.0);
        Button buttonMemRecall = createMemoryRelatedButton("MR", e -> {
            this.inputField.setText(Double.toString(this.memoryValue));
            this.isResult = true;
        });
        Button buttonMemAdd = createMemoryRelatedButton("M+", e -> {
            if (!inputField.getText().isEmpty()) {
                this.memoryValue += Double.parseDouble(inputField.getText());
            }
        });
        Button buttonMemSub = createMemoryRelatedButton("M-", e -> {
            if (!inputField.getText().isEmpty()) {
                this.memoryValue -= Double.parseDouble(inputField.getText());
            }
        });
        Button buttonMemSave = createMemoryRelatedButton("MS", e -> {
            if (!inputField.getText().isEmpty()) {
                this.memoryValue = Double.parseDouble(inputField.getText());
            }
        });

        // Création de la disposition de la calculatrice
        GridPane mainPane = new GridPane();     // panel principal

        GridPane buttonsPane = new GridPane();      // panel des boutons
        GridPane memoryPane = new GridPane();       // panel des boutons de gestion de mémoire

        mainPane.setPadding(new Insets(10));
        mainPane.setHgap(5);
        mainPane.setVgap(5);

        mainPane.add(inputField, 0, 0);
        mainPane.add(memoryPane, 0, 1);
        mainPane.add(buttonsPane, 0, 2);

        buttonsPane.add(button7, 0, 0);
        buttonsPane.add(button8, 1, 0);
        buttonsPane.add(button9, 2, 0);

        buttonsPane.add(button4, 0, 1);
        buttonsPane.add(button5, 1, 1);
        buttonsPane.add(button6, 2, 1);

        buttonsPane.add(button1, 0, 2);
        buttonsPane.add(button2, 1, 2);
        buttonsPane.add(button3, 2, 2);

        buttonsPane.add(button0, 0, 4);
        buttonsPane.add(buttonClear, 1, 4);
        buttonsPane.add(buttonComma, 2, 4);
        buttonsPane.add(buttonEquals, 3, 4, 2, 1);

        buttonsPane.add(buttonDivide, 4, 0);
        buttonsPane.add(buttonMultiply, 3, 0);
        buttonsPane.add(buttonSubtract, 3, 1);
        buttonsPane.add(buttonAdd, 4, 1, 1, 2);
        buttonsPane.add(buttonPercentage, 3, 2);

        memoryPane.add(buttonMemClear, 0, 0);
        memoryPane.add(buttonMemRecall, 1, 0);
        memoryPane.add(buttonMemAdd, 2, 0);
        memoryPane.add(buttonMemSub, 3, 0);
        memoryPane.add(buttonMemSave, 4, 0);

        // Création de la scène principale
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createNumberButton(String number) {
        Button button = createGenericButton(number);
        button.setOnAction(e -> {
            if (this.isResult) {
                inputField.setText(number);
                this.isResult = false;
            } else {
                String currentText = inputField.getText();
                inputField.setText(currentText + number);
            }
        });
        return button;
    }

    private Button createOperatorButton(String operator) {
        return createOperatorButton(operator, 64, 64);
    }

    private Button createOperatorButton(String operator, int width, int height) {
        Button button = createGenericButton(operator, width, height);
        button.setFont(globalFont);
        button.setOnAction(e -> {
            if (!inputField.getText().isEmpty()) {
                if (!isResult) {
                    if (this.operator != null && !this.operator.isEmpty()) {
                        switch (this.operator) {
                            case "+" -> num1 = num1 + Double.parseDouble(inputField.getText());
                            case "-" -> num1 = num1 - Double.parseDouble(inputField.getText());
                            case "*" -> num1 = num1 * Double.parseDouble(inputField.getText());
                            case "/" -> num1 = num1 / Double.parseDouble(inputField.getText());
                            default -> num1 = Double.parseDouble(inputField.getText());
                        }
                    } else {
                        num1 = Double.parseDouble(inputField.getText());
                    }
                } else {
                    num1 = Double.parseDouble(inputField.getText());
                }

                isResult = false;
                this.operator = operator;
                inputField.clear();
                inputField.setPromptText(num1+this.operator);
            }
        });
        return button;
    }

    private Button createPercentageButton() {
        Button button = createGenericButton("%");
        button.setOnAction(e -> {
            if (!inputField.getText().isEmpty()) {
                num1 = Double.parseDouble(inputField.getText()) + num1;
                num1 /= 100.0;

                isResult = false;
                this.operator = "*";
                inputField.clear();
                inputField.setPromptText(num1+this.operator);
            }
        });
        return button;
    }

    private Button createCommaButton() {
        Button button = createGenericButton(".");
        button.setOnAction(e -> {
            if (this.isResult || inputField.getText().isEmpty()) {
                inputField.setText("0.");
                this.isResult = false;
            } else if (!inputField.getText().isEmpty() && !inputField.getText().contains(".")) {
                inputField.setText(inputField.getText()+".");
            }
        });
        return button;
    }

    private Button createEqualsButton() {
        Button button = createGenericButton("=", 128, 64);
        button.setOnAction(e -> {
            if (!inputField.getText().isEmpty()) {
                num2 = Double.parseDouble(inputField.getText());
                double result = calculateResult();
                inputField.setText(String.valueOf(result));
                isResult = true;
                inputField.setPromptText("");
            }
        });
        return button;
    }

    private Button createClearButton() {
        Button button = createGenericButton("C");
        button.setOnAction(e -> {
            inputField.setPromptText("");
            inputField.clear();
            num1 = 0;
            num2 = 0;
            operator = "";
            isResult = false;
        });
        return button;
    }

    private Button createMemoryRelatedButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> e) {
        Button button = createGenericButton(text, 64,48);
        button.setFont(memoryFont);
        button.setOnAction(e);
        return button;
    }

    private Button createGenericButton(String text) { return createGenericButton(text, 64, 64); }

    private Button createGenericButton(String text, int width, int height) {
        Button button = new Button(text);
        button.setFont(globalFont);
        button.setMinSize(width, height);
        button.setMaxSize(width, height);
        return button;
    }

    private double calculateResult() {
        if (operator != null && !operator.isEmpty()) {
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
        } else if (num2 != 0) {
            return num2;
        }

        return 0;
    }

    public static void main(String[] args) {
        launch();
    }
}