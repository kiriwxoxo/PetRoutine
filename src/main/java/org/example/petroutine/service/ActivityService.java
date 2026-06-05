package org.example.petroutine.service;

import org.example.petroutine.model.*;
import org.example.petroutine.dto.*;
import org.example.petroutine.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    // Наши "базы данных" в памяти
    private final List<Family> families = new ArrayList<>();
    private final List<Pet> pets = new ArrayList<>();
    private final List<Activity> activities = new ArrayList<>();

    private final AtomicLong idGenerator = new AtomicLong(1);

    public ActivityService() {
        // Инициализация тестовыми данными, чтобы было с чем работать на фронтенде
        Family myFamily = new Family(1L, "Верховецькі");
        families.add(myFamily);

        Pet rex = new Pet(1L, "Рекс", "Лабрадор", 1L);
        Pet busya = new Pet(2L, "Буся", "Чихуахуа", 1L);
        pets.add(rex);
        pets.add(busya);
    }

    public ActivityResponse createActivity(Long petId, ActivityRequest request, String performedBy) {
        // Проверка: существует ли такая собака?
        pets.stream()
                .filter(p -> p.getId().equals(petId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Собаку з ID " + petId + " не знайдено"));

        Activity newActivity = new Activity(
                idGenerator.getAndIncrement(),
                petId,
                request.activityType(),
                request.performedAt(),
                performedBy,
                request.notes()
        );
        activities.add(newActivity);
        return mapToResponse(newActivity);
    }

    public List<ActivityResponse> getTodayActivities(Long petId) {
        OffsetDateTime startOfDay = OffsetDateTime.now(ZoneOffset.UTC).withHour(0).withMinute(0).withSecond(0);

        return activities.stream()
                .filter(a -> a.getPetId().equals(petId))
                .filter(a -> a.getPerformedAt().isAfter(startOfDay))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Новый метод: получить всех питомцев семьи
    public List<Pet> getPetsByFamily(Long familyId) {
        return pets.stream()
                .filter(p -> p.getFamilyId().equals(familyId))
                .collect(Collectors.toList());
    }

    private ActivityResponse mapToResponse(Activity activity) {
        return new ActivityResponse(
                activity.getId(),
                activity.getPetId(),
                activity.getActivityType(),
                activity.getPerformedAt(),
                activity.getPerformedBy(),
                activity.getNotes()
        );
    }
}