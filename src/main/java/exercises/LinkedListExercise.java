package exercises;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LinkedListExercise {

  // Exercise 2.1
  public static Collection<Integer> removeDumps(@NonNull final Collection<Integer> value) {
    final Iterator<Integer> iterator = new ArrayList<>(value).iterator();
    List<Integer> actualList = new ArrayList<>();

    while (iterator.hasNext()) {
      final Integer element = iterator.next();
      if (!actualList.contains(element)) {
        actualList.add(element);
      }
    }

    return actualList;
  }

  public static Collection<Integer> removeDumpsLambda(@NonNull final Collection<Integer> value) {
    return value.stream()
        .filter(new HashSet<Integer>()::add)
        .toList();
  }

  // Exercise 2.2
  public static Optional<Integer> returnKthToLast(@NonNull final Collection<Integer> value,
                                                  final int indexExpected) {
    if (indexExpected < 0 || value.size() <= indexExpected - 1) {
      return Optional.empty();
    }

    final List<Integer> valueList = new ArrayList<>(value);

    return Optional.ofNullable(valueList.get(value.size() - 1 - indexExpected));
  }

  public static Optional<Integer> returnKthToLastLambda(@NonNull final Collection<Integer> value,
                                                        final int indexExpected) {
    final List<Integer> valueList = new ArrayList<>(value);

    return IntStream.range(0, value.size())
        .mapToObj(i -> valueList.get(value.size() - i - 1))
        .skip(indexExpected)
        .findFirst();
  }

  // Exercise 2.3
  public static Collection<String> deleteMiddleNode(@NonNull final Collection<String> value) {
    if (value.isEmpty()) {
      return value;
    }

    final int middle = (value.size() - 1) / 2;

    final List<String> deletedMiddleNode = new ArrayList<>(value);

    deletedMiddleNode.remove(middle);

    return deletedMiddleNode;
  }

  public static Collection<String> deleteMiddleNodeLambda(@NonNull final Collection<String> value) {
    if (value.isEmpty()) {
      return value;
    }

    final List<String> valueList = new ArrayList<>(value);

    final int middle = (value.size() - 1) / 2;

    return IntStream.range(0, value.size())
        .filter(i -> i != middle)
        .mapToObj(valueList::get)
        .toList();
  }

  // Exercise 2.4
  public static Collection<Integer> partitionNumber(@NonNull final Collection<Integer> value,
                                                    final int number) {
    if (value.isEmpty()) {
      return value;
    }

    final Iterator<Integer> elements = value.iterator();
    final LinkedList<Integer> lessThan = new LinkedList<>();
    final LinkedList<Integer> greaterOrEqual = new LinkedList<>();

    while (elements.hasNext()) {
      final Integer element = elements.next();
      if (element >= number) {
        greaterOrEqual.add(element);
        continue;
      }
      lessThan.add(element);
    }

    lessThan.addAll(greaterOrEqual);
    return lessThan;
  }

  public static Collection<Integer> partitionNumberLambda(@NonNull final Collection<Integer> value,
                                                          final int number) {
    if (value.isEmpty()) {
      return value;
    }

    final LinkedList<Integer> lessThan = value.stream()
        .filter(elem -> elem < number)
        .collect(Collectors.toCollection(LinkedList::new));

    final LinkedList<Integer> greaterOrEqual = value.stream()
        .filter(elem -> elem >= number)
        .collect(Collectors.toCollection(LinkedList::new));

    lessThan.addAll(greaterOrEqual);
    return lessThan;
  }

  // Exercise 2.5
  public static Collection<Integer> sum(@NonNull final Collection<Integer> first,
                                        @NonNull final Collection<Integer> second) {
    final List<Integer> firstList = new ArrayList<>(first);
    final List<Integer> secondList = new ArrayList<>(second);

    final int firstSize = firstList.size();
    final int secondSize = secondList.size();

    final int maxSize = Math.max(firstSize, secondSize);

    List<Integer> sum = new ArrayList<>();
    int carry = 0;

    for (int i = 0; i < maxSize; i++) {
      final int firstElement = i < firstSize ? firstList.get(i) : 0;
      final int secondElement = i < secondSize ? secondList.get(i) : 0;
      final int total = firstElement + secondElement + carry;

      carry = total / 10;
      sum.add(total % 10);
    }

    if (carry > 0) {
      sum.add(carry);
    }

    return sum;
  }

  public static Collection<Integer> sumLambda(@NonNull final Collection<Integer> first,
                                              @NonNull final Collection<Integer> second) {
    final int maxSize = Math.max(first.size(), second.size());
    final List<Integer> firstList = new ArrayList<>(first);
    final List<Integer> secondList = new ArrayList<>(second);

    List<Integer> sum = IntStream.range(0, maxSize)
        .mapToObj(i -> {
          final int firstElement = (i < firstList.size()) ? firstList.get(i) : 0;
          final int secondElement = (i < secondList.size()) ? secondList.get(i) : 0;

          return firstElement + secondElement;
        })
        .collect(Collectors.toList());

    AtomicInteger carry = new AtomicInteger(0);

    sum.replaceAll(i -> {
      final int total = carry.get() + i;
      carry.set(total / 10);
      return total % 10;
    });

    if (carry.get() > 0) {
      sum.add(carry.get());
    }

    return sum;
  }

  public static Collection<Integer> sumReverse(@NonNull final Collection<Integer> first,
                                               @NonNull final Collection<Integer> second) {
    final List<Integer> firstList = new ArrayList<>(first);
    final List<Integer> secondList = new ArrayList<>(second);

    final int firstSize = firstList.size();
    final int secondSize = secondList.size();
    final int maxSize = Math.max(firstSize, secondSize);

    List<Integer> sum = new ArrayList<>();
    int carry = 0;

    for (int i = 0; i < maxSize; i++) {
      final int index = maxSize - 1 - i;

      final int firstElement = index < firstSize ? firstList.get(index) : 0;
      final int secondElement = index < secondSize ? secondList.get(index) : 0;
      final int total = firstElement + secondElement + carry;

      carry = total / 10;
      sum.addFirst(total % 10);
    }

    if (carry > 0) {
      sum.addFirst(carry);
    }

    return sum;
  }

  public static Collection<Integer> sumReverseLambda(@NonNull final Collection<Integer> first,
                                                     @NonNull final Collection<Integer> second) {
    final List<Integer> firstList = new ArrayList<>(first);
    final List<Integer> secondList = new ArrayList<>(second);
    int maxSize = Math.max(firstList.size(), secondList.size());

    List<Integer> sum = IntStream.range(0, maxSize)
        .mapToObj(i -> {
          final int index = maxSize - 1 - i;
          final int firstElement = (index < first.size()) ? firstList.get(index) : 0;
          final int secondElement = (index < second.size()) ? secondList.get(index) : 0;

          return firstElement + secondElement;
        }).collect(Collectors.toList());

    AtomicInteger carry = new AtomicInteger(0);

    sum.replaceAll(i -> {
      final int total = carry.get() + i;
      carry.set(total / 10);
      return total % 10;
    });

    if (carry.get() > 0) {
      sum.addLast(carry.get());
    }

    Collections.reverse(sum);

    return sum;
  }

  // Exercise 2.6
  public static boolean isPalindrome(@NonNull final Collection<Integer> value) {
    final List<Integer> valueList = new ArrayList<>(value);

    final ListIterator<Integer> forwardElements = valueList.listIterator();
    final ListIterator<Integer> backwardElements = valueList.listIterator(value.size());

    while (forwardElements.nextIndex() < backwardElements.previousIndex()) {
      if (!Objects.equals(forwardElements.next(), backwardElements.previous())) {
        return false;
      }

    }

    return true;
  }

  public static boolean isPalindromeLambda(@NonNull final Collection<Integer> value) {
    final List<Integer> valueList = new ArrayList<>(value);
    final int size = valueList.size();

    return IntStream.range(0, size / 2)
        .allMatch(i -> Objects.equals(valueList.get(i), valueList.get(size - i - 1)));
  }

  // Exercise 2.7
  public static Optional<Integer> intersectionMerge(@NonNull final Collection<Integer> first,
                                                    @NonNull final Collection<Integer> second) {
    if (first.isEmpty() || second.isEmpty()) {
      return Optional.empty();
    }

    final List<Integer> lessThan = new ArrayList<>(first.size() < second.size() ? first : second);
    final List<Integer> greaterEqualThan =
        new ArrayList<>(first.size() >= second.size() ? first : second);

    for (int i = 0; i < greaterEqualThan.size(); i++) {
      Integer element = greaterEqualThan.get(i);
      if (lessThan.contains(element)) {
        List<Integer> greaterEqualThanSublist =
            greaterEqualThan.subList(i, greaterEqualThan.size());
        List<Integer> lessThanSublist =
            lessThan.subList(lessThan.indexOf(element), lessThan.size());
        if (lessThanSublist.equals(greaterEqualThanSublist)) {
          return Optional.of(greaterEqualThan.get(i));
        }
      }
      if (greaterEqualThan.size() - i == lessThan.size()) {
        lessThan.removeFirst();
      }
    }

    return Optional.empty();
  }

  public static Optional<Integer> intersectionMergeLambda(@NonNull final Collection<Integer> first,
                                                          @NonNull
                                                          final Collection<Integer> second) {
    if (first.isEmpty() || second.isEmpty()) {
      return Optional.empty();
    }

    final List<Integer> lessThan = new ArrayList<>(first.size() < second.size() ? first : second);
    final List<Integer> greaterEqualThan =
        new ArrayList<>(first.size() >= second.size() ? first : second);

    List<Integer> lessThanInverse = IntStream.range(0, lessThan.size())
        .mapToObj(i -> lessThan.get(lessThan.size() - 1 - i))
        .toList();

    List<Integer> greaterEqualThanInverse = IntStream.range(0, greaterEqualThan.size())
        .mapToObj(i -> greaterEqualThan.get(greaterEqualThan.size() - 1 - i))
        .toList();

    Optional<Integer> mergeNode = IntStream.range(0, greaterEqualThanInverse.size())
        .filter(i -> !greaterEqualThanInverse.get(i).equals(lessThanInverse.get(i)))
        .mapToObj(i -> greaterEqualThanInverse.get(i - 1))
        .findFirst();

    if (mergeNode.isEmpty()) {
      return greaterEqualThan.stream().findFirst();
    } else {
      return mergeNode;
    }
  }

  // Exercise 2.8
  public static Optional<String> loopDetection(@NonNull final Collection<String> value) {
    Set<String> appears = new HashSet<>();

    for (String element : value) {
      if (appears.contains(element)) {
        return Optional.of(element);
      }
      appears.add(element);
    }

    return Optional.empty();
  }

  public static Optional<String> loopDetectionLambda(@NonNull final Collection<String> value) {
    final Set<String> appears = new HashSet<>();

    return value.stream()
        .filter(i -> !appears.add(i))
        .findFirst();
  }
}
