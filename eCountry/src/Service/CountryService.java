/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.CountryModel;
import Repository.CountryRepo;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CountryService {

    CountryRepo countryRepo = new CountryRepo();

    public ArrayList<CountryModel> combobox() {
        return countryRepo.Combobox();
    }

    public ArrayList<CountryModel> all() {
        return countryRepo.all();
    }

    public void insert(CountryModel countryModel) {
        countryRepo.insert(countryModel);
    }

    public void update(String id, CountryModel countryModel) {
        countryRepo.update(id, countryModel);
    }

    public void delete(String id) {
        countryRepo.delete(id);
    }
}
