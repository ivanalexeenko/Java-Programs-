package com.strings;

public class Main
{
    public static void main(String[]args)
    {

        String string = "";
        String errorMessage = "";
        try
        {
            errorMessage = "";
            if(args.length != 1)
            {
                throw new MyException("ARGUMENTS MISMATCH: 1 required,but " + args.length + " found, default string = ((lalala)) ");
            }
            string = args[0];

        }
        catch(MyException e)
        {
            errorMessage = e.getMessage();
            string = "((lalala))";
        }

        finally
        {
            System.out.println(errorMessage);
        }

        try
        {
            errorMessage = "";
            Acceptable acceptable = new Acceptable(string);
            if(!acceptable.isAcceptable())
            {
                throw new MyException("WRONG INPUT, braces amount error, default string = ((lalala))");
            }

        }
        catch(MyException e)
        {
            errorMessage = e.getMessage();
            string = "((lalala))";
        }
        finally
        {
            System.out.println(errorMessage);
        }

        BraceDeleter braceDeleter = new BraceDeleter(string);
        String stringLaFinale = braceDeleter.cleanBraces();

        System.out.println("String before cleaning = " + string);

        System.out.println("String after cleaning = " + stringLaFinale);


    }
}

