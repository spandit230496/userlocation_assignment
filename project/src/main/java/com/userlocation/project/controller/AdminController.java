package com.userlocation.project.controller;



import com.userlocation.project.dto.UserLocationDTO;
import com.userlocation.project.service.UserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserLocationService userLocationService;

    @PostMapping("/create_data")
    public String saveUserLocation(@RequestBody UserLocationDTO userLocationDTO){
        userLocationService.saveUserLocation(userLocationDTO);

        return "UserLocation Added Successfully";
    }

    @PostMapping("/update_data")
    public String updateUser( @RequestBody UserLocationDTO userLocationDTO){
        userLocationService.updateUserLocation(userLocationDTO);

        return "User Location Updated ";
    }

    @DeleteMapping("/delete_user")
    public ResponseEntity<String> deleteUserLocation(@RequestParam("name") String name){
        try{
        userLocationService.deleteUserLocation(name);

        }
       catch(Exception e){
           return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                   ("Deleted Not able tp delete "+name + " or details with name " +name+" not available" );
       }

        return  ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
    }

}
