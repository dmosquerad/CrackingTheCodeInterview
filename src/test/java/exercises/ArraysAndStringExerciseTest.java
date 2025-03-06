package exercises;

import static exercises.ArraysAndStringExercise.checkPermutation;
import static exercises.ArraysAndStringExercise.checkPermutationLambda;
import static exercises.ArraysAndStringExercise.isOneAway;
import static exercises.ArraysAndStringExercise.isOneAwayLambda;
import static exercises.ArraysAndStringExercise.isPalindromePermutation;
import static exercises.ArraysAndStringExercise.isPalindromePermutationLambda;
import static exercises.ArraysAndStringExercise.isRotation;
import static exercises.ArraysAndStringExercise.isRotationLambda;
import static exercises.ArraysAndStringExercise.isUnique;
import static exercises.ArraysAndStringExercise.isUniqueLambda;
import static exercises.ArraysAndStringExercise.rotate90Grades;
import static exercises.ArraysAndStringExercise.rotate90GradesLambda;
import static exercises.ArraysAndStringExercise.stringCompression;
import static exercises.ArraysAndStringExercise.stringCompressionLambda;
import static exercises.ArraysAndStringExercise.urlify;
import static exercises.ArraysAndStringExercise.urlifyLambda;
import static exercises.ArraysAndStringExercise.zeroMatrix;
import static exercises.ArraysAndStringExercise.zeroMatrixLambda;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ArraysAndStringExerciseTest {

  // Exercise 1.1
  @Nested
  class IsUniqueTest {
    @Test
    void isUnique_nullPointerException() {
      final String value = null;

      Assertions.assertThrows(NullPointerException.class, () -> isUnique(value));
      Assertions.assertThrows(NullPointerException.class, () -> isUniqueLambda(value));
    }

    @Test
    void isUnique_empty() {
      final String value = "";

      Assertions.assertTrue(isUnique(value));
      Assertions.assertTrue(isUniqueLambda(value));
    }

    @Test
    void isUnique_false() {
      final String value = "hola mundo";

      Assertions.assertFalse(isUnique(value));
      Assertions.assertFalse(isUniqueLambda(value));
    }

    @Test
    void isUnique_true() {
      final String value = "hola mund";

      Assertions.assertTrue(isUnique(value));
      Assertions.assertTrue(isUniqueLambda(value));
    }
  }

  // Exercise 1.2
  @Nested
  class CheckPermutationTest {
    @Test
    void checkPermutation_nullPointerException() {
      final String value = null;

      Assertions.assertThrows(NullPointerException.class, () -> checkPermutation(value, value));
      Assertions.assertThrows(NullPointerException.class,
          () -> checkPermutationLambda(value, value));
    }

    @Test
    void checkPermutation_empty() {
      final String value = "";

      Assertions.assertTrue(checkPermutation(value, value));
      Assertions.assertTrue(checkPermutationLambda(value, value));
    }

    @Test
    void checkPermutation_false() {
      final String first = "hola mundo";
      final String second = "odnum aloha";

      Assertions.assertFalse(checkPermutation(first, second));
      Assertions.assertFalse(checkPermutationLambda(first, second));
    }

    @Test
    void checkPermutation_true() {
      final String first = "hola mundo";
      final String second = "odnum aloh";

      Assertions.assertTrue(checkPermutation(first, second));
      Assertions.assertTrue(checkPermutationLambda(first, second));
    }
  }

  // Exercise 1.3
  @Nested
  class UrlifyTest {

    @Test
    void urlify_nullPointerException() {
      final String value = null;

      Assertions.assertThrows(NullPointerException.class, () -> urlify(value));
      Assertions.assertThrows(NullPointerException.class, () -> urlifyLambda(value));
    }

    @Test
    void urlify_empty() {
      final String value = "";

      Assertions.assertEquals(value, urlify(value));
      Assertions.assertEquals(value, urlifyLambda(value));
    }

    @Test
    void urlify_ok() {
      final String actual = "hola mundo !";
      final String expected = "hola%20mundo%20!";

      Assertions.assertEquals(expected, urlify(actual));
      Assertions.assertEquals(expected, urlifyLambda(actual));
    }
  }

  // Exercise 1.4
  @Nested
  class IsPalindromePermutationTest {
    @Test
    void isPalindromePermutation_nullPointerException() {
      final String value = null;

      Assertions.assertThrows(NullPointerException.class, () -> isPalindromePermutation(value));
      Assertions.assertThrows(NullPointerException.class,
          () -> isPalindromePermutationLambda(value));
    }

    @Test
    void isPalindromePermutation_empty() {
      final String value = "";

      Assertions.assertTrue(isPalindromePermutation(value));
      Assertions.assertTrue(isPalindromePermutationLambda(value));
    }

    @Test
    void isPalindromePermutation_false() {
      final String value = "taco cata";

      Assertions.assertFalse(isPalindromePermutation(value));
      Assertions.assertFalse(isPalindromePermutationLambda(value));
    }

    @Test
    void isPalindromePermutation_true() {
      final String value = "taco cat";

      Assertions.assertTrue(isPalindromePermutation(value));
      Assertions.assertTrue(isPalindromePermutationLambda(value));
    }
  }

  // Exercise 1.5
  @Nested
  class IsOneAwayLambdaTest {
    @Test
    void isOneAwayLambda_nullPointerException() {
      final String value = null;

      Assertions.assertThrows(NullPointerException.class, () -> isOneAway(value, value));
      Assertions.assertThrows(NullPointerException.class, () -> isOneAwayLambda(value, value));
    }

    @Test
    void isOneAwayLambda_empty() {
      final String value = "";

      Assertions.assertTrue(isOneAway(value, value));
      Assertions.assertTrue(isOneAwayLambda(value, value));
    }

    @Test
    void isOneAwayLambda_false() {
      final String first = "taco";
      final String second = "mapo";

      Assertions.assertFalse(isOneAway(first, second));
      Assertions.assertFalse(isOneAwayLambda(first, second));
    }

    @Test
    void isOneAwayLambda_true() {
      final String first = "taco";
      final String second = "maco";

      Assertions.assertTrue(isOneAway(first, second));
      Assertions.assertTrue(isOneAwayLambda(first, second));
    }
  }

  // Exercise 1.6
  @Nested
  class StringCompressionTest {
    @Test
    void stringCompression_nullPointerException() {
      final String value = null;

      Assertions.assertThrows(NullPointerException.class, () -> stringCompression(value));
      Assertions.assertThrows(NullPointerException.class, () -> stringCompressionLambda(value));
    }

    @Test
    void stringCompression_empty() {
      final String value = "";

      Assertions.assertEquals(value, stringCompression(value));
      Assertions.assertEquals(value, stringCompressionLambda(value));
    }

    @Test
    void stringCompression_noCompression() {
      final String actual = "abc";
      final String expected = "abc";

      Assertions.assertEquals(expected, stringCompression(actual));
      Assertions.assertEquals(expected, stringCompressionLambda(actual));
    }

    @Test
    void stringCompression_compression() {
      final String actual = "aabcccccaaa";
      final String expected = "a2b1c5a3";

      Assertions.assertEquals(expected, stringCompression(actual));
      Assertions.assertEquals(expected, stringCompressionLambda(actual));
    }
  }

  // Exercise 1.7
  @Nested
  class Rotate90GradesTest {
    @Test
    void rotate90Grades_nullPointerException() {
      final Collection<Collection<Integer>> value = null;

      Assertions.assertThrows(NullPointerException.class, () -> rotate90Grades(value));
      Assertions.assertThrows(NullPointerException.class, () -> rotate90GradesLambda(value));
    }

    @Test
    void rotate90Grades_empty() {
      final Collection<Collection<Integer>> value = Collections.emptyList();

      Assertions.assertEquals(value, rotate90Grades(value));
      Assertions.assertEquals(value, rotate90GradesLambda(value));
    }

    @Test
    void rotate90Grades_squareMatrix_ok() {
      final Collection<Collection<Integer>> actual =
          List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9));
      final Collection<Collection<Integer>> expected =
          List.of(List.of(1, 4, 7), List.of(2, 5, 8), List.of(3, 6, 9));

      Assertions.assertEquals(expected, rotate90Grades(actual));
      Assertions.assertEquals(expected, rotate90GradesLambda(actual));
    }

    @Test
    void rotate90Grades_squareMatrix_ignoreNonSquare() {
      final Collection<Collection<Integer>> actual =
          List.of(List.of(1, 2, 3, 10), List.of(4, 5, 6), List.of(7, 8, 9));
      final Collection<Collection<Integer>> expected =
          List.of(List.of(1, 4, 7), List.of(2, 5, 8), List.of(3, 6, 9));

      Assertions.assertEquals(expected, rotate90Grades(actual));
      Assertions.assertEquals(expected, rotate90GradesLambda(actual));
    }
  }

  // Exercise 1.8
  @Nested
  class ZeroMatrixTest {
    @Test
    void zeroMatrix_nullPointerException() {
      final Collection<Collection<Integer>> value = null;

      Assertions.assertThrows(NullPointerException.class, () -> zeroMatrix(value));
      Assertions.assertThrows(NullPointerException.class, () -> zeroMatrixLambda(value));
    }

    @Test
    void zeroMatrix_empty() {
      final Collection<Collection<Integer>> value = Collections.emptyList();

      Assertions.assertEquals(value, zeroMatrix(value));
      Assertions.assertEquals(value, zeroMatrixLambda(value));
    }

    @Test
    void zeroMatrix_squareMatrix_ok() {
      final Collection<Collection<Integer>> actual =
          List.of(List.of(0, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9));
      final Collection<Collection<Integer>> expected =
          List.of(List.of(0, 0, 0), List.of(0, 5, 6), List.of(0, 8, 9));

      Assertions.assertEquals(expected, zeroMatrix(actual));
      Assertions.assertEquals(expected, zeroMatrixLambda(actual));
    }

    @Test
    void zeroMatrix_nonSquareMatrix() {
      final Collection<Collection<Integer>> actual =
          List.of(List.of(1, 0, 3, 10), List.of(4, 5, 6), List.of(7, 8, 9));
      final Collection<Collection<Integer>> expected =
          List.of(List.of(0, 0, 0, 0), List.of(4, 0, 6), List.of(7, 0, 9));

      Assertions.assertEquals(expected, zeroMatrix(actual));
      Assertions.assertEquals(expected, zeroMatrixLambda(actual));
    }
  }

  // Exercise 1.9
  @Nested
  class IsRotationTest {
    @Test
    void isRotation_nullPointerException() {
      final String value = null;

      Assertions.assertThrows(NullPointerException.class, () -> isRotation(value, value));
      Assertions.assertThrows(NullPointerException.class, () -> isRotationLambda(value, value));
    }

    @Test
    void isRotation_empty() {
      final String value = "";

      Assertions.assertTrue(isRotation(value, value));
      Assertions.assertTrue(isRotationLambda(value, value));
    }

    @Test
    void isRotation_false() {
      final String first = "waterbottle";
      final String second = "erbottlewata";

      Assertions.assertFalse(isRotation(first, second));
      Assertions.assertFalse(isRotationLambda(first, second));
    }

    @Test
    void isRotation_true() {
      final String first = "waterbottle";
      final String second = "erbottlewat";

      Assertions.assertTrue(isRotation(first, second));
      Assertions.assertTrue(isRotationLambda(first, second));
    }
  }

}
