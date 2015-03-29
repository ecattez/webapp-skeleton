package fr.lordrski;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class UserMapper implements ResultSetMapper<User> {

	@Override
	public User map(int index, ResultSet rs, StatementContext sc) throws SQLException {
		return new User(rs.getString("login"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"),
				rs.getString("birthday"), rs.getString("email"));
	}

}
