package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class BishopBlackTest {

    @Test
    public void position() {
        Cell ps = Cell.B8;
        BishopBlack bishopBlack = new BishopBlack(ps);
        assertThat(bishopBlack.position(), is(ps));
    }

    @Test
    public void copy() {
        Cell ps = Cell.B8;
        BishopBlack bishopBlack = new BishopBlack(ps);
        assertThat(bishopBlack.copy(ps).position(), is(bishopBlack.position()));
    }

    @Test
    public void wayUnhExc() throws ImpossibleMoveException {
        Cell ps = Cell.C1;
        Cell psEnd = Cell.G5;
        BishopBlack bishopBlack = new BishopBlack(ps);
        Cell[] rsl = bishopBlack.way(psEnd);
        Cell[] chk = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertArrayEquals(chk, rsl);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void way() throws ImpossibleMoveException {
        Cell ps = Cell.C1;
        Cell psEnd = Cell.G4;
        BishopBlack bishopBlack = new BishopBlack(ps);
        Cell[] rsl = bishopBlack.way(psEnd);
        Cell[] chk = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertArrayEquals(chk, rsl);
    }

    @Test
    public void isDiagonalTrue() {
        Cell ps = Cell.C1;
        Cell psEnd = Cell.G5;
        BishopBlack bishopBlack = new BishopBlack(ps);
        boolean rsl =
                bishopBlack.isDiagonal(bishopBlack.position(), psEnd);
        assertTrue(rsl);
    }

    @Test
    public void isDiagonalFalse() {
        Cell ps = Cell.C1;
        Cell psEnd = Cell.A4;
        BishopBlack bishopBlack = new BishopBlack(ps);
        boolean rsl =
                bishopBlack.isDiagonal(bishopBlack.position(), bishopBlack.copy(psEnd).position());
        assertFalse(rsl);
    }
}