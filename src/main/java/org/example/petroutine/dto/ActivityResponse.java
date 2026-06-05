package org.example.petroutine.dto;

import org.example.petroutine.model.ActivityType;
import java.time.OffsetDateTime;

public record ActivityResponse(
        Long id,
        Long petId,
        ActivityType activityType,
        OffsetDateTime performedAt,
        String performedBy,
        String notes
) {}