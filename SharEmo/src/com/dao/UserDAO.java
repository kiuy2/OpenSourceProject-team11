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
import com.entity.FollowTO;
import com.entity.LikeTO;
import com.entity.PageTO;
import com.entity.UserTO;

public class UserDAO {
	DataSource ds;

	public UserDAO() {
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public UserTO getUser(String _id) {
		UserTO user = new UserTO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String query = "SELECT * FROM user WHERE id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));
				user.setFollowernum(rs.getInt("followernum"));
				user.setMascot(rs.getString("mascot"));
				user.setPhone(rs.getString("phone"));
				user.setRegdate(rs.getString("regdate"));
				user.setPostnum(rs.getInt("postnum"));
			}

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
		
		return user;
	}

	public int getFollow(String _follow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = ds.getConnection();
			String query = "SELECT followernum from user WHERE id='" + _follow + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt("followernum");

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

	public boolean isFollow(String _follow, String _follower) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isFollow = false;
		try {
			con = ds.getConnection();
			String query = "select * from follow WHERE follow='" + _follow + "' and follower='" + _follower + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next())
				isFollow = true;

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
		return isFollow;
	}

	public boolean isLike(String _num, String _userid) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isLike = false;
		try {
			con = ds.getConnection();
			String query = "select * from likes WHERE like_boardnum=" + _num + " and like_userid='" + _userid + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next())
				isLike = true;

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

		return isLike;
	}

	// 로그인
	public UserTO login(String _id, String _password) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserTO user = new UserTO();
		try {
			con = ds.getConnection();
			String query = "select * from user where id = '" + _id + "' and password = '" + _password + "';";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setMascot(rs.getString("mascot"));
				user.setRegdate(rs.getString("regdate"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setFollowernum(rs.getInt("followernum"));
			} else {
				return null;
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

	public int setFollow(String _follow, String _follower) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = ds.getConnection();

			String query = "select * from follow WHERE follow='" + _follow + "' and follower='" + _follower + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (!rs.next()) {
				query = "insert INTO follow values(?,?);";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, _follow);
				pstmt.setString(2, _follower);
				pstmt.executeUpdate();

				query = "UPDATE user SET followernum = followernum + 1 WHERE id='" + _follow + "'";
				pstmt = con.prepareStatement(query);
				pstmt.executeUpdate();
			} else {
				query = "delete from follow WHERE follow='" + _follow + "' and follower='" + _follower + "'";
				pstmt = con.prepareStatement(query);
				pstmt.executeUpdate();

				query = "UPDATE user SET followernum = followernum - 1 WHERE id='" + _follow + "'";
				pstmt = con.prepareStatement(query);
				pstmt.executeUpdate();
			}

			query = "SELECT followernum from user WHERE id='" + _follow + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt("followernum");

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
	public int setLikes(String _num, String _userid) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = ds.getConnection();

			String query = "select * from likes WHERE like_boardnum=" + _num + " and like_userid='" + _userid + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (!rs.next()) {
				query = "insert INTO likes values(?,?);";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(_num));
				pstmt.setString(2, _userid);
				pstmt.executeUpdate();

				query = "UPDATE board SET likes = likes + 1 WHERE num=" + _num;
				pstmt = con.prepareStatement(query);
				pstmt.executeUpdate();

			} else {
				query = "delete from likes WHERE like_boardnum=" + _num + " and like_userid='" + _userid + "'";
				pstmt = con.prepareStatement(query);
				pstmt.executeUpdate();

				query = "UPDATE board SET likes = likes - 1 WHERE num=" + _num;
				pstmt = con.prepareStatement(query);
				pstmt.executeUpdate();
			}

			query = "SELECT likes from board WHERE num=" + _num;
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt("likes");

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

	// 회원가입
	public void signUp(String _id, String _password, String _name, String _nickname, String _mascot, String _phone,
			String _email) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			String query = "INSERT INTO user(id, password, name, nickname, mascot, "
					+ "phone, email) values (?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, _id);
			pstmt.setString(2, _password);
			pstmt.setString(3, _name);
			pstmt.setString(4, _nickname);
			pstmt.setString(5, _mascot);
			pstmt.setString(6, _phone);
			pstmt.setString(7, _email);

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

	// 페이지 구현
	public PageTO page(int curPage, String field_name, boolean method) {
		PageTO to = new PageTO();
		int totalCount = totalCount();

		ArrayList<UserTO> list = new ArrayList<UserTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String query = "SELECT * from user order by ";
			if (field_name != null) {
				query += field_name;
				if (method)
					query += " asc, ";
				else
					query += " desc, ";
			}
			query += "UNIX_TIMESTAMP(regdate) desc";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			int perPage = to.getPerPage();// 5

			int skip = (curPage - 1) * perPage;

			if (skip > 0) {
				rs.absolute(skip);
			}

			for (int i = 0; i < perPage && rs.next(); i++) {
				UserTO data = new UserTO();
				data.setId(rs.getString("id"));
				data.setName(rs.getString("name"));
				data.setNickname(rs.getString("nickname"));
				data.setEmail(rs.getString("email"));
				data.setFollowernum(rs.getInt("followernum"));
				data.setMascot(rs.getString("mascot"));
				data.setPhone(rs.getString("phone"));
				data.setRegdate(rs.getString("regdate"));
				data.setPostnum(rs.getInt("postnum"));
				list.add(data);
			}
			to.setUserList(list);
			to.setTotalCount(totalCount);
			to.setCurPage(curPage);
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

		return to;
	}

	public int totalCount() {

		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String query = "SELECT count(*) FROM user";
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

	// 좋아요를 누른 게시글
	public PageTO pageFollow(int _curPage, String _userid) {
		PageTO to = new PageTO();
		int totalCount = totalCount();

		ArrayList<UserTO> list = new ArrayList<UserTO>();
		ArrayList<FollowTO> follow_list = new ArrayList<FollowTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String query = "SELECT * FROM follow WHERE follower = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _userid);
			rs = pstmt.executeQuery();

			int perPage = to.getPerPage();// 5

			int skip = (_curPage - 1) * perPage;

			if (skip > 0) {
				rs.absolute(skip);
			}
			for (int i = 0; i < perPage && rs.next(); i++) {
				FollowTO follow = new FollowTO();
				follow.setFollow(rs.getString("follow"));
				follow.setFollower(rs.getString("follower"));
				follow_list.add(follow);
			}

			for (FollowTO follow: follow_list) {
				query = "SELECT * FROM user WHERE id = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, follow.getFollow());
				rs = pstmt.executeQuery();

				if(rs.next()) {
					
					UserTO user = new UserTO();
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
					user.setNickname(rs.getString("nickname"));
					user.setEmail(rs.getString("email"));
					user.setFollowernum(rs.getInt("followernum"));
					user.setMascot(rs.getString("mascot"));
					user.setPhone(rs.getString("phone"));
					user.setRegdate(rs.getString("regdate"));
					user.setPostnum(rs.getInt("postnum"));
					list.add(user);
				}
			}
			
			to.setUserList(list);
			to.setTotalCount(totalCount);
			to.setCurPage(_curPage);
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

}
