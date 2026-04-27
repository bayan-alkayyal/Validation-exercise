package com.example.validation_exerciseQ2.Controller;

import com.example.validation_exerciseQ2.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@RequestBody @Valid Event event , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        events.add(event);
        return ResponseEntity.status(200).body("Event added successfully");
    }//add event (create)


    @GetMapping("/display")
    public ResponseEntity<?> displayEvent(){
        return ResponseEntity.status(200).body(events);
    }//display all event (read)


    @PutMapping("/update/{index}")
    public ResponseEntity<?> updateEvent(@PathVariable int index , @RequestBody @Valid Event event , Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if(index < 0 || index >= events.size()){
          return ResponseEntity.status(400).body("event not found");
        }

        events.set(index,event);
        return ResponseEntity.status(200).body("Event updated successfully");
    }//update event (update)

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> deleteEvent(@PathVariable int index){
        if(index < 0 || index >= events.size()){
            return ResponseEntity.status(400).body("event not found");
        }
        events.remove(index) ;
        return ResponseEntity.status(200).body("Event deleted successfully");
    }//delete event (delete)


    @PutMapping("/change/{index}/{capacity}")
    public ResponseEntity<?> changeCapacity(@PathVariable int index , @PathVariable int capacity){
        if(index < 0 || index >= events.size()){
            return ResponseEntity.status(400).body("event not found");
        }

        if(capacity <= 25){
            return ResponseEntity.status(400).body("capacity should be greater than 25");
        }
        events.get(index).setCapacity(capacity);
            return ResponseEntity.status(200).body("capacity changed successfully");
    }//change capacity

    @GetMapping("/id/{id}")
    public ResponseEntity<?> searchById(@PathVariable String id){
        for(Event event : events){
            if(event.getId().equals(id)){
            return ResponseEntity.status(200).body(event);
            }
        }
        return ResponseEntity.status(400).body("id not found");
    }//search by id








}
