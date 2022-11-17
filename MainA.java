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

public class MainA {
	
public static void main(String[] args) {
		EntityManagerFactory factory = null;
		EntityManager em = null;
	
		try {
			factory = Persistence.createEntityManagerFactory("HibernateDemo");
			em = factory.createEntityManager();
		
			
			RepositoryActor rep = new RepositoryActor(em);
			
			Actor a1 = new Actor("Aamir","Khan",1965,null);
			Actor a2 = new Actor("Tobey","Maguire",1975,null);
			Actor a3 = new Actor("Bill","Skarsgad",1990,null);
			Actor a4 = new Actor("Salman","Khan",1965,null);
			
			List<Actor> actors = new ArrayList<Actor> ();
			
			actors.add(a1);
			actors.add(a2);
			actors.add(a3);
			actors.add(a4);
			

			List<Actor> actors1 = new ArrayList<Actor>();
			actors1.add(a1);
			actors1.add(a2);
			
			List<Actor> actors2 = new ArrayList<Actor>();
			actors2.add(a3);
			actors2.add(a4);
			
			
			Genre g1 = new Genre("Thrillers",null);
			Genre g2 = new Genre("Action",null);
			Genre g3 = new Genre("Horror",null);
			Genre g4 = new Genre("Comedy",null);
			
			RepositoryGenre grep = new RepositoryGenre(em);
			grep.save(g1);
			grep.save(g2);
			grep.save(g3);
			grep.save(g4);

			
			Movie m1 = new Movie("Ghajini",2008 ,actors1,g1);
			Movie m2 = new Movie("Spider Man",2002 ,actors1,g2);
			Movie m3 = new Movie("It",2017 ,actors2,g3);
			Movie m4 = new Movie("Ready",2011 ,actors2,g4);
			
			RepositoryMovie mrep = new RepositoryMovie(em);
			mrep.save(m1);
			mrep.save(m2);
			mrep.save(m3);
			mrep.save(m4);
			
			//Display actor by using id
			Optional<Actor> a = rep.findById(4);
			System.out.println(a);
			
			//Display all actors 
			List<Actor> actorsList = rep.findAllActors();
			System.out.println("Actors : " + actorsList);
		
			//Display greater born year than given year
			a = rep.findAllBornByYear(1965);
			System.out.println(a);
			
			//Display the surname of the actors
			List<Actor> lastname = rep.findAllWithLastNameEndsWith("n");		
			System.out.println("Actors : " + lastname );
			

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
			
		}

	}
}