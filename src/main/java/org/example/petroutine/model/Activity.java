package org.example.petroutine.model;

import java.time.OffsetDateTime;

public class Activity {
    private Long id;
    private Long petId;
    private ActivityType activityType;
    private OffsetDateTime performedAt;
    private String performedBy;
    private String notes;

    // Конструктор, гетери та сетери
    public Activity(Long id, Long petId, ActivityType activityType, OffsetDateTime performedAt, String performedBy, String notes) {
        this.id = id;
        this.petId = petId;
        this.activityType = activityType;
        this.performedAt = performedAt;
        this.performedBy = performedBy;
        this.notes = notes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPetId() { return petId; }
    public ActivityType getActivityType() { return activityType; }
    public OffsetDateTime getPerformedAt() { return performedAt; }
    public String getPerformedBy() { return performedBy; }
    public String getNotes() { return notes; }
}