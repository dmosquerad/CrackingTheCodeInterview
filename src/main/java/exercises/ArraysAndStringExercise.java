package exercises;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArraysAndStringExercise {

    public static boolean isUnique(@NonNull final String value) {
        final HashSet<Character> charactersReplied = new HashSet<>();

        for (char character : value.toCharArray()) {
            if (charactersReplied.contains(character)) {
                return false;
            }
            charactersReplied.add(character);
        }
        return true;
    }

    public static boolean isUniqueLambda(@NonNull final String value) {
        final HashSet<Character> charactersReplied = new HashSet<>();

        return value.chars()
                .mapToObj(c -> (char) c)
                .allMatch(charactersReplied::add);
    }

    public static boolean checkPermutation(@NonNull final String first, @NonNull final String second) {
        final HashMap<Character, Integer> characterCount = new HashMap<>();

        for (char character : first.toCharArray()) {
            characterCount.put(character, characterCount.getOrDefault(character, 0) + 1);
        }

        for (char character : second.toCharArray()) {
            final Integer count = characterCount.getOrDefault(character, 0);

            if (count == 0) {
                return false;
            }

            characterCount.put(character, count - 1);
        }

        for (Map.Entry<Character, Integer> character : characterCount.entrySet()) {
            if (character.getValue() != 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkPermutationLambda(@NonNull final String first, @NonNull final String second) {
        final HashMap<Character, Integer> characterCount = new HashMap<>();

        first.chars()
                .mapToObj(c -> (char) c)
                .forEach(character -> characterCount.put(character, characterCount.getOrDefault(character, 0) + 1));

        second.chars()
                .mapToObj(c -> (char) c)
                .forEach(character -> characterCount.put(character, characterCount.getOrDefault(character, 0) - 1));

        return characterCount.entrySet().stream().allMatch(entry -> entry.getValue() == 0);
    }

    public static String urlify(@NonNull final String value) {
        return value.replace(" ", "%20");
    }

    public static String urlifyLambda(@NonNull final String value) {
        return String.valueOf(value.chars()
                .mapToObj(c -> (char) c)
                .reduce(new StringBuilder(), ArraysAndStringExercise::urifyCharactersLambda, StringBuilder::append));
    }

    private static StringBuilder urifyCharactersLambda(StringBuilder sb, Character character) {
        return character == ' ' ? sb.append("%20") : sb.append(character);
    }

    public static boolean isPalindromePermutation(@NonNull final String value) {
        final String valueChecker = value.replace(" ", "").toLowerCase();
        final Map<Character, Integer> characterCount = new HashMap<>();

        for (char character : valueChecker.toCharArray()) {
            characterCount.put(character, characterCount.getOrDefault(character, 0) + 1);
        }

        int oddCount = 0;
        for (Map.Entry<Character, Integer> character : characterCount.entrySet()) {
            if (character.getValue() % 2 != 0) {
                oddCount++;
            }
        }

        return oddCount <= 1;
    }

    public static boolean isPalindromePermutationLambda(@NonNull final String value) {
        final String valueChecker = value.replace(" ", "").toLowerCase();
        final Map<Character, Integer> characterCount = new HashMap<>();

        valueChecker.chars()
                .mapToObj(c -> (char) c)
                .forEach(character -> characterCount.put(character, characterCount.getOrDefault(character, 0) + 1));

        return characterCount.entrySet().stream()
                .filter(character -> character.getValue() % 2 != 0)
                .count() <= 1;
    }

    public static boolean isOneAway(@NonNull final String first, @NonNull final String second) {
        final List<Character> firstList = first.chars()
                .mapToObj(c -> (char) c).toList();

        final List<Character> secondList = second.chars()
                .mapToObj(c -> (char) c).toList();

        final List<Character> longer = firstList.size() > secondList.size() ? firstList : secondList;
        final List<Character> shorter = secondList.size() > firstList.size() ? secondList : firstList;

        boolean oneMoreChange = false;
        for (int i = 0; i < shorter.size(); i++) {
            if (!shorter.get(i).equals(longer.get(i))) {
                if (oneMoreChange) {
                    return false;
                }
                oneMoreChange = true;
            }
        }

        return true;
    }

    public static boolean isOneAwayLambda(@NonNull final String first, @NonNull final String second) {
        final List<Character> firstList = first.chars()
                .mapToObj(c -> (char) c).toList();

        final List<Character> secondList = second.chars()
                .mapToObj(c -> (char) c).toList();

        if (firstList.size() == secondList.size()) {
            return IntStream.range(0, firstList.size())
                    .filter(i -> !firstList.get(i).equals(secondList.get(i)))
                    .count() <= 1;
        }

        final List<Character> longer = firstList.size() > secondList.size() ? firstList : secondList;
        final List<Character> shorter = secondList.size() >= firstList.size() ? secondList : firstList;

        OptionalInt range = IntStream.range(0, shorter.size())
                .filter(x -> !shorter.get(x).equals(longer.get(x))).findFirst();

        if (range.isPresent()) {
            return IntStream.range(range.getAsInt(), shorter.size())
                    .allMatch(x -> shorter.get(x).equals(longer.get(x + 1)));
        }

        return true;
    }

    public static String stringCompression(@NonNull final String value) {
        if (value.isEmpty()) {
            return value;
        }

        char currentChar = value.charAt(0);
        int count = 0;
        StringBuilder compressed = new StringBuilder();

        for (char character : value.toCharArray()) {
            if (character == currentChar) {
                count++;
            }
            if (character != currentChar) {
                compressed.append(currentChar).append(count);
                currentChar = character;
                count = 1;
            }
        }

        compressed.append(currentChar).append(count);

        return compressed.length() < value.length() ? String.valueOf(compressed) : value;
    }

    public static String stringCompressionLambda(@NonNull final String value) {
        if (value.isEmpty()) {
            return value;
        }

        StringBuilder compressed = IntStream.range(0, value.length())
                .mapToObj(value::charAt)
                .reduce(new StringBuilder(), ArraysAndStringExercise::compressCharactersLambda, StringBuilder::append);

        return compressed.length() < value.length() ? String.valueOf(compressed) : value;

    }

    private static StringBuilder compressCharactersLambda(StringBuilder sb, Character character) {
        if (sb.isEmpty() || sb.charAt(sb.length() - 2) != character) {
            return sb.append(character).append(1);
        }

        int sbCount = sb.length() - 1;
        sb.setCharAt(sbCount, (char) (sb.charAt(sbCount) + 1));

        return sb;
    }

    public static List<List<Integer>> rotate90Grades(@NonNull final List<List<Integer>> value) {
        final List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < value.size(); i++) {
            for (int j = 0; j < value.get(i).size(); j++) {
                if (value.size() <= j) {
                    continue;
                }
                if (result.size() <= j) {
                    result.add(new ArrayList<>());
                }
                result.get(j).add(value.get(i).get(j));
            }
        }

        return result;
    }

    public static List<List<Integer>> rotate90GradesLambda(@NonNull final List<List<Integer>> value) {
        return IntStream.range(0, value.size()).mapToObj(i -> IntStream.range(0, value.get(i).size()).filter(j -> value.size() > j).mapToObj(j -> value.get(j).get(i)).toList()).toList();
    }

    public static List<List<Integer>> zeroMatrix(@NonNull final List<List<Integer>> value) {
        final List<List<Integer>> result = new ArrayList<>();

        for (List<Integer> innerList : value) {
            result.add(new ArrayList<>(innerList));
        }

        final Set<Integer> zeroRows = new HashSet<>();
        final Set<Integer> zeroColms = new HashSet<>();

        for (int i = 0; i < value.size(); i++) {
            for (int j = 0; j < value.get(i).size(); j++) {
                if (value.get(i).get(j) == 0) {
                    zeroRows.add(i);
                    zeroColms.add(j);
                }
            }
        }

        for (int i = 0; i < value.size(); i++) {
            for (int j = 0; j < value.get(i).size(); j++) {
                if (zeroRows.contains(i) || zeroColms.contains(j)) {
                    result.get(i).set(j, 0);
                }
            }
        }

        return result;
    }

    public static List<ArrayList<Integer>> zeroMatrixLambda(@NonNull final List<List<Integer>> value) {
        final List<ArrayList<Integer>> result = value.stream()
                .map(ArrayList::new).toList();

        final Set<Integer> zeroRows = new HashSet<>();
        final Set<Integer> zeroColms = new HashSet<>();

        IntStream.range(0, value.size())
                .forEach(i -> IntStream.range(0, value.get(i).size()).filter(j -> value.get(i).get(j) == 0)
                        .forEach(j -> {
                            zeroRows.add(i);
                            zeroColms.add(j);
                        }));

        IntStream.range(0, value.size())
                .forEach(i -> IntStream.range(0, value.get(i).size())
                        .filter(j -> zeroRows.contains(i) || zeroColms.contains(j))
                        .forEach(j -> result.get(i).set(j, 0)));

        return result;
    }

    public static boolean isRotation(@NonNull final String first, @NonNull final String second) {
        if (first.length() != second.length()) {
            return false;
        }

        return first.concat(first).contains(second);
    }

    public static boolean isRotationLambda(@NonNull final String first, @NonNull final String second) {
        if (first.length() != second.length()) {
            return false;
        }

        return Stream.of(first.concat(first))
                .anyMatch(concat -> concat.contains(second));
    }
}
