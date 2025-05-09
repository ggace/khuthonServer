package org.khuthon.agriserver.controller;

import java.util.List;

import org.khuthon.agriserver.dto.Land;
import org.khuthon.agriserver.service.LandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@RestController
@RequestMapping("/api/land")
public class LandController {

    private LandService landService;

    public LandController(LandService landService) {
        this.landService = landService;
    }

    @GetMapping("/")
    public List<Land> getLandList(HttpServletRequest request) {
        System.out.println(request.getAttribute("loginedId"));
        List<Land> landList = landService.getLandList();

        return landList;
    }
    @GetMapping("/{id}")
    public Land getLand(@PathVariable int id) {
        Land land = landService.getLand(id);
        
        return land;
    }

    @Getter
    @Setter
    class LandDto {
        private Double latitude;
        private Double longitude;
        private String landName;
        private String contents;
        private String phone;
        private Integer size;

    }

    @PostMapping(value="/")
    public String makeLand(Double latitude,Double longitude,String landName,String contents,String phone,Integer size, Integer price, HttpServletRequest request) {
        // System.out.println("test");
        if(request.getAttribute("isLogined") == null || !(boolean)(request.getAttribute("isLogined"))) {
            return "not logined <script> location.replace('/land.html') </script>";
        }
// 
        // System.out.println(latitude + " " + longitude);
        Integer id = (Integer)request.getAttribute("loginedId");
        if(!landService.registerLand(1, latitude, longitude, landName, contents, phone, size, price)) {
            return "failed to register <script> location.replace('/land.html') </script>";
        }
        
        return "successfully registered <script> location.replace('/land.html') </script>";
    }

    @DeleteMapping("/{id}")
    public String deleteLand(HttpServletRequest request, @PathVariable int id) {

        if(request.getAttribute("isLogined") == null || !(boolean)(request.getAttribute("isLogined"))) {
            return "not logined";
        }

        if(!landService.deleteLand(id, 1)) {
            return "failed to delete";
        }
        
        return "successfully deleted";
    }
    
    @GetMapping("/useLand/{landId}")
    public String registerLandUse(HttpServletRequest request, @PathVariable int landId) {
        if(request.getAttribute("isLogined") == null || !(boolean)(request.getAttribute("isLogined"))) {
            return "not logined";
        }

        Integer id = (Integer)request.getAttribute(("loginedId"));

        if(!landService.registerLandUse(id, landId)) {
            return "failed to delete";
        }        
        
        return "registered";

    }
    @PostMapping("/useLand/{landId}")
    public String finishLandUse(HttpServletRequest request, @PathVariable int landId) {
        if(request.getAttribute("isLogined") == null || !(boolean)(request.getAttribute("isLogined"))) {
            return "not logined";
        }

        Integer id = (Integer)request.getAttribute(("loginedId"));

        if(!landService.finishLandUse(id, landId)) {
            return "failed to delete";
        }        
        
        return "registered";

    }
    
    
}
