package CarPrj.Classes.Com;



import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    
    public static Scanner scanner = new Scanner(System.in);
    
    public static <E> int int_getChoice(ArrayList<E> options){
        
        boolean check = true;
        String line = "";
        
        for(int i = 0; i < options.size(); ++i) System.out.println((i+1) + "-" + options.get(i));
        
        do{
            System.out.print("Choose 1.." + options.size() + ": ");
            line = scanner.nextLine();
            line = line.trim();
            if (!line.matches("[0-9]+")) {
                System.out.println("Invalid number!");
                check = false;
            }
            else check = true;
        } while (!check);
        
        return Integer.parseInt(line) - 1;    
    }
    
    public static <E> E ref_getChoice(ArrayList<E> options){
        int response;
        int n = options.size();
        do{
            response = int_getChoice(options);
        } while(response < 0 || response > n);
        
        return options.get(response);
    }
    
        
    public static String inputNonBlankStr(String msg, String name){
        String data;
        
        do{
            System.out.println(msg);
            data = scanner.nextLine().trim();
            if(data.length() == 0) System.out.println(name + " cannot be blank!");
        } while(data.length() == 0);
        
        return data;
    }
    
    
    public static String inputNonBlankStr(String msg, String name, String answer){
        String data;
        
        do{
            System.out.println(msg);
            data = scanner.nextLine().trim();
            if(data.length() == 0) System.out.println(answer);
        } while(data.length() == 0);
        
        return data;
    }
    
    public static String inputPattern(String msg, String pattern, String answer){
        String data;
        
        do{
            System.out.println(msg);
            data = scanner.nextLine().trim();
            if(!data.matches(pattern)) System.out.println(answer);
        } while(!data.matches(pattern));
        
        return data;
    }
    
    public static double inputDouble(String msg, String name){
        double price = 0;
        String line = "";
        boolean check = true;
        
        do{
            try{
                System.out.print(msg);
                price = scanner.nextDouble();
                check = false;
                if(price <= 0) System.out.println(name + " must be a positive real number.");
                
            } catch(InputMismatchException e){
                System.out.println("Invalid number!");
                scanner.next();
            }
        } while (check || price <= 0);        
        return price;
    }
   
}
