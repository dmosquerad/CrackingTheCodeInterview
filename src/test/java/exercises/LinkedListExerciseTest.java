package exercises;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

class LinkedListExerciseTest {

    @Nested
    class removeDumps {
        @Test
        void removeDumps_nullPointerException() {
            final List<Integer> value = null;

            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.removeDumps(value));
            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.removeDumpsLambda(value));
        }

        @Test
        void removeDumps_empty() {
            final List<Integer> value = Collections.emptyList();

            Assertions.assertEquals(value, LinkedListExercise.removeDumps(value));
            Assertions.assertEquals(value, LinkedListExercise.removeDumpsLambda(value));
        }

        @Test
        void removeDumps_ok() {
            final List<Integer> actual = List.of(2, 3, 1, 2, 3, 4);
            final List<Integer> expected = List.of(2, 3, 1, 4);

            Assertions.assertEquals(expected, LinkedListExercise.removeDumps(actual));
            Assertions.assertEquals(expected, LinkedListExercise.removeDumpsLambda(actual));
        }
    }

    @Nested
    class returnKthToLast {
        @Test
        void returnKthToLast_nullPointerException() {
            final List<Integer> value = null;
            final Integer index = null;

            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.returnKthToLast(value, index));
            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.returnKthToLastLambda(value, index));
        }

        @Test
        void returnKthToLast_empty() {
            final List<Integer> value = Collections.emptyList();
            final Integer indexExpected = 1;
            final Optional<Integer> expected = Optional.empty();

            Assertions.assertEquals(expected, LinkedListExercise.returnKthToLast(value, indexExpected));
            Assertions.assertEquals(expected, LinkedListExercise.returnKthToLastLambda(value, indexExpected));
        }

        @Test
        void returnKthToLast_ok_index() {
            final List<Integer> value = List.of(2, 3, 1, 2, 3, 4);
            final Integer indexExpected = 2;
            final Optional<Integer> expected = Optional.of(2);

            Assertions.assertEquals(expected, LinkedListExercise.returnKthToLast(value, indexExpected));
            Assertions.assertEquals(expected, LinkedListExercise.returnKthToLastLambda(value, indexExpected));
        }

        @Test
        void returnKthToLast_ok_index0() {
            final List<Integer> value = List.of(2, 3, 1, 2, 3, 4);
            final Integer indexExpected = 0;
            final Optional<Integer> expected = Optional.of(4);

            Assertions.assertEquals(expected, LinkedListExercise.returnKthToLast(value, indexExpected));
            Assertions.assertEquals(expected, LinkedListExercise.returnKthToLastLambda(value, indexExpected));
        }
    }

    @Nested
    class deleteMiddleNode {
        @Test
        void deleteMiddleNode_nullPointerException() {
            final List<String> value = null;

            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.deleteMiddleNode(value));
            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.deleteMiddleNodeLambda(value));
        }

        @Test
        void deleteMiddleNode_empty() {
            final List<String> value = Collections.emptyList();
            final List<String> expected = Collections.emptyList();

            Assertions.assertEquals(expected, LinkedListExercise.deleteMiddleNode(value));
            Assertions.assertEquals(expected, LinkedListExercise.deleteMiddleNodeLambda(value));
        }

        @Test
        void deleteMiddleNode_ok_pair() {
            final List<String> value = List.of("a", "b", "c", "d", "e", "f");
            final List<String> expected = List.of("a", "b", "d", "e", "f");

            Assertions.assertEquals(expected, LinkedListExercise.deleteMiddleNode(value));
            Assertions.assertEquals(expected, LinkedListExercise.deleteMiddleNodeLambda(value));
        }

        @Test
        void deleteMiddleNode_ok_impair() {
            final List<String> value = List.of("a", "b", "c", "d", "e");
            final List<String> expected = List.of("a", "b", "d", "e");

            Assertions.assertEquals(expected, LinkedListExercise.deleteMiddleNode(value));
            Assertions.assertEquals(expected, LinkedListExercise.deleteMiddleNodeLambda(value));
        }
    }

    @Nested
    class partitionNumber {
        @Test
        void partitionNumber_nullPointerException() {
            final List<Integer> value = null;
            final Integer number = null;

            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.partitionNumber(value, number));
            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.partitionNumberLambda(value, number));
        }

        @Test
        void partitionNumber_empty() {
            final List<Integer> value = Collections.emptyList();
            final Integer number = 1;

            Assertions.assertEquals(value, LinkedListExercise.partitionNumber(value, number));
            Assertions.assertEquals(value, LinkedListExercise.partitionNumberLambda(value, number));
        }

        @Test
        void partitionNumber_ok_index() {
            final List<Integer> value = List.of(2, 3, 1, 2, 3, 4);
            final Integer number = 3;
            final List<Integer> expected = List.of(2, 1, 2, 3, 3, 4);

            Assertions.assertEquals(expected, LinkedListExercise.partitionNumber(value, number));
            Assertions.assertEquals(expected, LinkedListExercise.partitionNumberLambda(value, number));
        }
    }

    @Nested
    class isPalindrome {
        @Test
        void isPalindrome_nullPointerException() {
            final List<Integer> value = null;

            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.isPalindrome(value));
            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.isPalindromeLambda(value));
        }

        @Test
        void isUnique_empty() {
            final List<Integer> value = Collections.emptyList();

            Assertions.assertTrue(LinkedListExercise.isPalindrome(value));
            Assertions.assertTrue(LinkedListExercise.isPalindromeLambda(value));
        }

        @Test
        void isUnique_false() {
            final List<Integer> value = List.of(1, 3, 2, 1);

            Assertions.assertFalse(LinkedListExercise.isPalindrome(value));
            Assertions.assertFalse(LinkedListExercise.isPalindromeLambda(value));
        }

        @Test
        void isUnique_true() {
            final List<Integer> value = List.of(1, 2, 3, 2, 1);

            Assertions.assertTrue(LinkedListExercise.isPalindrome(value));
            Assertions.assertTrue(LinkedListExercise.isPalindromeLambda(value));
        }
    }

    @Nested
    class intersectionMerge {
        @Test
        void intersectionMerge_nullPointerException() {
            final List<Integer> value = null;

            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.intersectionMerge(value, value));
            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.intersectionMergeLambda(value, value));
        }

        @Test
        void intersectionMerge_empty() {
            final List<Integer> value = Collections.emptyList();
            final Optional<Integer> expected = Optional.empty();

            Assertions.assertEquals(expected, LinkedListExercise.intersectionMerge(value, value));
            Assertions.assertEquals(expected, LinkedListExercise.intersectionMergeLambda(value, value));
        }

        @Test
        void intersectionMerge_ok_return_value() {
            final List<Integer> first = List.of(2, 2, 4, 2, 1);
            final List<Integer> second = List.of(2, 3, 4, 2, 1);
            final Optional<Integer> expected = Optional.of(4);

            Assertions.assertEquals(expected, LinkedListExercise.intersectionMerge(first, second));
            Assertions.assertEquals(expected, LinkedListExercise.intersectionMergeLambda(first, second));
        }

        @Test
        void intersectionMerge_ok_return_first() {
            final List<Integer> value = List.of(1, 2);
            final Optional<Integer> expected = Optional.of(1);

            Assertions.assertEquals(expected, LinkedListExercise.intersectionMerge(value, value));
            Assertions.assertEquals(expected, LinkedListExercise.intersectionMergeLambda(value, value));
        }
    }

    @Nested
    class loopDetection {
        @Test
        void loopDetection_nullPointerException() {
            final List<String> value = null;

            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.loopDetection(value));
            Assertions.assertThrows(NullPointerException.class, () -> LinkedListExercise.loopDetectionLambda(value));
        }

        @Test
        void loopDetection_empty() {
            final List<String> value = Collections.emptyList();
            final Optional<String> expected = Optional.empty();

            Assertions.assertEquals(expected, LinkedListExercise.loopDetection(value));
            Assertions.assertEquals(expected, LinkedListExercise.loopDetectionLambda(value));
        }

        @Test
        void loopDetection_ok_return_value() {
            final List<String> value = List.of("a", "b", "c", "d", "e", "c");
            final Optional<String> expected = Optional.of("c");

            Assertions.assertEquals(expected, LinkedListExercise.loopDetection(value));
            Assertions.assertEquals(expected, LinkedListExercise.loopDetectionLambda(value));
        }

        @Test
        void loopDetection_ok_return_empty() {
            final List<String> value = List.of("a", "b", "c", "d", "e");
            final Optional<String> expected = Optional.empty();

            Assertions.assertEquals(expected, LinkedListExercise.loopDetection(value));
            Assertions.assertEquals(expected, LinkedListExercise.loopDetectionLambda(value));
        }
    }
}
