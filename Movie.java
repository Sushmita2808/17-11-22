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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private int id;
	
	@Column(name="movie_title", nullable = false, length = 225)
	private String title;

	@Column(name = "movie_release")
	private int year_of_release;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="actors_to_movies", 
			joinColumns = { @JoinColumn(name="actor_id")},
			inverseJoinColumns = { @JoinColumn(name = "movie_id")}
	)
	private List<Actor> actors;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="genre_id")
	private Genre genres;


	public Movie() {
		super();
		
	}
	

	public Movie(int id, String title, int year_of_release, List<Actor> actors, Genre genres) {
		super();
		this.id = id;
		this.title = title;
		this.year_of_release = year_of_release;
		this.actors= actors;
		this.genres = genres;
	}

	
	public Movie(String title, int year_of_release, List<Actor> actors, Genre genres) {
		super();
		this.title = title;
		this.year_of_release = year_of_release;
		this.actors = actors;
		this.genres = genres;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getYear_of_release() {
		return year_of_release;
	}


	public void setYear_of_release(int year_of_release) {
		this.year_of_release = year_of_release;
	}


	public List<Actor> getActors() {
		return actors;
	}


	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}


	public Genre getGenres() {
		return genres;
	}


	public void setGenres(Genre genres) {
		this.genres = genres;
	}


	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year_of_release=" + year_of_release + ", actors=" + actors
				+ ", genres=" + genres + "]";
	}


	
	

	
}
