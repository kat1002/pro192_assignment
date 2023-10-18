package CarPrj.Classes.Com;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarList extends ArrayList<Car> {
    BrandList brandList = new BrandList();
    
    public CarList(BrandList brandList){
        this.brandList = brandList;
    }
    
    public boolean loadFromFile(String filename){
        File f = new File(filename);
        if(!f.exists()) return false;
        
        try {
            FileInputStream fInput = new FileInputStream(f);
            Scanner sc = new Scanner(fInput);
            
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] e = line.split("[, ]+");
                //System.out.println(e[1]);
                Brand brand = brandList.get(brandList.searchID(e[1]));
                this.add(new Car(e[0], brand, e[2], e[3], e[4]));
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BrandList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean saveToFile(String filename){
        File f = new File(filename);
        
        try{
            FileWriter myWriter = new FileWriter(filename);
            for(Car element : this) myWriter.write(element + "\n");
            myWriter.close();
            
        } catch(IOException e){}
        
        return true;
    }
    
    public int searchID(String ID){
        int n = this.size();
        for(int i = 0; i < n; ++i) 
            if(this.get(i).getCarID().equals(ID)) return i;
        return -1;
    }
    
    public int searchFrame(String fID){
        int n = this.size();
        for(int i = 0; i < n; ++i) 
            if(this.get(i).getFrameID().equals(fID)) return i;
        return -1;
    }
    
    public int searchEngine(String eID){
        int n = this.size();
        for(int i = 0; i < n; ++i) 
            if(this.get(i).getEngineID().equals(eID)) return i;
        return -1;
    }
    
    public void addCar(){
        String nID, nColor, nFrameID, nEngineID;
        Brand brand;
        Scanner sc = new Scanner(System.in);
        
        //Take Input
        do{
            nID = Menu.inputNonBlankStr("Input Car ID: ", "Car ID");
            if(searchID(nID) > -1) System.out.println("Car ID can not be duplicated.");
        } while (searchID(nID) > -1);
        
        System.out.print("Choose Brand: ");
        brand = brandList.getUserChoice();
        
        nColor = Menu.inputNonBlankStr("Input Color: ", "Color");
        nFrameID = Menu.inputPattern("Input Frame ID: ", "[sF][\\d]{5}", "Frame ID can not be blank and must be in the “F00000” format and can not be duplicated");
        nEngineID = Menu.inputPattern("Input Engine ID: ", "[sE][\\d]{5}", "Engine ID can not be blank and must be in the “E00000” format and can not be duplicated");
        
        this.add(new Car(nID, brand, nColor, nFrameID, nEngineID));
        
    }
    
    public void printBasedBrandName(){
        String brandName;
        int n = this.size();
        int cnt = 0;
        
        brandName = Menu.inputNonBlankStr("Input Brand name: ", "Brand name");
        
        for(int i = 0; i < n; ++i){
            Car c = this.get(i);
            if(c.getBrand().brandName.contains(brandName)) {
                System.out.println(c.screenString());
                ++cnt;
            }
        }
        
        if(cnt == 0) System.out.println("No result");
    }
    
    public boolean removeCar(){
        String removedID;
        
        removedID = Menu.inputNonBlankStr("Input removed car ID: ", "Car ID");
        int pos = searchID(removedID);
        
        if(pos < 0){
            System.out.println("Not found");
            return false;
        }
        
        this.remove(pos);
        System.out.println("Remove successfully");
        return true;
    }
    
    public boolean updateCar(){
        Scanner sc = new Scanner(System.in);
        String uID;
        String uColor, uFrameID, uEngineID;
        Brand uBrand;
        int pos;
        
        do{
            uID = Menu.inputNonBlankStr("Your Car ID your want to update: ", "Car ID");
            pos = searchID(uID);
            if(pos == -1) System.out.println("Brand ID does not exist.");
        } while (pos == -1);
        
        System.out.print("Choose Brand: ");
        uBrand = brandList.getUserChoice();

        uColor = Menu.inputNonBlankStr("Input color: ", "Color");
        uFrameID = Menu.inputPattern("Input Frame ID: ", "[sF][\\d]{5}", "Frame ID can not be blank and must be in the “F00000” format and can not be duplicated");
        uEngineID = Menu.inputPattern("Input Engine ID: ", "[sE][\\d]{5}", "Engine ID can not be blank and must be in the “E00000” format and can not be duplicated");
        
        this.get(pos).setBrand(uBrand);
        this.get(pos).setColor(uColor);
        this.get(pos).setEngineID(uEngineID);
        this.get(pos).setFrameID(uFrameID);
        
        return true;
    }
    
    public void listCars(){
        Collections.sort(this);
        int n = this.size();
        for(Car c : this) System.out.println(c.screenString());
    }
    
    public void searchCar(){
        Scanner sc = new Scanner(System.in);
        String uID;
        int pos;
        
        do{
            uID = Menu.inputNonBlankStr("Your Car ID your want to update: ", "Car ID");
            pos = searchID(uID);
            if(pos == -1) System.out.println("Brand ID does not exist.");
        } while (pos == -1);
        
        System.out.println(this.get(pos));
    }
}
