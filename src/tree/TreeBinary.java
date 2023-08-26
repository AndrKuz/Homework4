package tree;

public class TreeBinary {
    Node root;
    private Node addRecursive(Node current, int data) {
        if (current == null) {
            return new Node(data);
        }
        if (data < current.data) {
            current.left = addRecursive(current.left, data);
        } else if (data > current.data){
            current.right = addRecursive(current.right, data);
        } else {
            return current;
        }
        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, int data) {
        if (current == null) {
            return false;
        }
        if (data == current.data) {
            return true;
        }
        return data < current.data
                ? containsNodeRecursive(current.left, data)
                : containsNodeRecursive(current.right, data);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    public void printTree() {
        Node node = root;
        recPrintTree(node);
        System.out.println("____________");
    }

    private void recPrintTree(Node node) {
        if (node.left != null) recPrintTree(node.left);
        System.out.println(node.data);
        if (node.right != null) recPrintTree(node.right);
    }

    private int findSmallValue(Node current) {
        if (current.left == null) {
            return root.data;
        }
        return findSmallValue(current.left);
    }

    private Node delElem(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.data) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            int smallValue = findSmallValue(current.right);
            current.data = smallValue;
            current.right = delElem(current.right, smallValue);
            return current;
        }
        if (value < current.data) {
            current.left = delElem(current.left, value);
            return current;
        }
        current.right = delElem(current.right, value);
        return current;
    }
//      не смог сам придумать, как реализовать данный метод, увы

    public void remove(int value) {
        root = delElem(root, value);
    }

}
