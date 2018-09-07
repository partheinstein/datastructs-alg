import java.util.ArrayList;

/**
 * Binary Search Tree (sorted, unbalanced)
 */
public class BST {
  private class Node {
    int key; // sort key
    Node left = null;
    Node right = null;
    Node(int key) {
      this.key = key;
    }
  }

  private Node root = null;

  @Override
  public String toString() {
    return toString(root);
  }

  private String toString(Node n) {
    String s = "";
    if (n != null) {
      s = " " + n.key;
      s += toString(n.left);
      s += toString(n.right);
    }
    return s;
  }

  public boolean insert(int key) {
    Node n = new Node(key);
 
    if (root == null) {
      root = n;
      return true;
    }

    boolean wentLeft = false;
    Node p = root;
    Node c = root;
    while (c != null && c.key != key) {
      p = c;
      if (key < c.key) {
        c = c.left;
        wentLeft = true;
      } else {
        c = c.right;
        wentLeft = false;
      }
    }

    if (c != null && c.key == key) {
      return false;
    }

    if (wentLeft) {
      p.left = n;
    } else {
      p.right = n;
    }

    return true;
  }

  public boolean search(int key) {
    return search(key, root);
  }

  private boolean search(int key, Node n) {
    if (n == null) {
      return false;
    }

    if (n.key == key) {
      return true;
    }

    if (key < n.key) {
      return search(key, n.left);
    } else {
      return search(key, n.right);
    }
  }

  public ArrayList<Integer> getPath(int key) {
    return getPath(key, root, new ArrayList<>());
  }

  public ArrayList<Integer> getPath(int key, Node n, ArrayList<Integer> path) {
    if (n == null) {
      return null;
    }

    path.add(n.key);

    if (key == n.key) {
      return path;
    }

    if (key < n.key) {
      return getPath(key, n.left, path);
    } else {
      return getPath(key, n.right, path);
    }
      
  }

  public boolean delete(int key) {
    if (root == null) {
      return false;
    }

    Node p = root;
    Node c = root;
    
    boolean wentLeft = false;
    while (c != null && c.key != key) {
      p = c;
      if (key < c.key) {
        c = c.left;
        wentLeft = true;
      } else {
        c = c.right;
        wentLeft = false;
      }
    }

    if (c == null || c.key != key) {
      return false;
    }

    // c.key == key
    // c is the node to be deleted

    if (c.left == null || c.right == null) {

      // 1 leaf or no leaf node
      Node n = (c.left != null)? c.left : c.right;
      if (wentLeft) {
        p.left = n;
      } else {
        p.right = n;
      }

   } else {
      // 2 leaf nodes
      // go left and then right all the way to the end
      Node n = c; // n is the actual node to be deleted but we will delete c instead
      p = c;
      c = c.left;
      wentLeft = true;
      while (c.right != null) {
        p = c;
        c = c.right;
        wentLeft = false;
      }
      
      n.key = c.key;
      if (wentLeft) {
        p.left = null;
      } else {
        p.right = null;
      }
    }

    return true;
  }

  public static void testSanity() {
    BST tree = new BST();

    tree.insert(100);
    tree.insert(1);
    tree.insert(1000);
    tree.insert(2000);
    tree.insert(500);
    tree.insert(5);

    System.out.println("tests:");
    System.out.println(tree.toString() + " " + tree.toString().equals(" 100 1 5 1000 500 2000"));
    System.out.println(tree.insert(100) == false);
    System.out.println(tree.search(400) == false);
    System.out.println(tree.search(1000) == true);

    tree.delete(1000);
    System.out.println(tree.toString() + " " + tree.toString().equals(" 100 1 5 500 2000"));
    tree.delete(1);
    System.out.println(tree.toString() + " " + tree.toString().equals(" 100 5 500 2000"));
    tree.delete(100);
    System.out.println(tree.toString() + " " + tree.toString().equals(" 5 500 2000"));

    tree = new BST();

    tree.insert(100);
    tree.insert(1);
    tree.insert(1000);
    tree.insert(2000);
    tree.insert(500);
    tree.insert(5);

    tree.delete(100);
    System.out.println(tree.toString() + " " + tree.toString().equals(" 5 1 1000 500 2000"));
  }

  public static void testGetPathEmptyTree() {
    BST tree = new BST();
    String s = "testGetPathEmptyTree: " + (tree.getPath(1) == null);
    System.out.println(s);
  }

  public static void testGetPath1Node() {
    BST tree = new BST();
    tree.insert(1);
    ArrayList<Integer> path = tree.getPath(1);
    String s = "testGetPath1Node: " + (path.get(0).equals(1) && path.size() == 1);
    System.out.println(s);
  }

  public static void testGetPath(BST tree, int key, int expectedSize) {

    ArrayList<Integer> path = tree.getPath(key);
    String pathStr = "";
    for (Integer k : path) {
      pathStr = pathStr + k.toString() + " ";
    }

    String s = "testGetPath1: " + pathStr + " " + (path.size() == expectedSize);
    System.out.println(s);
  }

  public static void main(String[] args) {
    testGetPathEmptyTree();
    testGetPath1Node();

    BST tree = new BST();

    tree.insert(100);
    tree.insert(1);
    tree.insert(1000);
    tree.insert(2000);
    tree.insert(500);
    tree.insert(5);
    
    testGetPath(tree, 100, 1);
    testGetPath(tree, 2000, 3);
    testGetPath(tree, 500, 3);
    testGetPath(tree, 5, 3);
  }
}
    
        
