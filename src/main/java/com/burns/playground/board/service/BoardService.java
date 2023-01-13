package com.burns.playground.board.service;

import com.burns.playground.board.elem.Board;
import com.burns.playground.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * packageName    : com.burns.playground.board.service
 * fileName       : BoardService
 * author         : beomsu
 * date           : 2023-01-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-13        beomsu       최초 생성
 */
@RequiredArgsConstructor
@Service
public class BoardService
{
    private final BoardRepository repository;

    @Transactional
    public Long save(Board request)
    {
        return repository.save(request).getId();
    }

    public List<Board> findAll()
    {
        return new ArrayList<>(repository.findAll(Sort.by(Sort.Direction.DESC, "createDt")));
    }

    public Board FindById(Long id)
    {
        return repository.findById(id).orElse(null);
    }

    public int updateBoard(Board request)
    {
        return repository.updateBoard(request);
    }

    public void deleteBoard(long id)
    {
        repository.deleteById(id);
    }
}
