package exercises;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StackAndQueueExercise {

  // Exercise 3.1
  public static StackList generateStackList(final @NonNull List<Integer> stack,
                                            final int totalStack) {
    if (totalStack < 1) {
      throw new IllegalArgumentException("Number of stacks cannot minor than 1");
    }

    List<List<Integer>> actualStackMatrix = new ArrayList<>();

    final int dividend = stack.size() / totalStack;
    final int quotient = stack.size() % totalStack;
    int index = 0;

    for (int currentStack = 0; currentStack < totalStack; currentStack++) {
      final List<Integer> stackRow = new ArrayList<>();
      int elementsInCurrentStack = dividend + (currentStack < quotient ? 1 : 0);

      for (int element = 0; element < elementsInCurrentStack; element++) {
        stackRow.add(stack.get(index++));
      }

      actualStackMatrix.add(stackRow);
    }

    return StackList.builder()
        .stackMatrix(actualStackMatrix)
        .build();
  }

  public static StackList generateStackListLambda(final @NonNull List<Integer> stack,
                                                  final int totalStack) {
    if (totalStack < 1) {
      throw new IllegalArgumentException("Number of stacks cannot minor than 1");
    }

    final int dividend = stack.size() / totalStack;
    final int quotient = stack.size() % totalStack;
    final AtomicInteger index = new AtomicInteger(0);

    return StackList.builder()
        .stackMatrix(
            IntStream.range(0, totalStack)
                .mapToObj(currentStack -> {
                  int elementsInCurrentStack = dividend + (currentStack < quotient ? 1 : 0);
                  List<Integer> stackRow = new ArrayList<>(elementsInCurrentStack);

                  IntStream.range(0, elementsInCurrentStack)
                      .forEach(i -> stackRow.add(stack.get(index.getAndIncrement())));

                  return stackRow;
                })
                .toList())
        .build();
  }

  @Builder
  @Value
  public static class StackList {

    @NonNull
    List<List<Integer>> stackMatrix;

    public void push(final int value, final int currentStack) {
      this.validateCurrentStack(currentStack);

      this.stackMatrix.get(currentStack - 1).addLast(value);
    }

    public Collection<Integer> queue(final int currentStack) {
      return this.stackMatrix.get(currentStack - 1);
    }

    public Optional<Integer> peek(final int currentStack) {
      this.validateCurrentStack(currentStack);

      return Optional.of(currentStack)
          .filter(stack -> !this.isEmpty(stack))
          .map(value -> this.stackMatrix.get(currentStack - 1).getLast());
    }

    public boolean isEmpty(final int currentStack) {
      this.validateCurrentStack(currentStack);

      return this.stackMatrix.get(currentStack - 1).isEmpty();
    }

    public Optional<Integer> pop(final int currentStack) {
      this.validateCurrentStack(currentStack);

      return Optional.of(currentStack)
          .filter(stack -> !this.isEmpty(stack))
          .map(value -> this.stackMatrix.get(currentStack - 1).removeLast());
    }

    public Optional<Integer> min(final int currentStack) {
      this.validateCurrentStack(currentStack);

      return this.stackMatrix.get(currentStack - 1).stream().min(Integer::compareTo);
    }

    private void validateCurrentStack(final int currentStack) {
      if (currentStack < 1 || this.stackMatrix.size() < currentStack) {
        throw new IllegalArgumentException(
            "Number of stacks cannot be greater than the number of stacks declared on the object");
      }
    }
  }

  // Exercise 3.2
  @Value
  @Getter(value = AccessLevel.PRIVATE)
  public static class Stack {

    @NonNull
    List<Integer> stackList = new ArrayList<>();

    @NonNull
    List<Integer> minValue = new ArrayList<>();

    public void push(final int value) {
      this.stackList.addLast(value);

      if (this.minValue.isEmpty() || this.minValue.getLast() > value) {
        this.minValue.add(value);
      }
    }

    public Collection<Integer> queue() {
      return this.stackList.stream().toList();
    }

    public Optional<Integer> peek() {
      return Optional.of(this.stackList)
          .filter(stack -> !stack.isEmpty())
          .map(List::getLast);
    }

    public boolean isEmpty() {
      return this.stackList.isEmpty();
    }

    public Optional<Integer> pop() {
      return Optional.of(this.stackList)
          .filter(stack -> !stack.isEmpty())
          .map(List::removeLast);
    }

    public Optional<Integer> min() {
      return Optional.of(this.minValue)
          .filter(min -> !min.isEmpty())
          .map(List::getLast);
    }
  }

  // Exercise 3.3
  @Value
  @Getter(value = AccessLevel.PRIVATE)
  public static class SetOfStacks {

    static int maxValuePeerStack = 1;

    @NonNull
    List<List<Integer>> stackMatrix = new ArrayList<>(new ArrayList<>(maxValuePeerStack));

    @NonNull
    List<Integer> minValue = new ArrayList<>();

    public void push(final int value) {
      if (this.stackMatrix.isEmpty() || this.stackMatrix.getLast().size() >= maxValuePeerStack) {
        final ArrayList<Integer> newList = new ArrayList<>(maxValuePeerStack);
        newList.add(value);

        this.stackMatrix.addLast(newList);
      } else {
        this.stackMatrix.getLast().add(value);
      }

      if (this.minValue.isEmpty() || this.minValue.getLast() > value) {
        this.minValue.add(value);
      }
    }

    public Collection<Integer> queue() {
      return this.stackMatrix.stream()
          .flatMap(List::stream)
          .toList();
    }

    public Optional<Integer> peek() {
      return Optional.of(this.stackMatrix)
          .filter(stack -> !stack.isEmpty())
          .map(stack -> stackMatrix.getLast().getLast());
    }

    public boolean isEmpty() {
      return this.stackMatrix.isEmpty();
    }

    public Optional<Integer> pop() {
      return Optional.of(this.stackMatrix)
          .filter(stack -> !stack.isEmpty())
          .map(stack -> stackMatrix.getLast().removeLast());
    }

    public Optional<Integer> min() {
      return Optional.of(this.minValue)
          .filter(min -> !min.isEmpty())
          .map(List::getLast);
    }
  }

  // Exercise 3.4
  @Value
  @Getter(value = AccessLevel.PRIVATE)
  public static class QueueShiftStacks {

    @NonNull
    Deque<Integer> stackNewest = new ArrayDeque<>();

    @NonNull
    Deque<Integer> stackOldest = new ArrayDeque<>();

    @NonNull
    List<Integer> minValue = new ArrayList<>();

    public void shiftStacks() {
      if (this.stackOldest.isEmpty()) {
        this.stackNewest.stream()
            .toList()
            .forEach(stackOldest::push);
        this.stackNewest.clear();
      }
    }

    public void push(final int value) {
      this.stackNewest.add(value);

      if (this.minValue.isEmpty() || this.minValue.getLast() > value) {
        this.minValue.add(value);
      }
    }

    public Collection<Integer> queue() {
      return Stream.concat(
          stackOldest.stream().toList().reversed().stream(),
          stackNewest.stream()
      ).toList();
    }

    public Optional<Integer> peek() {
      this.shiftStacks();

      return Optional.of(this.stackOldest)
          .filter(stack -> !stack.isEmpty())
          .map(Deque::peek);
    }

    public boolean isEmpty() {
      this.shiftStacks();

      return this.stackOldest.isEmpty();
    }

    public Optional<Integer> pop() {
      this.shiftStacks();

      return Optional.of(this.stackOldest)
          .filter(stack -> !stack.isEmpty())
          .map(Deque::pop);
    }

    public Optional<Integer> min() {
      this.shiftStacks();

      return Optional.of(this.minValue)
          .filter(min -> !min.isEmpty())
          .map(List::getLast);
    }
  }

  // Exercise 3.5
  public static Deque<Integer> sort(final @NonNull Deque<Integer> value) {
    List<Integer> list = new ArrayList<>(value);
    Collections.sort(list);
    return new ArrayDeque<>(list);
  }

  public static Deque<Integer> sortLambda(final @NonNull Deque<Integer> value) {
    return new ArrayDeque<>(
        value.stream()
            .sorted()
            .toList());
  }

  // Exercise 3.6
  @Getter
  @SuperBuilder
  public abstract static class Animal {
    String name;
  }

  @SuperBuilder
  public static class Dog extends Animal {
  }

  @SuperBuilder
  public static class Cat extends Animal {
  }

  @Value
  public static class DequeueAnimal {

    Deque<Animal> animalDeque = new ArrayDeque<>();

    public void enqueue(final @NonNull Animal animal) {
      this.animalDeque.add(animal);
    }

    public Optional<Animal> poll() {
      return Optional.ofNullable(this.animalDeque.poll());
    }

    public Optional<Animal> poll(final Class<?> subClass) {
      return this.animalDeque.stream()
          .filter(subClass::isInstance)
          .findFirst()
          .map(animal -> {
            this.animalDeque.remove(animal);
            return animal;
          });
    }
  }

  @Getter
  @SuperBuilder(toBuilder = true)
  public abstract static class AnimalWithOrder {
    String name;

    @Builder.Default
    Integer order = 0;
  }

  @SuperBuilder(toBuilder = true)
  public static class DogWithOrder extends AnimalWithOrder {

  }

  @SuperBuilder(toBuilder = true)
  public static class CatWithOrder extends AnimalWithOrder {

  }

  @Value
  @Getter(value = AccessLevel.PRIVATE)
  public static class DequeueAnimalWithOrder {

    Deque<DogWithOrder> dogDeque = new ArrayDeque<>();

    Deque<CatWithOrder> catDeque = new ArrayDeque<>();

    AtomicInteger order = new AtomicInteger(0);

    public void enqueueDog(final @NonNull DogWithOrder dog) {
      DogWithOrder dogWithOrder = dog.toBuilder().order(order.getAndIncrement()).build();
      this.dogDeque.add(dogWithOrder);
    }

    public void enqueueCat(final @NonNull CatWithOrder cat) {
      CatWithOrder catWithOrder = cat.toBuilder().order(order.getAndIncrement()).build();
      this.catDeque.add(catWithOrder);
    }

    public Deque<AnimalWithOrder> getAnimalDeque() {
      List<AnimalWithOrder> animalDeque = new ArrayList<>();

      animalDeque.addAll(dogDeque);
      animalDeque.addAll(catDeque);

      animalDeque.sort(Comparator.comparingInt(AnimalWithOrder::getOrder));

      return new ArrayDeque<>(animalDeque);
    }

    public Optional<AnimalWithOrder> poll() {
      Optional<DogWithOrder> dogOptionalQueue = Optional.ofNullable(dogDeque.peek());
      Optional<CatWithOrder> catOptionalQueue = Optional.ofNullable(catDeque.peek());

      return Optional.ofNullable(dogOptionalQueue
          .map(dog -> catOptionalQueue
              .map(cat -> dog.getOrder() > cat.getOrder() ? catDeque.poll() : dogDeque.poll())
              .orElse(dogDeque.poll()))
          .orElseGet(catDeque::poll));
    }

    public Optional<DogWithOrder> pollDog() {
      return Optional.ofNullable(this.dogDeque.poll());
    }

    public Optional<CatWithOrder> pollCat() {
      return Optional.ofNullable(this.catDeque.poll());
    }
  }
}
