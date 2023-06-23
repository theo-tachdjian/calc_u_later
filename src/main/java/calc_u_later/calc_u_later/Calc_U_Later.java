import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Convert extends Application {

    private TextField displayField;
    private double operand1;
    private String operator;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculatrice de base");

        // Création des éléments de l'interface utilisateur
        displayField = new TextField();
        displayField.setEditable(false);

        Button button0 = createNumericButton("0");
        Button button1 = createNumericButton("1");
        Button button2 = createNumericButton("2");
        Button button3 = createNumericButton("3");
        Button button4 = createNumericButton("4");
        Button button5 = createNumericButton("5");
        Button button6 = createNumericButton("6");
        Button button7 = createNumericButton("7");
        Button button8 = createNumericButton("8");
        Button button9 = createNumericButton("9");

        Button buttonDecimal = createNumericButton(".");
        Button buttonEquals = createOperatorButton("=");
        Button buttonAdd = createOperatorButton("+");
        Button buttonSubtract = createOperatorButton("-");
        Button buttonMultiply = createOperatorButton("*");
        Button buttonDivide = createOperatorButton("/");

        Button buttonCelsiusToFahrenheit = createConversionButton("Celsius en Fahrenheit");
        Button buttonFahrenheitToCelsius = createConversionButton("Fahrenheit en Celsius");
        Button buttonMeterToFeet = createConversionButton("Meter en Feet");
        Button buttonFeetToMeter = createConversionButton("Feet en Meter");
        Button buttonKilogramToPound = createConversionButton("Kilogram en Pound");
        Button buttonPoundToKilogram = createConversionButton("Pound en Kilogram");
        Button buttonLiterToGallon = createConversionButton("Liter en Gallon");
        Button buttonGallonToLiter = createConversionButton("Gallon en Liter");

        // Création de la disposition de la calculatrice
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(displayField, 0, 0, 4, 1);

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
        gridPane.add(buttonDecimal, 1, 4);
        gridPane.add(buttonEquals, 2, 4);
        gridPane.add(buttonAdd, 3, 4);

        gridPane.add(buttonCelsiusToFahrenheit, 0, 5);
        gridPane.add(buttonFahrenheitToCelsius, 1, 5);
        gridPane.add(buttonMeterToFeet, 2, 5);
        gridPane.add(buttonFeetToMeter, 3, 5);
        gridPane.add(buttonKilogramToPound, 0, 6);
        gridPane.add(buttonPoundToKilogram, 1, 6);
        gridPane.add(buttonLiterToGallon, 2, 6);
        gridPane.add(buttonGallonToLiter, 3, 6);

        // Gestion des événements des boutons numériques
        button0.setOnAction(e -> appendToDisplay("0"));
        button1.setOnAction(e -> appendToDisplay("1"));
        button2.setOnAction(e -> appendToDisplay("2"));
        button3.setOnAction(e -> appendToDisplay("3"));
        button4.setOnAction(e -> appendToDisplay("4"));
        button5.setOnAction(e -> appendToDisplay("5"));
        button6.setOnAction(e -> appendToDisplay("6"));
        button7.setOnAction(e -> appendToDisplay("7"));
        button8.setOnAction(e -> appendToDisplay("8"));
        button9.setOnAction(e -> appendToDisplay("9"));
        buttonDecimal.setOnAction(e -> appendToDisplay("."));

        // Gestion des événements des boutons d'opération
        buttonEquals.setOnAction(e -> performOperation());
        buttonAdd.setOnAction(e -> setOperator("+"));
        buttonSubtract.setOnAction(e -> setOperator("-"));
        buttonMultiply.setOnAction(e -> setOperator("*"));
        buttonDivide.setOnAction(e -> setOperator("/"));

        // Gestion des événements des boutons de conversion
        buttonCelsiusToFahrenheit.setOnAction(e -> {
            String result = celsiusToFahrenheit(displayField.getText());
            displayField.setText(result);
        });
        buttonFahrenheitToCelsius.setOnAction(e -> {
            String result = fahrenheitToCelsius(displayField.getText());
            displayField.setText(result);
        });
        buttonMeterToFeet.setOnAction(e -> {
            String result = meterToFeet(displayField.getText());
            displayField.setText(result);
        });
        buttonFeetToMeter.setOnAction(e -> {
            String result = feetToMeter(displayField.getText());
            displayField.setText(result);
        });
        buttonKilogramToPound.setOnAction(e -> {
            String result = kilogramToPound(displayField.getText());
            displayField.setText(result);
        });
        buttonPoundToKilogram.setOnAction(e -> {
            String result = poundToKilogram(displayField.getText());
            displayField.setText(result);
        });
        buttonLiterToGallon.setOnAction(e -> {
            String result = literToGallon(displayField.getText());
            displayField.setText(result);
        });
        buttonGallonToLiter.setOnAction(e -> {
            String result = gallonToLiter(displayField.getText());
            displayField.setText(result);
        });

        // Création de la scène principale
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createNumericButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(50);
        return button;
    }

    private Button createOperatorButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(50);
        return button;
    }

    private Button createConversionButton(String type) {
        Button button = new Button(type);
        button.setPrefWidth(120);
        return button;
    }

    private void appendToDisplay(String value) {
        displayField.appendText(value);
    }

    private void setOperator(String operator) {
        this.operator = operator;
        operand1 = Double.parseDouble(displayField.getText());
        displayField.clear();
    }

    private void performOperation() {
        if (!displayField.getText().isEmpty() && operator != null) {
            double operand2 = Double.parseDouble(displayField.getText());
            double result = 0.0;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    result = operand1 / operand2;
                    break;
                default:
                    break;
            }

            displayField.setText(String.valueOf(result));
            operator = null;
        }
    }

    private String celsiusToFahrenheit(String celsius) {
        try {
            double tempCelsius = Double.parseDouble(celsius);
            double tempFahrenheit = (tempCelsius * 9 / 5) + 32;
            return String.valueOf(tempFahrenheit);
        } catch (NumberFormatException e) {
            return "Invalid input";
        }
    }

    private String fahrenheitToCelsius(String fahrenheit) {
        try {
            double tempFahrenheit = Double.parseDouble(fahrenheit);
            double tempCelsius = (tempFahrenheit - 32) * 5 / 9;
            return String.valueOf(tempCelsius);
        } catch (NumberFormatException e) {
            return "Invalid input";
        }
    }

    private String meterToFeet(String meter) {
        try {
            double lengthMeter = Double.parseDouble(meter);
            double lengthFeet = lengthMeter * 3.28084;
            return String.valueOf(lengthFeet);
        } catch (NumberFormatException e) {
            return "Invalid input";
        }
    }

    private String feetToMeter(String feet) {
        try {
            double lengthFeet = Double.parseDouble(feet);
            double lengthMeter = lengthFeet / 3.28084;
            return String.valueOf(lengthMeter);
        } catch (NumberFormatException e) {
            return "Invalid input";
        }
    }

    private String kilogramToPound(String kilogram) {
        try {
            double weightKilogram = Double.parseDouble(kilogram);
            double weightPound = weightKilogram * 2.20462;
            return String.valueOf(weightPound);
        } catch (NumberFormatException e) {
            return "Invalid input";
        }
    }

    private String poundToKilogram(String pound) {
        try {
            double weightPound = Double.parseDouble(pound);
            double weightKilogram = weightPound / 2.20462;
            return String.valueOf(weightKilogram);
        } catch (NumberFormatException e) {
            return "Invalid input";
        }
    }

    private String literToGallon(String liter) {
        try {
            double volumeLiter = Double.parseDouble(liter);
            double volumeGallon = volumeLiter * 0.264172;
            return String.valueOf(volumeGallon);
        } catch (NumberFormatException e) {
            return "Invalid input";
        }
    }

    private String gallonToLiter(String gallon) {
        try {
            double volumeGallon = Double.parseDouble(gallon);
            double volumeLiter = volumeGallon / 0.264172;
            return String.valueOf(volumeLiter);
        } catch (NumberFormatException e) {
            return "Invalid input";
        }
    }
}
