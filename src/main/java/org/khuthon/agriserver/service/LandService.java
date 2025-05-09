package org.khuthon.agriserver.service;

import java.util.List;

import org.khuthon.agriserver.dao.LandDao;
import org.khuthon.agriserver.dto.Land;
import org.springframework.stereotype.Service;

@Service
public class LandService {

    private LandDao landDao;

    public LandService(LandDao landDao) {
        this.landDao = landDao;
    }

    public List<Land> getLandList() {
        // return null;
        return landDao.getLandList();
    }

    public Land getLand(int id) {
        
        return landDao.getLand(id);
    }

    public boolean registerLand(int ownerid, Double latitude, double longitude, String landName, String contents, String phone,
            int size, int price) {
        
        try {
            landDao.registerLand(ownerid, latitude, longitude, landName, contents, phone, size, price);
            return true;
        }
        catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteLand(int id, int owner_id) {
        try {
            landDao.deleteLand(id, owner_id);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public boolean registerLandUse(Integer id, int landId) {
        try {
            landDao.registerLandUse(id,landId );
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public boolean finishLandUse(Integer id, int landId) {
        try {
            landDao.finishLandUse(id,landId );
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    // 사용자 ID로 해당 사용자가 보유한 땅들 조회
    public List<Land> getLandsByUserId(int userId) {
        return landDao.getLandsByUserId(userId);
    }
}
