package org.khuthon.agriserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.khuthon.agriserver.dto.Land;

@Mapper
public interface LandDao {

    public List<Land> getLandList();
    public Land getLand(int id);
    public void registerLand(int ownerid, int latitude, int longitude, String landName, String contents, String phone,
            int size);
    public void deleteLand(int id, int owner_id);
    public void registerLandUse(int id, int landId);
    public void finishLandUse(int id, int landId);
}
