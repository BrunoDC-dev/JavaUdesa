package stack;

public class NonEmptyStack extends StackState{
    private String top;
    private StackState tail;

    public NonEmptyStack(String top, StackState tail) {
        this.top = top;
        this.tail = tail;
    }
    public boolean isEmpty() {
        return false;
    }
    public String top() {
        return top;
    }
    public StackState pop() {
        return tail;
    }
    public StackState push(String element) {
        return new NonEmptyStack(element, tail.push(top));
    }
    public int size() {
        return 1 + tail.size();
    }

}
