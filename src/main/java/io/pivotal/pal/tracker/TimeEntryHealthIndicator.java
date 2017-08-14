package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created by pivotal on 8/14/17.
 */
@Component
public class TimeEntryHealthIndicator implements HealthIndicator {
    private static final int MAX_TIME_ENTRIES = 5;
    private final TimeEntryRepository timeEntryRepo;

    public TimeEntryHealthIndicator(TimeEntryRepository timeEntryRepo) {
        this.timeEntryRepo = timeEntryRepo;
    }

    @Override
    public Health health() {

        boolean isUp = timeEntryRepo.list().size() < MAX_TIME_ENTRIES;
        if (isUp) {
            return Health.up().build();
        }
        return Health.down().build();
    }
}
