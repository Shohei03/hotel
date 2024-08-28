package com.example.Hotel.rest;

import com.example.Hotel.domain.auth.UserEntity;
import com.example.Hotel.domain.auth.UserMapper;
import com.example.Hotel.domain.auth.UserService;
import com.example.Hotel.domain.hotelinfo.HotelForm;
import com.example.Hotel.domain.top.HotelEntity;
import com.example.Hotel.domain.top.HotelService;
import com.example.Hotel.web.user.UserForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelRestController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/restList")
    public List<HotelEntity> hotelList(String formData) {
        System.out.println("testrestHotel__" +formData);
        List<HotelEntity> hotels = hotelService.getHotelByName(formData);
        return hotels;
    }

    @DeleteMapping("/deletes")
    public int hotelDelete(String formData) {
        hotelService.deleteById(formData);
        return 0;
    }

}
