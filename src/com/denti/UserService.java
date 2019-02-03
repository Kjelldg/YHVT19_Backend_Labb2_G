package com.denti;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Path("/UserService")

public class UserService {

	UserDao userDao = new UserDao();

	// http://localhost:8080/YHVT19_Backend_Labb2_G/rest/UserService/addusersURL/inputTheNameHere/inputTheProfessionHere
	@GET
	@Path("/addusersURL/{param}/{param2}")
	@Produces(MediaType.APPLICATION_XML)
	public void SQL_Writer_URL(@PathParam("param") String inputName, @PathParam("param2") String inputProfession)
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

		userDao.writeToSQL_URL(inputName, inputProfession);
	}

	// http://localhost:8080/YHVT19_Backend_Labb2_G/rest/UserService/viewusers
	@GET
	@Path("/viewusers")
	@Produces(MediaType.APPLICATION_XML)
	public List<User> SQL_Retriever()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

		return userDao.retrieveFromSQL();
	}

}