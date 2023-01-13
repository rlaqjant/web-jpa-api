package com.burns.playground.board.repository;

import com.burns.playground.board.elem.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * packageName    : com.burns.playground.board.repository
 * fileName       : BoardRepository
 * author         : beomsu
 * date           : 2023-01-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-13        beomsu       최초 생성
 */
public interface BoardRepository extends JpaRepository<Board,Long>
{
    String updateBoardQuery = "UPDATE board " +
            "SET title = :#{#request.title}, " +
            "contents = :#{#request.contents}, " +
            "use_yn = :#{#request.useYn}, " +
            "modify_id = :#{#request.modifyId}, " +
            "modify_dt = NOW() " +
            "WHERE id = :#{#request.id}";

    @Transactional
    @Modifying
    @Query(value = updateBoardQuery, nativeQuery = true)
    public int updateBoard(@Param("request") Board request);
}
