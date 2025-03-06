package exercises;

import static exercises.LinkedListExercise.deleteMiddleNode;
import static exercises.LinkedListExercise.deleteMiddleNodeLambda;
import static exercises.LinkedListExercise.intersectionMerge;
import static exercises.LinkedListExercise.intersectionMergeLambda;
import static exercises.LinkedListExercise.isPalindrome;
import static exercises.LinkedListExercise.isPalindromeLambda;
import static exercises.LinkedListExercise.loopDetection;
import static exercises.LinkedListExercise.loopDetectionLambda;
import static exercises.LinkedListExercise.partitionNumber;
import static exercises.LinkedListExercise.partitionNumberLambda;
import static exercises.LinkedListExercise.removeDumps;
import static exercises.LinkedListExercise.removeDumpsLambda;
import static exercises.LinkedListExercise.returnKthToLast;
import static exercises.LinkedListExercise.returnKthToLastLambda;
import static exercises.LinkedListExercise.sum;
import static exercises.LinkedListExercise.sumLambda;
import static exercises.LinkedListExercise.sumReverse;
import static exercises.LinkedListExercise.sumReverseLambda;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LinkedListExerciseTest {

  // Exercise 2.1
  @Nested
  class RemoveDumpsTest {
    @Test
    void removeDumps_nullPointerException() {
      final List<Integer> value = null;

      Assertions.assertThrows(NullPointerException.class, () -> removeDumps(value));
      Assertions.assertThrows(NullPointerException.class, () -> removeDumpsLambda(value));
    }

    @Test
    void removeDumps_empty() {
      final List<Integer> value = Collections.emptyList();

      Assertions.assertEquals(value, removeDumps(value));
      Assertions.assertEquals(value, removeDumpsLambda(value));
    }

    @Test
    void removeDumps_ok() {
      final List<Integer> actual = List.of(2, 3, 1, 2, 3, 4);
      final List<Integer> expected = List.of(2, 3, 1, 4);

      Assertions.assertEquals(expected, removeDumps(actual));
      Assertions.assertEquals(expected, removeDumpsLambda(actual));
    }
  }

  // Exercise 2.2
  @Nested
  class ReturnKthToLastTest {
    @Test
    void returnKthToLast_nullPointerException() {
      final List<Integer> value = null;
      final int index = 1;

      Assertions.assertThrows(NullPointerException.class, () -> returnKthToLast(value, index));
      Assertions.assertThrows(NullPointerException.class,
          () -> returnKthToLastLambda(value, index));
    }

    @Test
    void returnKthToLast_empty() {
      final List<Integer> value = Collections.emptyList();
      final int indexExpected = 1;
      final Optional<Integer> expected = Optional.empty();

      Assertions.assertEquals(expected, returnKthToLast(value, indexExpected));
      Assertions.assertEquals(expected, returnKthToLastLambda(value, indexExpected));
    }

    @Test
    void returnKthToLast_ok_index() {
      final List<Integer> value = List.of(2, 3, 1, 2, 3, 4);
      final int indexExpected = 2;
      final Optional<Integer> expected = Optional.of(2);

      Assertions.assertEquals(expected, returnKthToLast(value, indexExpected));
      Assertions.assertEquals(expected, returnKthToLastLambda(value, indexExpected));
    }

    @Test
    void returnKthToLast_ok_index0() {
      final List<Integer> value = List.of(2, 3, 1, 2, 3, 4);
      final int indexExpected = 0;
      final Optional<Integer> expected = Optional.of(4);

      Assertions.assertEquals(expected, returnKthToLast(value, indexExpected));
      Assertions.assertEquals(expected, returnKthToLastLambda(value, indexExpected));
    }
  }

  // Exercise 2.3
  @Nested
  class DeleteMiddleNodeTest {
    @Test
    void deleteMiddleNode_nullPointerException() {
      final List<String> value = null;

      Assertions.assertThrows(NullPointerException.class, () -> deleteMiddleNode(value));
      Assertions.assertThrows(NullPointerException.class, () -> deleteMiddleNodeLambda(value));
    }

    @Test
    void deleteMiddleNode_empty() {
      final List<String> value = Collections.emptyList();
      final List<String> expected = Collections.emptyList();

      Assertions.assertEquals(expected, deleteMiddleNode(value));
      Assertions.assertEquals(expected, deleteMiddleNodeLambda(value));
    }

    @Test
    void deleteMiddleNode_ok_pair() {
      final List<String> value = List.of("a", "b", "c", "d", "e", "f");
      final List<String> expected = List.of("a", "b", "d", "e", "f");

      Assertions.assertEquals(expected, deleteMiddleNode(value));
      Assertions.assertEquals(expected, deleteMiddleNodeLambda(value));
    }

    @Test
    void deleteMiddleNode_ok_impair() {
      final List<String> value = List.of("a", "b", "c", "d", "e");
      final List<String> expected = List.of("a", "b", "d", "e");

      Assertions.assertEquals(expected, deleteMiddleNode(value));
      Assertions.assertEquals(expected, deleteMiddleNodeLambda(value));
    }
  }

  // Exercise 2.4
  @Nested
  class PartitionNumberTest {
    @Test
    void partitionNumber_nullPointerException() {
      final List<Integer> value = null;
      final int number = 1;

      Assertions.assertThrows(NullPointerException.class, () -> partitionNumber(value, number));
      Assertions.assertThrows(NullPointerException.class,
          () -> partitionNumberLambda(value, number));
    }

    @Test
    void partitionNumber_empty() {
      final List<Integer> value = Collections.emptyList();
      final int number = 1;

      Assertions.assertEquals(value, partitionNumber(value, number));
      Assertions.assertEquals(value, partitionNumberLambda(value, number));
    }

    @Test
    void partitionNumber_ok_index() {
      final List<Integer> value = List.of(2, 3, 1, 2, 3, 4);
      final int number = 3;
      final List<Integer> expected = List.of(2, 1, 2, 3, 3, 4);

      Assertions.assertEquals(expected, partitionNumber(value, number));
      Assertions.assertEquals(expected, partitionNumberLambda(value, number));
    }
  }

  // Exercise 2.5
  @Nested
  class SumTest {
    @Test
    void sum_nullPointerException() {
      final List<Integer> first = null;
      final List<Integer> second = null;

      Assertions.assertThrows(NullPointerException.class, () -> sum(first, second));
      Assertions.assertThrows(NullPointerException.class, () -> sumLambda(first, second));
    }

    @Test
    void sum_empty() {
      final List<Integer> first = Collections.emptyList();
      final List<Integer> second = Collections.emptyList();

      final List<Integer> expected = Collections.emptyList();

      Assertions.assertEquals(expected, sum(first, second));
      Assertions.assertEquals(expected, sumLambda(first, second));
    }

    @Test
    void sum_with_carry() {
      final List<Integer> first = Collections.singletonList(5);
      final List<Integer> second = Collections.singletonList(8);

      final List<Integer> expected = List.of(3, 1);

      Assertions.assertEquals(expected, sum(first, second));
      Assertions.assertEquals(expected, sumLambda(first, second));
    }

    @Test
    void sum_without_last_carry() {
      final List<Integer> first = List.of(7, 1, 6);
      final List<Integer> second = List.of(5, 9, 2);

      final List<Integer> expected = List.of(2, 1, 9);

      Assertions.assertEquals(expected, sum(first, second));
      Assertions.assertEquals(expected, sumLambda(first, second));
    }
  }

  @Nested
  class SumReverseTest {
    @Test
    void sumReverse_nullPointerException() {
      final List<Integer> first = null;
      final List<Integer> second = null;

      Assertions.assertThrows(NullPointerException.class, () -> sumReverse(first, second));
      Assertions.assertThrows(NullPointerException.class, () -> sumReverseLambda(first, second));
    }

    @Test
    void sumReverse_empty() {
      final List<Integer> first = Collections.emptyList();
      final List<Integer> second = Collections.emptyList();

      final List<Integer> expected = Collections.emptyList();

      Assertions.assertEquals(expected, sumReverse(first, second));
      Assertions.assertEquals(expected, sumReverseLambda(first, second));
    }

    @Test
    void sumReverse_with_carry() {
      final List<Integer> first = Collections.singletonList(5);
      final List<Integer> second = Collections.singletonList(8);

      final List<Integer> expected = List.of(1, 3);

      Assertions.assertEquals(expected, sumReverse(first, second));
      Assertions.assertEquals(expected, sumReverseLambda(first, second));
    }

    @Test
    void sumReverse_without_last_carry() {
      final List<Integer> first = List.of(6, 1, 7);
      final List<Integer> second = List.of(2, 9, 5);

      final List<Integer> expected = List.of(9, 1, 2);

      Assertions.assertEquals(expected, sumReverse(first, second));
      Assertions.assertEquals(expected, sumReverseLambda(first, second));
    }
  }

  // Exercise 2.6
  @Nested
  class IsPalindromeTest {
    @Test
    void isPalindrome_nullPointerException() {
      final List<Integer> value = null;

      Assertions.assertThrows(NullPointerException.class, () -> isPalindrome(value));
      Assertions.assertThrows(NullPointerException.class, () -> isPalindromeLambda(value));
    }

    @Test
    void isUnique_empty() {
      final List<Integer> value = Collections.emptyList();

      Assertions.assertTrue(isPalindrome(value));
      Assertions.assertTrue(isPalindromeLambda(value));
    }

    @Test
    void isUnique_false() {
      final List<Integer> value = List.of(1, 3, 2, 1);

      Assertions.assertFalse(isPalindrome(value));
      Assertions.assertFalse(isPalindromeLambda(value));
    }

    @Test
    void isUnique_true() {
      final List<Integer> value = List.of(1, 2, 3, 2, 1);

      Assertions.assertTrue(isPalindrome(value));
      Assertions.assertTrue(isPalindromeLambda(value));
    }
  }

  // Exercise 2.7
  @Nested
  class IntersectionMergeTest {
    @Test
    void intersectionMerge_nullPointerException() {
      final List<Integer> value = null;

      Assertions.assertThrows(NullPointerException.class, () -> intersectionMerge(value, value));
      Assertions.assertThrows(NullPointerException.class,
          () -> intersectionMergeLambda(value, value));
    }

    @Test
    void intersectionMerge_empty() {
      final List<Integer> value = Collections.emptyList();
      final Optional<Integer> expected = Optional.empty();

      Assertions.assertEquals(expected, intersectionMerge(value, value));
      Assertions.assertEquals(expected, intersectionMergeLambda(value, value));
    }

    @Test
    void intersectionMerge_ok_return_value() {
      final List<Integer> first = List.of(2, 2, 4, 2, 1);
      final List<Integer> second = List.of(2, 3, 4, 2, 1);
      final Optional<Integer> expected = Optional.of(4);

      Assertions.assertEquals(expected, intersectionMerge(first, second));
      Assertions.assertEquals(expected, intersectionMergeLambda(first, second));
    }

    @Test
    void intersectionMerge_ok_return_first() {
      final List<Integer> value = List.of(1, 2);
      final Optional<Integer> expected = Optional.of(1);

      Assertions.assertEquals(expected, intersectionMerge(value, value));
      Assertions.assertEquals(expected, intersectionMergeLambda(value, value));
    }
  }

  // Exercise 2.8
  @Nested
  class LoopDetectionTest {
    @Test
    void loopDetection_nullPointerException() {
      final List<String> value = null;

      Assertions.assertThrows(NullPointerException.class, () -> loopDetection(value));
      Assertions.assertThrows(NullPointerException.class, () -> loopDetectionLambda(value));
    }

    @Test
    void loopDetection_empty() {
      final List<String> value = Collections.emptyList();
      final Optional<String> expected = Optional.empty();

      Assertions.assertEquals(expected, loopDetection(value));
      Assertions.assertEquals(expected, loopDetectionLambda(value));
    }

    @Test
    void loopDetection_ok_return_value() {
      final List<String> value = List.of("a", "b", "c", "d", "e", "c");
      final Optional<String> expected = Optional.of("c");

      Assertions.assertEquals(expected, loopDetection(value));
      Assertions.assertEquals(expected, loopDetectionLambda(value));
    }

    @Test
    void loopDetection_ok_return_empty() {
      final List<String> value = List.of("a", "b", "c", "d", "e");
      final Optional<String> expected = Optional.empty();

      Assertions.assertEquals(expected, loopDetection(value));
      Assertions.assertEquals(expected, loopDetectionLambda(value));
    }
  }
}
