/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.TypeCountryModel;
import Repository.TypeCountryRepo;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class TypeCountryService {

    TypeCountryRepo typeCountryRepo = new TypeCountryRepo();

    public ArrayList<TypeCountryModel> all() {
        return typeCountryRepo.all();
    }

    public void insert(TypeCountryModel typeCountryModel) {
        typeCountryRepo.insert(typeCountryModel);
    }

    public void update(String id, TypeCountryModel typeCountryModel) {
        typeCountryRepo.update(id, typeCountryModel);
    }

    public void delete(String id) {
        typeCountryRepo.delete(id);
    }
}
