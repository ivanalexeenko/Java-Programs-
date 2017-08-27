package Codeforces_Tasks;
import java.lang.String;
import java.math.BigInteger;
import java.util.*;

public class Main
{
    private static int Steps_to_Equal(String s1,String s2)
    {
        int steps = 0;
        String buffer = s1;
        char[] str = new char[s2.length()];
        for(int i = 0; i < s2.length();i++)
        {
            str[i] = s1.charAt(i);
        }

        while(!buffer.equals(s2) && steps < s2.length() )
        {
            char transfer = str[0];
            for(int i = 0; i < s2.length() - 1;i++)
            {
                str[i] = str[i+1];
            }
            str[s2.length() - 1] = transfer;
            steps++;
            buffer = new String(str);

        }
        if(steps < s1.length()) return steps;
        else return -1;

    }
    private static BigInteger Factorial_Calc(int n,int j)
    {
        BigInteger factorial = BigInteger.valueOf(1);
        j++;
        for(int i = j; i < n + 1; i++)
        {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }
    private static float round_off(float num)
    {
        int digit_part = (int) num;
        float fraction_part = num - (float) digit_part;
        if (fraction_part < 0.5) {
            return (float) digit_part;
        } else {
            digit_part++;
            return (float) digit_part;
        }

    }
    private static boolean Is_Lucky(int year)
    {
        String buffer = Integer.toString(year);
        int not_nulls = 0;
        for(int i = 0; i < buffer.length();i++ )
        {
            if(buffer.charAt(i) != '0')
            {
                not_nulls++;
            }
        }
        if(not_nulls <= 1)

            return true;

        else
            return false;
    }
    private static int Spin_Position(String first)
    {
        if(first.charAt(0) == '^' )
        {
            return  1;
        }
        else if(first.charAt(0) == '>')
        {
            return  2;
        }
        else if(first.charAt(0) == 'v')
        {
            return  3;
        }
        else if (first.charAt(0) == '<')
        {
            return  4;
        }
        else return 0;

    }
    private static void Task_810_A()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Task 810A");
        int n, k;
        System.out.println("Please,enter the number of marks(n) and maximum mark(k): ");
        n = input.nextInt();
        k = input.nextInt();
        if (n < 1 || n > 100 || k < 1 || k > 100)
        {
            System.out.println("Error: n or k is out of range!!!");
        } else
        {
            float[] array = new float[n];
            System.out.println("Please,enter the marks:");
            boolean success_input = true;
            for (int i = 0; i < n; i++)
            {
                array[i] = input.nextFloat();
                if (array[i] < 1 || array[i] > 100 || array[i] > k)
                {
                    System.out.println("Error: mark is out of range!!!");
                    success_input = false;
                    break;
                }
            }
            if (success_input)
            {
                float sum = 0;
                float mark = 0;
                for (int i = 0; i < n; i++)
                {
                    sum += array[i];
                }
                mark = sum / n;
                int minimum = 0;
                while (round_off(mark) != k)
                {
                    sum += k;
                    minimum++;
                    mark = sum / (minimum + n);
                }
                System.out.println("Minimal amount of marks to achieve = " + minimum);


            }
        }
    }
    private static void Task_831_B()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Task 831B");
        String keyboard_one;
        String keyboard_two;
        String buffer;
        System.out.println("First keyboard:");
        keyboard_one = input.next();
        System.out.println("Second keyboard:");
        keyboard_two = input.next();
        System.out.println("String to translate:");
        buffer = input.next();
        char[] translate = new char[buffer.length()];
        for(int i = 0;i < buffer.length();i++)
        {
            if (buffer.charAt(i) >= 65 && buffer.charAt(i) <= 90 )
            {
                char smaller = (char)(buffer.charAt(i)+32);
                int index_one = keyboard_one.indexOf(smaller);
                char in_second = (char)((int)keyboard_two.charAt(index_one) - 32);
                translate[i] = in_second;
            }
            else if(buffer.charAt(i) >= 97 && buffer.charAt(i) <= 122 )
            {
                char symb = buffer.charAt(i);
                int index_one = keyboard_one.indexOf(symb);
                char in_second = keyboard_two.charAt(index_one);
                translate[i] = in_second;
            }
            else
            {
                translate[i] = buffer.charAt(i);
            }

        }
        System.out.println("Translated:");
        System.out.println(translate);
    }
    private static void Task_820_A()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Task 820A");
        int c,v_0,v_1,a,l;
        int current = 0;
        int days = 0;
        c = input.nextInt();
        v_0 = input.nextInt();
        v_1 = input.nextInt();
        a = input.nextInt();
        l = input.nextInt();
        while(current < c )
        {
            days++;
            if(days == 1)
            {
                current = v_0;
            }
            else
            {
                v_0 +=a;
                if(v_0 > v_1)
                {
                    v_0 = v_1;
                }
                current = current + v_0 - l;

            }
        }
        System.out.println(days);
    }
    private static void Task_808_A()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Task 808A");
        int year;
        year = input.nextInt();
        int add = 0;
        String buffer = Integer.toString(year);
        if(buffer.length() == 1)
        {
            add = 1;
        }
        else if(buffer.length() != 1)
        {
            double first_num = year / (Math.pow(10,(buffer.length()-1)));
            double next_lucky = ( (int)first_num + 1 ) * Math.pow ( 10 ,(buffer.length()-1));
            add = (int)next_lucky - year;
        }
        System.out.println(add);
    }
    private static void Task_831_A()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Task 831A");
        int n = input.nextInt();
        int[] array = new int[n];
        for(int i = 0; i < n;i++)
        {
            array[i] = input.nextInt();
        }
        int max = array[0];
        int index_of_max_right = 0;
        for(int i = 0; i < n;i++)
        {
            if (array[i] >= max)
            {
                max = array[i];
                index_of_max_right = i;
            }
        }


        int index_of_max_left = 0;
        int i = index_of_max_right;
        int max_in_row = 0;
        while (i >= 0 && max == array[i] )
        {
            max_in_row++;
            i--;
        }
        index_of_max_left = index_of_max_right - max_in_row + 1;
        i = 0;
        int up = 0;
        while( i < index_of_max_left && array[i] < array[i+1] )
        {
            up++;
            i++;
        }
        int down = 0;
        i = index_of_max_right;
        while( i < (n - 1) && array[i] > array[i+1] )
        {
            down++;
            i++;
        }
        if(up + down + max_in_row == n  || max_in_row == n )
        {
            System.out.println("YES");
        }
        else System.out.println("NO");
    }
    private static void Task_828_A()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Task 828A");
        int n,a,b;
        n = input.nextInt();
        a = input.nextInt();
        b = input.nextInt();
        int[] clients = new int [n];
        for(int i = 0; i < n;i++)
        {
            clients[i] = input.nextInt();
        }
        int denied = 0,half_b = 0;
        for(int i = 0; i < n; i++)
        {
            if(clients[i] == 1)
            {
                if(a != 0)
                {
                    a--;
                }
                else if (b != 0)
                {
                    half_b ++;
                    b--;
                }
                else if (half_b != 0)
                {
                    half_b --;
                }
                else denied++;


            }
            else if (clients[i] == 2)
            {
                if(b!=0)
                {
                    b--;
                }
                else denied += 2;
            }

        }
        System.out.println(denied);
    }
    private static void Task_799_A()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Task 799A");
        int n,t,k,d;
        n = input.nextInt();
        t = input.nextInt();
        k = input.nextInt();
        d = input.nextInt();
        int minutes = 0;
        int pies = 0;
        while(pies < n)
        {
            minutes ++;
            if(minutes % t == 0)
            {
                pies += k;
            }
        }
        pies = 0;

        int minutes_furnace = 1;
        int just_furnace = 0;
        while (pies < n)
        {

            if(minutes_furnace % t == 0)
            {
                pies += k;
            }
            if(minutes_furnace > d)
            {
                just_furnace++;
            }
            if(just_furnace % t == 0 )
            {
                pies += k;
            }
            minutes_furnace++;
        }
        minutes_furnace--;
        if(minutes <= minutes_furnace)
        {
            System.out.println("NO");
        }
        else System.out.println("YES");

    }
    private static void Task_834_A()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Task 834А");

        String first = input.next();
        String second = input.next();

        int current_pos = Spin_Position(first);
        int current_f_pos = Spin_Position(second);

        int n = input.nextInt();

        int current_time = 0;
        if((current_pos == 1 && current_f_pos == 3)
                || (current_pos == 2 && current_f_pos == 4)
                || (current_pos == 3 && current_f_pos == 1)
                || (current_pos == 4 && current_f_pos == 2) )
        {
            System.out.println("undefined");
        }
        else
        {
            while (current_time < n)
            {
                current_pos++;
                if(current_pos == 5)
                {
                    current_pos = 1;
                }
                current_time++;
            }

            if (current_pos == current_f_pos)
            {
                System.out.println("cw");
            }
            else System.out.println("ccw");

        }
    }
    private static void Task_821_A()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Task 821А");
        int n = input.nextInt();
        int[][] lab = new int[n][n];

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                lab[i][j] = input.nextInt();
            }
        }
        int ok = 0;
        boolean found = false;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                found = false;
                for(int s = 0; s < n && found != true ; s++)
                {
                    for(int l = 0;l < n && found != true; l++)
                    {
                        if(lab[i][j] == 1)
                        {
                            ok++;
                            found = true;
                        }
                        if(l!=j)
                        {
                            if (lab[i][j] == (lab[i][l] + lab[s][j]))
                            {
                                ok++;
                                found = true;
                            }
                        }

                    }
                }


            }
        }
        if(ok == n*n )
        {
            System.out.println("YES");
        }
        else  System.out.println("NO");


    }
    private static void Task_817_B()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Task 817B");
        int n = input.nextInt();
        Integer[] array = new Integer[n];
        for(int i = 0;i < n;i++)
        {
            array[i] = input.nextInt();
        }
        Arrays.sort(array, new Comparator<Integer>() {

            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        int n_3 = 0;
        for(int i = 0; i < n; i++)
        {
            if(array[2].equals(array[i]))
            {
                n_3++;
            }
        }
        BigInteger answer;
        if (array[0].equals(array[1]) && array[1].equals(array[2])&& array[0].equals(array[2]))
        {
            answer = Factorial_Calc(n_3,(n_3 - 3));
            answer = answer.divide(BigInteger.valueOf(6));
        }
        else if( array[0].equals(array[1]) && !array[1].equals(array[2]))
        {
            answer = BigInteger.valueOf(n_3);
        }
        else if(!array[0].equals(array[1])&& array[1].equals(array[2]))
        {
            answer = Factorial_Calc(n_3,(n_3 - 2));
            answer = answer.divide(BigInteger.valueOf(2));
        }
        else
        {
            answer = BigInteger.valueOf(n_3);
        }

        System.out.println(answer);

    }
    private static void Task_798_B()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Task 798B");
        int n = input.nextInt();
        String[] strings = new String[n];
        for(int i = 0; i < n; i++)
        {
            strings[i] = input.next();
        }
        int sum_steps = 0;
        int[] minimums = new int[n];
        int a = 0;
        int amount = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0;j < n; j++)
            {
                a = Steps_to_Equal(strings[j],strings[i]);
                if(j!= i)
                {
                    if(a == -1)
                    {
                        sum_steps = 0;
                        minimums [i] = -1;
                        amount = 0;
                        break;
                    }
                    else
                    {
                        sum_steps += a;
                        amount++;
                        if(amount == n - 1)
                        {
                            minimums[i] = sum_steps;
                            amount = 0;
                            sum_steps = 0;
                        }
                    }
                }

            }


        }
        int cout = minimums[0];
        for(int i = 0; i < n;i++)
        {
            if(cout > minimums[i]) cout = minimums[i];
        }
        System.out.println(cout);

    }
    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);
        boolean process = true;
        while (process)
        {
            System.out.println("Number of program (0 to exit):");
            int program = input.nextInt();
            if (program == 0)
            {
                process = false;
            }
            switch (program)
            {
                case 1:
                {
                    Task_810_A();
                    break;
                }
                case 2:
                {
                    Task_831_B();
                    break;
                }
                case 3:
                {
                    Task_820_A();
                    break;
                }
                case 4:
                {
                    Task_808_A();
                    break;

                }
                case 5:
                {
                    Task_831_A();
                    break;
                }
                case 6:
                {
                    Task_828_A();
                    break;
                }
                case 7:
                {
                    Task_799_A();
                    break;
                }
                case 8:
                {
                   Task_834_A();
                   break;
                }
                case 9:
                {
                   Task_821_A();
                   break;
                }
                case 10:
                {
                   Task_817_B();
                   break;
                }
                case 11:
                {
                   Task_798_B();
                   break;
                }
                case 12:
                {
                    System.out.println("Task 830B");
                    int n = input.nextInt();
                    Integer[] cards = new Integer[n];
                    Queue<Integer> dup = new LinkedList<Integer>();
                    for(int i = 0 ; i < n; i++)
                    {
                        cards[i] = input.nextInt();
                        dup.add(cards[i]);
                    }


                    Arrays.sort(cards, new Comparator<Integer>(){
                        public int compare(Integer o1,Integer o2)
                        {
                            return  o1-o2;
                        }
                    });
                    BigInteger steps = BigInteger.valueOf(0);
                    int min;
                    int index = 0;
                    int buffer = 0;
                    while(!dup.isEmpty())
                    {
                        min = cards[index];
                        steps = BigInteger.valueOf(1).add(steps);
                        if(dup.element() == min)
                        {
                            dup.remove();
                            index++;
                        }
                        else
                            {
                                buffer = dup.element();
                                dup.remove();
                                dup.add(buffer);
                            }
                    }
                    System.out.println(steps);

                    break;
                }

            }

        }

    }
}
