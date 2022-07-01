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
		return null;
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
