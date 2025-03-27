package exercises;

import static exercises.StackAndQueueExercise.generateStackList;
import static exercises.StackAndQueueExercise.generateStackListLambda;
import static exercises.StackAndQueueExercise.sort;
import static exercises.StackAndQueueExercise.sortLambda;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StackAndQueueExerciseTest {

  @Nested
  class StackListTest {

  // Exercise 3.1
    @Nested
    class GenerateTest {
      @Test
      void generate_nullPointerException() {
        final int totalStack = 1;
        final List<Integer> stack = null;

        Assertions.assertThrows(NullPointerException.class,
            () -> generateStackList(stack, totalStack));
        Assertions.assertThrows(NullPointerException.class,
            () -> generateStackListLambda(stack, totalStack));
      }

      @Test
      void generate_illegalArgumentException() {
        final int totalStack = 0;
        final List<Integer> stack = List.of(1, 2);

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> generateStackList(stack, totalStack));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> generateStackListLambda(stack, totalStack));
      }

      @Test
      void generate_moreElementsThanStacks() {
        final int totalStack = 1;
        final List<Integer> stack = List.of(1, 2);
        final StackAndQueueExercise.StackList expected = StackAndQueueExercise.StackList.builder()
            .stackMatrix(Collections.singletonList(stack))
            .build();

        StackAndQueueExercise.StackList stackList = generateStackList(stack, totalStack);
        StackAndQueueExercise.StackList stackListLambda =
            generateStackListLambda(stack, totalStack);

        Assertions.assertEquals(expected, stackList);
        Assertions.assertEquals(expected, stackListLambda);
      }

      @Test
      void generate_minorElementsThanStacks() {
        final int totalStack = 2;
        final List<Integer> stack = Collections.singletonList(1);
        final StackAndQueueExercise.StackList expected = StackAndQueueExercise.StackList.builder()
            .stackMatrix(List.of(stack, Collections.emptyList()))
            .build();

        StackAndQueueExercise.StackList stackList = generateStackList(stack, totalStack);
        StackAndQueueExercise.StackList stackListLambda =
            generateStackListLambda(stack, totalStack);

        Assertions.assertEquals(expected, stackList);
        Assertions.assertEquals(expected, stackListLambda);
      }

      @Test
      void generate_equalElementsThanStacks() {
        final int totalStack = 1;
        final List<Integer> stack = Collections.singletonList(1);
        final StackAndQueueExercise.StackList expected = StackAndQueueExercise.StackList.builder()
            .stackMatrix(Collections.singletonList(stack))
            .build();

        StackAndQueueExercise.StackList stackList = generateStackList(stack, totalStack);
        StackAndQueueExercise.StackList stackListLambda =
            generateStackListLambda(stack, totalStack);

        Assertions.assertEquals(expected, stackList);
        Assertions.assertEquals(expected, stackListLambda);
      }
    }

    @Nested
    class PushTest {
      StackAndQueueExercise.StackList stackList;

      final List<List<Integer>> STACK_MATRIX = new ArrayList<>(
          Arrays.asList(new ArrayList<>(Collections.singletonList(1)), new ArrayList<>()));

      @BeforeEach
      void setUp() {
        stackList = StackAndQueueExercise.StackList.builder()
            .stackMatrix(STACK_MATRIX)
            .build();
      }

      @Test
      void push_illegalArgumentException() {
        final int currentStack = 3;
        final int value = 1;

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> stackList.push(value, currentStack));
      }

      @Test
      void push_with_element_ok() {
        final int currentStack = 1;
        final int value = 3;
        final List<Integer> expected = List.of(1, value);

        stackList.push(value, currentStack);

        Assertions.assertEquals(expected, stackList.queue(currentStack));
      }

      @Test
      void push_without_element_ok() {
        final int currentStack = 2;
        final int value = 3;
        final List<Integer> expected = List.of(value);

        stackList.push(value, currentStack);

        Assertions.assertEquals(expected, stackList.queue(currentStack));
      }
    }

    @Nested
    class PeekTest {
      static StackAndQueueExercise.StackList stackList;

      static final List<List<Integer>> STACK_MATRIX = new ArrayList<>(
          Arrays.asList(new ArrayList<>(Collections.singletonList(1)), new ArrayList<>()));

      @BeforeAll
      static void setUp() {
        stackList = StackAndQueueExercise.StackList.builder()
            .stackMatrix(STACK_MATRIX)
            .build();
      }

      @Test
      void peek_illegalArgumentException() {
        final int currentStack = 3;

        Assertions.assertThrows(IllegalArgumentException.class, () -> stackList.peek(currentStack));
      }

      @Test
      void peek_with_element_ok() {
        final int currentStack = 1;
        final Optional<Integer> expected = Optional.of(1);
        final StackAndQueueExercise.StackList expectedList =
            StackAndQueueExercise.StackList.builder()
                .stackMatrix(STACK_MATRIX)
                .build();

        Assertions.assertEquals(expected, stackList.peek(currentStack));
        Assertions.assertEquals(expectedList, stackList);
      }

      @Test
      void peek_without_element_ok() {
        final int currentStack = 2;
        final Optional<Integer> expected = Optional.empty();
        final StackAndQueueExercise.StackList expectedList =
            StackAndQueueExercise.StackList.builder()
                .stackMatrix(STACK_MATRIX)
                .build();

        Assertions.assertEquals(expected, stackList.peek(currentStack));
        Assertions.assertEquals(expectedList, stackList);
      }
    }

    @Nested
    class IsEmptyTest {
      static StackAndQueueExercise.StackList stackList;

      static final List<List<Integer>> STACK_MATRIX = new ArrayList<>(
          Arrays.asList(new ArrayList<>(Collections.singletonList(1)), new ArrayList<>()));

      @BeforeAll
      static void setUp() {
        stackList = StackAndQueueExercise.StackList.builder()
            .stackMatrix(STACK_MATRIX)
            .build();
      }

      @Test
      void isEmpty_illegalArgumentException() {
        final int currentStack = 3;

        Assertions.assertThrows(IllegalArgumentException.class,
            () -> stackList.isEmpty(currentStack));
      }

      @Test
      void isEmpty_false() {
        final int currentStack = 1;
        final StackAndQueueExercise.StackList expectedList =
            StackAndQueueExercise.StackList.builder()
                .stackMatrix(STACK_MATRIX)
                .build();

        Assertions.assertFalse(stackList.isEmpty(currentStack));
        Assertions.assertEquals(expectedList, stackList);
      }

      @Test
      void isEmpty_true() {
        final int currentStack = 2;
        final StackAndQueueExercise.StackList expectedList =
            StackAndQueueExercise.StackList.builder()
                .stackMatrix(STACK_MATRIX)
                .build();

        Assertions.assertTrue(stackList.isEmpty(currentStack));
        Assertions.assertEquals(expectedList, stackList);
      }
    }

    @Nested
    class PopTest {
      static StackAndQueueExercise.StackList stackList;

      static final List<List<Integer>> STACK_MATRIX = new ArrayList<>(
          Arrays.asList(new ArrayList<>(Collections.singletonList(1)), new ArrayList<>()));

      @BeforeAll
      static void setUp() {
        stackList = StackAndQueueExercise.StackList.builder()
            .stackMatrix(STACK_MATRIX)
            .build();
      }

      @Test
      void pop_illegalArgumentException() {
        final int currentStack = 3;

        Assertions.assertThrows(IllegalArgumentException.class, () -> stackList.pop(currentStack));
      }

      @Test
      void pop_with_element_ok() {
        final int currentStack = 1;
        final Optional<Integer> expected = Optional.of(1);
        final StackAndQueueExercise.StackList expectedList =
            StackAndQueueExercise.StackList.builder()
                .stackMatrix(List.of(Collections.emptyList(), Collections.emptyList()))
                .build();

        Assertions.assertEquals(expected, stackList.pop(currentStack));
        Assertions.assertEquals(expectedList, stackList);
      }

      @Test
      void pop_without_element_ok() {
        final int currentStack = 2;
        final Optional<Integer> expected = Optional.empty();
        final StackAndQueueExercise.StackList expectedList =
            StackAndQueueExercise.StackList.builder()
                .stackMatrix(STACK_MATRIX)
                .build();

        Assertions.assertEquals(expected, stackList.pop(currentStack));
        Assertions.assertEquals(expectedList, stackList);
      }
    }

    @Nested
    class MinTest {

      static StackAndQueueExercise.StackList stackList;

      static final List<List<Integer>> STACK_MATRIX = new ArrayList<>(
          Arrays.asList(new ArrayList<>(Collections.singletonList(1)), new ArrayList<>()));

      @BeforeAll
      static void setUp() {
        stackList = StackAndQueueExercise.StackList.builder()
            .stackMatrix(STACK_MATRIX)
            .build();
      }

      @Test
      void min_illegalArgumentException() {
        final int currentStack = 3;

        Assertions.assertThrows(IllegalArgumentException.class, () -> stackList.min(currentStack));
      }

      @Test
      void min_with_element_ok() {
        final int currentStack = 1;
        final Optional<Integer> expected = Optional.of(1);
        final StackAndQueueExercise.StackList expectedList =
            StackAndQueueExercise.StackList.builder()
                .stackMatrix(STACK_MATRIX)
                .build();

        Assertions.assertEquals(expected, stackList.min(currentStack));
        Assertions.assertEquals(expectedList, stackList);
      }

      @Test
      void min_without_element_ok() {
        final int currentStack = 2;
        final Optional<Integer> expected = Optional.empty();
        final StackAndQueueExercise.StackList expectedList =
            StackAndQueueExercise.StackList.builder()
                .stackMatrix(STACK_MATRIX)
                .build();

        Assertions.assertEquals(expected, stackList.min(currentStack));
        Assertions.assertEquals(expectedList, stackList);
      }
    }
  }

  // Exercise 3.2
  @Nested
  class StackTest {

    @Nested
    class PushTest {
      @Test
      void push_with_element_ok() {
        final StackAndQueueExercise.Stack stack = new StackAndQueueExercise.Stack();
        final int value = 1;

        stack.push(value);

        Assertions.assertEquals(Collections.singletonList(value), stack.queue());
      }
    }

    @Nested
    class PeekTest {
      @Test
      void peek_with_element_ok() {
        final StackAndQueueExercise.Stack stack = new StackAndQueueExercise.Stack();
        final int value = 1;

        stack.push(value);

        Assertions.assertEquals(Optional.of(value), stack.peek());
        Assertions.assertEquals(Collections.singletonList(value), stack.queue());
      }

      @Test
      void peek_without_element_ok() {
        final StackAndQueueExercise.Stack stack = new StackAndQueueExercise.Stack();

        Assertions.assertEquals(Optional.empty(), stack.peek());
        Assertions.assertEquals(Collections.emptyList(), stack.queue());
      }
    }

    @Nested
    class IsEmptyTest {
      @Test
      void isEmpty_false() {
        final StackAndQueueExercise.Stack stack = new StackAndQueueExercise.Stack();

        Assertions.assertTrue(stack.isEmpty());
        Assertions.assertEquals(Collections.emptyList(), stack.queue());
      }

      @Test
      void isEmpty_true() {
        final StackAndQueueExercise.Stack stack = new StackAndQueueExercise.Stack();
        final int value = 1;

        stack.push(value);

        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertEquals(Collections.singletonList(value), stack.queue());
      }
    }

    @Nested
    class PopTest {
      @Test
      void pop_with_element_ok() {
        final StackAndQueueExercise.Stack stack = new StackAndQueueExercise.Stack();
        final int value = 1;

        stack.push(value);

        Assertions.assertEquals(Optional.of(value), stack.pop());
        Assertions.assertEquals(Collections.emptyList(), stack.queue());
      }

      @Test
      void pop_without_element_ok() {
        final StackAndQueueExercise.Stack stack = new StackAndQueueExercise.Stack();

        Assertions.assertEquals(Optional.empty(), stack.pop());
        Assertions.assertEquals(Collections.emptyList(), stack.queue());
      }
    }

    @Nested
    class MinTest {
      @Test
      void min_with_element_ok() {
        final StackAndQueueExercise.Stack stack = new StackAndQueueExercise.Stack();
        final int minValue = 1;
        final int value = 2;

        stack.push(minValue);
        stack.push(value);

        Assertions.assertEquals(Optional.of(minValue), stack.min());
        Assertions.assertEquals(List.of(minValue, value), stack.queue());
      }

      @Test
      void min_without_element_ok() {
        final StackAndQueueExercise.Stack stack = new StackAndQueueExercise.Stack();

        Assertions.assertEquals(Optional.empty(), stack.min());
        Assertions.assertEquals(Collections.emptyList(), stack.queue());
      }
    }
  }

  // Exercise 3.3
  @Nested
  class SetOfStacksTest {

    @Nested
    class PushTest {
      @Test
      void push_with_element_ok() {
        final StackAndQueueExercise.SetOfStacks setOfStacks =
            new StackAndQueueExercise.SetOfStacks();
        final int value = 1;

        setOfStacks.push(value);

        Assertions.assertEquals(Collections.singletonList(value), setOfStacks.queue());
      }

      @Test
      void push_with_elements_ok() {
        final StackAndQueueExercise.SetOfStacks setOfStacks =
            new StackAndQueueExercise.SetOfStacks();
        final int value = 1;
        final int value2 = 2;

        setOfStacks.push(value);
        setOfStacks.push(value2);

        Assertions.assertEquals(List.of(value, value2), setOfStacks.queue());
      }
    }

    @Nested
    class PeekTest {
      @Test
      void peek_with_element_ok() {
        final StackAndQueueExercise.SetOfStacks setOfStacks =
            new StackAndQueueExercise.SetOfStacks();
        final int value = 1;

        setOfStacks.push(value);

        Assertions.assertEquals(Optional.of(value), setOfStacks.peek());
        Assertions.assertEquals(Collections.singletonList(value), setOfStacks.queue());
      }

      @Test
      void peek_without_element_ok() {
        final StackAndQueueExercise.SetOfStacks setOfStacks =
            new StackAndQueueExercise.SetOfStacks();

        Assertions.assertEquals(Optional.empty(), setOfStacks.peek());
      }
    }

    @Nested
    class IsEmptyTest {
      @Test
      void isEmpty_false() {
        final StackAndQueueExercise.SetOfStacks setOfStacks =
            new StackAndQueueExercise.SetOfStacks();

        Assertions.assertTrue(setOfStacks.isEmpty());
      }

      @Test
      void isEmpty_true() {
        final StackAndQueueExercise.SetOfStacks setOfStacks =
            new StackAndQueueExercise.SetOfStacks();
        final int value = 1;

        setOfStacks.push(value);

        Assertions.assertFalse(setOfStacks.isEmpty());
        Assertions.assertEquals(Collections.singletonList(value), setOfStacks.queue());
      }
    }

    @Nested
    class PopTest {
      @Test
      void pop_with_element_ok() {
        final StackAndQueueExercise.SetOfStacks setOfStacks =
            new StackAndQueueExercise.SetOfStacks();
        final int value = 1;
        final int value2 = 2;

        setOfStacks.push(value);
        setOfStacks.push(value2);

        Assertions.assertEquals(Optional.of(value2), setOfStacks.pop());
        Assertions.assertEquals(Collections.singletonList(value), setOfStacks.queue());
      }

      @Test
      void pop_without_element_ok() {
        final StackAndQueueExercise.SetOfStacks setOfStacks =
            new StackAndQueueExercise.SetOfStacks();

        Assertions.assertEquals(Optional.empty(), setOfStacks.pop());
      }
    }

    @Nested
    class MinTest {
      @Test
      void min_with_element_ok() {
        final StackAndQueueExercise.SetOfStacks setOfStacks =
            new StackAndQueueExercise.SetOfStacks();
        final int minValue = 1;
        final int value = 2;

        setOfStacks.push(minValue);
        setOfStacks.push(value);

        Assertions.assertEquals(Optional.of(minValue), setOfStacks.min());
        Assertions.assertEquals(List.of(minValue, value), setOfStacks.queue());
      }

      @Test
      void min_without_element_ok() {
        final StackAndQueueExercise.SetOfStacks setOfStacks =
            new StackAndQueueExercise.SetOfStacks();

        Assertions.assertEquals(Optional.empty(), setOfStacks.min());
      }
    }
  }

  // Exercise 3.4
  @Nested
  class QueueShiftStacksTest {

    @Nested
    class PushTest {
      @Test
      void push_with_element_ok() {
        final StackAndQueueExercise.QueueShiftStacks queueShiftStacks =
            new StackAndQueueExercise.QueueShiftStacks();
        final int value = 1;

        queueShiftStacks.push(value);

        Assertions.assertEquals(Collections.singletonList(value), queueShiftStacks.queue());
      }

      @Test
      void push_with_elements_ok() {
        final StackAndQueueExercise.QueueShiftStacks queueShiftStacks =
            new StackAndQueueExercise.QueueShiftStacks();
        final int value = 1;
        final int value2 = 2;

        queueShiftStacks.push(value);
        queueShiftStacks.push(value2);

        Assertions.assertEquals(List.of(value, value2), queueShiftStacks.queue());
      }
    }

    @Nested
    class PeekTest {
      @Test
      void peek_with_element_ok() {
        final StackAndQueueExercise.QueueShiftStacks queueShiftStacks =
            new StackAndQueueExercise.QueueShiftStacks();
        final int value = 1;

        queueShiftStacks.push(value);

        Assertions.assertEquals(Optional.of(value), queueShiftStacks.peek());
        Assertions.assertEquals(Collections.singletonList(value), queueShiftStacks.queue());
      }

      @Test
      void peek_without_element_ok() {
        final StackAndQueueExercise.QueueShiftStacks queueShiftStacks =
            new StackAndQueueExercise.QueueShiftStacks();

        Assertions.assertEquals(Optional.empty(), queueShiftStacks.peek());
      }
    }

    @Nested
    class IsEmptyTest {
      @Test
      void isEmpty_false() {
        final StackAndQueueExercise.QueueShiftStacks queueShiftStacks =
            new StackAndQueueExercise.QueueShiftStacks();

        Assertions.assertTrue(queueShiftStacks.isEmpty());
      }

      @Test
      void isEmpty_true() {
        final StackAndQueueExercise.QueueShiftStacks queueShiftStacks =
            new StackAndQueueExercise.QueueShiftStacks();
        final int value = 1;

        queueShiftStacks.push(value);

        Assertions.assertFalse(queueShiftStacks.isEmpty());
        Assertions.assertEquals(Collections.singletonList(value), queueShiftStacks.queue());
      }
    }

    @Nested
    class PopTest {
      @Test
      void pop_with_element_ok() {
        final StackAndQueueExercise.QueueShiftStacks queueShiftStacks =
            new StackAndQueueExercise.QueueShiftStacks();
        final int value = 1;
        final int value2 = 2;

        queueShiftStacks.push(value);
        queueShiftStacks.push(value2);

        Assertions.assertEquals(Optional.of(value2), queueShiftStacks.pop());
        Assertions.assertEquals(Collections.singletonList(value), queueShiftStacks.queue());
      }

      @Test
      void pop_without_element_ok() {
        final StackAndQueueExercise.QueueShiftStacks queueShiftStacks =
            new StackAndQueueExercise.QueueShiftStacks();

        Assertions.assertEquals(Optional.empty(), queueShiftStacks.pop());
      }
    }

    @Nested
    class MinTest {
      @Test
      void min_with_element_ok() {
        final StackAndQueueExercise.QueueShiftStacks queueShiftStacks =
            new StackAndQueueExercise.QueueShiftStacks();
        final int minValue = 1;
        final int value = 2;

        queueShiftStacks.push(minValue);
        queueShiftStacks.push(value);

        Assertions.assertEquals(Optional.of(minValue), queueShiftStacks.min());
        Assertions.assertEquals(List.of(minValue, value), queueShiftStacks.queue());
      }

      @Test
      void min_without_element_ok() {
        final StackAndQueueExercise.QueueShiftStacks queueShiftStacks =
            new StackAndQueueExercise.QueueShiftStacks();

        Assertions.assertEquals(Optional.empty(), queueShiftStacks.min());
      }
    }
  }

  // Exercise 3.5
  @Nested
  class SortTest {
    @Test
    void sort_nullPointerException() {
      final Deque<Integer> stack = null;

      Assertions.assertThrows(NullPointerException.class, () -> sort(stack));
      Assertions.assertThrows(NullPointerException.class, () -> sortLambda(stack));
    }

    @Test
    void sort_ok() {
      final Deque<Integer> stack = new ArrayDeque<>(List.of(5, 10, 7, 12, 8, 3, 1));
      final Deque<Integer> expected = new ArrayDeque<>(List.of(1, 3, 5, 7, 8, 10, 12));

      Assertions.assertEquals(new ArrayList<>(expected), new ArrayList<>(sort(stack)));
      Assertions.assertEquals(new ArrayList<>(expected), new ArrayList<>(sortLambda(stack)));
    }
  }

  // Exercise 3.6
  @Nested
  class DequeueAnimalTest {
    @Test
    void dequeueAnimal_nullPointerException() {
      final StackAndQueueExercise.DequeueAnimal animals = new StackAndQueueExercise.DequeueAnimal();
      final StackAndQueueExercise.DequeueAnimalWithOrder animalsWithOrder =
          new StackAndQueueExercise.DequeueAnimalWithOrder();

      final StackAndQueueExercise.Animal animal = null;
      final StackAndQueueExercise.CatWithOrder catWithOrder = null;
      final StackAndQueueExercise.DogWithOrder dogWithOrder = null;

      Assertions.assertThrows(NullPointerException.class, () -> animals.enqueue(animal));
      Assertions.assertThrows(NullPointerException.class,
          () -> animalsWithOrder.enqueueCat(catWithOrder));
      Assertions.assertThrows(NullPointerException.class,
          () -> animalsWithOrder.enqueueDog(dogWithOrder));
    }

    @Test
    void dequeueAnimal_ok() {
      final StackAndQueueExercise.DequeueAnimal animals = new StackAndQueueExercise.DequeueAnimal();
      final StackAndQueueExercise.Cat cat = StackAndQueueExercise.Cat.builder()
          .name("catName")
          .build();
      final StackAndQueueExercise.Dog dog = StackAndQueueExercise.Dog.builder()
          .name("dogName")
          .build();
      animals.enqueue(cat);
      animals.enqueue(dog);

      Assertions.assertEquals(new ArrayList<>(List.of(cat, dog)),
          new ArrayList<>(animals.getAnimalDeque()));

      final StackAndQueueExercise.DequeueAnimalWithOrder animalsWithOrder =
          new StackAndQueueExercise.DequeueAnimalWithOrder();
      final StackAndQueueExercise.CatWithOrder catWithOrder =
          StackAndQueueExercise.CatWithOrder.builder()
              .name("catName")
              .build();
      final StackAndQueueExercise.DogWithOrder dogWithOrder =
          StackAndQueueExercise.DogWithOrder.builder()
              .name("dogName")
              .build();
      animalsWithOrder.enqueueCat(catWithOrder);
      animalsWithOrder.enqueueDog(dogWithOrder);

      Assertions.assertEquals(
          new ArrayList<>(List.of(catWithOrder.getName(), dogWithOrder.getName())),
          animalsWithOrder.getAnimalDeque().stream()
              .map(StackAndQueueExercise.AnimalWithOrder::getName).toList());
    }

    @Test
    void dequeueAnimal_poll() {
      final StackAndQueueExercise.DequeueAnimal animals = new StackAndQueueExercise.DequeueAnimal();
      final StackAndQueueExercise.Cat cat = StackAndQueueExercise.Cat.builder()
          .name("catName")
          .build();
      final StackAndQueueExercise.Dog dog = StackAndQueueExercise.Dog.builder()
          .name("dogName")
          .build();
      animals.enqueue(cat);
      animals.enqueue(dog);

      final StackAndQueueExercise.DequeueAnimalWithOrder animalsWithOrder =
          new StackAndQueueExercise.DequeueAnimalWithOrder();
      final StackAndQueueExercise.CatWithOrder catWithOrder =
          StackAndQueueExercise.CatWithOrder.builder()
              .name("catName")
              .build();
      final StackAndQueueExercise.DogWithOrder dogWithOrder =
          StackAndQueueExercise.DogWithOrder.builder()
              .name("dogName")
              .build();
      animalsWithOrder.enqueueCat(catWithOrder);
      animalsWithOrder.enqueueDog(dogWithOrder);

      Assertions.assertEquals(Optional.of(cat), animals.poll());
      Assertions.assertEquals(catWithOrder.getName(), animalsWithOrder.poll().get().getName());
    }

    @Test
    void dequeueAnimal_pollClass() {
      final StackAndQueueExercise.DequeueAnimal animals = new StackAndQueueExercise.DequeueAnimal();
      final StackAndQueueExercise.Cat cat = StackAndQueueExercise.Cat.builder()
          .name("catName")
          .build();
      final StackAndQueueExercise.Dog dog = StackAndQueueExercise.Dog.builder()
          .name("dogName")
          .build();
      animals.enqueue(cat);
      animals.enqueue(dog);

      Assertions.assertEquals(Optional.of(dog), animals.poll(StackAndQueueExercise.Dog.class));
      Assertions.assertEquals(Optional.of(cat), animals.poll(StackAndQueueExercise.Cat.class));

      final StackAndQueueExercise.DequeueAnimalWithOrder animalsWithOrder =
          new StackAndQueueExercise.DequeueAnimalWithOrder();
      final StackAndQueueExercise.CatWithOrder catWithOrder =
          StackAndQueueExercise.CatWithOrder.builder()
              .name("catName")
              .build();
      final StackAndQueueExercise.DogWithOrder dogWithOrder =
          StackAndQueueExercise.DogWithOrder.builder()
              .name("dogName")
              .build();
      animalsWithOrder.enqueueCat(catWithOrder);
      animalsWithOrder.enqueueDog(dogWithOrder);

      Assertions.assertEquals(dogWithOrder.getName(), animalsWithOrder.pollDog().get().getName());
      Assertions.assertEquals(catWithOrder.getName(), animalsWithOrder.pollCat().get().getName());
    }

  }
}
