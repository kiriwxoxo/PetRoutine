package org.example.petroutine.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.example.petroutine.model.ActivityType;
import java.time.OffsetDateTime;

public record ActivityRequest(
        @NotNull(message = "Activity type is required")
        ActivityType activityType,

        @NotNull(message = "Time is required")
        @PastOrPresent(message = "Activity cannot be in the future")
        OffsetDateTime performedAt,

        String notes
) {}