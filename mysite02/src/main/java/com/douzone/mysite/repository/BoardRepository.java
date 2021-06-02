package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardRepository {

	public Boolean insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();

			String sql = " insert" + "   into board" + " values (null, ?, ?, now(), ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getHit());
			pstmt.setInt(4, vo.getGroupNo());
			pstmt.setInt(5, vo.getOrderNo());
			pstmt.setInt(6, vo.getDepth());
			pstmt.setLong(7, vo.getUserNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public Boolean insert2(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();

			String sql = " insert" + "   into board" + " values (null, ?, ?, now(), ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getHit());
			pstmt.setInt(4, vo.getGroupNo());
			pstmt.setInt(5, vo.getOrderNo());
			pstmt.setInt(6, vo.getDepth());
			pstmt.setLong(7, vo.getUserNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	
	
	
	
	public int findMaxGroupNo() {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select max(group_no)" + "  from board";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				result = rs.getInt(1);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public Boolean delete(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();

			String sql = "delete " + " from board" + "   where no = ? " ;
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}


	
	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select a.no, a.title, a.contents, a.reg_date, a.hit, a.group_no, a.order_no, a.depth, a.user_no, b.name"
					+ "   from board a, user b" + "  where a.user_no = b.no" + " order by group_no desc , order_no asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);
				int hit = rs.getInt(5);
				int groupNo = rs.getInt(6);
				int orderNo = rs.getInt(7);
				int depth = rs.getInt(8);
				Long userNo = rs.getLong(9);
				String userName = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setRegDate(regDate);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);

				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	public BoardVo findByNo(Long boardNo) {
		BoardVo result = new BoardVo();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select a.no, a.title, a.contents, a.reg_date, a.hit, a.group_no, a.order_no, a.depth, a.user_no, b.name"
					+ "   from board a, user b" + "  where a.user_no = b.no" + " and a.no = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, boardNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);
				int hit = rs.getInt(5);
				int groupNo = rs.getInt(6);
				int orderNo = rs.getInt(7);
				int depth = rs.getInt(8);
				Long userNo = rs.getLong(9);
				String userName = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setRegDate(regDate);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);

				result = vo;
			}

		} catch (SQLException e) {
			System.out.println("error " + e);
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error " + e);
			}
		}
		return result;
	}

	public boolean updateHit(Long boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();

			String sql = "update board "
					+ "set hit=hit+1 "
					+ "where no=? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, boardNo);

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error " + e);
			}
		}
		return result;
	}
	
	public boolean update(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();

			String sql = "update board " + 
						 "  set title=?, contents=?, reg_date=now() " +
						 " where no=? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error " + e);
			}
		}
		return result;
		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.80.131:3307/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}


}
