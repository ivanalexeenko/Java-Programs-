package com.strings;

public class BraceDeleter
{
    private String string;

    public BraceDeleter(String string)
    {
        this.string = string;
    }
    public String cleanBraces()
    {
        StringBuffer stringBuffer = new StringBuffer(string);

        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;


        leftIndex = stringBuffer.indexOf("(");
        rightIndex = stringBuffer.indexOf(")") + 1;

        while(rightIndex!=-1)
        {

            while (leftIndex < rightIndex && leftIndex!=-1)
            {
                index = leftIndex;
                leftIndex = stringBuffer.indexOf("(",leftIndex + 1);
                rightIndex = stringBuffer.indexOf(")",index) + 1;
            }

            if(index >=0 && rightIndex >= 0)
            {
                stringBuffer.delete(index,rightIndex);
            }
            if(leftIndex == -1)
            {
                rightIndex = -1;
            }
            else
            {
                leftIndex = stringBuffer.indexOf("(",index);
                rightIndex = stringBuffer.indexOf(")",leftIndex);
            }

        }

        string = stringBuffer.toString();
        return string;
    }
}
