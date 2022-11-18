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
			
			Actor a1 = new Actor("Vijay","Deverakonda",1989,null);
			Actor a2 = new Actor("Rashmika","Mandana",1996,null);
			Actor a3 = new Actor("Allu ","Arjun",1982,null);
			Actor a4 = new Actor("Kajal"," Aggarwal",1985,null);
			
			List<Actor> actors = new ArrayList<Actor> ();
			
			actors.add(a1);
			actors.add(a2);
			actors.add(a3);
			actors.add(a4);
			
			//7.	Saving objects of type Actor to the database
			for(Actor a:actors) {
				rep.save(a);;
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
			
			//Display actor by using id
			Optional<Actor> a = rep.findById(1);
			System.out.println("-----------------   Find Id    -------------------------");
			System.out.println("Actors"+a);
			System.out.println("--------------------------------------------------------");
			
			//Display all actors 
			List<Actor> actorsList = rep.findAllActors();
			System.out.println("-----------------   Show All   -------------------------");
			System.out.println("Actors : " + actorsList);
			System.out.println("--------------------------------------------------------");
			
			//Display greater born year than given year
			List<Actor> aList = rep.findAllBornByYear(1985);
			System.out.println("-----------------After Born Year-------------------------");
			System.out.println("Actors : "+ aList);
			System.out.println("--------------------------------------------------------");
			
			//Display the surname of the actors
			List<Actor> lastname = rep.findAllWithLastNameEndsWith("da");
			System.out.println("-----------------  Find Actors   -------------------------");
			System.out.println("Actors : " + lastname );
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
