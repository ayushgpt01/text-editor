package main.components;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class EditorCanvas extends Canvas {
  private StringBuilder text;
  private int cursorPosition = 0;
  private final int MAX_WIDTH = 800;
  private final int MAX_CURSOR_POS = 52;

  public EditorCanvas(String value) {
    text = new StringBuilder(value);
    setBackground(Color.LIGHT_GRAY);
    setSize(MAX_WIDTH, 600);
  }

  public EditorCanvas() {
    this("");
  }

  @Override
  public void paint(Graphics g) {
    Font font = new Font("Monospaced", Font.PLAIN, 18);
    g.setFont(font);
    g.setColor(Color.BLACK);
    g.drawString(text.toString(), 20, 50); // Draw text at position

    int cursorX = 20 + (cursorPosition * 12);
    g.drawLine(cursorX, 35, cursorX, 55);
  }

  private void moveCursor(int pos) {
    int updatedPosition = cursorPosition + pos;
    if (updatedPosition >= 0 && updatedPosition <= MAX_CURSOR_POS) {
      cursorPosition = updatedPosition;
    }
  }

  public void appendText(char c, int keyCode) {
    if (c == Character.MIN_VALUE) {
      switch (keyCode) {
        case KeyEvent.VK_BACK_SPACE:
          if (text.length() > 0) {
            text.deleteCharAt(text.length() - 1);
            moveCursor(-1);
          }
          break;
        case KeyEvent.VK_LEFT:
          moveCursor(-1);
          break;
        case KeyEvent.VK_RIGHT:
          moveCursor(1);
          break;
        default:
          break;
      }
    } else {
      text.append(c);
      moveCursor(1);
    }

    repaint(); // Redraw canvas with updated text
  }
}