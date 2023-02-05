public class Book{
  //instance variables
  private String title; 
  private String subject;
  private int yearPub;
  private int numPages;
  private double rating; 

  //constructor
  public Book(String name, String sub, int year, int pages, double review){
    title = name;
    subject = sub;
    yearPub = year;
    numPages = pages;
    rating = review;
  }

  //getters
  public String getTitle(){
    return title;
  }
  public String getSubject(){
    return subject;
  }
  public int getYearPub(){
    return yearPub;
  }
  public int getNumPages(){
    return numPages;
  }
  public double getRating(){
    return rating;
  }

  //toString method
  public String toString(){
    return "Title: " + title + "\nSubject: " + subject + "\nYear Published: " + yearPub + "\nTotal Pages: " + numPages + "\nRating: " + rating; 
  }
}