package com.burns.playground.board.controller;

import com.burns.playground.board.elem.Board;
import com.burns.playground.board.exception.BoardNotFoundException;
import com.burns.playground.board.service.BoardService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * packageName    : com.burns.playground.board.controller
 * fileName       : BoardController
 * author         : beomsu
 * date           : 2023-01-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-13        beomsu       최초 생성
 */
@RestController
@RequestMapping("/board")
public class BoardController
{

    private final BoardService service;

    public BoardController(BoardService service)
    {
        this.service = service;
    }

    @GetMapping("/")
    public MappingJacksonValue getBoardIndexPage()
    {
        List<Board> boards = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","title","contents","useYn","creatorId","createDt","modifyId","modifyDt");

        FilterProvider filters = new SimpleFilterProvider().addFilter("BoardInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(boards);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/{id}")
    //public EntityModel<MappingJacksonValue> post(@PathVariable long id)
    public MappingJacksonValue getBoard(@PathVariable Long id)
    {
        Board board = service.FindById(id);

        if (board == null) {
            throw new BoardNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","title","contents","useYn","creatorId","createDt","modifyId","modifyDt");

        FilterProvider filters = new SimpleFilterProvider().addFilter("BoardInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(board);
        mapping.setFilters(filters);

        /*// HATEOAS
        EntityModel<MappingJacksonValue> resource = EntityModel.of(mapping);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).boardIndexPage());
        resource.add(linkTo.withRel("all-board"));*/

        return mapping;
    }

    @PostMapping("/")
    public ResponseEntity<Board> postBoardWrite(@Valid @RequestBody Board board)
    {
        Long boardId = service.save(board);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(boardId)
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/")
    public ResponseEntity<Board> putBoardUpdate(@Valid @RequestBody Board board)
    {
        int boardId = service.updateBoard(board);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(boardId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id)
    {
        service.deleteBoard(id);
    }
}
