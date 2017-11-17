package com.techelevator.clebrews.model;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.clebrews.security.PasswordHasher;



@Component
public class JDBCUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;

	@Autowired
	public JDBCUserDAO(DataSource dataSource, PasswordHasher passwordHasher) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.passwordHasher = passwordHasher;
	}
	
	@Override
	public void saveUser(String userName, String password) {
		byte[] salt = passwordHasher.generateRandomSalt();
		String hashedPassword = passwordHasher.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		jdbcTemplate.update("INSERT INTO users(username, password, salt) VALUES (?, ?, ?)", userName, hashedPassword, saltString);
	}

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * "+
							      "FROM users "+
							      "WHERE UPPER(username) = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(results.next()) {
			String storedSalt = results.getString("salt");
			String storedPassword = results.getString("password");
			String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
			return storedPassword.equals(hashedPassword);
		} else {
			return false;
		}
	}

	@Override
	public boolean searchForUsername(String userName) {
		String sqlSearchForUser = "SELECT * "+
								  "FROM users "+
			      				  "WHERE UPPER(username) = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(results.next()) {
			return true;
		} else {
		return false;
		}
	}
	
	@Override
	public void updatePassword(String userName, String password) {
		byte[] salt = passwordHasher.generateRandomSalt();
		String hashedPassword = passwordHasher.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		jdbcTemplate.update("UPDATE users SET password = ?, salt = ? WHERE username = ?", hashedPassword, saltString, userName);
	}

}
