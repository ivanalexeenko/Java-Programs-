package First_Lab_Java;
import java.lang.String;
public class Main {

    static public void main(String[] args) {

        System.out.println("Let's calculate a sum of a number series");
        System.out.println("A Number Series is :");
        System.out.println("SUM() x^(3*k^2) for any k = 1,n");

        double x = 0;
        double e = 0;

        String errorMessage = "";
        try
        {
            if (args.length != 2)
            {
                throw new MyException("ARGUMENTS MISMATCH, 2 required, but  " + args.length + " found");
            }
            x = Double.parseDouble(args[0]);
            e = Double.parseDouble(args[1]);
        }

        catch(MyException me)
        {
              errorMessage = me.getMessage();
              errorMessage += "; Default x = 0, Epsilon = 1";
              x = 0;
              e = 1;

        }

        catch(NumberFormatException ex)
        {
            errorMessage += "WRONG INPUT ";
            errorMessage += ex.getMessage();
            errorMessage += "(double required)";
            errorMessage += "; Default x = 0, Epsilon = 1";
            x = 0;
            e = 1;
        }
        finally
        {
            System.out.println(errorMessage);
        }
        Sum s = new Sum(x,e);
        System.out.println("for x = " + x + " and Epsilon = " + e + " Sum = "  + s.sum());
    }
}
