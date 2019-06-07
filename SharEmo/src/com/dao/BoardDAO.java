package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.entity.BoardDTO;
import com.entity.Emoticon;
import com.entity.PageTO;
import com.entity.User;


public class BoardDAO {

	DataSource ds;
	// 생성자
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 게시물 리스트 보여주기
	public ArrayList<BoardDTO> list() {

		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String query = "SELECT num,author,title,content,likes, " + "DATE_FORMAT(writeday,'%Y/%M/%D') writeday,"
					+ "readcnt,repRoot, repStep, repIndent FROM " + "board order by repRoot desc, repStep asc";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int likes=rs.getInt("likes");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");

				BoardDTO data = new BoardDTO();
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setLikes(likes);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				data.setRepRoot(repRoot);
				data.setRepStep(repStep);
				data.setRepIndent(repIndent);
				list.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 회원가입
	public void signUp(String _id, String _password, String _name, String _nickname, String _phone, String _email) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			String query = "INSERT INTO user values (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, _id);
			pstmt.setString(2, _password);
			pstmt.setString(3, _name);
			pstmt.setString(4, _nickname);
			pstmt.setString(5, _phone);
			pstmt.setString(6, _email);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 로그인
	public User login(String _id, String _password) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user=new User();
		try {
			con = ds.getConnection();
			String query = "select * from user where id = '" + _id + "' and password = '" + _password + "';";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				user.islogin=true;
				user.id = rs.getString("id");
				user.name = rs.getString("name");
				user.nickname = rs.getString("nickname");
				user.phone = rs.getString("phone");
				user.email = rs.getString("email");
			}
			
			if (!user.islogin && _id != null) {
				user.islogin=false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	// 글 쓰기
	public void write(String _userid, String _title, String _author, String _content) {
		int currval = 1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String sql = "select ifnull(max(num), 0)+1 from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				currval = rs.getInt(1);

			
			String query = "INSERT INTO board( userid, title, author,content,"
					+ "repRoot,repStep, repIndent) values (?,?,?,?,?, 0, 0)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, _userid);
			pstmt.setString(2, _title);
			pstmt.setString(3, _author);
			pstmt.setString(4, _content);
			pstmt.setInt(5, currval);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeImage(String _sysname, String _orgname) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardnum=0;
		try {
			con = ds.getConnection();
			String sql = "select ifnull(max(num), 0) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				boardnum = rs.getInt(1);

			String query = "INSERT INTO emoticon( boardnum, sysname, orgname)"
					+ " values (?,?,?)";
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, boardnum);
			pstmt.setString(2, _sysname);
			pstmt.setString(3, _orgname);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	// 조회수 1증가
	public int setLikes(String _num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int num=0;
		try {
			con = ds.getConnection();
			String query = "UPDATE board SET likes = likes + 1 WHERE num=" + _num;

			pstmt = con.prepareStatement(query);

			pstmt.executeUpdate();
			query = "SELECT likes from board WHERE num=" + _num;
			pstmt = con.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			if (rs.next())
				num=rs.getInt("likes");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	
	// 조회수 1증가
	public void readCount(String _num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String query = "UPDATE board SET readcnt = readcnt + 1 WHERE num=" + _num;

			pstmt = con.prepareStatement(query);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 게시물 자세히 보기
	public BoardDTO retrieve(String _num) {

		// 조회수 1증가
		readCount(_num);

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO data = new BoardDTO();

		try {
			con = ds.getConnection();
			String query = "SELECT * FROM board WHERE num = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String userid=rs.getString("userid");
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String content = rs.getString("content");
				int likes =rs.getInt("likes");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");

				data.setUserid(userid);
				data.setNum(num);
				data.setTitle(title);
				data.setAuthor(author);
				data.setContent(content);
				data.setLikes(likes);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return data;
	}

	// 게시물 수정
	public void update(String _num, String _title, String _author, String _content) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String query = "UPDATE board SET title = ?, author = ?," + "content = ? WHERE num = ?";

			pstmt = con.prepareStatement(query);

			pstmt.setString(1, _title);
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			pstmt.setInt(4, Integer.parseInt(_num));

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 게시물 삭제
	public void delete(String _num) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String query = "DELETE FROM board WHERE num=?";

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 답변 글 쓰기 UI 로드
	public BoardDTO replyui(String _num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO data = new BoardDTO();

		try {
			con = ds.getConnection();
			String query = "SELECT * FROM board WHERE num=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				data.setNum(rs.getInt("num"));
				data.setTitle(rs.getString("title"));
				data.setAuthor(rs.getString("author"));
				data.setContent(rs.getString("content"));
				data.setLikes(rs.getInt("likes"));
				data.setWriteday(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));
				data.setRepRoot(rs.getInt("repRoot"));
				data.setRepStep(rs.getInt("repStep"));
				data.setRepIndent(rs.getInt("repIndent"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return data;
	}

	// 답변시 repStep 1 증가
	public void makeReply(String _root, String _step) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String query = "UPDATE board SET repStep = repStep + 1 " + "WHERE repRoot = ? AND repStep > ?";

			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, Integer.parseInt(_root));
			pstmt.setInt(2, Integer.parseInt(_step));

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 답변달기
	public void reply(String _num, String _title, String _author, String _content, String _repRoot, String _repStep,
			String _repIndent) {

		// repStep + 1
		makeReply(_repRoot, _repStep);

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			String query = "INSERT INTO board(title, author, content, repRoot,"
					+ "repStep, repIndent) values (?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, _title);
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			pstmt.setInt(4, Integer.parseInt(_repRoot));
			pstmt.setInt(5, Integer.parseInt(_repStep) + 1);
			pstmt.setInt(6, Integer.parseInt(_repIndent) + 1);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 글 검색하기
	public PageTO search(String _searchName, String _searchValue, int curPage) {
		PageTO to = new PageTO();
		int totalCount = totalCount();

		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			String query = "SELECT num,author,title,content,"
					+ "DATE_FORMAT(writeday,'%Y/%M/%D') writeday, readcnt "
					+ ",repRoot, repStep, repIndent FROM "
					+ "board ";
			if (_searchName.equals("title")) {
				query += "WHERE title LIKE ? ";
			} else {
				query += "WHERE author LIKE ? ";
			}
			query+="order by repRoot desc, repStep asc";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + _searchValue + "%");
			rs = pstmt.executeQuery();
			
			int perPage = to.getPerPage();// 5

			int skip = (curPage - 1) * perPage;

			if (skip > 0) {
				rs.absolute(skip);
			}
			

			for (int i = 0; i < perPage && rs.next(); i++) {
				int num = rs.getInt("num");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");

				BoardDTO data = new BoardDTO();
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				data.setRepRoot(repRoot);
				data.setRepStep(repStep);
				data.setRepIndent(repIndent);
				list.add(data);
			}
			to.setList(list);
			to.setTotalCount(totalCount);
			to.setCurPage(curPage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return to;
	}

	public int totalCount() {

		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String query = "SELECT count(*) FROM board";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	// 페이지 구현
	public PageTO page(int curPage) {
		PageTO to = new PageTO();
		int totalCount = totalCount();

		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String query = "SELECT num,author,title,content," + "DATE_FORMAT(writeday,'%Y/%M/%D') writeday,"
					+ "readcnt,repRoot, repStep, repIndent FROM " + "board order by repRoot desc, repStep asc";
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			int perPage = to.getPerPage();// 5

			int skip = (curPage - 1) * perPage;

			if (skip > 0) {
				rs.absolute(skip);
			}
			for (int i = 0; i < perPage && rs.next(); i++) {
				int num = rs.getInt("num");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");

				BoardDTO data = new BoardDTO();
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				data.setRepRoot(repRoot);
				data.setRepStep(repStep);
				data.setRepIndent(repIndent);
				list.add(data);
			}
			to.setList(list);
			to.setTotalCount(totalCount);
			to.setCurPage(curPage);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return to;
	}

	public boolean IdCheck(String _id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String query = "SELECT id FROM user where id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<Emoticon> getEmoticon() {
		ArrayList<Emoticon> ticon = new ArrayList<Emoticon>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String query = "SELECT boardnum, sysname FROM emoticon order by boardnum desc;";
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			while( rs.next()) {
				int num = rs.getInt("boardnum");
				String sysname = rs.getString("sysname");
				Emoticon data = new Emoticon();
				data.setBoardnum(num);
				data.setSrc(sysname);
				ticon.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ticon;
	}

	//해당 게시물의 이모티콘만 추출
	public ArrayList<Emoticon> getEmoticon(String _num) {
		ArrayList<Emoticon> ticon = new ArrayList<Emoticon>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String query = "SELECT * FROM emoticon where boardnum="+_num;
			pstmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			while( rs.next()) {
				int num = rs.getInt("boardnum");
				String sysname = rs.getString("sysname");
				String orgname = rs.getString("orgname");
				Emoticon data = new Emoticon();
				data.setBoardnum(num);
				data.setSrc(sysname);
				data.setOrg(orgname);
				ticon.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ticon;
	}
	
}
