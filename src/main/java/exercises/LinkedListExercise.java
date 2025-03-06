package exercises;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LinkedListExercise {

    public static List<Integer> removeDumps(@NonNull final List<Integer> value) {
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

    public static List<Integer> removeDumpsLambda(@NonNull final List<Integer> value) {
        return value.stream()
                .filter(new HashSet<Integer>()::add)
                .toList();
    }

    public static Optional<Integer> returnKthToLast(@NonNull final List<Integer> value, @NonNull Integer indexExpected) {
        if (indexExpected < 0 || value.size() <= indexExpected - 1) {
            return Optional.empty();
        }

        return Optional.of(value.get(value.size() - 1 - indexExpected));
    }

    public static Optional<Integer> returnKthToLastLambda(@NonNull final List<Integer> value, @NonNull Integer indexExpected) {
        return IntStream.range(0, value.size())
                .mapToObj(i -> value.get(value.size() - i - 1))
                .skip(indexExpected)
                .findFirst();
    }

    public static List<String> deleteMiddleNode(@NonNull final List<String> value) {
        if (value.isEmpty()) {
            return value;
        }

        final int middle = (value.size() - 1) / 2;

        final List<String> deletedMiddleNode = new ArrayList<>(value);

        deletedMiddleNode.remove(middle);

        return deletedMiddleNode;
    }

    public static List<String> deleteMiddleNodeLambda(@NonNull final List<String> value) {
        if (value.isEmpty()) {
            return value;
        }

        final int middle = (value.size() - 1) / 2;

        return IntStream.range(0, value.size())
                .filter(i -> i != middle)
                .mapToObj(value::get)
                .toList();
    }

    public static List<Integer> partitionNumber(@NonNull final List<Integer> value, @NonNull final Integer number) {
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

    public static List<Integer> partitionNumberLambda(@NonNull final List<Integer> value, @NonNull  Integer number) {
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

    public static boolean isPalindrome(@NonNull final List<Integer> value) {
        final ListIterator<Integer> forwardElements = value.listIterator();
        final ListIterator<Integer> backwardElements = value.listIterator(value.size());

        while (forwardElements.nextIndex() < backwardElements.previousIndex()) {
            if (!Objects.equals(forwardElements.next(), backwardElements.previous())) {
                return false;
            }

        }

        return true;
    }

    public static boolean isPalindromeLambda(@NonNull final List<Integer> value) {
        final int size = value.size();

        return IntStream.range(0, size / 2)
                .allMatch(i -> Objects.equals(value.get(i), value.get(size - i - 1)));
    }

    public static Optional<Integer> intersectionMerge(@NonNull final List<Integer> first, @NonNull final List<Integer> second) {
        if (first.isEmpty() || second.isEmpty()) {
            return Optional.empty();
        }

        final List<Integer> lessThan = new ArrayList<>(first.size() < second.size() ? first : second);
        final List<Integer> greaterEqualThan = new ArrayList<>(first.size() >= second.size() ? first : second);

        for (int i = 0; i < greaterEqualThan.size(); i++) {
            Integer element = greaterEqualThan.get(i);
            if (lessThan.contains(element)) {
                List<Integer> greaterEqualThanSublist = greaterEqualThan.subList(i, greaterEqualThan.size());
                List<Integer> lessThanSublist = lessThan.subList(lessThan.indexOf(element), lessThan.size());
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

    public static Optional<Integer> intersectionMergeLambda(@NonNull final List<Integer> first, @NonNull final List<Integer> second) {
        if (first.isEmpty() || second.isEmpty()) {
            return Optional.empty();
        }

        final List<Integer> lessThan = new ArrayList<>(first.size() < second.size() ? first : second);
        final List<Integer> greaterEqualThan = new ArrayList<>(first.size() >= second.size() ? first : second);

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

        if (mergeNode.isEmpty()){
            return greaterEqualThan.stream().findFirst();
        } else {
            return mergeNode;
        }
    }

    public static Optional<String> loopDetection(@NonNull final List<String> value) {
        Set<String> appears = new HashSet<>();

        for (String element : value) {
            if (appears.contains(element)) {
                return Optional.of(element);
            }
            appears.add(element);
        }

        return Optional.empty();
    }

    public static Optional<String> loopDetectionLambda(@NonNull final List<String> value) {
        final Set<String> appears = new HashSet<>();

        return value.stream()
                .filter(i -> !appears.add(i))
                .findFirst();
    }
}
