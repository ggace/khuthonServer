package org.khuthon.agriserver.controller;

import java.util.List;

import org.khuthon.agriserver.dto.Land;
import org.khuthon.agriserver.service.LandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/land")
public class LandController {

    private LandService landService;

    public LandController(LandService landService) {
        this.landService = landService;
    }

    @GetMapping("/")
    public List<Land> getLandList(HttpServletRequest request) {
        
        List<Land> landList = landService.getLandList();

        return landList;
    }
    @GetMapping("/{id}")
    public Land getLand(@PathVariable int id) {
        Land land = landService.getLand(id);
        
        return land;
    }
    @PostMapping("/")
    public String makeLand(HttpServletRequest request, @RequestBody RegRequest regRequest) {
        if(request.getAttribute("isLogined") == null || !(boolean)(request.getAttribute("isLogined"))) {
            return "not logined";
        }

        if(!landService.registerLand(1, regRequest.latitude, regRequest.longitude, regRequest.landName, regRequest.contents, regRequest.phone, regRequest.size)) {
            return "failed to register";
        }
        
        return "successfully registered";
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

    class RegRequest {
        public int latitude;
        public int longitude;
        public String landName;
        public String contents;
        public String phone;
        public int size;
    }
    
    @GetMapping("/useLand")
    public String registerLandUse(HttpServletRequest request, int landId) {
        if(request.getAttribute("isLogined") == null || !(boolean)(request.getAttribute("isLogined"))) {
            return "not logined";
        }

        Integer id = (Integer)request.getAttribute(("loginedId"));

        if(!landService.registerLandUse(id, landId)) {
            return "failed to delete";
        }        
        
        return "registered";

    }
    @PostMapping("/useLand")
    public String finishLandUse(HttpServletRequest request, int landId) {
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
