package linkedlist;

/**
 * @author Batman on 2018/12/19.
 */
public class LinkedListArray {
    /**
     * value
     */
    Object value;

    /**
     * 下个节点
     */
    LinkedListArray next = null;

    public void setValue(Object value) {
        this.value = value;
    }

    public void setNext(LinkedListArray next) {
        this.next = next;
    }

    public Object getValue() {
        return value;
    }

    public LinkedListArray getNext() {
        return next;
    }
}

