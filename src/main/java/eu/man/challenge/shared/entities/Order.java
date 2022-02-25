package eu.man.challenge.shared.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "id" }, allowGetters = true, allowSetters = true)
public class Order {
	private String id;
	private String customer;
	private List<String> ingredients;

	public Order(String customer, List<String> ingredients) {
		this.customer = customer;
		this.ingredients = ingredients;
	}

	public Order(String id, String customer, List<String> ingredients) {
		this.id = id;
		this.customer = customer;
		this.ingredients = ingredients;
	}

	public Order() {

	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	@JsonIgnore
	public boolean isNull() {
		return false;
	}
	
}
