package com.spring.cucumber.models;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@Entity @Data
@Table(name = "addresses")
@AllArgsConstructor
@JsonPropertyOrder({"addressType","addressLine1","addressLine2","city","state","postalCode","country"})
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(
			title = "Address ID",
			description = "Address ID of the user. Auto-generated by the application",
			example = "yyyyy-zzzz-xxxx-uuuu"
	)
	@JsonIgnore
	private long id;

	@NotNull(message = "Address type is mandatory!!!")
	@Enumerated
	@Schema(
			title = "AddressType",
			description = "Address Type",
			required = true,
			implementation = AddressType.class
	)
	@Column(name = "address_type", nullable = false, length = 10)
	private AddressType addressType;

	@NotBlank(message = "Address Line 1 is mandatory!!!")
	@NotNull(message = "Address Line 1 is mandatory!!!")
	@Schema(
			title = "addressLine1",
			description = "addressLine1 of the user",
			example = "H.No : 1-2-36/7",
			required = true,
			minLength = 6,
			maxLength = 100
	)
	@Column(name = "address_line_1", nullable = false, length = 100)
	private String addressLine1;
	
	@Schema(
			title = "addressLine2",
			description = "addressLine2 of the user",
			example = "Shilpa Enclave, Friends Colony, Chandanagar",
			required = true,
			minLength = 6,
			maxLength = 100
	)
	@Column(name = "address_line_2", length = 100)
	private String addressLine2;
	
	@NotBlank(message = "City is mandatory!!!")
	@NotNull(message = "City is mandatory!!!")
	@Schema(
			title = "city",
			description = "City",
			example = "Hyderabad",
			required = true,
			maxLength = 50
	)
	@Column(name = "city", nullable = false, length = 50)
	private String city;
	
	@Schema(
			title = "state",
			description = "State",
			example = "Telanagana",
			required = true,
			maxLength = 50
	)
	@Column(name = "state", length = 50)
	private String state;
	
	@Positive(message = "Postal code should be a positive number")
	@Schema(
			title = "postalCode",
			description = "Postal Code",
			example = "500050",
			required = true,
			minLength = 6,
			maxLength = 6
	)
	@Column(name = "postal_code", nullable = false, length = 6)
	private int postalCode;
	
	@NotBlank(message = "Country is mandatory!!!")
	@NotNull(message = "Country is mandatory!!!")
	@Schema(
			title = "country",
			description = "Country",
			example = "India",
			required = true,
			maxLength = 32
	)
	@Column(name = "country", nullable = false, length = 32)
	private String country;
}