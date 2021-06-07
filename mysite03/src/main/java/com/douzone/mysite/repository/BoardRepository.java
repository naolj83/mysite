package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(BoardVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("board.insert", vo);
		System.out.println(vo);
		return count == 1;
	}
		
	public boolean findMaxGroupNo(Long groupNo) {
		System.out.println(groupNo);
		int count = sqlSession.selectOne("board.findMaxGroupNo");
		System.out.println(groupNo);
		return count == 1;
	}

	public boolean delete(BoardVo vo) {
		System.out.println(vo);
		int count = sqlSession.selectOne("board.delete", vo);
		System.out.println(vo);
		return count == 1;

	}

	public List<BoardVo> findAll() {
		return sqlSession.selectList("board.findAll");
	}

	public boolean updateHit(BoardVo vo) {
		System.out.println(vo);
		int count = sqlSession.selectOne("board.updateHit", vo);
		return count == 1;
	}
	
	public boolean update(BoardVo vo) {
		System.out.println(vo);
		int count = sqlSession.selectOne("board.update", vo);
		System.out.println(vo);
		return count == 1;
	}
	
	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}
	
	public boolean updatereply(BoardVo vo) {
		System.out.println(vo);
		int count = sqlSession.selectOne("board.updatereply", vo);
		System.out.println(vo);
		return count == 1;
	}
}
