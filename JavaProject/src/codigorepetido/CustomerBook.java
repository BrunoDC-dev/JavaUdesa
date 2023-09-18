package codigorepetido;

import java.util.ArrayList;
import java.util.List;

public class CustomerBook {
  public static String CannotSuspend = "Cannot suspend customer";
  public static String CustomerNotFound = "Customer not found";
  public static String CustomerAlreadyExists = "customer already exists!!!!!!";
  public static String CustomerNameCannotBeEmpty = "customer name cannot be empty!!!!!!";
  
    private List<String> active = new ArrayList();
    private List<String> suspended = new ArrayList();

    public boolean includesCustomerNamed(String customerName) {
        return active.contains(customerName) || suspended.contains(customerName);
    }

    public boolean isEmpty() {
        return active.isEmpty() && suspended.isEmpty();
    }

    public void addCustomerNamed(String customerName) {
        if (customerName.isEmpty()) {
            throw new RuntimeException(CustomerNameCannotBeEmpty);
        }

        if (includesCustomerNamed(customerName)) {
            throw new RuntimeException(CustomerAlreadyExists);
        }

        active.add(customerName);
    }

    public int numberOfActiveCustomers() {
        return active.size();
    }

    public int numberOfCustomers() {
        return numberOfActiveCustomers() +numberOfSuspendedCustomers();
          }

    public int numberOfSuspendedCustomers() {
        return suspended.size();
    }
    public void removeCustomerNamed( String aName ) {
      if (active.removeIf( (each) -> aName.equals( each ) )) {
        return;
      }
  
      if (suspended.removeIf( (each) -> aName.equals( each ) )) {
        return;
      }
  
      throw new RuntimeException( CustomerNotFound );
    }

    public void suspendCustomerNamed(String name) {
        if (!active.contains(name)) {
            throw new RuntimeException(CannotSuspend);
        }
        active.remove(name);
        suspended.add(name);
    }
}
