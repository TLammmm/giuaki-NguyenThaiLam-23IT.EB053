package test;

import java.sql.Connection;

import database.jd;

public class testjdbc {
public static void main(String[]args) {
	Connection connection=jd.getConnection();
	System.out.print(connection);
	jd.closeConnection(connection);
	System.out.print(connection);
}
}
