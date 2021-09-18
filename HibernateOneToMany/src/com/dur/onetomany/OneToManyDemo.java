package com.dur.onetomany;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;





public class OneToManyDemo {
	
	private static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
	
	public static void main(String[] args) {
		Session session= factory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Question question= new Question();
		question.setQuestionId(1);
		question.setQuestion("What is java?");
	

		Answer answer = new Answer();
	    answer.setAnswerId(1);
		answer.setAnswer("Java is programming lang");
		answer.setQuestion(question);
		
		Answer answer1 = new Answer();
		answer1.setAnswerId(2);
		answer1.setAnswer("Java is platform independent");
		answer1.setQuestion(question);
		
		Answer answer2 = new Answer();
		answer2.setAnswerId(3);
		answer2.setAnswer("Java is object orinted");
		answer2.setQuestion(question);
		
		
		session.save(answer);
		session.save(answer1);
		session.save(answer2);
		List<Answer> ansList = new LinkedList<>();
		ansList.add(answer);
		ansList.add(answer1);
		ansList.add(answer2);
		
		question.setAnswers(ansList); 
		session.save(question);
		
		
		Question q1= (Question) session.get(Question.class, 1);
		System.out.println(q1.getQuestion());
		
		for(Answer a1:ansList)
		{
			System.out.println(a1.getAnswer());
		}

		transaction.commit();
		


	}

}
