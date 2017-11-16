package com.strings;

import java.util.Stack;

/**
 * Created by LENOVO on 18.10.2017.
 */
public class Acceptable
{
    private String string;

    public Acceptable(String string)
    {
        this.string = string;
    }

    public boolean isAcceptable()
    {
        Stack stack = new Stack();
        int index = 0;
        for(int i = 0;i < string.length();i++)
            if(string.charAt(i) == '(')
            {
                stack.push(string.charAt(i));
            }
            else if(!stack.isEmpty() && string.charAt(i) == ')')
            {
                stack.pop();
            }
            else if(stack.isEmpty() && string.charAt(i) == ')')
            {
                return false;
            }



        return stack.isEmpty();

    }
}
