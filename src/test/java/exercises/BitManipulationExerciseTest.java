package exercises;

import static exercises.BitManipulationExercise.MonochromeScreen;
import static exercises.BitManipulationExercise.countBitsToFlip;
import static exercises.BitManipulationExercise.isPowerOfBinaryTwo;
import static exercises.BitManipulationExercise.longestSequence;
import static exercises.BitManipulationExercise.longestSequenceLambda;
import static exercises.BitManipulationExercise.nextLargest;
import static exercises.BitManipulationExercise.nextSmallest;
import static exercises.BitManipulationExercise.printBinary;
import static exercises.BitManipulationExercise.printBinaryLambda;
import static exercises.BitManipulationExercise.swapOddEvenBits;
import static exercises.BitManipulationExercise.updateBits;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BitManipulationExerciseTest {

  // Exercise 5.1
  @Nested
  class UpdateBitsTest {

    @Test
    void updateBits_ko() {
      int baseInt = 1024;     // 10000000000
      int insertInt = 19;     // 10011
      int expected = 0;

      assertEquals(expected, updateBits(baseInt, insertInt, 2, 1));
    }

    @Test
    void updateBits_ok() {
      int baseInt = 1024;     // 10000000000
      int insertInt = 19;     // 10011
      int expected = 1100;    // 10001001100

      assertEquals(expected, updateBits(baseInt, insertInt, 2, 6));
    }
  }

  // Exercise 5.2
  @Nested
  class PrintBinaryTest {

    @Test
    void printBinary_ko() {
      assertEquals("ERROR", printBinary(0));
      assertEquals("ERROR", printBinaryLambda(0));
    }

    @Test
    void printBinary_ok() {
      assertEquals("0.1", printBinary(0.5));
      assertEquals("0.1", printBinaryLambda(0.5));

      assertEquals("0.01", printBinary(0.25));
      assertEquals("0.01", printBinaryLambda(0.25));

      assertEquals("0.11", printBinary(0.75));
      assertEquals("0.11", printBinaryLambda(0.75));
    }
  }

  // Exercise 5.3
  @Nested
  class LongestSequenceTest {

    @Test
    void longestSequence_zero() {
      assertEquals(1, longestSequence(0));
      assertEquals(1, longestSequenceLambda(0));
    }

    @Test
    void longestSequence_ok() {
      final int value = 1775;
      final int expected = 8;

      assertEquals(expected, longestSequence(value));
      assertEquals(expected, longestSequenceLambda(value));

    }
  }

  // Exercise 5.4
  @Nested
  class NextNumberTest {

    @Test
    void nextSmallest_ko() {
      final int value = 32;
      final int expected = -1;

      assertEquals(expected, nextSmallest(value));
    }

    @Test
    void nextSmallest_ok() {
      final int value = 23;
      final int expected = 31;

      assertEquals(expected, nextSmallest(value));
    }

    @Test
    void nextLargest_ko() {
      final int value = 0;
      final int expected = -1;

      assertEquals(expected, nextLargest(value));
    }

    @Test
    void nextLargest_ok() {
      final int value = 23;
      final int expected = 124;

      assertEquals(expected, nextLargest(value));
    }
  }

  // Exercise 5.5
  @Nested
  class IsPowerOfBinaryTwoTest {

    @Test
    void isPowerOfBinaryTwo_true() {
      Assertions.assertTrue(isPowerOfBinaryTwo(8));
    }

    @Test
    void isPowerOfBinaryTwo_false() {
      Assertions.assertFalse(isPowerOfBinaryTwo(10));
    }
  }

  // Exercise 5.6
  @Nested
  class CountBitsToFlipTest {

    @Test
    void countBitsToFlip_ok() {
      assertEquals(2, countBitsToFlip(29, 15));
    }
  }

  // Exercise 5.7
  @Nested
  class SwapOddEvenBitsTest {

    @Test
    void swapOddEvenBits_ok() {
      final int evenPositionBits = 5;
      final int oddPositionBits = 10;

      assertEquals(evenPositionBits, swapOddEvenBits(oddPositionBits));
      assertEquals(oddPositionBits, swapOddEvenBits(evenPositionBits));
    }
  }

  // Exercise 5.8
  @Nested
  class MonochromeScreenTest {

    private static MonochromeScreen display;

    @BeforeAll
    static void setUp() {
      display = MonochromeScreen.builder()
          .width(16)
          .height(8)
          .build();

      display.initialize();
    }

    @Test
    void testDrawLine_outOfBoundsY() {
      assertThrows(IllegalArgumentException.class, () -> display.drawHorizontalLine(2, 6, 9));
    }

    @Test
    void testDrawLine_outOfBoundsX() {
      assertThrows(IllegalArgumentException.class, () -> display.drawHorizontalLine(5, 20, 3));
    }

    @Test
    void testDrawLine_invalidX1GreaterThanX2() {
      assertThrows(IllegalArgumentException.class, () -> display.drawHorizontalLine(6, 2, 3));
    }

    @Test
    void testDrawLineAndReturn() {
      assertEquals(display.getScreen(), display.drawHorizontalLine(2, 6, 3));
    }
  }
}
