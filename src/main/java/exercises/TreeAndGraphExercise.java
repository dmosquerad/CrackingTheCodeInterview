package exercises;

import java.awt.geom.Point2D;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
      Set<Graph.Node> neighbours = new HashSet<>();
    }

    public static boolean hasRouteBfs(final @NonNull Node start, final @NonNull Node end) {
      if (end.equals(start)) {
        return true;
      }

      Set<Node> visited = new HashSet<>();
      Deque<Node> queue = new ArrayDeque<>();

      queue.add(start);
      visited.add(start);

      while (!queue.isEmpty()) {
        Node current = queue.poll();

        for (Node neighbour : current.getNeighbours()) {
          if (visited.contains(neighbour)) {
            continue;
          }

          if (neighbour.equals(end)) {
            return true;
          }

          visited.add(neighbour);
          queue.add(neighbour);
        }
      }

      return false;
    }

    public static boolean hasRouteBfsLambda(final @NonNull Node start, final @NonNull Node end) {
      if (end.equals(start)) {
        return true;
      }

      Set<Node> visited = new HashSet<>();
      Deque<Node> queue = new ArrayDeque<>();

      queue.add(start);
      visited.add(start);

      while (!queue.isEmpty()) {
        Node current = queue.poll();

        if (current.getNeighbours().stream().filter(neighbour -> !visited.contains(neighbour))
            .peek(neighbour -> {
              synchronized (visited) {
                visited.add(neighbour);
              }
              synchronized (queue) {
                queue.add(neighbour);
              }
            }).anyMatch(neighbour -> neighbour.equals(end))) {
          return true;
        }
      }

      return false;
    }

    public static boolean hasRouteDfs(final @NonNull Node start, final @NonNull Node end) {
      Set<Node> visited = new HashSet<>();
      return dfs(start, end, visited);
    }

    public static boolean hasRouteDfsLambda(final @NonNull Node start, final @NonNull Node end) {
      Set<Node> visited = new HashSet<>();
      return dfsLambda(start, end, visited);
    }


    private static boolean dfs(final @NonNull Node start, final @NonNull Node end,
                        final @NonNull Collection<Node> visited) {
      if (end.equals(start)) {
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
      if (end.equals(start)) {
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
      if (end.equals(start)) {
        return true;
      }

      Set<Node> visited = new HashSet<>();
      PriorityQueue<NodeWithPriority> priorityQueue =
          new PriorityQueue<>(Comparator.comparingInt(NodeWithPriority::getPriority));

      priorityQueue.add(NodeWithPriority.builder()
          .node(start)
          .priority(heuristic.heuristic(start, end))
          .build());

      while (!priorityQueue.isEmpty()) {
        Node currentNode = priorityQueue.poll().getNode();

        if (!visited.contains(currentNode)) {
          if (currentNode.equals(end)) {
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
      if (end.equals(start)) {
        return true;
      }

      PriorityQueue<NodeWithPriority> priorityQueue =
          new PriorityQueue<>(Comparator.comparingInt(NodeWithPriority::getPriority));

      priorityQueue.add(NodeWithPriority.builder()
          .node(start)
          .priority(heuristic.heuristic(start, end))
          .build());

      while (!priorityQueue.isEmpty()) {
        Node currentNode = priorityQueue.poll().getNode();

        if (currentNode.equals(end)) {
          return true;
        }

        Set<Node> unvisitedNeighbours = currentNode.getNeighbours().stream()
            .filter(neighbour -> priorityQueue.stream()
                .noneMatch(priorityNeighbour -> priorityNeighbour.getNode().equals(neighbour)))
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
      if (end.equals(start)) {
        return true;
      }

      Set<Node> visited = new HashSet<>();

      PriorityQueue<NodeWithCost> priorityQueue =
          new PriorityQueue<>(Comparator.comparingInt(NodeWithCost::getTotalCost));

      Map<Node, Integer> pathCosts = new HashMap<>();
      pathCosts.put(start, 0);

      priorityQueue.add(NodeWithCost.builder()
          .node(start)
          .pathCost(0)
          .heuristicCost(heuristic.heuristic(start, end))
          .build());

      while (!priorityQueue.isEmpty()) {
        Node currentNode = priorityQueue.poll().getNode();
        if (visited.contains(currentNode)) {
          continue;
        }

        if (currentNode.equals(end)) {
          return true;
        }

        visited.add(currentNode);

        for (Node neighbour : currentNode.getNeighbours()) {
          if (visited.contains(neighbour)) {
            continue;
          }

          int edgeCost = 1;

          int tentativePathCost = pathCosts.get(currentNode) + edgeCost;

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
      if (end.equals(start)) {
        return true;
      }

      Set<Node> visited = new HashSet<>();

      PriorityQueue<NodeWithCost> priorityQueue =
          new PriorityQueue<>(Comparator.comparingInt(NodeWithCost::getTotalCost));

      Map<GraphWithDistance.Node, Integer> pathCosts = new HashMap<>();
      pathCosts.put(start, 0);

      priorityQueue.add(NodeWithCost.builder()
          .node(start)
          .pathCost(0)
          .heuristicCost(heuristic.heuristic(start, end))
          .build());

      while (!priorityQueue.isEmpty()) {
        GraphWithDistance.Node currentNode = priorityQueue.poll().getNode();

        if (visited.contains(currentNode)) {
          continue;
        }

        if (currentNode.equals(end)) {
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
      double dx = Math.abs(current.coordinates.getX() - goal.coordinates.getX());
      double dy = Math.abs(current.coordinates.getY() - goal.coordinates.getY());
      return (int) (dx + dy);
    }
  }

  public static class EuclideanHeuristic implements Heuristic {
    @Override
    public int heuristic(final GraphWithDistance.Node current, final GraphWithDistance.Node goal) {
      double dx = current.getCoordinates().getX() - goal.getCoordinates().getX();
      double dy = current.getCoordinates().getY() - goal.getCoordinates().getY();
      return (int) Math.sqrt(dx * dx + dy * dy);
    }
  }

  public static class ChebyshevHeuristic implements Heuristic {

    @Override
    public int heuristic(final GraphWithDistance.Node current, final GraphWithDistance.Node goal) {
      double dx = Math.abs(current.getCoordinates().getX() - goal.getCoordinates().getX());
      double dy = Math.abs(current.getCoordinates().getY() - goal.getCoordinates().getY());
      return (int) Math.max(dx, dy);
    }
  }

}
