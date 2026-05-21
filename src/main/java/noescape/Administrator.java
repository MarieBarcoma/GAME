<<<<<<< HEAD
package noescape;

public class Administrator {

    private String message;
    private boolean isOverridden;

=======
public class Administrator {
 
    private String message;
    private boolean isOverridden;
 
>>>>>>> c3e02708c9d42df23cec62226de306e4e171b963
    public Administrator() {
        this.message = " ";
        this.isOverridden = false;
    }
<<<<<<< HEAD

    public void sendMessage(String msg) {
        this.message = msg;
    }

    public void resetGame() {
        this.isOverridden = false;
    }

    public String  getMessage() {
        return message;      
    }
    
    public boolean isOverridden() {
        return isOverridden; 
    }

    public void overrideLoop() {
        this.isOverridden = true;
        this.message = "LOOP OVERRIDE: You are free.";
    }
=======
 
    // Send a message that appears in the game log
    public void sendMessage(String msg) {
        this.message = msg;
    }
 
    // Reset the game state
    public void resetGame() {
        this.isOverridden = false;
    }
 
    public String getMessage() {
         return message; 
    }
    public boolean isOverridden() { 
        return isOverridden; 
    }
>>>>>>> c3e02708c9d42df23cec62226de306e4e171b963
}