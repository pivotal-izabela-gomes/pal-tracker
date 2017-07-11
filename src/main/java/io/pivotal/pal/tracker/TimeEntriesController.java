package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

/**
 * Created by pivotal on 7/11/17.
 */
@RestController
public class TimeEntriesController {

    private TimeEntryRepository repository;

    public TimeEntriesController(TimeEntryRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path= "/timeEntries")
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(repository.list());
    }

    @GetMapping(path= "/timeEntries/{id}")
    public ResponseEntity<?> read(@PathVariable long id) {
        TimeEntry timeEntry = repository.get(id);
        if(timeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(timeEntry);
    }

    @PostMapping(path= "/timeEntries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        repository.create(timeEntry);
        return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);
    }

    @PutMapping(path= "/timeEntries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updated = repository.update(id, timeEntry);
        if(updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(path="/timeEntries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        repository.delete(id);
        return ResponseEntity.noContent().build();

    }
}
