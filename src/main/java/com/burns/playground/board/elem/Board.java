package com.burns.playground.board.elem;

import com.burns.playground.common.CommonTimeEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * packageName    : com.burns.playground.board
 * fileName       : Board
 * author         : beomsu
 * date           : 2023-01-12
 * description    : 게시판 entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-12        beomsu       최초 생성
 */
@Entity(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonFilter("BoardInfo")
public class Board extends CommonTimeEntity
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String contents;
    private String useYn;
    private Long creatorId;
    private Long modifyId;
}
