package org.khuthon.agriserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.khuthon.agriserver.dto.Board;

@Mapper
public interface BoardDao {

    // 모든 게시판 조회
    List<Board> getAllBoards();

    // 게시판 생성
    void insertBoard(Board board);
}
