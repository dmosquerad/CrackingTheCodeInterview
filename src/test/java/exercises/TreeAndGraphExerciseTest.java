package exercises;

import static exercises.ArraysAndStringExercise.checkPermutation;
import static exercises.ArraysAndStringExercise.checkPermutationLambda;

import java.awt.geom.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TreeAndGraphExerciseTest {

  @Nested
  class GraphTest {

    static TreeAndGraphExercise.Graph.Node node0 = TreeAndGraphExercise.Graph.Node.builder()
        .name("zero")
        .build();

    static TreeAndGraphExercise.Graph.Node node1 = TreeAndGraphExercise.Graph.Node.builder()
        .name("one")
        .build();

    static TreeAndGraphExercise.Graph.Node node2 = TreeAndGraphExercise.Graph.Node.builder()
        .name("two")
        .build();

    static TreeAndGraphExercise.Graph.Node node3 = TreeAndGraphExercise.Graph.Node.builder()
        .name("three")
        .build();

    @BeforeAll
    static void setUp() {
      node0.getNeighbours().add(node1);
      node1.getNeighbours().add(node2);
      node2.getNeighbours().add(node0);
      node2.getNeighbours().add(node3);
    }

    @Test
    void hasRoute_nullPointerException() {
      final TreeAndGraphExercise.Graph.Node value = null;

      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.Graph.hasRouteBfs(value, value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.Graph.hasRouteBfsLambda(value, value));

      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.Graph.hasRouteDfs(value, value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.Graph.hasRouteDfsLambda(value, value));
    }

    @Test
    void hasRoute_ok() {
      Assertions.assertTrue(TreeAndGraphExercise.Graph.hasRouteBfs(node1, node3));
      Assertions.assertTrue(TreeAndGraphExercise.Graph.hasRouteBfsLambda(node1, node3));

      Assertions.assertTrue(TreeAndGraphExercise.Graph.hasRouteDfs(node1, node3));
      Assertions.assertTrue(TreeAndGraphExercise.Graph.hasRouteDfsLambda(node1, node3));
    }

    @Test
    void hasRoute_ko() {
      Assertions.assertFalse(TreeAndGraphExercise.Graph.hasRouteBfs(node3, node1));
      Assertions.assertFalse(TreeAndGraphExercise.Graph.hasRouteBfsLambda(node3, node1));

      Assertions.assertFalse(TreeAndGraphExercise.Graph.hasRouteDfs(node3, node1));
      Assertions.assertFalse(TreeAndGraphExercise.Graph.hasRouteDfsLambda(node3, node1));
    }
  }

  @Nested
  class GraphWithDistanceTest {
    static TreeAndGraphExercise.GraphWithDistance.Node node0 =
        TreeAndGraphExercise.GraphWithDistance.Node.builder()
            .name("zero")
            .coordinates(new Point2D.Double(0, 1))
            .build();

    static TreeAndGraphExercise.GraphWithDistance.Node node1 =
        TreeAndGraphExercise.GraphWithDistance.Node.builder()
            .name("one")
            .coordinates(new Point2D.Double(1, 1))
            .build();

    static TreeAndGraphExercise.GraphWithDistance.Node node2 =
        TreeAndGraphExercise.GraphWithDistance.Node.builder()
            .name("two")
            .coordinates(new Point2D.Double(2, 1))
            .build();

    static TreeAndGraphExercise.GraphWithDistance.Node node3 =
        TreeAndGraphExercise.GraphWithDistance.Node.builder()
            .name("three")
            .coordinates(new Point2D.Double(3, 1))
            .build();

    @BeforeAll
    static void setUp() {
      node0.getNeighbours().add(node1);
      node1.getNeighbours().add(node2);
      node2.getNeighbours().add(node0);
      node2.getNeighbours().add(node3);
    }

    @Test
    void hasRoute_nullPointerException() {
      final TreeAndGraphExercise.GraphWithDistance.Node value = null;

      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstManhattanHeuristic(value, value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstEuclideanHeuristic(value, value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstChebyshevHeuristic(value, value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstManhattanHeuristicLambda(value,
              value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstEuclideanHeuristicLambda(value,
              value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstChebyshevHeuristicLambda(value,
              value));

      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarManhattanHeuristic(value, value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarEuclideanHeuristic(value, value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarChebyshevHeuristic(value, value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarManhattanHeuristicLambda(value,
              value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarEuclideanHeuristicLambda(value,
              value));
      Assertions.assertThrows(NullPointerException.class, () ->
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarChebyshevHeuristicLambda(value,
              value));
    }

    @Test
    void hasRoute_ok() {
      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstManhattanHeuristic(node1, node3));
      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstEuclideanHeuristic(node1, node3));
      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstChebyshevHeuristic(node1, node3));
      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstManhattanHeuristicLambda(node1,
              node3));
      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstEuclideanHeuristicLambda(node1,
              node3));
      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstChebyshevHeuristicLambda(node1,
              node3));

      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarManhattanHeuristic(node1, node3));
      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarEuclideanHeuristic(node1, node3));
      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarChebyshevHeuristic(node1, node3));
      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarManhattanHeuristicLambda(node1,
              node3));
      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarEuclideanHeuristicLambda(node1,
              node3));
      Assertions.assertTrue(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarChebyshevHeuristicLambda(node1,
              node3));

    }

    @Test
    void hasRoute_ko() {
      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstManhattanHeuristic(node3, node1));
      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstEuclideanHeuristic(node3, node1));
      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstChebyshevHeuristic(node3, node1));
      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstManhattanHeuristicLambda(node3, node1));
      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstEuclideanHeuristicLambda(node3, node1));
      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteBestFirstChebyshevHeuristicLambda(node3, node1));

      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarManhattanHeuristic(node3, node1));
      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarEuclideanHeuristic(node3, node1));
      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarChebyshevHeuristic(node3, node1));
      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarManhattanHeuristicLambda(node3,
              node1));
      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarEuclideanHeuristicLambda(node3,
              node1));
      Assertions.assertFalse(
          TreeAndGraphExercise.GraphWithDistance.hasRouteAstarChebyshevHeuristicLambda(node3,
              node1));
    }

  }

}
