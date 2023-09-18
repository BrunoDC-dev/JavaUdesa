package stack;

public class EmptyStack extends StackState {
    public boolean isEmpty() {
        return true;
    }
    public String top() {
        throw new Error(OOStack.stackEmptyErrorDescription);
    }
    public StackState pop() {
        throw new Error(OOStack.stackEmptyErrorDescription);
    }
    public StackState push(String element) {
        return new NonEmptyStack(element, this);
    }
    public int size() {
        return 0;
    }

}
