package stack;

public abstract class StackState {
    public abstract boolean isEmpty();
    public abstract String top();
    public abstract StackState pop();
    public abstract StackState push(String element);
    public abstract int size();
}
