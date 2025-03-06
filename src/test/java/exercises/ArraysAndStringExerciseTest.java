package exercises;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class ArraysAndStringExerciseTest {

    @Nested
    class isUnique {
        @Test
        void isUnique_nullPointerException() {
            final String value = null;

            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.isUnique(value));
            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.isUniqueLambda(value));
        }

        @Test
        void isUnique_empty() {
            final String value = "";

            Assertions.assertTrue(ArraysAndStringExercise.isUnique(value));
            Assertions.assertTrue(ArraysAndStringExercise.isUniqueLambda(value));
        }

        @Test
        void isUnique_false() {
            final String value = "hola mundo";

            Assertions.assertFalse(ArraysAndStringExercise.isUnique(value));
            Assertions.assertFalse(ArraysAndStringExercise.isUniqueLambda(value));
        }

        @Test
        void isUnique_true() {
            final String value = "hola mund";

            Assertions.assertTrue(ArraysAndStringExercise.isUnique(value));
            Assertions.assertTrue(ArraysAndStringExercise.isUniqueLambda(value));
        }
    }

    @Nested
    class checkPermutation {
        @Test
        void checkPermutation_nullPointerException() {
            final String value = null;

            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.checkPermutation(value, value));
            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.checkPermutationLambda(value, value));
        }

        @Test
        void checkPermutation_empty() {
            final String value = "";

            Assertions.assertTrue(ArraysAndStringExercise.checkPermutation(value, value));
            Assertions.assertTrue(ArraysAndStringExercise.checkPermutationLambda(value, value));
        }

        @Test
        void checkPermutation_false() {
            final String first = "hola mundo";
            final String second = "odnum aloha";

            Assertions.assertFalse(ArraysAndStringExercise.checkPermutation(first, second));
            Assertions.assertFalse(ArraysAndStringExercise.checkPermutationLambda(first, second));
        }

        @Test
        void checkPermutation_true() {
            final String first = "hola mundo";
            final String second = "odnum aloh";

            Assertions.assertTrue(ArraysAndStringExercise.checkPermutation(first, second));
            Assertions.assertTrue(ArraysAndStringExercise.checkPermutationLambda(first, second));
        }
    }

    @Nested
    class urlify {

        @Test
        void urlify_nullPointerException() {
            final String value = null;

            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.urlify(value));
            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.urlifyLambda(value));
        }

        @Test
        void urlify_empty() {
            final String value = "";

            Assertions.assertEquals(value, ArraysAndStringExercise.urlify(value));
            Assertions.assertEquals(value, ArraysAndStringExercise.urlifyLambda(value));
        }

        @Test
        void urlify_ok() {
            final String actual = "hola mundo !";
            final String expected = "hola%20mundo%20!";

            Assertions.assertEquals(expected, ArraysAndStringExercise.urlify(actual));
            Assertions.assertEquals(expected, ArraysAndStringExercise.urlifyLambda(actual));
        }
    }

    @Nested
    class isPalindromePermutation {
        @Test
        void isPalindromePermutation_nullPointerException() {
            final String value = null;

            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.isPalindromePermutation(value));
            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.isPalindromePermutationLambda(value));
        }

        @Test
        void isPalindromePermutation_empty() {
            final String value = "";

            Assertions.assertTrue(ArraysAndStringExercise.isPalindromePermutation(value));
            Assertions.assertTrue(ArraysAndStringExercise.isPalindromePermutationLambda(value));
        }

        @Test
        void isPalindromePermutation_false() {
            final String value = "taco cata";

            Assertions.assertFalse(ArraysAndStringExercise.isPalindromePermutation(value));
            Assertions.assertFalse(ArraysAndStringExercise.isPalindromePermutationLambda(value));
        }

        @Test
        void isPalindromePermutation_true() {
            final String value = "taco cat";

            Assertions.assertTrue(ArraysAndStringExercise.isPalindromePermutation(value));
            Assertions.assertTrue(ArraysAndStringExercise.isPalindromePermutationLambda(value));
        }
    }

    @Nested
    class isOneAwayLambda {
        @Test
        void isOneAwayLambda_nullPointerException() {
            final String value = null;

            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.isOneAway(value, value));
            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.isOneAwayLambda(value, value));
        }

        @Test
        void isOneAwayLambda_empty() {
            final String value = "";

            Assertions.assertTrue(ArraysAndStringExercise.isOneAway(value, value));
            Assertions.assertTrue(ArraysAndStringExercise.isOneAwayLambda(value, value));
        }

        @Test
        void isOneAwayLambda_false() {
            final String first = "taco";
            final String second = "mapo";

            Assertions.assertFalse(ArraysAndStringExercise.isOneAway(first, second));
            Assertions.assertFalse(ArraysAndStringExercise.isOneAwayLambda(first, second));
        }

        @Test
        void isOneAwayLambda_true() {
            final String first = "taco";
            final String second = "maco";

            Assertions.assertTrue(ArraysAndStringExercise.isOneAway(first, second));
            Assertions.assertTrue(ArraysAndStringExercise.isOneAwayLambda(first, second));
        }
    }

    @Nested
    class stringCompression {
        @Test
        void stringCompression_nullPointerException() {
            final String value = null;

            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.stringCompression(value));
            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.stringCompressionLambda(value));
        }

        @Test
        void stringCompression_empty() {
            final String value = "";

            Assertions.assertEquals(value, ArraysAndStringExercise.stringCompression(value));
            Assertions.assertEquals(value, ArraysAndStringExercise.stringCompressionLambda(value));
        }

        @Test
        void stringCompression_noCompression() {
            final String actual = "abc";
            final String expected = "abc";

            Assertions.assertEquals(expected, ArraysAndStringExercise.stringCompression(actual));
            Assertions.assertEquals(expected, ArraysAndStringExercise.stringCompressionLambda(actual));
        }

        @Test
        void stringCompression_compression() {
            final String actual = "aabcccccaaa";
            final String expected = "a2b1c5a3";

            Assertions.assertEquals(expected, ArraysAndStringExercise.stringCompression(actual));
            Assertions.assertEquals(expected, ArraysAndStringExercise.stringCompressionLambda(actual));
        }
    }

    @Nested
    class rotate90Grades {
        @Test
        void rotate90Grades_nullPointerException() {
            final List<List<Integer>> value = null;

            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.rotate90Grades(value));
            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.rotate90GradesLambda(value));
        }

        @Test
        void rotate90Grades_empty() {
            final List<List<Integer>> value = Collections.emptyList();

            Assertions.assertEquals(value, ArraysAndStringExercise.rotate90Grades(value));
            Assertions.assertEquals(value, ArraysAndStringExercise.rotate90GradesLambda(value));
        }

        @Test
        void rotate90Grades_squareMatrix_ok() {
            final List<List<Integer>> actual = List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9));
            final List<List<Integer>> expected = List.of(List.of(1, 4, 7), List.of(2, 5, 8), List.of(3, 6, 9));

            Assertions.assertEquals(expected, ArraysAndStringExercise.rotate90Grades(actual));
            Assertions.assertEquals(expected, ArraysAndStringExercise.rotate90GradesLambda(actual));
        }

        @Test
        void rotate90Grades_squareMatrix_ignoreNonSquare() {
            final List<List<Integer>> actual = List.of(List.of(1, 2, 3, 10), List.of(4, 5, 6), List.of(7, 8, 9));
            final List<List<Integer>> expected = List.of(List.of(1, 4, 7), List.of(2, 5, 8), List.of(3, 6, 9));

            Assertions.assertEquals(expected, ArraysAndStringExercise.rotate90Grades(actual));
            Assertions.assertEquals(expected, ArraysAndStringExercise.rotate90GradesLambda(actual));
        }
    }

    @Nested
    class zeroMatrix {
        @Test
        void zeroMatrix_nullPointerException() {
            final List<List<Integer>> value = null;

            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.zeroMatrix(value));
            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.zeroMatrixLambda(value));
        }

        @Test
        void zeroMatrix_empty() {
            final List<List<Integer>> value = Collections.emptyList();

            Assertions.assertEquals(value, ArraysAndStringExercise.zeroMatrix(value));
            Assertions.assertEquals(value, ArraysAndStringExercise.zeroMatrixLambda(value));
        }

        @Test
        void zeroMatrix_squareMatrix_ok() {
            final List<List<Integer>> actual = List.of(List.of(0, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9));
            final List<List<Integer>> expected = List.of(List.of(0, 0, 0), List.of(0, 5, 6), List.of(0, 8, 9));

            Assertions.assertEquals(expected, ArraysAndStringExercise.zeroMatrix(actual));
            Assertions.assertEquals(expected, ArraysAndStringExercise.zeroMatrixLambda(actual));
        }

        @Test
        void zeroMatrix_nonSquareMatrix() {
            final List<List<Integer>> actual = List.of(List.of(1, 0, 3, 10), List.of(4, 5, 6), List.of(7, 8, 9));
            final List<List<Integer>> expected = List.of(List.of(0, 0, 0, 0), List.of(4, 0, 6), List.of(7, 0, 9));

            Assertions.assertEquals(expected, ArraysAndStringExercise.zeroMatrix(actual));
            Assertions.assertEquals(expected, ArraysAndStringExercise.zeroMatrixLambda(actual));
        }
    }

    @Nested
    class isRotation {
        @Test
        void isRotation_nullPointerException() {
            final String value = null;

            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.isRotation(value, value));
            Assertions.assertThrows(NullPointerException.class, () -> ArraysAndStringExercise.isRotationLambda(value, value));
        }

        @Test
        void isRotation_empty() {
            final String value = "";

            Assertions.assertTrue(ArraysAndStringExercise.isRotation(value, value));
            Assertions.assertTrue(ArraysAndStringExercise.isRotationLambda(value, value));
        }

        @Test
        void isRotation_false() {
            final String first = "waterbottle";
            final String second = "erbottlewata";

            Assertions.assertFalse(ArraysAndStringExercise.isRotation(first, second));
            Assertions.assertFalse(ArraysAndStringExercise.isRotationLambda(first, second));
        }

        @Test
        void isRotation_true() {
            final String first = "waterbottle";
            final String second = "erbottlewat";

            Assertions.assertTrue(ArraysAndStringExercise.isRotation(first, second));
            Assertions.assertTrue(ArraysAndStringExercise.isRotationLambda(first, second));
        }
    }

}
