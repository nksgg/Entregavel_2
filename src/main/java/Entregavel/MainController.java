package Entregavel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Arrays;

import java.io.IOException;

public class MainController {

    private Stage stage;

    // Injetar elementos da cena VerificaPrimo
    @FXML
    private TextField numeroField;
    @FXML
    private Label resultadoLabelPrimo;

    // Injetar elementos da cena Soma
    @FXML
    private TextField quantidadeField;
    @FXML
    private TextField numeroSomaField;
    @FXML
    private Label resultadoLabelSoma;

    // Injetar elementos da cena Quicksort
    @FXML
    private TextField quantidadeQuickField;
    @FXML
    private TextField numeroQuickField;
    @FXML
    private Label resultadoLabelQuick;

    // Injetar elementos da cena MDC
    @FXML
    private TextField numeroMDC1Field;
    @FXML
    private TextField numeroMDC2Field;
    @FXML
    private Label resultadoLabelMDC;

    // Injetar elementos da cena Fibonacci
    @FXML
    private TextField termosFibonacciField;
    @FXML
    private Label resultadoLabelFibonacci;

    // Injetar elementos da cena Contagem
    @FXML
    private TextField quantidadeAlunosField;
    @FXML
    private TextField notaField;
    @FXML
    private Label resultadoLabelContagem;

    // Variáveis para soma
    private int quantidade;
    private int soma = 0;
    private int cont = 0;

    // Definir o stage da aplicação
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Método para verificar se o número é primo
    @FXML
    protected void verificaPrimo() {
        try {
            int numero = Integer.parseInt(numeroField.getText());

            if (numero <= 1) {
                resultadoLabelPrimo.setText("O número deve ser maior que 1.");
                return;
            }

            int contDivisores = 0;
            for (int i = 1; i <= Math.sqrt(numero); i++) {
                if (numero % i == 0) {
                    contDivisores++;
                }
            }

            if (contDivisores == 1) {
                resultadoLabelPrimo.setText(numero + " é um número primo.");
            } else {
                resultadoLabelPrimo.setText(numero + " não é um número primo.");
            }
        } catch (NumberFormatException e) {
            resultadoLabelPrimo.setText("Por favor, insira um número válido.");
        }
    }

    // Método para somar os números inseridos
    @FXML
    protected void adicionarESomar() {
        try {
            if (quantidade == 0) {
                quantidade = Integer.parseInt(quantidadeField.getText());
                resultadoLabelSoma.setText("Quantidade de números: " + quantidade);
            }

            if (cont < quantidade) {
                int numAtual = Integer.parseInt(numeroSomaField.getText());
                soma += numAtual;
                cont++;
                resultadoLabelSoma.setText("Soma parcial: " + soma + " (faltam " + (quantidade - cont) + " números)");
            }

            if (cont == quantidade) {
                resultadoLabelSoma.setText("A soma dos números é: " + soma);
                resetarSoma(); // Reseta para uma nova soma
            }
        } catch (NumberFormatException e) {
            resultadoLabelSoma.setText("Por favor, insira um número válido.");
        }
    }

    // Reseta os valores após a soma
    private void resetarSoma() {
        soma = 0;
        cont = 0;
        quantidade = 0;
    }

    // Método para ordenar números usando Quicksort
    @FXML
    protected void quicksort() {
        String input = numeroQuickField.getText();
        try {
            // Divide a string em números e converte para um array de inteiros
            int[] numeros = Arrays.stream(input.split(","))
                    .map(String::trim) // Remove espaços em branco
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // Chama o método quicksort e exibe o resultado
            quicksort(numeros, 0, numeros.length - 1);
            String resultado = Arrays.toString(numeros);
            resultadoLabelQuick.setText("Os números ordenados são: " + resultado);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Entrada Inválida");
            alert.setHeaderText("Por favor, insira números válidos separados por vírgula.");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro inesperado.");
            alert.showAndWait();
        }
    }

    // Método de Quicksort
    private void quicksort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quicksort(array, low, pivotIndex - 1); // Recursão à esquerda do pivô
            quicksort(array, pivotIndex + 1, high); // Recursão à direita do pivô
        }
    }

    // Particiona o array e retorna o índice do pivô
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    // Método auxiliar para trocar elementos no array
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    // Método para calcular o MDC
    @FXML
    protected void calcularMDC() {
        try {
            int n1 = Integer.parseInt(numeroMDC1Field.getText());
            int n2 = Integer.parseInt(numeroMDC2Field.getText());

            while (n2 != 0) {
                int resto = n1 % n2;
                n1 = n2;
                n2 = resto;
            }

            resultadoLabelMDC.setText("O MDC é: " + n1);
        } catch (NumberFormatException e) {
            resultadoLabelMDC.setText("Por favor, insira números válidos.");
        }
    }

    // Método para gerar a sequência de Fibonacci
    @FXML
    protected void gerarFibonacci() {
        try {
            int n = Integer.parseInt(termosFibonacciField.getText());
            StringBuilder fibonacci = new StringBuilder("0, 1");
            int a = 0, b = 1;

            for (int i = 2; i < n; i++) {
                int temp = b;
                b = a + b;
                a = temp;
                fibonacci.append(", ").append(b);
            }

            resultadoLabelFibonacci.setText(fibonacci.toString());
        } catch (NumberFormatException e) {
            resultadoLabelFibonacci.setText("Por favor, insira um número válido.");
        }
    }

    // Método para contar alunos aprovados
    @FXML
    protected void contarAlunos() {
        try {
            int n = Integer.parseInt(quantidadeAlunosField.getText());
            int cont = 0;

            for (int i = 0; i < n; i++) {
                int nota = Integer.parseInt(notaField.getText());
                if (nota >= 50) {
                    cont++;
                }
            }

            resultadoLabelContagem.setText("Alunos aprovados: " + cont);
        } catch (NumberFormatException e) {
            resultadoLabelContagem.setText("Por favor, insira um número válido.");
        }
    }

    // Método para trocar para a cena VerificaPrimo
    @FXML
    protected void trocarParaPrimo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("primo-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 280);

        MainController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
        stage.show();
    }

    // Método para trocar para a cena Soma
    @FXML
    protected void trocarParaSoma() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Soma.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 280);

        MainController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void trocarParaFibonacci() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Fibonacci.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 280);

        MainController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void trocarParaContagem() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Contagem.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 280);

        MainController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void trocarParaMDC() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MDC.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 280);

        MainController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void trocarParaQuicksort() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Quicksort.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 280);

        MainController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
        stage.show();
    }
}