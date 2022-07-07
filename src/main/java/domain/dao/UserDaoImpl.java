package domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.entity.User;
import domain.jdbc.DBConnectionMgr;

public class UserDaoImpl implements UserDao {
	private DBConnectionMgr pool;
	
	private String sql;
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDaoImpl() {
		pool = DBConnectionMgr.getInstance();
	}
	
	@Override
	public int save(User user) throws Exception {
		int result = 0;
		
		sql = "INSERT INTO\n"
				+ "	user_mst\n"
				+ "VALUES(\n"
				+ "	0,\n"
				+ "	?,\n"
				+ "	?,\n"
				+ "	?,\n"
				+ "	?,\n"
				+ "	?,\n"
				+ "	?,\n"
				+ "	NOW(),\n"
				+ "	NOW()\n"
				+ ");";
		con = pool.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getRoles());
			pstmt.setString(6, user.getProvider());
			
			result = pstmt.executeUpdate(); // 몇건의 insert가 완료됐는지
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return result;
	}

	@Override
	public User findUserByUsername(String username) throws Exception {
		User user = null;
		
		sql = "SELECT\n"
				+ "	um.user_code,\n"
				+ "	um.name,\n"
				+ "	um.email,\n"
				+ "	um.username,\n"
				+ "	um.password,\n"
				+ "	um.roles,\n"
				+ "	um.provider,\n"
				+ "	ud.user_profile_img,\n"
				+ "	ud.user_address,\n"
				+ "	ud.user_phone,\n"
				+ "	ud.user_gender\n"
				+ "FROM \n"
				+ "	user_mst um\n"
				+ "	LEFT OUTER JOIN user_dtl ud ON(ud.user_code = um.user_code)\n"
				+ "WHERE\n"
				+ "	um.username=?";
		
		con = pool.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // returns true or false
				System.out.println("hi");
				user = User.builder()
						.user_code(rs.getInt(1))
						.name(rs.getString(2))
						.email(rs.getString(3))
						.username(rs.getString(4))
						.password(rs.getString(5))
						.roles(rs.getString(6))
						.provider(rs.getString(7))
						.user_profile_img(rs.getString(8))
						.user_address(rs.getString(9))
						.user_phone(rs.getString(10))
						.user_gender(rs.getInt(11))
						.build();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return user;
	}

	@Override
	public int modify(int user_code) throws Exception {
		return 0;
	}

	@Override
	public int remove(int user_code) throws Exception {
		return 0;
	}

	
}
