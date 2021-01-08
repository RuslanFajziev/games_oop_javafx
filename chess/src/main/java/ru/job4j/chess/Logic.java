package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import java.util.Arrays;

public final class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        int index = findBy(source);
        Cell[] steps = figures[index].way(dest);
        if (!free(steps)) {
            throw new OccupiedCellException("The path is occupied by another shape");
        }
        figures[index] = figures[index].copy(dest);
    }

    private boolean free(Cell[] steps) {
        boolean rsl = true;
        for (int index = 0; index < steps.length; index++) {
            for (int idx = 0; idx < figures.length; idx++) {
                if (figures[idx].position() == steps[index]) {
                    rsl = false;
                    break;
                }
            }
        }
        return rsl;
    }

    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.length; index++) {
            Figure figure = figures[index];
            if (figure != null && figure.position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException("Index not found for shape");
    }
}
