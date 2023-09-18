package stack;


public class OOStack {
	static public String stackEmptyErrorDescription = "Stack is empty";
	private StackState state ;
	public OOStack() {
		this.state = new EmptyStack();
	}

	public boolean isEmpty() {
		return state.isEmpty();
	}

	public OOStack push(String string) {
		this.state = this.state.push(string);
		return this;
	}

	public Object pop() {
		String top = state.top();
		this.state = state.pop();
		return top;
	}

	public Object top() {
		return state.top();
	}

	public int size() {
		return state.size();
	}

}
