package service;

import config.ServletContextConfig;
import domain.dao.UserDao;
import domain.dao.UserDaoImpl;
import domain.entity.User;
import web.dto.SignupReqDto;

public class UserServiceImpl implements UserService {
	private final UserDao userDao; 
	
	public UserServiceImpl() {
		userDao = ServletContextConfig.getInstance().getUserDao();
	}
	
	@Override
	public boolean createUser(SignupReqDto signupReqDto) throws Exception {
		return userDao.save(signupReqDto.toEntity()) > 0;
	}

	@Override
	public void getUser() throws Exception {
		
	}

	@Override
	public boolean checkUsername(String username) throws Exception {
		return userDao.findUserByUsername(username) != null;
	}

	@Override
	public boolean updateUser() throws Exception {
		return false;
	}

	@Override
	public boolean deleteUser() throws Exception {
		return false;
	}

	@Override
	public User loadUser(String username, String password) throws Exception {
		User user = userDao.findUserByUsername(username);
		if(user == null) {
			return null; //원래는 throws Exception
		} else {
			return user.getPassword().equals(password)? user : null;			
		}
	}


}
