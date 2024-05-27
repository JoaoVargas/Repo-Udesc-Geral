import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class TestTicTacToe {
    @Test
    public void TestDraw(){
        Board b = new Board();

        b.setPosition(new int[]{0,0}, 1);
        b.setPosition(new int[]{0,1}, 2);
        b.setPosition(new int[]{1,1}, 1);
        b.setPosition(new int[]{2,0}, 2);
        b.setPosition(new int[]{0,2}, 1);
        b.setPosition(new int[]{2,2}, 2);
        b.setPosition(new int[]{2,1}, 1);
        b.setPosition(new int[]{1,0}, 2);

        b.showBoard();

        TicTacToe test = new TicTacToe(b, 1, 8);
        test.checkDraw();

        Assert.assertTrue("Não possivel checar empate", test.checkDraw());
    }

}
