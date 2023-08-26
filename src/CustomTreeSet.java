public class CustomTreeSet<E> {
    CustomTreeMap<E, Object> map = new CustomTreeMap<>();
    private final static Object PRESENT = new Object();
    public boolean add(E el) {
        return map.put(el, PRESENT) == null;
    }

    public boolean remove(E el) {
        return map.remove(el) == PRESENT;
    }

    public boolean contains(Object o) {
        return map.findNode(o) != null;
    }
}
