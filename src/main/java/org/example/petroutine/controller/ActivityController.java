package org.example.petroutine.controller;

import jakarta.validation.Valid;
import org.example.petroutine.dto.ActivityRequest;
import org.example.petroutine.dto.ActivityResponse;
import org.example.petroutine.model.Pet;
import org.example.petroutine.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1") // Общий путь для всего API
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    // --- Методы для семей и питомцев ---
    @GetMapping("/families/{familyId}/pets")
    public List<Pet> getPetsByFamily(@PathVariable Long familyId) {
        return activityService.getPetsByFamily(familyId);
    }

    // --- Методы для активностей ---
    @PostMapping("/pets/{petId}/activities")
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityResponse logActivity(
            @PathVariable Long petId,
            @Valid @RequestBody ActivityRequest request,
            @RequestHeader(value = "Authorization", defaultValue = "Bearer mock_token") String token) {

        String mockUser = "Олександр";
        return activityService.createActivity(petId, request, mockUser);
    }

    @GetMapping("/pets/{petId}/activities/today")
    public List<ActivityResponse> getTodayActivities(@PathVariable Long petId) {
        return activityService.getTodayActivities(petId);
    }
}