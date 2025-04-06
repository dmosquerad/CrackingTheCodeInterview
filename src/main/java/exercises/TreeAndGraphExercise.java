package exercises;

import java.awt.geom.Point2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeAndGraphExercise {

  // Exercise 4.1
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Graph {

    @Value
    @Builder
    public static class Node {
      @NonNull
      String name;

      @NonNull
      @ToString.Exclude
      @EqualsAndHashCode.Exclude
      Set<Node> neighbours = new HashSet<>();
    }

    public static boolean hasRouteBfs(final @NonNull Node start, final @NonNull Node end) {
      if (Objects.equals(end, start)) {
        return true;
      }

      final Set<Node> visited = new HashSet<>();
      final Deque<Node> queue = new ArrayDeque<>();

      queue.add(start);
      visited.add(start);

      while (!queue.isEmpty()) {
        final Node current = queue.poll();

        for (Node neighbour : current.getNeighbours()) {
          if (visited.contains(neighbour)) {
            continue;
          }

          if (Objects.equals(neighbour, end)) {
            return true;
          }

          visited.add(neighbour);
          queue.add(neighbour);
        }
      }

      return false;
    }

    public static boolean hasRouteBfsLambda(final @NonNull Node start, final @NonNull Node end) {
      if (Objects.equals(end, start)) {
        return true;
      }

      final Set<Node> visited = new HashSet<>();
      final Deque<Node> queue = new ArrayDeque<>();

      queue.add(start);
      visited.add(start);

      while (!queue.isEmpty()) {
        final Node current = queue.poll();

        if (current.getNeighbours().stream().filter(neighbour -> !visited.contains(neighbour))
            .peek(neighbour -> {
              synchronized (visited) {
                visited.add(neighbour);
              }
              synchronized (queue) {
                queue.add(neighbour);
              }
            }).anyMatch(neighbour -> Objects.equals(neighbour, end))) {
          return true;
        }
      }

      return false;
    }

    public static boolean hasRouteDfs(final @NonNull Node start, final @NonNull Node end) {
      final Set<Node> visited = new HashSet<>();
      return dfs(start, end, visited);
    }

    public static boolean hasRouteDfsLambda(final @NonNull Node start, final @NonNull Node end) {
      final Set<Node> visited = new HashSet<>();
      return dfsLambda(start, end, visited);
    }


    private static boolean dfs(final @NonNull Node start, final @NonNull Node end,
                               final @NonNull Collection<Node> visited) {
      if (Objects.equals(end, start)) {
        return true;
      }

      visited.add(start);

      for (Node neighbour : start.getNeighbours()) {
        if (visited.contains(neighbour)) {
          continue;
        }

        visited.add(neighbour);

        if (dfs(neighbour, end, visited)) {
          return true;
        }
      }

      return false;
    }

    private static boolean dfsLambda(final @NonNull Node start, final @NonNull Node end,
                                     final @NonNull Collection<Node> visited) {
      if (Objects.equals(end, start)) {
        return true;
      }

      visited.add(start);

      return start.getNeighbours().stream()
          .filter(neighbour -> !visited.contains(neighbour))
          .anyMatch(neighbour -> {
            synchronized (visited) {
              visited.add(neighbour);
            }
            return dfsLambda(neighbour, end, visited);
          });
    }
  }

  // Exercise 4.1 (with Best-First and A*)
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class GraphWithDistance {

    @Value
    @Builder
    public static class Node {
      @NonNull
      String name;

      @NonNull
      @ToString.Exclude
      @EqualsAndHashCode.Exclude
      Set<Node> neighbours = new HashSet<>();

      @NonNull
      Point2D coordinates;
    }

    @Value
    @Builder
    private static class NodeWithPriority {
      @NonNull
      Node node;

      int priority;
    }

    public static boolean hasRouteBestFirstManhattanHeuristic(final @NonNull Node start,
                                                              final @NonNull Node end) {
      return hasRouteBestFirst(start, end, new TreeAndGraphExercise.ManhattanHeuristic());
    }

    public static boolean hasRouteBestFirstEuclideanHeuristic(final @NonNull Node start,
                                                              final @NonNull Node end) {
      return hasRouteBestFirst(start, end, new TreeAndGraphExercise.EuclideanHeuristic());
    }

    public static boolean hasRouteBestFirstChebyshevHeuristic(final @NonNull Node start,
                                                              final @NonNull Node end) {
      return hasRouteBestFirst(start, end, new TreeAndGraphExercise.ChebyshevHeuristic());
    }

    public static boolean hasRouteBestFirst(final @NonNull Node start, final @NonNull Node end,
                                            final @NonNull Heuristic heuristic) {
      if (Objects.equals(end, start)) {
        return true;
      }

      final Set<Node> visited = new HashSet<>();
      final PriorityQueue<NodeWithPriority> priorityQueue =
          new PriorityQueue<>(Comparator.comparingInt(NodeWithPriority::getPriority));

      priorityQueue.add(NodeWithPriority.builder()
          .node(start)
          .priority(heuristic.heuristic(start, end))
          .build());

      while (!priorityQueue.isEmpty()) {
        final Node currentNode = priorityQueue.poll().getNode();

        if (!visited.contains(currentNode)) {
          if (Objects.equals(currentNode, end)) {
            return true;
          }

          visited.add(currentNode);

          for (Node neighbour : currentNode.getNeighbours()) {
            if (visited.contains(neighbour)) {
              continue;
            }

            priorityQueue.add(NodeWithPriority.builder()
                .node(neighbour)
                .priority(heuristic.heuristic(neighbour, end))
                .build());
          }
        }
      }

      return false;
    }

    public static boolean hasRouteBestFirstManhattanHeuristicLambda(final @NonNull Node start,
                                                                    final @NonNull Node end) {
      return hasRouteBestFirstLambda(start, end, new TreeAndGraphExercise.ManhattanHeuristic());
    }

    public static boolean hasRouteBestFirstEuclideanHeuristicLambda(final @NonNull Node start,
                                                                    final @NonNull Node end) {
      return hasRouteBestFirstLambda(start, end, new TreeAndGraphExercise.EuclideanHeuristic());
    }

    public static boolean hasRouteBestFirstChebyshevHeuristicLambda(final @NonNull Node start,
                                                                    final @NonNull Node end) {
      return hasRouteBestFirstLambda(start, end, new TreeAndGraphExercise.ChebyshevHeuristic());
    }

    public static boolean hasRouteBestFirstLambda(final @NonNull Node start,
                                                  final @NonNull Node end,
                                                  final @NonNull Heuristic heuristic) {
      if (Objects.equals(end, start)) {
        return true;
      }

      final PriorityQueue<NodeWithPriority> priorityQueue =
          new PriorityQueue<>(Comparator.comparingInt(NodeWithPriority::getPriority));

      priorityQueue.add(NodeWithPriority.builder()
          .node(start)
          .priority(heuristic.heuristic(start, end))
          .build());

      while (!priorityQueue.isEmpty()) {
        final Node currentNode = priorityQueue.poll().getNode();

        if (Objects.equals(currentNode, end)) {
          return true;
        }

        final Set<Node> unvisitedNeighbours = currentNode.getNeighbours().stream()
            .filter(neighbour -> priorityQueue.stream()
                .noneMatch(
                    priorityNeighbour -> Objects.equals(priorityNeighbour.getNode(), neighbour))
            )
            .collect(Collectors.toSet());

        unvisitedNeighbours.forEach(neighbour -> priorityQueue.add(NodeWithPriority.builder()
            .node(neighbour)
            .priority(heuristic.heuristic(neighbour, end))
            .build()));
      }

      return false;
    }

    @Value
    @Builder
    private static class NodeWithCost {
      @NonNull
      Node node;

      int heuristicCost;
      int pathCost;

      public int getTotalCost() {
        return this.getHeuristicCost() + this.getPathCost();
      }
    }

    public static boolean hasRouteAstarManhattanHeuristic(final @NonNull Node start,
                                                          final @NonNull Node end) {
      return hasRouteAstar(start, end, new TreeAndGraphExercise.ManhattanHeuristic());
    }

    public static boolean hasRouteAstarEuclideanHeuristic(final @NonNull Node start,
                                                          final @NonNull Node end) {
      return hasRouteAstar(start, end, new TreeAndGraphExercise.EuclideanHeuristic());
    }

    public static boolean hasRouteAstarChebyshevHeuristic(final @NonNull Node start,
                                                          final @NonNull Node end) {
      return hasRouteAstar(start, end, new TreeAndGraphExercise.ChebyshevHeuristic());
    }

    private static boolean hasRouteAstar(final @NonNull Node start, final @NonNull Node end,
                                         final @NonNull Heuristic heuristic) {
      if (Objects.equals(end, start)) {
        return true;
      }

      final Set<Node> visited = new HashSet<>();

      final PriorityQueue<NodeWithCost> priorityQueue =
          new PriorityQueue<>(Comparator.comparingInt(NodeWithCost::getTotalCost));

      final Map<Node, Integer> pathCosts = new HashMap<>();
      pathCosts.put(start, 0);

      priorityQueue.add(NodeWithCost.builder()
          .node(start)
          .pathCost(0)
          .heuristicCost(heuristic.heuristic(start, end))
          .build());

      while (!priorityQueue.isEmpty()) {
        final Node currentNode = priorityQueue.poll().getNode();
        if (visited.contains(currentNode)) {
          continue;
        }

        if (Objects.equals(currentNode, end)) {
          return true;
        }

        visited.add(currentNode);

        for (Node neighbour : currentNode.getNeighbours()) {
          if (visited.contains(neighbour)) {
            continue;
          }

          final int edgeCost = 1;

          final int tentativePathCost = pathCosts.get(currentNode) + edgeCost;

          pathCosts.computeIfAbsent(neighbour, n -> {
            priorityQueue.add(NodeWithCost.builder()
                .node(neighbour)
                .pathCost(tentativePathCost)
                .heuristicCost(heuristic.heuristic(neighbour, end))
                .build());
            return tentativePathCost;
          });

          if (tentativePathCost < pathCosts.get(neighbour)) {
            pathCosts.put(neighbour, tentativePathCost);
            priorityQueue.add(NodeWithCost.builder()
                .node(neighbour)
                .pathCost(tentativePathCost)
                .heuristicCost(heuristic.heuristic(neighbour, end))
                .build());
          }
        }

      }

      return false;
    }

    public static boolean hasRouteAstarManhattanHeuristicLambda(final @NonNull Node start,
                                                                final @NonNull Node end) {
      return hasRouteAstarLambda(start, end, new TreeAndGraphExercise.ManhattanHeuristic());
    }

    public static boolean hasRouteAstarEuclideanHeuristicLambda(final @NonNull Node start,
                                                                final @NonNull Node end) {
      return hasRouteAstarLambda(start, end, new TreeAndGraphExercise.EuclideanHeuristic());
    }

    public static boolean hasRouteAstarChebyshevHeuristicLambda(final @NonNull Node start,
                                                                final @NonNull Node end) {
      return hasRouteAstarLambda(start, end, new TreeAndGraphExercise.ChebyshevHeuristic());
    }


    private static boolean hasRouteAstarLambda(final @NonNull Node start, final @NonNull Node end,
                                               final @NonNull Heuristic heuristic) {
      if (Objects.equals(end, start)) {
        return true;
      }

      final Set<Node> visited = new HashSet<>();

      final PriorityQueue<NodeWithCost> priorityQueue =
          new PriorityQueue<>(Comparator.comparingInt(NodeWithCost::getTotalCost));

      final Map<Node, Integer> pathCosts = new HashMap<>();
      pathCosts.put(start, 0);

      priorityQueue.add(NodeWithCost.builder()
          .node(start)
          .pathCost(0)
          .heuristicCost(heuristic.heuristic(start, end))
          .build());

      while (!priorityQueue.isEmpty()) {
        final GraphWithDistance.Node currentNode = priorityQueue.poll().getNode();

        if (visited.contains(currentNode)) {
          continue;
        }

        if (Objects.equals(currentNode, end)) {
          return true;
        }

        visited.add(currentNode);

        currentNode.getNeighbours().stream()
            .filter(neighbour -> !visited.contains(neighbour))
            .forEach(neighbour -> {
              int edgeCost = 1;

              int tentativePathCost = pathCosts.get(currentNode) + edgeCost;

              pathCosts.compute(neighbour, (nodeKey, oldPathCost) -> {
                if (Objects.isNull(oldPathCost) || tentativePathCost < oldPathCost) {
                  priorityQueue.add(NodeWithCost.builder()
                      .node(neighbour)
                      .pathCost(tentativePathCost)
                      .heuristicCost(heuristic.heuristic(neighbour, end))
                      .build());
                  return tentativePathCost;
                }
                return oldPathCost;
              });
            });

      }

      return false;
    }
  }

  public interface Heuristic {
    int heuristic(final @NonNull TreeAndGraphExercise.GraphWithDistance.Node current,
                  final @NonNull TreeAndGraphExercise.GraphWithDistance.Node goal);
  }

  public static class ManhattanHeuristic implements Heuristic {

    @Override
    public int heuristic(final GraphWithDistance.Node current,
                         final GraphWithDistance.Node goal) {
      final double dx = Math.abs(current.coordinates.getX() - goal.coordinates.getX());
      final double dy = Math.abs(current.coordinates.getY() - goal.coordinates.getY());
      return (int) (dx + dy);
    }
  }

  public static class EuclideanHeuristic implements Heuristic {

    @Override
    public int heuristic(final @NonNull GraphWithDistance.Node current,
                         final @NonNull GraphWithDistance.Node goal) {
      final double dx = current.getCoordinates().getX() - goal.getCoordinates().getX();
      final double dy = current.getCoordinates().getY() - goal.getCoordinates().getY();
      return (int) Math.sqrt(dx * dx + dy * dy);
    }
  }

  public static class ChebyshevHeuristic implements Heuristic {

    @Override
    public int heuristic(final @NonNull GraphWithDistance.Node current,
                         final @NonNull GraphWithDistance.Node goal) {
      final double dx = Math.abs(current.getCoordinates().getX() - goal.getCoordinates().getX());
      final double dy = Math.abs(current.getCoordinates().getY() - goal.getCoordinates().getY());
      return (int) Math.max(dx, dy);
    }
  }

  public static class TreeNodeExercise {

    @Value
    @Builder(toBuilder = true)
    public static class TreeNode {
      int value;
      TreeNode left;
      TreeNode right;
    }

    // Exercise 4.2
    public static Optional<TreeNode> createMinimalBst(final @NonNull Collection<Integer> value) {
      final List<Integer> orderedValue = value.stream().sorted().toList();
      return createMinimalBst(orderedValue, 0, value.size() - 1);
    }

    private static Optional<TreeNode> createMinimalBst(final @NonNull List<Integer> value,
                                                       final int start, final int end) {
      if (end < start) {
        return Optional.empty();
      }

      final int middle = (start + end) / 2;
      return Optional.of(TreeNode.builder()
              .value(value.get(middle))
              .left(createMinimalBst(value, start, middle - 1).orElse(null))
              .right(createMinimalBst(value, middle + 1, end).orElse(null))
              .build());
    }

    // Exercise 4.3
    public static Collection<LinkedList<TreeNode>> createLevelLinkedList(
            final @NonNull TreeNode root) {
      return createLevelLinkedList(root, new ArrayList<>(), 0);
    }

    private static List<LinkedList<TreeNode>> createLevelLinkedList(
            final @NonNull TreeNode currentTree,
            final @NonNull List<LinkedList<TreeNode>> lists,
            final int level) {
      if (lists.size() <= level) {
        lists.add(new LinkedList<>());
      }

      lists.get(level).add(currentTree);

      if (Objects.nonNull(currentTree.getLeft())) {
        createLevelLinkedList(currentTree.getLeft(), lists, level + 1);
      }
      if (Objects.nonNull(currentTree.getRight())) {
        createLevelLinkedList(currentTree.getRight(), lists, level + 1);
      }

      return lists;
    }

    public static Collection<LinkedList<TreeNode>> createLevelLinkedListLambda(
            final @NonNull TreeNode root) {
      final ArrayList<LinkedList<TreeNode>> result = new ArrayList<>();

      LinkedList<TreeNode> currentLevel = new LinkedList<>();
      currentLevel.add(root);

      while (!currentLevel.isEmpty()) {

        result.add(currentLevel);
        currentLevel = currentLevel.stream()
                .flatMap(node -> Stream.concat(
                        Optional.ofNullable(node.getLeft()).stream(),
                        Optional.ofNullable(node.getRight()).stream()
                ))
                .collect(Collectors.toCollection(LinkedList::new));
      }
      return result;
    }

    // Exercise 4.4
    enum BalanceStatus {
      BALANCED,
      UNBALANCED,
      UNKNOWN
    }

    private static BalanceStatus checkHeight(final @NonNull TreeNode treeNode) {
      final BalanceStatus leftBalanced = Objects.nonNull(treeNode.getLeft())
          ? checkHeight(treeNode.getLeft()) : BalanceStatus.BALANCED;
      final BalanceStatus rightBalanced = Objects.nonNull(treeNode.getRight())
          ? checkHeight(treeNode.getRight()) : BalanceStatus.BALANCED;

      if (BalanceStatus.UNBALANCED.equals(leftBalanced)
          || BalanceStatus.UNBALANCED.equals(rightBalanced)) {
        return BalanceStatus.UNBALANCED;
      }

      final int leftHeight = Objects.nonNull(treeNode.getLeft())
          ? getHeight(treeNode.getLeft()) : 0;
      final int rightHeight = Objects.nonNull(treeNode.getRight())
          ? getHeight(treeNode.getRight()) : 0;

      if (Math.abs(leftHeight - rightHeight) > 1) {
        return BalanceStatus.UNBALANCED;
      }

      return BalanceStatus.BALANCED;
    }

    private static int getHeight(final @NonNull TreeNode treeNode) {
      final int leftHeight = Objects.nonNull(treeNode.getLeft())
          ? getHeight(treeNode.getLeft()) : 0;
      final int rightHeight = Objects.nonNull(treeNode.getRight())
          ? getHeight(treeNode.getRight()) : 0;

      return Math.max(leftHeight, rightHeight) + 1;
    }

    public static boolean isBalanced(final @NonNull TreeNode treeNode) {
      return checkHeight(treeNode) != BalanceStatus.UNBALANCED;
    }

    public static boolean isBalanced2(final @NonNull TreeNode treeNode) {
      return checkHeightInt(treeNode) != -1;
    }

    private static int checkHeightInt(final @NonNull TreeNode treeNode) {
      final int leftHeight = Objects.nonNull(treeNode.getLeft())
          ? checkHeightInt(treeNode.getLeft()) : 0;
      final int rightHeight = Objects.nonNull(treeNode.getRight())
          ? checkHeightInt(treeNode.getRight()) : 0;

      if (leftHeight == -1 || rightHeight == -1) {
        return -1;
      }

      if (Math.abs(leftHeight - rightHeight) > 1) {
        return -1;
      }

      return Math.max(leftHeight, rightHeight) + 1;
    }

    // Exercise 4.5
    public static boolean isValidBst(final @NonNull TreeNode treeNode) {
      return isValidBst(treeNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isValidBst(final @NonNull TreeNode treeNode, final int minimum,
                                      final int maximum) {
      if (treeNode.getValue() <= minimum || treeNode.getValue() >= maximum) {
        return false;
      }

      final boolean isValidLeftBts = Objects.isNull(treeNode.getLeft())
          || isValidBst(treeNode.getLeft(), minimum, treeNode.getValue());
      final boolean isValidRightBts = Objects.isNull(treeNode.getRight())
          || isValidBst(treeNode.getRight(), treeNode.getValue(), maximum);

      return isValidLeftBts && isValidRightBts;
    }

    // Exercise 4.6
    public static Optional<TreeNode> findInOrderSuccessor(final @NonNull TreeNode root,
                                                          final @NonNull TreeNode treeNode) {
      if (Objects.nonNull(treeNode.getRight())) {
        final AtomicReference<TreeNode> current = new AtomicReference<>(treeNode.getRight());
        while (Objects.nonNull(current.get().getLeft())) {
          current.set(current.get().getLeft());
        }
        return Optional.of(current.get());
      }

      final AtomicReference<TreeNode> successor = new AtomicReference<>();
      final AtomicReference<TreeNode> current = new AtomicReference<>(root);

      while (Objects.nonNull(current.get())) {
        if (treeNode.getValue() == current.get().getValue()) {
          break;
        }
        if (treeNode.getValue() < current.get().getValue()) {
          successor.set(current.get());
          current.set(current.get().getLeft());
        } else {
          current.set(current.get().getRight());
        }
      }

      return Optional.ofNullable(successor.get());
    }

    // Exercise 4.8
    public static Optional<TreeNode> findCommonAncestor(final @NonNull TreeNode root,
                                                        final @NonNull TreeNode ancestor) {
      if (Objects.equals(root, ancestor)) {
        return Optional.of(root);
      }

      final Optional<TreeNode> left = Objects.nonNull(root.getLeft())
          ? findCommonAncestor(root.getLeft(), ancestor) : Optional.empty();
      final Optional<TreeNode> right = Objects.nonNull(root.getRight())
          ? findCommonAncestor(root.getRight(), ancestor) : Optional.empty();

      if (left.isPresent() && right.isPresent()) {
        return Optional.of(root);
      }

      return left.isPresent() ? left : right;
    }

    // Exercise 4.9
    private static List<List<Integer>> weaveLists(final @NonNull List<Integer> left,
                                                  final @NonNull List<Integer> right,
                                                  final @NonNull List<Integer> prefix) {
      final List<List<Integer>> results = new ArrayList<>();

      if (left.isEmpty() || right.isEmpty()) {
        final List<Integer> result = new ArrayList<>(prefix);
        result.addAll(left);
        result.addAll(right);
        results.add(result);
        return results;
      }

      final List<Integer> prefixWithFirst = new ArrayList<>(prefix);
      prefixWithFirst.add(left.getFirst());
      results.addAll(weaveLists(left.subList(1, left.size()), right, prefixWithFirst));

      final List<Integer> prefixWithSecond = new ArrayList<>(prefix);
      prefixWithSecond.add(right.getFirst());
      results.addAll(weaveLists(left, right.subList(1, right.size()), prefixWithSecond));

      return results;
    }

    public static List<List<Integer>> allSequences(final @NonNull TreeNode root) {
      final List<List<Integer>> result = new ArrayList<>();

      final List<List<Integer>> leftSequence = Objects.nonNull(root.getLeft())
          ? allSequences(root.getLeft()) : List.of(List.of());
      final List<List<Integer>> rightSequence = Objects.nonNull(root.getRight())
          ? allSequences(root.getRight()) : List.of(List.of());

      final List<Integer> prefix = new ArrayList<>();
      prefix.add(root.getValue());

      for (List<Integer> left : leftSequence) {
        for (List<Integer> right : rightSequence) {
          final List<List<Integer>> weaved = weaveLists(left, right, prefix);
          result.addAll(weaved);
        }
      }

      return result;
    }

    private static List<List<Integer>> weaveListsLambda(final @NonNull List<Integer> left,
                                                        final @NonNull List<Integer> right,
                                                        final @NonNull List<Integer> prefix) {

      if (left.isEmpty() || right.isEmpty()) {
        return List.of(Stream.concat(prefix.stream(), Stream.concat(left.stream(), right.stream()))
                .toList());
      }

      return Stream.concat(
              weaveListsLambda(left.subList(1, left.size()), right, Stream.concat(prefix.stream(),
                              Stream.of(left.getFirst()))
                      .toList())
                      .stream(),
              weaveListsLambda(left, right.subList(1, right.size()), Stream.concat(prefix.stream(),
                              Stream.of(right.getFirst()))
                      .toList())
                      .stream()
      ).toList();
    }

    public static List<List<Integer>> allSequencesLambda(final @NonNull TreeNode root) {
      final List<List<Integer>> leftSequence = root.getLeft() != null
          ? allSequences(root.getLeft()) : List.of(List.of());

      final List<List<Integer>> rightSequence = root.getRight() != null
          ? allSequences(root.getRight()) : List.of(List.of());

      final List<Integer> prefix = List.of(root.getValue());

      return leftSequence.stream()
              .flatMap(left -> rightSequence.stream()
                      .flatMap(right -> weaveListsLambda(left, right, prefix).stream()))
              .toList();
    }

    // Exercise 4.10
    public static boolean isSubtree(final @NonNull TreeNode treeNode,
                                    final @NonNull TreeNode subTreeNode) {
      if (areIdentical(treeNode, subTreeNode)) {
        return true;
      }

      final boolean treeNodeLeft = Objects.nonNull(treeNode.getLeft())
          && isSubtree(treeNode.getLeft(), subTreeNode);
      final boolean treeNodeRight = Objects.nonNull(treeNode.getRight())
          && isSubtree(treeNode.getRight(), subTreeNode);

      return treeNodeLeft || treeNodeRight;
    }

    private static boolean areIdentical(final @NonNull TreeNode treeNode,
                                        final @NonNull TreeNode subTreeNode) {
      final boolean centralIdentical = Objects.equals(treeNode.getValue(), subTreeNode.getValue());

      final boolean leftIdentical = Objects.equals(treeNode.getLeft(), subTreeNode.getLeft())
          || (Objects.nonNull(treeNode.getLeft()) && Objects.nonNull(subTreeNode.getLeft())
          && areIdentical(treeNode.getLeft(), subTreeNode.getLeft()));

      final boolean rightIdentical = Objects.equals(treeNode.getRight(), subTreeNode.getRight())
          || (Objects.nonNull(treeNode.getRight()) && Objects.nonNull(subTreeNode.getRight())
          && areIdentical(treeNode.getRight(), subTreeNode.getRight()));

      return centralIdentical && leftIdentical && rightIdentical;
    }

    public static boolean isSubtreeLambda(final @NonNull TreeNode treeNode,
                                          final @NonNull TreeNode subTreeNode) {
      return Optional.of(treeNode)
              .filter(node -> areIdenticalLambda(node, subTreeNode))
              .map(n -> true)
              .orElseGet(() ->
                      Stream.of(
                              Optional.ofNullable(treeNode.getLeft())
                                      .filter(left -> isSubtreeLambda(left, subTreeNode)),
                              Optional.ofNullable(treeNode.getRight())
                                      .filter(right -> isSubtreeLambda(right, subTreeNode))
                      ).anyMatch(Optional::isPresent)
              );
    }

    static BiPredicate<TreeNode, TreeNode> compareChild = (treeNodeChild, subTreeNodeChild) ->
        Objects.equals(treeNodeChild, subTreeNodeChild)
            || (Objects.nonNull(treeNodeChild) && Objects.nonNull(subTreeNodeChild)
            && areIdenticalLambda(treeNodeChild, subTreeNodeChild));

    private static boolean areIdenticalLambda(final @NonNull TreeNode treeNode,
                                        final @NonNull TreeNode subTreeNode) {

      final boolean centralIdentical = Objects.equals(treeNode.getValue(), subTreeNode.getValue());

      final boolean leftIdentical =
          compareChild.test(treeNode.getLeft(), subTreeNode.getLeft());
      final boolean rightIdentical =
          compareChild.test(treeNode.getRight(), subTreeNode.getRight());

      return centralIdentical && leftIdentical && rightIdentical;
    }

    // Exercise 4.12
    public static int countPaths(final @NonNull TreeNode root, final int targetSum) {
      final int leftPath = Objects.nonNull(root.getLeft())
          ? countPaths(root.getLeft(), targetSum) : 0;
      final int rightPath = Objects.nonNull(root.getRight())
          ? countPaths(root.getRight(), targetSum) : 0;

      return countFromNode(root, targetSum) + leftPath + rightPath;
    }

    private static int countFromNode(final @NonNull TreeNode node, final int remainingSum) {
      final int count = Objects.equals(node.getValue(), remainingSum) ? 1 : 0;
      final int leftCount = Objects.nonNull(node.getLeft())
          ? countFromNode(node.getLeft(), remainingSum - node.getValue()) : 0;
      final int rightCount = Objects.nonNull(node.getRight())
          ? countFromNode(node.getRight(), remainingSum - node.getValue()) : 0;

      return count + leftCount + rightCount;
    }

  }

  // Exercise 4.7
  private static Set<Character> gatherAllDependencies(
          final @NonNull Map<Character, Collection<Character>> project) {
    final Set<Character> dependencies = new HashSet<>();

    for (Map.Entry<Character, Collection<Character>> entry : project.entrySet()) {
      dependencies.add(entry.getKey());
      dependencies.addAll(entry.getValue());
    }

    return dependencies;
  }

  private static Map<Character, Integer> initializeDependencyCalls(
          final @NonNull Map<Character, Collection<Character>> project,
          final @NonNull Set<Character> allDependencies) {
    final Map<Character, Integer> dependencyCalls = new HashMap<>();

    for (Character dependency : allDependencies) {
      dependencyCalls.putIfAbsent(dependency, 0);
    }

    for (Map.Entry<Character, Collection<Character>> entry : project.entrySet()) {
      final Character dependency = entry.getKey();

      entry.getValue().forEach(
          i -> dependencyCalls.put(dependency, dependencyCalls.get(dependency) + 1)
      );
    }

    return dependencyCalls;
  }

  private static Queue<Character> findInitialDependencies(
          final @NonNull Map<Character, Integer> dependencyCalls) {
    final Queue<Character> initialDependency = new LinkedList<>();
    for (Map.Entry<Character, Integer> entry : dependencyCalls.entrySet()) {
      if (Objects.equals(entry.getValue(), 0)) {
        initialDependency.add(entry.getKey());
      }
    }
    return initialDependency;
  }

  private static Map<Character, List<Character>> buildGraph(
          final @NonNull Map<Character, Collection<Character>> project,
          final @NonNull Set<Character> allDependencies) {
    final Map<Character, List<Character>> graphDependencies = new HashMap<>();

    for (Character dependency : allDependencies) {
      graphDependencies.putIfAbsent(dependency, new ArrayList<>());
    }

    for (Map.Entry<Character, Collection<Character>> entry : project.entrySet()) {
      final Character dependency = entry.getKey();
      for (Character sonDependency : entry.getValue()) {
        graphDependencies.get(sonDependency).add(dependency);
      }
    }
    return graphDependencies;
  }

  private static List<Character> resolveDependencies(
          final @NonNull Map<Character, List<Character>> graphDependencies,
          final @NonNull Map<Character, Integer> dependencyCalls,
          final @NonNull Queue<Character> nonCircularDependency) {
    final List<Character> dependencyOrder = new ArrayList<>();

    while (!nonCircularDependency.isEmpty()) {
      final Character dependency = nonCircularDependency.poll();
      dependencyOrder.add(dependency);

      for (Character neighbor : graphDependencies.get(dependency)) {
        dependencyCalls.put(neighbor, dependencyCalls.get(neighbor) - 1);
        if (Objects.equals(dependencyCalls.get(neighbor), 0)) {
          nonCircularDependency.add(neighbor);
        }
      }
    }

    return dependencyOrder;
  }

  private static void checkForCycles(final @NonNull Set<Character> allDependencies,
                                     final @NonNull List<Character> dependencyOrder) {
    final Set<Character> circularDependencies = allDependencies.stream()
            .filter(dependency -> !dependencyOrder.contains(dependency))
            .collect(Collectors.toUnmodifiableSet());

    if (!circularDependencies.isEmpty()) {
      throw new IllegalStateException("Non circular dependencies: " + dependencyOrder
          + " Cycle dependency detected: " + circularDependencies);
    }
  }

  public static Collection<Character> dependencyOrder(
          final @NonNull Map<Character, Collection<Character>> project) {
    final Set<Character> allDependencies = gatherAllDependencies(project);

    final Map<Character, Integer> dependencyCalls =
        initializeDependencyCalls(project, allDependencies);

    final Queue<Character> initialDependency =
        findInitialDependencies(dependencyCalls);

    final Map<Character, List<Character>> graphDependencies =
        buildGraph(project, allDependencies);

    final List<Character> dependencyOrder =
        resolveDependencies(graphDependencies, dependencyCalls, initialDependency);

    checkForCycles(allDependencies, dependencyOrder);

    return dependencyOrder;
  }

  private static Set<Character> gatherAllDependenciesLambda(
          final @NonNull Map<Character, Collection<Character>> project) {
    return project.entrySet().stream()
            .flatMap(entry ->
                    Stream.concat(Stream.of(entry.getKey()), entry.getValue().stream()))
            .collect(Collectors.toSet());
  }

  private static Map<Character, Integer> initializeDependencyCallsLambda(
          final @NonNull Map<Character, Collection<Character>> project,
          final @NonNull Set<Character> allDependencies) {
    final Map<Character, Integer> dependencyCalls = allDependencies.stream()
            .collect(Collectors.toMap(Function.identity(), value -> 0));

    project.forEach((dependency, dependents) ->
            dependents.forEach(d -> dependencyCalls.merge(dependency, 1, Integer::sum))
    );

    return dependencyCalls;
  }

  private static Queue<Character> findInitialDependenciesLambda(
          final @NonNull Map<Character, Integer> dependencyCalls) {
    return dependencyCalls.entrySet().stream()
            .filter(entry -> Objects.equals(entry.getValue(), 0))
            .map(Map.Entry::getKey)
            .collect(Collectors.toCollection(LinkedList::new));
  }

  private static Map<Character, List<Character>> buildGraphLambda(
          final @NonNull Map<Character, Collection<Character>> project,
          final @NonNull Set<Character> allDependencies) {
    final Map<Character, List<Character>> graphDependencies = allDependencies.stream()
            .collect(Collectors.toMap(Function.identity(), value -> new ArrayList<>()));

    project.forEach((dependency, dependents) -> dependents.forEach(sonDependency ->
            graphDependencies.get(sonDependency).add(dependency))
    );

    return graphDependencies;
  }

  private static List<Character> resolveDependenciesLambda(
          final @NonNull Map<Character, List<Character>> graphDependencies,
          final @NonNull Map<Character, Integer> dependencyCalls,
          final @NonNull Queue<Character> nonCircularDependency) {

    final List<Character> dependencyOrder = new ArrayList<>();

    while (!nonCircularDependency.isEmpty()) {
      Character dependency = nonCircularDependency.poll();
      dependencyOrder.add(dependency);

      graphDependencies.getOrDefault(dependency, List.of()).forEach(neighbor -> {
        dependencyCalls.merge(neighbor, -1, Integer::sum);
        if (Objects.equals(dependencyCalls.get(neighbor), 0)) {
          nonCircularDependency.add(neighbor);
        }
      });
    }

    return dependencyOrder;
  }


  public static Collection<Character> dependencyOrderLambda(
          final @NonNull Map<Character, Collection<Character>> project) {
    final Set<Character> allDependencies = gatherAllDependenciesLambda(project);

    final Map<Character, Integer> dependencyCalls =
        initializeDependencyCallsLambda(project, allDependencies);

    final Queue<Character> initialDependency =
        findInitialDependenciesLambda(dependencyCalls);

    final Map<Character, List<Character>>
        graphDependencies = buildGraphLambda(project, allDependencies);

    final List<Character> dependencyOrder =
        resolveDependenciesLambda(graphDependencies, dependencyCalls, initialDependency);

    checkForCycles(allDependencies, dependencyOrder);

    return dependencyOrder;
  }

  // Exercise 4.11
  @Builder
  public static class RandomNode {

    @NonNull
    TreeNode root;

    @Getter
    private static final Random random = new Random();

    @Value
    @Builder(toBuilder = true)
    public static class TreeNode {
      int value;
      TreeNode left;
      TreeNode right;
      int size;
    }

    public Optional<TreeNode> find(final int value) {
      if (value < this.root.getValue()) {
        return Objects.nonNull(this.root.getLeft())
            ? this.find(this.root.getLeft().getValue()) : Optional.empty();
      }
      if (value > this.root.getValue()) {
        return Objects.nonNull(this.root.getRight())
            ? this.find(this.root.getRight().getValue()) : Optional.empty();
      }

      return Optional.of(this.root);
    }

    public TreeNode insert(final int value) {
      return insert(this.root, value);
    }

    private TreeNode insert(final @NonNull TreeNode node, final int value) {
      if (Objects.equals(value, node.getValue())) {
        return node;
      }

      TreeNode.TreeNodeBuilder builder = node.toBuilder();

      if (value < node.getValue()) {
        builder.left(
            Objects.isNull(node.getLeft())
                ? TreeNode.builder().value(value).build()
                : insert(node.getLeft(), value)
        );
      }

      if (value > node.getValue()) {
        builder.right(
            Objects.isNull(node.getRight())
                ? TreeNode.builder().value(value).build()
                : insert(node.getRight(), value)
        );
      }

      TreeNode updatedNode = builder.build();

      updatedNode.toBuilder().size(1
              + (Objects.nonNull(node.getLeft()) ? node.getLeft().getSize() : 0)
              + (Objects.nonNull(node.getRight()) ? node.getRight().getSize() : 0))
              .build();

      return updatedNode;
    }

    public TreeNode delete(final int value) {
      return delete(this.root, value);
    }

    private TreeNode delete(final @NonNull TreeNode node, final int val) {
      if (Objects.equals(val, node.getValue())) {
        if (Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight())) {
          return null;
        }
        if (Objects.isNull(node.getLeft())) {
          return node.getRight();
        }
        if (Objects.isNull(node.getRight())) {
          return node.getLeft();
        }

        TreeNode successor = extractMin(node.getRight());

        return successor.toBuilder()
                .left(node.getLeft())
                .right(node.getRight() != successor ? node.getRight() : null)
                .build();
      }

      if (val < node.getValue()) {
        return Objects.nonNull(node.getLeft()) ? delete(node.getLeft(), val) : null;
      }

      return Objects.nonNull(node.getRight()) ? delete(node.getRight(), val) : null;
    }

    private TreeNode extractMin(final @NonNull TreeNode node) {
      return Objects.isNull(node.getLeft()) ? node : extractMin(node.getLeft());
    }

    public Optional<TreeNode> getRandomNode() {
      return getRandomNode(this.root, random.nextInt(this.root.getSize()));
    }

    private Optional<TreeNode> getRandomNode(final @NonNull TreeNode node, final int randomIndex) {
      final int leftSize = Objects.nonNull(node.getLeft()) ? node.getLeft().getSize() : 0;

      if (Objects.equals(randomIndex, leftSize)) {
        return Optional.of(node);
      }

      if (randomIndex < leftSize) {
        return Objects.nonNull(node.getLeft())
            ? getRandomNode(node.getLeft(), randomIndex) : Optional.empty();
      }

      return Objects.nonNull(node.getRight())
          ? getRandomNode(node.getRight(), randomIndex) : Optional.empty();
    }

  }
}
