package com.burns.playground;

import com.burns.playground.board.elem.Board;
import com.burns.playground.board.service.BoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlaygroundApplicationTests {

	@Autowired
	private BoardService service;

	@Test
	public void save()
	{
		Board request = new Board();
		request.setTitle("제목1");
		request.setContents("내용1");
		request.setUseYn("Y");
		request.setCreatorId(666L);
		request.setModifyId(666L);

		Assertions.assertNotEquals(0, service.save(request));
		Assertions.assertNotEquals(0, service.save(request));
	}

	@Test
	public void findAll()
	{
		Assertions.assertNotEquals(0, service.findAll().size());
	}

	@Test
	public void updateBoard()
	{
		Board request = new Board();
		request.setId(1L);
		request.setTitle("제목 업데이트");
		request.setContents("내용 업데이트");
		request.setCreatorId(666L);
		request.setUseYn("Y");

		Assertions.assertEquals(1,service.updateBoard(request));
	}

	@Test
	public void deleteBoard()
	{
		service.deleteBoard(2L);
	}
}
