package net.hibernate.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookManager {
	
	protected SessionFactory sessionFactory;
	
	protected void setup()
	{
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		        .configure() // configures settings from hibernate.cfg.xml
		        .build();
		try {
		    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			ex.printStackTrace();
		    StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	protected void exit()
	{
		sessionFactory.close();
	}
	
    protected void create()
    {
    	  Book book = new Book();
    	    book.setTitle("Game of Thrones");
    	    book.setAuthor("George Martin");
    	    book.setPrice(25.0f);
    	    
    	    
    	    
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	session.save(book);
    	session.close();
    }
    
    protected void read()
    {
    	 Session session = sessionFactory.openSession();
    	 
    	    long bookId = 6;
    	    Book book = session.get(Book.class, bookId);
    	 
    	    System.out.println("Title: " + book.getTitle());
    	    System.out.println("Author: " + book.getAuthor());
    	    System.out.println("Price: " + book.getPrice());
    	 
    	    session.close();
    	    
    	 
    }
    
    protected void update() {
        Book book = new Book();
        book.setId(3);
        book.setTitle("Game of Thrones");
        book.setAuthor("George Martin");
        book.setPrice(23.99f);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.update(book);
     
        session.getTransaction().commit();
        session.close();
    }
    
    protected void delete() {
        Book book = new Book();
        book.setId(5);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.delete(book);
     
        session.getTransaction().commit();
        session.close();
    }
	
	public static void main(String[]args)
	{
	      BookManager manager=new BookManager();	
	      manager.setup();
	      manager.create();
	      manager.read();
	      /*manager.update();*/
	      /*manager.delete();*/
	      manager.exit();
	}

}


