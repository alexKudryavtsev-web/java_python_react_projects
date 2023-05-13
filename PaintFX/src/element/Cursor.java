package element;

import javafx.scene.canvas.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;

import memento.ListState;

public class Cursor {
    public final static Cursor CURSOR = new Cursor();

    private GraphicsContext context;
    private Color brush_color;
    private int brush_line, eraser_line;

    private Cursor() {
    }

    public void setCanvas(Canvas canvas) {
        this.context = canvas.getGraphicsContext2D();
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    context.beginPath();
                    context.moveTo(event.getX(), event.getY());
                    context.stroke();
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                event -> {

                    context.lineTo(event.getX(), event.getY());
                    context.stroke();
                });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                event -> {
                    ListState.LIST_STATE.add();
                    context.lineTo(event.getX(), event.getY());
                    context.stroke();
                });

    }

    private void newCursor(Color color, int line_width) {
        context.setFill(Color.RED);
        context.setStroke(color);
        context.setLineWidth(line_width);
    }

    public void addBrush() {
        newCursor(brush_color, brush_line);
    }

    public void addEraser() {
        newCursor(Color.WHITE, eraser_line);
    }

    public void brushColor(Color brush_color) {
        this.brush_color = brush_color;
        addBrush();
    }

    public void eraserLine(int eraser_line) {
        this.eraser_line = eraser_line;
        addEraser();
    }

    public void brushLine(int brush_line) {
        this.brush_line = brush_line;
        addBrush();
    }
}