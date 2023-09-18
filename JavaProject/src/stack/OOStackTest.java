package stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
public class OOStackTest {
  @Test public void test01StackShouldBeEmptyWhenCreated() {
    assertTrue( new OOStack().isEmpty() );
  }
  @Test public void test02PushAddElementsToTheStack() {
    assertFalse( new OOStack().push( "Something" ).isEmpty() );
  }
  @Test public void test03PopRemovesElementsFromTheStack() {
    OOStack stack = getStack( pushedObject);
    stack.pop();
    assertTrue( stack.isEmpty() );
  }
  @Test public void test04PopReturnsLastPushedObject() {
    OOStack stack = getStack(pushedObject);
    assertEquals( stack.pop(), pushedObject );
  }
  @Test public void test05StackBehavesLIFO() {
    OOStack stack = getStack(firstPushedObject);
    stack.push( secondPushedObject );
    assertEquals( stack.pop(), secondPushedObject );
    assertEquals( stack.pop(), firstPushedObject );
    assertTrue( stack.isEmpty() );
  }
  @Test public void test06TopReturnsLastPushedObject() {
    assertEquals( getStack(pushedObject).top(), pushedObject );
  }
  @Test public void test07TopDoesNotRemoveObjectFromStack() {
    OOStack stack = getStack(pushedObject);
    assertEquals( stack.size(), 1 );
    stack.top();
    assertEquals( stack.size(), 1 );
  }
  @Test public void test08CanNotPopWhenThereAreNoObjectsInTheStack() {
    OOStack stack = new OOStack();
    assertThrows(Error.class, () -> stack.pop(),OOStack.stackEmptyErrorDescription );
  }
  @Test public void test09CanNotPopWhenThereAreNoObjectsInTheStackAndTheStackHadObjects() {
    OOStack stack = getStack(pushedObject);
    stack.pop();
    assertThrows(Error.class, () -> stack.pop(),OOStack.stackEmptyErrorDescription );
  } 
  @Test public void test10CanNotTopWhenThereAreNoObjectsInTheStack() {
    OOStack stack = new OOStack();
    assertThrows(Error.class, () -> stack.top(),OOStack.stackEmptyErrorDescription );
  }
  String pushedObject = "Something";
  String firstPushedObject = "First";
  String secondPushedObject = "Second";
  private OOStack getStack(String elment) {
    OOStack stack = new OOStack();
    stack.push( elment );
    return stack;
  }
}