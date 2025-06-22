package exercises;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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

  @Nested
  class TreeNodeExerciseTest {

    // Exercise 4.2
    @Test
    void createMinimalBst_nullPointerException() {
      final Collection<Integer> value = null;

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.createMinimalBst(value));
    }

    @Test
    void createMinimalBst_ok() {
      final Collection<Integer> value = List.of(1, 3);

      final Optional<TreeAndGraphExercise.TreeNodeExercise.TreeNode> expected =
              Optional.of(TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .right(TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                              .value(3)
                              .build())
                      .build());

      Assertions.assertEquals(expected, TreeAndGraphExercise.TreeNodeExercise.createMinimalBst(value));
    }

    // Exercise 4.3
    @Test
    void createLevelLinkedList_nullPointerException() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value = null;

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.createLevelLinkedList(value));

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.createLevelLinkedListLambda(value));
    }

    @Test
    void createLevelLinkedList_ok() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .right(childrenTreeNode)
                      .left(childrenTreeNode)
                      .build();

      final Collection<Collection<TreeAndGraphExercise.TreeNodeExercise.TreeNode>> expected =
              List.of(List.of(value), List.of(childrenTreeNode, childrenTreeNode));

      Assertions.assertEquals(expected, TreeAndGraphExercise.TreeNodeExercise.createLevelLinkedList(value));
      Assertions.assertEquals(expected, TreeAndGraphExercise.TreeNodeExercise.createLevelLinkedListLambda(value));
    }

    // Exercise 4.4
    @Test
    void isBalanced_nullPointerException() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value = null;

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.isBalanced(value));

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.isBalanced2(value));
    }

    @Test
    void isBalanced_ko() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode4 =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(4)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode3 =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .right(childrenTreeNode4)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .right(childrenTreeNode3)
                      .build();

      Assertions.assertFalse(TreeAndGraphExercise.TreeNodeExercise.isBalanced(value));
      Assertions.assertFalse(TreeAndGraphExercise.TreeNodeExercise.isBalanced2(value));
    }

    @Test
    void isBalanced_ok() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .right(childrenTreeNode)
                      .left(childrenTreeNode)
                      .build();

      Assertions.assertTrue(TreeAndGraphExercise.TreeNodeExercise.isBalanced(value));
      Assertions.assertTrue(TreeAndGraphExercise.TreeNodeExercise.isBalanced2(value));
    }

    // Exercise 4.5
    @Test
    void validatedBTS_nullPointerException() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value = null;

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.isValidBst(value));
    }

    @Test
    void validatedBTS_ok() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .right(childrenTreeNode)
                      .build();

      Assertions.assertTrue(TreeAndGraphExercise.TreeNodeExercise.isValidBst(value));
    }

    @Test
    void validatedBTS_ko() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .left(childrenTreeNode)
                      .build();

      Assertions.assertFalse(TreeAndGraphExercise.TreeNodeExercise.isValidBst(value));
    }

    // Exercise 4.6
    @Test
    void findInOrderSuccessor_nullPointerException() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value = null;

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.findInOrderSuccessor(value,value));
    }

    @Test
    void findInOrderSuccessor_ko() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .right(childrenTreeNode)
                      .build();

        Assertions.assertEquals(Optional.empty(),
                TreeAndGraphExercise.TreeNodeExercise.findInOrderSuccessor(value, centralTreeNode));
    }

    @Test
    void findInOrderSuccessor_ok() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .left(childrenTreeNode)
                      .build();

      Assertions.assertEquals(Optional.of(value),
              TreeAndGraphExercise.TreeNodeExercise.findInOrderSuccessor(value, childrenTreeNode));
    }

    // Exercise 4.8
    @Test
    void findCommonAncestor_nullPointerException() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value = null;

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.findCommonAncestor(value,value));
    }

    @Test
    void findCommonAncestor_ko() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .right(childrenTreeNode)
                      .build();

      Assertions.assertEquals(Optional.empty(),
              TreeAndGraphExercise.TreeNodeExercise.findCommonAncestor(value, centralTreeNode));
    }

    @Test
    void findCommonAncestor_ok() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .left(childrenTreeNode)
                      .build();

      Assertions.assertEquals(Optional.of(childrenTreeNode),
              TreeAndGraphExercise.TreeNodeExercise.findCommonAncestor(value, childrenTreeNode));
    }

    //Exercise 4.9
    @Test
    void allSequences_nullPointerException() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value = null;

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.allSequences(value));

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.allSequencesLambda(value));
    }

    @Test
    void allSequences_ok() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenLeftTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenRightTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(4)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .left(childrenLeftTreeNode)
                      .right(childrenRightTreeNode)
                      .build();

      Assertions.assertEquals(List.of(List.of(3, 1, 4), List.of(3, 4, 1)),
              TreeAndGraphExercise.TreeNodeExercise.allSequences(value));
      Assertions.assertEquals(List.of(List.of(3, 1, 4), List.of(3, 4, 1)),
              TreeAndGraphExercise.TreeNodeExercise.allSequencesLambda(value));
    }

    //Exercise 4.10
    @Test
    void isSubtree_nullPointerException() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value = null;

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.isSubtree(value, value));
      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.isSubtreeLambda(value, value));
    }

    @Test
    void isSubtree_true() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .right(childrenTreeNode)
                      .build();

      Assertions.assertTrue(TreeAndGraphExercise.TreeNodeExercise.isSubtree(value, childrenTreeNode));
      Assertions.assertTrue(TreeAndGraphExercise.TreeNodeExercise.isSubtreeLambda(value, childrenTreeNode));
    }

    @Test
    void isSubtree_false() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .right(childrenTreeNode)
                      .build();

      Assertions.assertFalse(TreeAndGraphExercise.TreeNodeExercise.isSubtree(value, centralTreeNode));
      Assertions.assertFalse(TreeAndGraphExercise.TreeNodeExercise.isSubtreeLambda(value, centralTreeNode));
    }

    //Exercise 4.12
    @Test
    void countPaths_nullPointerException() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value = null;

      Assertions.assertThrows(NullPointerException.class, () ->
              TreeAndGraphExercise.TreeNodeExercise.countPaths(value, 0));
    }

    @Test
    void countPath_zero() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNodeRight =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNodeLeft =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(4)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .right(childrenTreeNodeRight)
                      .left(childrenTreeNodeLeft)
                      .build();

      Assertions.assertEquals(TreeAndGraphExercise.TreeNodeExercise.countPaths(value, 2), 0);
    }

    @Test
    void countPath_nonZero() {
      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNodeRight =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(1)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode childrenTreeNodeLeft =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(4)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode centralTreeNode =
              TreeAndGraphExercise.TreeNodeExercise.TreeNode.builder()
                      .value(3)
                      .build();

      final TreeAndGraphExercise.TreeNodeExercise.TreeNode value =
              centralTreeNode.toBuilder()
                      .right(childrenTreeNodeRight)
                      .left(childrenTreeNodeLeft)
                      .build();

      Assertions.assertEquals(TreeAndGraphExercise.TreeNodeExercise.countPaths(value, 4), 2);
    }
  }

  // Exercise 4.7
  @Test
  void dependencyOrder_nullPointerException() {
    final HashMap<Character, Collection<Character>> projects = null;

    Assertions.assertThrows(NullPointerException.class, () -> TreeAndGraphExercise.dependencyOrder(projects));
    Assertions.assertThrows(NullPointerException.class, () -> TreeAndGraphExercise.dependencyOrderLambda(projects));
  }

  @Test
  void dependencyOrder_ok() {
    final HashMap<Character, Collection<Character>> projects = new HashMap<>();
    projects.put('A', List.of('B', 'C'));
    projects.put('B', List.of('C'));
    projects.put('D', List.of('B'));

    Collection<Character> expected = List.of('C', 'B', 'A', 'D');

    Assertions.assertEquals(expected, TreeAndGraphExercise.dependencyOrder(projects));
    Assertions.assertEquals(expected, TreeAndGraphExercise.dependencyOrderLambda(projects));
  }

  @Test
  void dependencyOrder_exception() {
    final HashMap<Character, Collection<Character>> projects = new HashMap<>();
    projects.put('A', List.of('C'));
    projects.put('B', List.of('B'));
    projects.put('C', List.of('A', 'D'));

    Assertions.assertThrows(IllegalStateException.class, () -> TreeAndGraphExercise.dependencyOrder(projects));
    Assertions.assertThrows(IllegalStateException.class, () -> TreeAndGraphExercise.dependencyOrderLambda(projects));
  }

  @Nested
  class RandomNodeTest {

    @Test
    void find_nullPointerException() {
      TreeAndGraphExercise.RandomNode randomNode = null;
      int value = 1;

      Assertions.assertThrows(NullPointerException.class, () -> randomNode.find(value));
    }

    @Test
    void find_ok() {
      int value = 10;
      TreeAndGraphExercise.RandomNode.TreeNode treeNode = TreeAndGraphExercise.RandomNode.TreeNode.builder()
              .value(value)
              .size(1)
              .build();
      TreeAndGraphExercise.RandomNode randomNode = TreeAndGraphExercise.RandomNode.builder()
              .root(treeNode)
              .build();

      Assertions.assertEquals(randomNode.find(value), Optional.of(treeNode));
    }

    @Test
    void find_ko() {
      int value = 10;
      TreeAndGraphExercise.RandomNode.TreeNode treeNode = TreeAndGraphExercise.RandomNode.TreeNode.builder()
              .value(value)
              .size(1)
              .build();
      TreeAndGraphExercise.RandomNode randomNode = TreeAndGraphExercise.RandomNode.builder()
              .root(treeNode)
              .build();

      Assertions.assertEquals(randomNode.find(8), Optional.empty());
    }

    @Test
    void insert_nullPointerException() {
      TreeAndGraphExercise.RandomNode randomNode = null;
      int value = 1;

      Assertions.assertThrows(NullPointerException.class, () -> randomNode.insert(value));
    }

    @Test
    void inserted_before() {
      int value = 10;
      TreeAndGraphExercise.RandomNode.TreeNode treeNode = TreeAndGraphExercise.RandomNode.TreeNode.builder()
              .value(value)
              .size(1)
              .build();
      TreeAndGraphExercise.RandomNode randomNode = TreeAndGraphExercise.RandomNode.builder()
              .root(treeNode)
              .build();

      Assertions.assertEquals(randomNode.insert(value), treeNode);
    }

    @Test
    void insertLeft_ok() {
      int value = 8;
      TreeAndGraphExercise.RandomNode.TreeNode treeNode = TreeAndGraphExercise.RandomNode.TreeNode.builder()
              .value(10)
              .size(1)
              .build();
      TreeAndGraphExercise.RandomNode randomNode = TreeAndGraphExercise.RandomNode.builder()
              .root(treeNode)
              .build();
      TreeAndGraphExercise.RandomNode.TreeNode expectedTreeNode = treeNode.toBuilder()
              .left(TreeAndGraphExercise.RandomNode.TreeNode.builder()
                      .value(value)
                      .build())
              .build();

      Assertions.assertEquals(randomNode.insert(value), expectedTreeNode);
    }

    @Test
    void insertRight_ok() {
      int value = 11;
      TreeAndGraphExercise.RandomNode.TreeNode treeNode = TreeAndGraphExercise.RandomNode.TreeNode.builder()
              .value(10)
              .size(1)
              .build();
      TreeAndGraphExercise.RandomNode randomNode = TreeAndGraphExercise.RandomNode.builder()
              .root(treeNode)
              .build();
      TreeAndGraphExercise.RandomNode.TreeNode expectedTreeNode = treeNode.toBuilder()
              .right(TreeAndGraphExercise.RandomNode.TreeNode.builder()
                      .value(value)
                      .build())
              .build();

      Assertions.assertEquals(randomNode.insert(value), expectedTreeNode);
    }

    @Test
    void delete_nullPointerException() {
      TreeAndGraphExercise.RandomNode randomNode = null;
      int value = 1;

      Assertions.assertThrows(NullPointerException.class, () -> randomNode.delete(value));
    }

    @Test
    void delete_ok() {
      int value = 10;

      TreeAndGraphExercise.RandomNode.TreeNode treeNodeNonDelete = TreeAndGraphExercise.RandomNode.TreeNode.builder()
              .value(2)
              .build();

      TreeAndGraphExercise.RandomNode.TreeNode treeNode = TreeAndGraphExercise.RandomNode.TreeNode.builder()
              .value(value)
              .right(treeNodeNonDelete)
              .size(1)
              .build();

      TreeAndGraphExercise.RandomNode randomNode = TreeAndGraphExercise.RandomNode.builder()
              .root(treeNode)
              .build();

      Assertions.assertEquals(randomNode.delete(value), treeNodeNonDelete);
    }

    @Test
    void randomNode_nullPointerException() {
      TreeAndGraphExercise.RandomNode randomNode = null;

      Assertions.assertThrows(NullPointerException.class, () -> randomNode.getRandomNode());
    }

    @Test
    void randomNode_ok() {
      int value = 10;
      TreeAndGraphExercise.RandomNode.TreeNode treeNode = TreeAndGraphExercise.RandomNode.TreeNode
          .builder()
          .value(value)
          .size(1)
          .build();

      TreeAndGraphExercise.RandomNode randomNode = TreeAndGraphExercise.RandomNode.builder()
              .root(treeNode)
              .build();

      Assertions.assertEquals(randomNode.getRandomNode(), Optional.of(treeNode));
    }
  }
}
