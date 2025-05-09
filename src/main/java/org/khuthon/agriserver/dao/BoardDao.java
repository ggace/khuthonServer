package org.khuthon.agriserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.khuthon.agriserver.dto.Board;

@Mapper
public interface BoardDao {
    List<Board> getAllBoards();
}
