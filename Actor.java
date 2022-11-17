package com.hibernate.jpa.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "actors")
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actor_id")
	private int id;
	
	@Column(name="first_name", nullable = false, length = 225)
	private String name;
	
	@Column(name="last_name", nullable = false, length = 225)
	private String last_name;
	
	@Column(name = "birthday")
	private int year_of_birth;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="actors_to_movies", 
			joinColumns = { @JoinColumn(name="actor_id")},
			inverseJoinColumns = { @JoinColumn(name = "movie_id")}
	)
    private List<Movie> movie;
 
	
	public Actor() {
		super();
		
	}


	public Actor(int id, String name, String last_name, int year_of_birth, List<Movie> movie) {
		super();
		this.id = id;
		this.name = name;
		this.last_name = last_name;
		this.year_of_birth = year_of_birth;
		this.movie = movie;
	}


	public Actor(String name, String last_name, int year_of_birth, List<Movie> movie) {
		super();
		this.name = name;
		this.last_name = last_name;
		this.year_of_birth = year_of_birth;
		this.movie = movie;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public int getYear_of_birth() {
		return year_of_birth;
	}


	public void setYear_of_birth(int year_of_birth) {
		this.year_of_birth = year_of_birth;
	}


	public List<Movie> getMovie() {
		return movie;
	}


	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}


	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", last_name=" + last_name + ", year_of_birth=" + year_of_birth
				+ ", movie=" + movie + "]";
	}

	
}
