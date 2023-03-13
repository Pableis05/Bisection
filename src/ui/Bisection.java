package ui;
import java.util.Scanner;

/**
The Bisection class provides a program to evaluate the bisection method to find the root of a function.
The user can input an interval [a,b] and a function number to evaluate.
The program uses the mid-point C of the interval and selects a new interval [a,c] or [c,b] based on the sign of f(a)*f(b).
The program then calculates the value of f(c) and iterates until the absolute value of f(c) is smaller than the predefined epsilon value.
The class includes methods for inputting the interval values and function, evaluating the function at specific points, and running the bisection method.
The main method provides a menu with options to either run the bisection method or exit the program.
*/
public class Bisection{

    /* Inputs
    *
    * option: The user enters the option to do (1. Bisection method or 0. Exit).
    * function: The user enters the function to calculate (1. f(x)=2cos(x^2), 2. f(x)= 3x^3+7x^2+5 or 3. f(x)= xcos(x)).
    * valueA: The value use to calculate f(a) and select the interval.
    * valueB: The value use to calculate f(b) and select the interval.
    */

    /* Outputs
    * 
    * numbersC: The amount of C used to find the root.
    * functions(function, valueC) = The f(c) value.
    * valueC = The value of the root in the interval.
    */

    /* Examples:
    * 1. f(x)=2cos(x^2) 
    * valueA = 0 && valueB = 2 
    * Root = 1.2533
    * 
    * 2. f(x)= 3x^3+7x^2+5
    * valueA = -20  && valueB = 20 
    * Root = -2.5831
    * 
    * 3. f(x)= xcos(x)
    * valueA = -16 && valueB = -12 
    * Root = -14.1371
    */

    static Scanner input = new Scanner(System.in);

    // Variables used to calculate the bisection method
    static private double totalOperation = 0;
    static private double valueA = 0;
    static private double valueB = 0;
    static private double functionA = 0;
    static private double functionB = 0;
    static private double valueC = 0;
    final static private double valueEpsilon = 0.00001;
    final static private double pi = 3.14159;

    /**
    *The main method runs a menu with options to choose to calculate the bisection method or exit the program
    *@param args the arguments passed to the program
    */
    public static void main(String[] args) {
        
        int option = -1;
        System.out.println("Welcome to your bisection method");      
        do{ 
            System.out.printf("%s%n%s%n%s%n","Enter your option:", "1. Bisection method", "0. Exit");
            while(!input.hasNextInt()){ 
                input.nextLine();
                System.out.println("Input a number");
            }
            option = input.nextInt();
            if(option == 1){
                menu();
            }
            else if(option == 0){
                System.out.println("Thank you");
            }
            else{
                System.out.println("invalid number to enter");
            }
        }while(option != 0);

    }
    
    /**
    * The menu method presents options to choose which function to calculate 
    * and calls the functionsAB method to perform the bisection method calculations.
    */
    public static void menu(){
        int function = 0;
        String confirmationFunction;
        do{ 
            System.out.printf("%s%n%s%n%s%n%s%n", "Enter your function: ", "1. f(x)=2cos(x^2)", "2. f(x)= 3x^3+7x^2+5", "3. f(x)= xcos(x)");
            while(!input.hasNextInt()){ 
                input.nextLine();
                System.out.println("Input a correct number");
            }
            function = input.nextInt();

        }while(function > 3 || function < 1);
        confirmationFunction = "you select the " +function+ " function";
        System.out.println(confirmationFunction);
        functionsAB(function);

    }

    /**
    *The cosine method receives an angle in radians and returns the cosine value of that angle in a range [-pi,pi].
    *@param x the angle in radians.
    *@return the cosine of the angle.
    */
    
    public static double cosine(double x){
        double cos = 0;
        
        // reduce x to the range [-pi, pi]
        x = x % (2*pi);
        if(x < -pi){
            x += 2*pi;
        }
        else if (x > pi) {
            x -= 2*pi;
        }
        
        for(int i = 0; i < 300; i++){
            cos += (pow(-1, i)/(factorial(2*i)))*(pow(x , 2*i));
        }
    
        return cos;
    }

    /**
    *Raises the base to the power of the index.
    *@param base the base number
    *@param index the index of the power
    *@return the result of the power operation
    */
    public static double pow(double base, int index){

        double empowered = 1;
        
        for (int i = 0; i < index; i++) {
            empowered *= base;
        } 
        
        return empowered;
    }

