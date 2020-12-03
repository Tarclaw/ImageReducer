package lets.doit.gui;

import lets.doit.reducer.ImageReducer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EntryForm {

    private final ImageReducer imageReducer;
    private final JPanel panel;

    public EntryForm() {
        this.imageReducer = new ImageReducer();
        this.panel = new JPanel();
        this.panel.setLayout(null);
    }

    public void processForm() {
        JFrame frame = new JFrame();
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        createInputPathFunctionality();
        createOutputPathFunctionality();
        createReduceCoefficientFunctionality();
        createExtensionFunctionality();
        createReduceFunctionality();

        frame.setVisible(true);
    }

    private void createInputPathFunctionality() {
        JLabel inputLabel = new JLabel("Input Image Path:");
        inputLabel.setBounds(10,20,130,25);
        panel.add(inputLabel);

        JTextField inputField = new JTextField(20);
        inputField.setBounds(150,20,165,25);
        panel.add(inputField);

        JLabel showInput = new JLabel(imageReducer.getInputImagePath());
        showInput.setBounds(10,40,300,25);
        showInput.setFont(new Font("Verdana", Font.PLAIN, 13));
        showInput.setForeground(Color.BLUE);
        panel.add(showInput);

        JLabel exception = new JLabel("");
        exception.setBounds(10,40,300,25);
        exception.setFont(new Font("Verdana", Font.BOLD, 13));
        exception.setForeground(Color.RED);
        panel.add(exception);

        JButton inputButton = new JButton("Add");
        inputButton.setBounds(330,20,80,25);
        inputButton.addActionListener(actionEvent -> {
            String inputPath = inputField.getText();
            try {
                showInput.setText(imageReducer.getInputImagePath());
                exception.setText("");
                isDirectoryExist(inputPath);
                imageReducer.setInputImagePath(inputPath);
                inputField.setText("");
                showInput.setText(inputPath);
            } catch (IOException e) {
                showInput.setText("");
                exception.setText("Input Path doesn't exist.");
            }
        });
        panel.add(inputButton);
    }

    private void createOutputPathFunctionality() {
        JLabel outputLabel = new JLabel("Output Image Path:");
        outputLabel.setBounds(10,70,140,25);
        panel.add(outputLabel);

        JTextField outputField = new JTextField(20);
        outputField.setBounds(150,70,165,25);
        panel.add(outputField);

        JLabel showOutput = new JLabel(imageReducer.getOutputImagePath());
        showOutput.setBounds(10,90,300,25);
        showOutput.setFont(new Font("Verdana", Font.PLAIN, 13));
        showOutput.setForeground(Color.BLUE);
        panel.add(showOutput);

        JLabel exception = new JLabel("");
        exception.setBounds(10,90,300,25);
        exception.setFont(new Font("Verdana", Font.BOLD, 13));
        exception.setForeground(Color.RED);
        panel.add(exception);

        JButton outputButton = new JButton("Add");
        outputButton.setBounds(330,70,80,25);
        outputButton.addActionListener(actionEvent -> {
            String outputPath = outputField.getText();
            try {
                showOutput.setText(imageReducer.getOutputImagePath());
                exception.setText("");
                isDirectoryExist(outputPath);
                showOutput.setText(imageReducer.getOutputImagePath());
                imageReducer.setOutputImagePath(outputPath);
                outputField.setText("");
                showOutput.setText(outputPath);
            } catch (IOException e) {
                showOutput.setText("");
                exception.setText("Output Path doesn't exist.");
            }
        });
        panel.add(outputButton);
    }

    private void isDirectoryExist(String path) throws IOException {
        if (! new File(path).isDirectory()) throw new IOException();
    }

    private void createReduceCoefficientFunctionality() {
        JLabel reduceCoefLabel = new JLabel("Reduce Coefficient:");
        reduceCoefLabel.setBounds(10,120,140,25);
        panel.add(reduceCoefLabel);

        JTextField reduceCoefField = new JTextField(20);
        reduceCoefField.setBounds(150,120,165,25);
        panel.add(reduceCoefField);

        JLabel showReduceCoef = new JLabel(String.valueOf(imageReducer.getReduceCoefficient()));
        showReduceCoef.setBounds(10,140,300,25);
        showReduceCoef.setFont(new Font("Verdana", Font.PLAIN, 13));
        showReduceCoef.setForeground(Color.BLUE);
        panel.add(showReduceCoef);

        JLabel exception = new JLabel("");
        exception.setBounds(10,140,300,25);
        exception.setFont(new Font("Verdana", Font.BOLD, 13));
        exception.setForeground(Color.RED);
        panel.add(exception);

        JButton reduceCoefButton = new JButton("Add");
        reduceCoefButton.setBounds(330,120,80,25);
        reduceCoefButton.addActionListener(actionEvent -> {
            try {
                showReduceCoef.setText(String.valueOf(imageReducer.getReduceCoefficient()));
                exception.setText("");
                String reduceCoef = reduceCoefField.getText();
                imageReducer.setReduceCoefficient(Float.parseFloat(reduceCoef));
                reduceCoefField.setText("");
                showReduceCoef.setText(reduceCoef);
            } catch (NumberFormatException e) {
                showReduceCoef.setText("");
                exception.setText("Number " + reduceCoefField.getText() + " is invalid. Try again.");
            }
        });
        panel.add(reduceCoefButton);
    }

    private void createExtensionFunctionality() {
        JLabel extensionLabel = new JLabel("Add new Extension:");
        extensionLabel.setBounds(10,170,140,25);
        panel.add(extensionLabel);

        JTextField extensionField = new JTextField(20);
        extensionField.setBounds(150,170,165,25);
        panel.add(extensionField);

        String extensions = imageReducer.getExtensions().toString();
        JLabel showExtension = new JLabel(extensions.substring(1, extensions.length() - 1));
        showExtension.setBounds(10,190,300,25);
        showExtension.setFont(new Font("Verdana", Font.PLAIN, 13));
        showExtension.setForeground(Color.BLUE);
        panel.add(showExtension);

        JButton showExtensionButton = new JButton("Add");
        showExtensionButton.setBounds(330,170,80,25);
        showExtensionButton.addActionListener(actionEvent -> {
            String extension = extensionField.getText();
            imageReducer.addExtension(extension);
            extensionField.setText("");
            extension = imageReducer.getExtensions().toString();
            showExtension.setText(extension.substring(1, extension.length() - 1));
        });
        panel.add(showExtensionButton);
    }

    private void createReduceFunctionality() {
        JLabel success = new JLabel("");
        success.setBounds(10,240,300,25);
        success.setFont(new Font("Verdana", Font.BOLD, 13));
        success.setForeground(Color.GREEN);
        panel.add(success);

        JLabel exception = new JLabel("");
        exception.setBounds(10,240,500,25);
        exception.setFont(new Font("Verdana", Font.BOLD, 13));
        exception.setForeground(Color.RED);
        panel.add(exception);

        JButton reduceButton = new JButton("Reduce");
        reduceButton.setBounds(10,220,100,25);
        reduceButton.addActionListener(actionEvent -> {
            try {
                exception.setText("");
                imageReducer.reduceImageSize();
                success.setText("Done! Checkout output directory.");
            } catch (IOException e) {
                exception.setText(e.getMessage());
            }
        });
        panel.add(reduceButton);
    }

    public static void main(String[] args) {
        EntryForm entryForm = new EntryForm();
        entryForm.processForm();
    }
}
