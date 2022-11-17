package com.hibernate.jpa.demo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_id")
	private int id;
	
	@Column(name="genre_name", nullable = false, length = 225)
	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="genre_id")
	private List<Movie> movie;
	
	public Genre() {
		super();
		
	}
	

	public Genre(String name, List<Movie> movie) {
		super();
		this.name = name;
		this.movie = movie;
	}


	public Genre(int id, String name, List<Movie> movie) {
		super();
		this.id = id;
		this.name = name;
		this.movie = movie;
	}


	public Genre(String name) {
		super();
		this.name = name;
		
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


	public List<Movie> getMovie() {
		return movie;
	}


	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}


	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + ", movie=" + movie + "]";
	}

	
}
