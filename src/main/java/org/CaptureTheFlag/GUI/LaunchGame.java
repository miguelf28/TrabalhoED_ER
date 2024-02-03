package org.CaptureTheFlag.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchGame {
    public static void main(String[] args) {
        // Criando uma instância da classe SwingAWTExample
        LaunchGame swingAWTExample = new LaunchGame();
        swingAWTExample.StartGame();
    }

    private void StartGame() {
        // Criando uma nova janela
        JFrame frame = new JFrame("Swing and AWT Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Criando um painel para conter os componentes
        JPanel panel = new JPanel();
        frame.add(panel);

        // Definindo o layout do painel como BorderLayout
        panel.setLayout(new BorderLayout());

        // Criando um botão
        JButton button = new JButton("Click Me!");

        // Adicionando um ActionListener ao botão para lidar com eventos de clique
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exibindo uma caixa de diálogo com uma mensagem quando o botão é clicado
                JOptionPane.showMessageDialog(frame, "Button Clicked!");
            }
        });

        // Adicionando o botão ao painel
        panel.add(button, BorderLayout.CENTER);

        // Tornando a janela visível
        frame.setVisible(true);
    }
}
