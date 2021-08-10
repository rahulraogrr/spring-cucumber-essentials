package com.spring.cucumber.api;

import com.spring.cucumber.exceptions.custom.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.cucumber.exceptions.models.ApiErrorResponse;
import com.spring.cucumber.models.User;
import com.spring.cucumber.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "USER", description = "UserController")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Operation(summary = "Get All Users", description = "Get All Users From Database", tags = {"USER"})
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = User.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "404", description = "Resource Not Found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers() throws NotFoundException{
	  return ResponseEntity.ok(userService.getAllUsers());
	}

	@Operation(summary = "Save User", description = "Save A New User To Database", tags = {"USER"})
	@ApiResponses( value = {
			@ApiResponse(responseCode = "201", description = "Successful Operation", content = @Content(schema = @Schema(implementation = String.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "404", description = "Resource Not Found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody User user){
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentRequest() 
				.path("/{username}")
				.buildAndExpand(userService.createUser(user).getUsername()).toUri())
				.build();
	}

	@Operation(summary = "Delete User", description = "Delete A New User From Database", tags = {"USER"})
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = String.class), mediaType = MediaType.TEXT_HTML_VALUE)),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "404", description = "Resource Not Found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	@DeleteMapping(value = "/{username}")
	public ResponseEntity<?> deleteUser(@Valid
										@Size(min = 8, max = 12,
												message = "Username must be a minimum of 6 and maximum of 12 Characters")
										@PathVariable String username) throws NotFoundException {
		log.info("Delete Process Started For {}",username);
		userService.deleteUser(username);
		return ResponseEntity.ok("Resource Deleted Successfully!!!");
	}

	@Operation(summary = "Get User By Username", description = "Get User By Passing His Username", tags = {"USER"})
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = User.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "404", description = "Resource Not Found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	@GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserByUsername(@Valid
												  @Size(min = 8, max = 12,
														  message = "Username must be a minimum of 6 and maximum of 12 Characters")
												  @PathVariable String username) throws NotFoundException {
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}

	@Operation(summary = "Modify User By Username", description = "Modify User By Passing His Username", tags = {"USER"})
	@ApiResponses( value = {
			@ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = User.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "404", description = "Resource Not Found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	@PutMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> modifyUser(@PathVariable String username, @Valid @RequestBody User user) throws NotFoundException {
		return ResponseEntity.ok(userService.modifyUser(user,username));
	}
}