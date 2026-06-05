package org.example.petroutine.model;

public class Pet {
    private Long id;
    private String name;
    private String breed;
    private Long familyId; // ID сім'ї, якій належить собака

    public Pet(Long id, String name, String breed, Long familyId) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.familyId = familyId;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getBreed() { return breed; }
    public Long getFamilyId() { return familyId; }
}