public class CustomTreeMap<K, V> {
    private int size = 0;
    private Node root = null;

    class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new Node(key, value);
            size++;
            return null;
        }
        return putHelper(root, key, value);
    }

    private V putHelper (Node current, K key, V value) {
        Comparable<? super K> k = (Comparable<? super K>) key;
        int cmp = k.compareTo(current.key);
        if (cmp < 0) {
            if (current.left == null) {
                current.left = new Node(key, value);
                size++;
                return null;
            }
            return putHelper(current.left, key, value);
        }
        if (cmp > 0) {
            if (current.right == null) {
                current.right = new Node(key, value);
                size++;
                return null;
            }
            return putHelper(current.right, key, value);
        }
        V oldValue = current.value;
        current.value = value;
        return oldValue;
    }

    public Node findNode(Object key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Comparable<? super K> k = (Comparable<? super K>) key;
        Node node = root;
        while (node != null) {
            int cmp = k.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    public V get(K key) {
        Node node = findNode(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node findSmallValue(Node current) {
        return current.left == null ? current : findSmallValue(current.left);
    }

    private Node delRecursive(K key) {
        Node node = findNode(key);
        Node tempNode = node;
        Node parent = findParent(key);
        if (node.left == null && node.right == null) {
            if (parent.left == node) {
                parent.left = null;
            }
            if (parent.right == node) {
                parent.right = null;
            }
            size--;
            return tempNode;
        }
        if (node.left == null) {
            if (parent.left == node) {
                parent.left = node.right;
            }
            if (parent.right == node) {
                parent.right = node.right;
            }
            size--;
            return tempNode;
        }
        if (node.right == null) {
            if (parent.left == node) {
                parent.left = node.left;
            }
            if (parent.right == node) {
                parent.right = node.left;
            }
            size--;
            return tempNode;
        }
        Node small = findSmallValue(node.right);
        delRecursive(small.key);
        node.key = small.key;
        node.value = small.value;
        return tempNode;
    }

    private Node findParent(K key) {
        Comparable<? super K> k = (Comparable<? super K>) key;
        Node node = root;
        Node parent = root;
        while (node != null) {
            int cmp = k.compareTo(node.key);
            if (cmp < 0) {
                parent = node;
                node = node.left;
            }
            if (cmp > 0) {
                parent = node;
                node = node.right;
            }
            if (cmp == 0) {
                return parent;
            }
        }
        return root;
    }

    public V remove(K key) {
        return delRecursive(key).value;
    }
}
