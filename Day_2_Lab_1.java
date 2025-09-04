package lab;

public class Day_2_Lab_1 { 
	  public static void main(String[] args) {
	        testAccess(new Admin());
	        testAccess(new Manager());
	        testAccess(new Employee());
	        testAccess(new Guest());
	    }

	    private static void testAccess(Role role) {
	        String[] resources = {"reports", "team-data", "confidential", "public-info"};

	        System.out.println("\nTesting access for: " + role.getClass().getSimpleName());
	        for (String res : resources) {
	            role.accessResource(res);
	        }
	    }

} 

interface Role {
    void accessResource(String resource);
}

class Admin implements Role {
    @Override
    public void accessResource(String resource) {
        System.out.println("Admin accessing: " + resource + " Granted");
    }
}

class Manager implements Role {
    @Override
    public void accessResource(String resource) {
        switch (resource) {
            case "reports":
            case "team-data":
                System.out.println("Manager accessing: " + resource + " Granted");
                break;
            default:
                System.out.println("Manager accessing: " + resource + " Denied");
        }
    }
}

class Employee implements Role {
    @Override
    public void accessResource(String resource) {
        if ("team-data".equals(resource)) {
            System.out.println("Employee accessing: " + resource + " Granted");
        } else {
            System.out.println("Employee accessing: " + resource + " Denied");
        }
    }
}

class Guest implements Role {
    @Override
    public void accessResource(String resource) {
        if ("public-info".equals(resource)) {
            System.out.println("Guest accessing: " + resource + " Granted");
        } else {
            System.out.println("Guest accessing: " + resource + " Denied");
        }
    }
}

