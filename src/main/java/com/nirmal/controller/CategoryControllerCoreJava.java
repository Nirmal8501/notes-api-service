package com.nirmal.controller;

import java.util.List;

import com.google.gson.Gson;
import com.nirmal.dto.CategoryDto;
import com.nirmal.service.CategoryService;
import com.nirmal.service.impl.CategoryServiceImpl;

import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


// THIS FILE ISNT PART OF MAIN PROJECT, IT IS JUST MY OWN CORE JAVA IMPLEMENTATION

@Path("/categoryy")
public class CategoryControllerCoreJava {
	// this wont work as impl expects a dao to get autowired, but this is just a test class for core java implementation
	private final CategoryService categoryService = new CategoryServiceImpl();
	private final Gson gson = new Gson();
	
//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getCategoryyById(@PathParam("id") int id) {
//		
//	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategoriess(@PathParam("id") int id) {
		List<CategoryDto> allCategory = categoryService.getAllCategory();
		 String jsonResp =  gson.toJson(allCategory);
		 return Response.ok(jsonResp).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveCategoryy(String requestBody) {
		CategoryDto category = gson.fromJson(requestBody, CategoryDto.class);
		Boolean saveCategory = categoryService.saveCategory(category);
		if(saveCategory) {
			return Response.status(Response.Status.CREATED).entity("{\"msg\":\"Ok\"}").build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"error\":\"Unable to save\"}").build();
	}
}
