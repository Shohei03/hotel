package com.example.Hotel.rest;

import com.example.Hotel.domain.top.HotelEntity;
import com.example.Hotel.domain.top.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class RestIndexController {
    @Autowired
    private HotelService hotelService;


    @GetMapping("/get/list")
    public List<HotelEntity> getList(String orderItem, List<HotelEntity> hotelList ) {
        //List<HotelEntity> hotelList = null;

        hotelList = hotelService.getHotelByNameByOrder("ホテル2", orderItem);
        //hotelList = hotelService.getHotelByName("ホテル2", orderItem);
        System.out.println("hotelList : " + hotelList);

        return hotelList;

    }
}
