import java.util.Scanner; 

class Main {
  //declaring global data set array 
  public static ArrayList<Book> bookDataSet;
  //declaring global scanner
  public static Scanner scan = new Scanner(System.in); //create scanner object
  //declaring global exit variable
  public static boolean exit = false; 
  
  //method to create data set of books
  public static ArrayList<Book> createDataSet(){
    //used to keep track of publication years and subjects already assigned
    ArrayList<Integer> yearsUsed = new ArrayList<>();
    ArrayList<String> subjects = new ArrayList<>();
    subjects.add("Programming");
    subjects.add("Data Structures");
    subjects.add("Algorithms");
    subjects.add("Operating Systems");
    subjects.add("Gaming");
    int[] subjectCount = {0, 0, 0, 0, 0}; 
  
    //create data set of 20 books
    ArrayList<Book> books = new ArrayList<>();
    
    for(int i = 1; i <= 20; i++){
      String title = "Book" + i;
      int yearPub = (int)(Math.random()*(2019-1980)+1980); //assign a random year published
      while(yearsUsed.indexOf(yearPub) != -1){ //check if that year is already used: if yes, then assign a new random year until we get an unused year
        yearPub = (int)(Math.random()*(2019-1980)+1980);
      }
      yearsUsed.add(yearPub); //add year to yearsUsed when year is taken 
      int numPages = (int)(Math.random()*(1000-50)+50);
      String subject = subjects.get((int)(Math.random()*5)); //assign a random subject
      while(subjectCount[subjects.indexOf(subject)] >= 5){ //check if that subject is already used 5 times: if yes, then assign a new random subject until we get an available subject 
        subject = subjects.get((int)(Math.random()*5));
      }
      subjectCount[subjects.indexOf(subject)]++; //increment subject count when book is assigned to subject
      double rating = Math.random()*(10.0-0.1)+0.1;
      rating = ((double)((int)(rating *10.0)))/10.0; //trimming rating to 1 decimal place
      books.add(new Book(title, subject, yearPub, numPages, rating)); 
    }
    
    return books;
  }

  //method to print menu
  public static void printMenu(){
    System.out.println("Options:");
    System.out.println("1. Display all books");
    System.out.println("2. Display books sorted by year of publication (starting with oldest)");
    System.out.println("3. Display books sorted by length in pages (starting with shortest)");
    System.out.println("4. Display books sorted by review ratings (starting with highest rating)");
    System.out.println("5. Search for a subject");
    System.out.println("6. Search for a specific book");
    System.out.println("7. Add a book");
    System.out.println("8. Exit");
  }
  
  //method to display a set of books 
  public static void display(ArrayList<Book> set){
    for(int i = 0; i < set.getSize(); i++){
      System.out.println(set.get(i).getTitle());
    }
  }
  
  //method to sort arraylist by year of publication (uses selection sort)
  public static void sortByYear(){
    for(int i = 0; i < bookDataSet.getSize()-1; i++){
      int min = i;
      for(int j = i+1; j < bookDataSet.getSize(); j++){
        if(bookDataSet.get(j).getYearPub() < bookDataSet.get(min).getYearPub()){
          min = j;
        }
      }
      Book temp = bookDataSet.get(i); 
      bookDataSet.set(i, bookDataSet.get(min));
      bookDataSet.set(min, temp);
    }
  }
  
  //method to sort arraylist by length (uses selection sort) 
  public static void sortByLength(){
    for(int i = 0; i < bookDataSet.getSize()-1; i++){
      int min = i;
      for(int j = i+1; j < bookDataSet.getSize(); j++){
        if(bookDataSet.get(j).getNumPages() < bookDataSet.get(min).getNumPages()){
          min = j;
        }
      }
      Book temp = bookDataSet.get(i); 
      bookDataSet.set(i, bookDataSet.get(min));
      bookDataSet.set(min, temp);
    }
  }

  //method to sort arraylist by rating (uses selection sort)
  public static void sortByRating(){
    for(int i = 0; i < bookDataSet.getSize()-1; i++){
      int max = i;
      for(int j = i+1; j < bookDataSet.getSize(); j++){
        if(bookDataSet.get(j).getRating() > bookDataSet.get(max).getRating()){
          max = j;
        }
      }
      Book temp = bookDataSet.get(i); 
      bookDataSet.set(i, bookDataSet.get(max));
      bookDataSet.set(max, temp);
    }
  }

  //method to return all books belonging specific subject (uses selection search)
  public static ArrayList<Book> inSubject(String subject){
    ArrayList<Book> booksInSubject = new ArrayList<>();
    for(int i = 0; i < bookDataSet.getSize(); i++){
      if(bookDataSet.get(i).getSubject().equals(subject)){
        booksInSubject.add(bookDataSet.get(i));
      }
    }
    return booksInSubject; 
  }

  //method to add book
  public static void addBook(){
    System.out.print("Title: ");
    String title = scan.nextLine();
    
    System.out.print("Subject: ");
    String subject = scan.nextLine();
    
    System.out.print("Year Published: ");
    int yearPub = scan.nextInt();
    
    System.out.print("Number of Pages: ");
    int numPages = scan.nextInt();
    
    System.out.print("Review Rating (1.0-10.0): ");
    double rating = scan.nextDouble();
    scan.nextLine();
    
    Book newBook = new Book(title, subject, yearPub, numPages, rating);
    bookDataSet.add(newBook);
    System.out.println("Book added!");
  }
  
  //method to analyze user input
  public static void choice(String input){
    if(input.equals("1")){
      display(bookDataSet);
    }
    else if(input.equals("2")){
      sortByYear();
      display(bookDataSet);
    }
    else if(input.equals("3")){
      sortByLength();
      display(bookDataSet);
    }
    else if(input.equals("4")){
      sortByRating();
      display(bookDataSet);
    }
    else if(input.equals("5")){
      System.out.print("Search for a subject: "); 
      String subject = scan.nextLine();
      display(inSubject(subject));
    }
    else if(input.equals("6")){
      System.out.print("Search for a book: ");
      String book = scan.nextLine();
      //search for book in data set (using selection search)
      boolean found = false;
      for(int i = 0; i < bookDataSet.getSize(); i++){
        if(bookDataSet.get(i).getTitle().equals(book)){
          System.out.println(bookDataSet.get(i).toString());
          found = true;
        }
      }
      if(!found){
        System.out.println("That book does not exist in our library.");
      }
    }
    else if(input.equals("7")){
      addBook();
    }
    else if(input.equals("8")){
      exit = true;
    }
    else{
      System.out.println("Not an option!");
    }
  }

  //main
  public static void main(String[] args) {
    bookDataSet = createDataSet(); //initialize data set
    while(!exit){
      printMenu();
      System.out.print("Select an option #: ");
      String input = scan.nextLine(); //read user input
      choice(input); //analyze user input and prompt accordingly
    } 
  }
}

