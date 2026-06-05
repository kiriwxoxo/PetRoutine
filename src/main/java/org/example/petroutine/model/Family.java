package org.example.petroutine.model;

import java.util.ArrayList;
import java.util.List;

public class Family {
    private Long id;
    private String familyName;
    private List<Pet> pets = new ArrayList<>();

    public Family(Long id, String familyName) {
        this.id = id;
        this.familyName = familyName;
    }

    // Геттери та методи для додавання собак
    public Long getId() { return id; }
    public String getFamilyName() { return familyName; }
    public List<Pet> getPets() { return pets; }
    public void addPet(Pet pet) { this.pets.add(pet); }
}