    /**
    *Calculates the factorial of a number.
    *@param base the number to calculate the factorial of
    *@return the factorial of the number
    */
    public static double factorial(double base){
    
        double factorial = 1;
        for (int i = 1; i <= base; i++) {
            factorial *= i;
        }
    
        return factorial;
    
    }

    /**
    * Calculates the absolute value of a number
    * @param number The number to calculate the absolute value of
    * @return the absolute value of the number
    */

    public static double absoluteValue(double number){

        if(number < 0){
            number *= -1;
        }
        return number;
    }

    /**

    *Calculates a mathematical function based on the function parameter and the input value.
    *@param function the function to calculate (1: 2*(cos(pow(x, 2))), 2: ((3pow(x, 3))+(7pow(x, 2))+5), 3: (x*cos(x)))
    *@param x the input value for the function
    *@return the result of the function calculation
    */

    public static double functions(int function, double x){ 
        switch(function) {
            case 1:
            totalOperation = 2*(cosine(pow(x, 2)));
            break;
            case 2:
            totalOperation = ((3*pow(x, 3))+(7*pow(x, 2))+5);
            break;
            case 3:
            totalOperation = (x*cosine(x));      
            break;         
        }       
        return totalOperation;
    }
   
    /**
    *This method prompts the user to enter two values, a and b, in radians.
    *It reads the input from the user and stores the values in the global variables valueA and valueB.
    *If the user enters a non-numeric value, it prompts the user to enter a number until valid input is provided.
    */

    public static void valuesAB(){

        System.out.println("enter the value a in radians");
        while(!input.hasNextDouble()){ 
            input.nextLine();
            System.out.println("Input a number");
        }
        valueA = input.nextDouble();
        System.out.println("enter the value b in radians");
        while(!input.hasNextDouble()){ 
            input.nextLine();
            System.out.println("Input a number");
        }
        valueB = input.nextDouble();
        
    }

    /**
    *This method takes a function number as input and calls the valoresAB method to prompt the user to enter values a and b.
    *It calculates the value of the function at a and b, and stores the values in the global variables functionA and functionB.
    *If the values of a and b are equal, it prints an error message.
    *If the value of the function at a or b is zero, it prints a message indicating which value matches with a zero.
    *If none of the values match with a zero, it calls the biseccionAB method to perform the bisection method.
    *@param function The function number to be evaluated.
    */

    public static void functionsAB(int function){
        valuesAB();
        if(valueA == valueB){
            System.out.println("Enter error, same numbers");   
        }
        else{
            functionA = functions(function, valueA);
            functionB = functions(function, valueB);
            System.out.println("The value of f(a) is: " + functionA);
            System.out.println("The value of f(b) is: " + functionB);
            if(functions(function, valueA) == 0){
                System.out.println("The value of A match with a ZERO");
            }
            else if(functions(function, valueB) == 0){
                System.out.println("The value of B match with a ZERO");
            }
            else{
                bisectionAB(valueA, valueB, function);
            }
            
        }
        
    }

    /**
    *This method evaluates the root value for an interval [a,b] if f(a)*f(b) < 0.
    *The value of C is calculated at the midpoint of the interval, and a new interval is selected that contains the root, either [a,c] or [c,b].
    *The value of C is recalculated in a loop for new intervals until the absolute value of f(c) is smaller than Epsilon.
    *The system then prints the value of C, f(c), and the root value.
    * 
    * @param valueA The value of a to use in the interval.
    * @param valueB The value of b to use in the interval.
    * @param function The function number to be evaluated.
    */

    public static void bisectionAB(double valueA, double valueB, int function){
        int numbersC = 0;
        if((functions(function, valueA)*functions(function, valueB)) < 0){ 
            do{    
                valueC = (valueA+valueB)/2;
                numbersC++;
                if((functions(function, valueA)*functions(function, valueC)) < 0){
                    valueB = valueC;
                }
                else{
                    valueA = valueC;
                }
                    
            }while(absoluteValue(functions(function, valueC)) > valueEpsilon);
            System.out.println("El amount of c is:"+ numbersC);
            System.out.println("The f(c) value is:" + functions(function, valueC));
            System.out.println("The root value is:" + valueC);

        }
        else{
            System.out.println("It doesnt satisfy f(a)*f(b) < 0, therefore bisection don't possible");
        }

    }

}