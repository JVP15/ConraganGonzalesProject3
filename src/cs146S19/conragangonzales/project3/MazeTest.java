package cs146S19.conragangonzales.project3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Maze class.
 */
class MazeTest {
	List<Integer> testSizes;

	@BeforeEach
	void setUp() throws Exception {
		testSizes = Arrays.asList(4, 5, 6, 7, 8, 9, 10);
	}

	@Test
	void test() {
		for (int n : testSizes) {
			System.out.println("Size: " + n + "x" + n);
			new Maze(n, n).printMaze();
			System.out.print("\n");
		}
	}

}
