package zhewu.me.code.patterns.before.composemethod;

public class MyList {
    public static final int GROWTH = 10;
    private boolean readOnly;
    private int size;
    private Object[] elements;

    public MyList(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public void add(Object ele) {
        if (!readOnly) {
            int newSize = size + 1;
            if (newSize > elements.length) {
                Object[] newElements = new Object[elements.length + GROWTH];
                if (size >= 0) System.arraycopy(elements, 0, newElements, 0, size);
                newElements[size] = ele;
                elements = newElements;
                size = size + 1;
            }
        }
    }

    public int count() {
        return size;
    }
}
