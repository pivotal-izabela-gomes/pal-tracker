package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pivotal on 7/11/17.
 */
@RestController
@RequestMapping(path= "/time-entries")
public class TimeEntriesController {

    private TimeEntryRepository repository;

    public TimeEntriesController(TimeEntryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(repository.list());
    }

    @GetMapping(path= "{id}")
    public ResponseEntity<?> read(@PathVariable long id) {
        TimeEntry timeEntry = repository.find(id);
        if(timeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(timeEntry);
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        TimeEntry created = repository.create(timeEntry);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(path= "{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updated = repository.update(id, timeEntry);
        if(updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        repository.delete(id);
        return ResponseEntity.noContent().build();

    }
}
