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

	/*
	 * Use below URL to add users to the database. The URL takes 2 params so that
	 * the user can add a name and profession into the MySQL database.
	 */
	// http://localhost:8090/YHVT19_Backend_Labb2_G/rest/UserService/addusersURL/inputNameHere/inputProfessionHere
	@GET
	@Path("/addusersURL/{param}/{param2}")
	@Produces(MediaType.APPLICATION_XML)
	public void SQL_Writer_URL(@PathParam("param") String inputName, @PathParam("param2") String inputProfession)
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

		userDao.writeToSQL_URL(inputName, inputProfession);
	}

	// The URL below retrieves users from the database.
	// http://localhost:8090/YHVT19_Backend_Labb2_G/rest/UserService/viewusers
	@GET
	@Path("/viewusers")
	@Produces(MediaType.APPLICATION_XML)
	public List<User> SQL_Retriever()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

		return userDao.retrieveFromSQL();
	}

	/*
	 * Use below URL to add users to the database. The URL takes 2 params so that
	 * the user can add a name and profession into the MySQL database.
	 */
	// http://localhost:8090/YHVT19_Backend_Labb2_G/rest/UserService/addusersURL_JSON/inputNameHere/inputProfessionHere
	@GET
	@Path("/addusersURL_JSON/{param}/{param2}")
	@Produces(MediaType.APPLICATION_JSON)
	public void SQL_Writer_URL_JSON(@PathParam("param") String inputName, @PathParam("param2") String inputProfession)
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

		userDao.writeToSQL_URL(inputName, inputProfession);
	}

	// The URL below retrieves users from the database.
	// http://localhost:8090/YHVT19_Backend_Labb2_G/rest/UserService/viewusers_JSON
	@GET
	@Path("/viewusers_JSON")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> SQL_Retriever_JSON()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

		return userDao.retrieveFromSQL();
	}

}