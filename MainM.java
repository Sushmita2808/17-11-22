package com.hibernate.jpa.demo;
/*
 * 1.	Create the following tables by using Hibernate JPA. 
 * (Don’t create tables by your own, let hibernate to do it.)
	a.	actors
	id – int
 	name – varchar(255)
	last_name – varchar(255)
	year_of_birth–int
	b.	genres
	id – int
	name – varchar(255)
	c.	movies
	id – int
	title – varchar(255)
	year_of_release – int
	genre_idint
	d.	actors_to_movies (There is no class by this name)
	actor_id – int
	movie_id – int

 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;

public class MainM {
	
public static void main(String[] args) {
	EntityManagerFactory factory = null;
	EntityManager em = null;
	
	try {
		
		factory = Persistence.createEntityManagerFactory("HibernateDemo");
		em = factory.createEntityManager();
		
		RepositoryActor rep = new RepositoryActor(em);
	
	Actor a1 = new Actor("Vijay","Deverakonda",1989,null);
	Actor a2 = new Actor("Rashmika","Mandana",1996,null);
	Actor a3 = new Actor("Allu ","Arjun",1982,null);
	Actor a4 = new Actor("Kajal"," Aggarwal",1985,null);
	
	List<Actor> actors = new ArrayList<Actor> ();
	
	actors.add(a1);
	actors.add(a2);
	actors.add(a3);
	actors.add(a4);
	
	for(Actor a:actors) {
		rep.save(a);
	}
	
	List<Actor> actors1 = new ArrayList<Actor>();
	actors1.add(a1);
	actors1.add(a2);
	
	List<Actor> actors2 = new ArrayList<Actor>();
	actors2.add(a3);
	actors2.add(a4);
	
	
	Genre g1 = new Genre("Drama",null);
	Genre g2 = new Genre("Action",null);
	
	RepositoryGenre grep = new RepositoryGenre(em);
	grep.save(g1);
	grep.save(g2);
	
	Movie m1 = new Movie("Dear comrade",2019 ,actors1,g1);
	Movie m2 = new Movie("Arya 2",2009 ,actors2,g2);
	
	RepositoryMovie mrep = new RepositoryMovie(em);
	mrep.save(m1);
	mrep.save(m2);
	
	
	//Display movie by using id
	
	Optional<Movie> m = mrep.findById(1);
	System.out.println("------------------  Find  ID  --------------------------");
	System.out.println("\nMovies : "+ m);
	System.out.println("--------------------------------------------------------");
	
	//Display all movies
	List<Movie> movieList = mrep.findAll();
	System.out.println("-----------------   Show All   -------------------------");
	System.out.println("\nMovies : " + movieList);
	System.out.println("--------------------------------------------------------");
	
	//find movie by using title
	m = mrep.findByTitle("Dear comrade");
	System.out.println("-----------------  Find Title  -------------------------");
	System.out.println("\nMovies :"+ m);
	System.out.println("--------------------------------------------------------");
	
	//removing one Movie record from the database.
	 mrep.remove(m2);
	 List<Movie> mList = mrep.findAll();
	 System.out.println("-----------------   Show All   -------------------------");
	System.out.println("\nMovies : " + mList);
	System.out.println("--------------------------------------------------------");	
	
	//removing all Movie records from the database
	 mrep.remove();
	 System.out.println("---------------- After Remove -------------------------");
	System.out.println("--------------------------------------------------------");
	}
	catch(HibernateException e) {
	e.printStackTrace();
	System.out.println("Exception Hibernate");
	}
	catch(Exception e) {
	e.printStackTrace();
	System.out.println("Exception");
	}
	finally {
		 if(factory!= null) {
			 factory.close();
		 }
	   }
	}
}	

 
