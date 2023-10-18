package CarPrj.Classes;

import CarPrj.Classes.Com.BrandList;
import CarPrj.Classes.Com.CarList;
import CarPrj.Classes.Com.Menu;
import java.io.IOException;
import java.util.ArrayList;

public class CarManager {
    public static void main(String[] args) throws IOException {
        BrandList brandList = new BrandList();   
        CarList carList = new CarList(brandList);
        ArrayList<String> ops = new ArrayList<>();
        
        brandList.loadFromFile("brands.txt");
        carList.loadFromFile("cars.txt");
        
        ops.add("List all brands");
        ops.add("Add a new brand");
        ops.add("Search a brand based on its ID");
        ops.add("Update a brand");
        ops.add("Save brands to the file, named brands.txt");
        ops.add("List all cars in ascending order of brand names");
        ops.add("List cars based on a part of an input brand name");
        ops.add("Add a car");
        ops.add("Remove a car based on its ID");
        ops.add("Update a car based on its ID");
        ops.add("Save cars to file, named cars.txt");
        ops.add("Exit");
      
        
        int choice;
        
        do{
            System.out.println(); choice = Menu.int_getChoice(ops);
            
            switch(choice){
                case 0: brandList.listBrands();                                     break;
                case 1: brandList.addBrand();                                       break;
                case 2: brandList.searchBrand();                                    break;
                case 3: brandList.updateBrand();    carList.updateCar();            break;
                case 4: brandList.saveToFile("brands.txt");                         break;
                case 5: carList.listCars();                                         break;
                case 6: carList.printBasedBrandName();                              break;
                case 7: carList.addCar();                                           break;
                case 8: carList.removeCar();                                        break;
                case 9: carList.updateCar();                                        break;
                case 10: carList.saveToFile("cars.txt");                            break;
                case 11:                                                            return;                            
            }
            
        } while(choice >= 0 && choice < ops.size());
        
    }
}
