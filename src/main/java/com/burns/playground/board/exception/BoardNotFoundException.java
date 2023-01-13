package com.burns.playground.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * packageName    : com.burns.playground.board.exception
 * fileName       : BoardNotFoundException
 * author         : beomsu
 * date           : 2023-01-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-13        beomsu       최초 생성
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BoardNotFoundException extends RuntimeException
{
    public BoardNotFoundException(String message) {
        super(message);
    }
}
