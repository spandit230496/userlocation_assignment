package com.userlocation.project.service;


import com.userlocation.project.dto.UserLocationDTO;
import com.userlocation.project.modal.UserLocation;
import com.userlocation.project.repository.UserLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserLocationService {
    @Autowired
    UserLocationRepository userLocationRepository;
    // in this i have used to save the data through userlocationDTO
    public void saveUserLocation(UserLocationDTO userLocationDTO) {
        UserLocation userLocation = new UserLocation();
        userLocation.setName(userLocationDTO.getName());
        userLocation.setLongitude(userLocationDTO.getLongitude());
        userLocation.setLatitude(userLocationDTO.getLatitude());

        userLocationRepository.save(userLocation);
    }

  // in this i have use to update the location of the user
    public void updateUserLocation(UserLocationDTO userLocationDTO) {
        try {
            UserLocation userLocation = userLocationRepository.findByName(userLocationDTO.getName());
            userLocation.setLongitude(userLocationDTO.getLongitude());
            userLocation.setLatitude(userLocationDTO.getLatitude());

            userLocationRepository.save(userLocation);
        }
        catch (Exception e){
            throw new RuntimeException("User not Found");
        }


    }

    //in this i have use to delete the userlocation of the user if available
  public void deleteUserLocation(String name){
        try{UserLocation userLocation=userLocationRepository.findByName(name);
        userLocationRepository.delete(userLocation);
        }
        catch(Exception e){
            throw new RuntimeException("Sorry details not available");
        }
  }
  // in this we can get N near user  if available
    public List<UserLocation> getNearUser(int N){
        List<UserLocation> all=userLocationRepository.findAll();

        List<UserLocation>nearest= new ArrayList<>();

        Collections.sort(all,(a,b)->{

            if(a.getLatitude()==b.getLatitude())
                return a.getLongitude()-b.getLongitude();

            else
                return a.getLatitude()-b.getLatitude();
        });


   if(N<all.size()) {
       for (int i = 0; i < N; i++)
           nearest.add(all.get(i));
   }


   else { for (int i = 0; i < all.size(); i++)
       nearest.add(all.get(i));}


        return nearest;
    }

}

