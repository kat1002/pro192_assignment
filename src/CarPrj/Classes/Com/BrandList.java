package CarPrj.Classes.Com;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrandList extends ArrayList<Brand> {
    
    public BrandList(){};
    
    public boolean loadFromFile(String filename) throws IOException{
        File f = new File(filename);
        if(!f.exists()) return false;
        
        try {
            FileInputStream fInput = new FileInputStream(f);
            Scanner sc = new Scanner(fInput);
            
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] e = line.split("[,:]+");
                double price = Double.parseDouble(e[3]);
                this.add(new Brand(e[0], e[1], e[2], price));
                //System.out.println(new Brand(e[0], e[1], e[2], price));
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
            for(Brand element : this) myWriter.write(element + "\n");
            myWriter.close();
            
        } catch(IOException e){}
        
        return true;
    }
    
    public int searchID(String bID){
        int n = this.size();
        
        for(int i = 0; i < n; ++i) 
            if(this.get(i).getBrandID().equals(bID)) return i;
        
        return -1;
    }
    
    public Brand getUserChoice(){
        return (Brand) Menu.<Brand> ref_getChoice(this);
    }
    
    public void addBrand(){
        String nID, nBrandName, nSoundBrand;
        double nPrice;
        
        Scanner sc = new Scanner(System.in);
        
        //Take Input
        do{
            nID = Menu.inputNonBlankStr("Enter Brand ID: ", "Brand ID");
            if(searchID(nID) > -1) System.out.println("Brand ID can not be duplicated.");
        } while (searchID(nID) > -1);
        
        nBrandName = Menu.inputNonBlankStr("Enter Brand Name: ", "Brand name");
        nSoundBrand = Menu.inputNonBlankStr("Enter Sound Brand: ", "Sound manufacturer");
        nPrice = Menu.inputDouble("Enter Price: ", "Price");
        
        this.add(new Brand(nID, nBrandName, nSoundBrand, nPrice));
        
    }
    
    public void updateBrand(){
        String iID;
        String uBrandName, uSoundBrand;
        int pos;
        double uPrice;
        Scanner sc = new Scanner(System.in);
       
        //Take Input
        do{
            iID = Menu.inputNonBlankStr("Your Brand ID your want to update: ", "Brand ID");
            pos = searchID(iID);
            if(pos == -1) System.out.println("Brand ID does not exist.");
        } while (pos == -1);
        
        uBrandName = Menu.inputNonBlankStr("Enter Brand Name: ", "Brand name");
        uSoundBrand = Menu.inputNonBlankStr("Enter Sound Brand", "Sound manufacturer");
        uPrice = Menu.inputDouble("Enter Price: ", "Pricce");
        
        //Update brand
        this.get(pos).setBrandName(uBrandName);
        this.get(pos).setSoundBrand(uSoundBrand);
        this.get(pos).setPrice(uPrice);
        
    }
    
    public void searchBrand(){
        Scanner sc = new Scanner(System.in);
        String iID;
        int pos;
        
        do{
            iID = Menu.inputNonBlankStr("Your Brand ID your want to search: ", "Brand ID");
            pos = searchID(iID);
            if(pos == -1) System.out.println("Not found");
        } while (searchID(iID) == -1);
        
        System.out.println(this.get(pos));
        
    }
    
    public void listBrands(){
        int n = this.size();
        for(int i = 0; i < n; ++i)  System.out.println(this.get(i));
    }
}