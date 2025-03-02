package main;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.components.EditorCanvas;

public class Editor extends Frame implements KeyListener {
  private EditorCanvas canvas;

  public Editor() {
    setTitle("Custom Text Editor");
    setSize(800, 600);
    setLayout(new BorderLayout());

    canvas = new EditorCanvas();
    add(canvas, BorderLayout.CENTER);

    addKeyListener(this);
    canvas.addKeyListener(this); // Allow canvas to receive key events

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    setVisible(true);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    char c = e.getKeyChar();
    if (Character.isLetterOrDigit(c) || Character.isSpaceChar(c)) {
      canvas.appendText(c, 0);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
    canvas.appendText(Character.MIN_VALUE, code);
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  public static void main(String[] args) {
    new Editor();
  }
}
