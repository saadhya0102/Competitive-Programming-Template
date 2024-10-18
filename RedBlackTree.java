
enum Color {
RED, BLACK
}

// Node Class
class Node<T extends Comparable<T>> {
T data;
Node<T> left;
Node<T> right;
Node<T> parent;
Color color;

Node(T data) {
    this.data = data;
  
    this.color = Color.RED; 
    this.left = null;
    this.right = null;
    this.parent = null;
}
}

public class RedBlackTree<T extends Comparable<T>> {
private Node<T> root;
private final Node<T> TNULL; 

// Constructor to initialize the Red-Black Tree
public RedBlackTree() {
    TNULL = new Node<>(null);
    TNULL.color = Color.BLACK;
    root = TNULL;
}

private void preOrderHelper(Node<T> node) {
    if (node != TNULL) {
        System.out.print(node.data + " ");
        preOrderHelper(node.left);
        preOrderHelper(node.right);
    }
}

public void preorder() {
    preOrderHelper(this.root);
}

private void inOrderHelper(Node<T> node) {
    if (node != TNULL) {
        inOrderHelper(node.left);
        System.out.print(node.data + " ");
        inOrderHelper(node.right);
    }
}

// Function to start inorder traversal
public void inorder() {
    inOrderHelper(this.root);
}

// Postorder traversal helper function
private void postOrderHelper(Node<T> node) {
    if (node != TNULL) {
        postOrderHelper(node.left);
        postOrderHelper(node.right);
        System.out.print(node.data + " ");
    }
}

// Function to start postorder traversal
public void postorder() {
    postOrderHelper(this.root);
}

// Function to perform left rotation
private void leftRotate(Node<T> x) {
    Node<T> y = x.right;
    x.right = y.left;
    if (y.left != TNULL) {
        y.left.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
        this.root = y;
    } else if (x == x.parent.left) {
        x.parent.left = y;
    } else {
        x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
}

// Function to perform right rotation
private void rightRotate(Node<T> x) {
    Node<T> y = x.left;
    x.left = y.right;
    if (y.right != TNULL) {
        y.right.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
        this.root = y;
    } else if (x == x.parent.right) {
        x.parent.right = y;
    } else {
        x.parent.left = y;
    }
    y.right = x;
    x.parent = y;
}

// Function to insert a new node
public void insert(T key) {
    Node<T> node = new Node<>(key);
    node.parent = null;
    node.left = TNULL;
    node.right = TNULL;
    node.color = Color.RED; // New node must be red

    Node<T> y = null;
    Node<T> x = this.root;

    // Find the correct position to insert the new node
    while (x != TNULL) {
        y = x;
        if (node.data.compareTo(x.data) < 0) {
            x = x.left;
        } else {
            x = x.right;
        }
    }

    node.parent = y;
    if (y == null) {
        root = node;
    } else if (node.data.compareTo(y.data) < 0) {
        y.left = node;
    } else {
        y.right = node;
    }

    // Fix the tree if the properties are violated
    if (node.parent == null) {
        node.color = Color.BLACK;
        return;
    }

    if (node.parent.parent == null) {
        return;
    }

    fixInsert(node);
}

// Function to fix violations after insertion
private void fixInsert(Node<T> k) {
    Node<T> u;
    while (k.parent.color == Color.RED) {
        if (k.parent == k.parent.parent.right) {
            u = k.parent.parent.left;
            if (u.color == Color.RED) {
                u.color = Color.BLACK;
                k.parent.color = Color.BLACK;
                k.parent.parent.color = Color.RED;
                k = k.parent.parent;
            } else {
                if (k == k.parent.left) {
                    k = k.parent;
                    rightRotate(k);
                }
                k.parent.color = Color.BLACK;
                k.parent.parent.color = Color.RED;
                leftRotate(k.parent.parent);
            }
        } else {
            u = k.parent.parent.right;

            if (u.color == Color.RED) {
                u.color = Color.BLACK;
                k.parent.color = Color.BLACK;
                k.parent.parent.color = Color.RED;
                k = k.parent.parent;
            } else {
                if (k == k.parent.right) {
                    k = k.parent;
                    leftRotate(k);
                }
                k.parent.color = Color.BLACK;
                k.parent.parent.color = Color.RED;
                rightRotate(k.parent.parent);
            }
        }
        if (k == root) {
            break;
        }
    }
    root.color = Color.BLACK;
}
