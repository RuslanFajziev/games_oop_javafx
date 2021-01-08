package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.ImpossibleMoveException;
import java.lang.Math;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not move by diagonal from %s to %s", position, dest)
            );
        }
        int deltaX = 1;
        int deltaY = 1;
        int x = position.getX();
        int y = position.getY();
        int x2 = dest.getX();
        int y2 = dest.getY();
        if (x > x2) {
            deltaX = -1;
        }
        if (y > y2) {
            deltaY = -1;
        }
        int size = Math.abs(x - x2);
        Cell[] steps = new Cell[size];
        for (int index = 0; index < size; index++) {
            x += deltaX;
            y += deltaY;
            steps[index] = Cell.findBy(x, y);
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        /* TODO check diagonal */
        int x = source.getX();
        int y = source.getY();
        int x2 = dest.getX();
        int y2 = dest.getY();
        return Math.abs(x - x2) == Math.abs(y - y2);
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
