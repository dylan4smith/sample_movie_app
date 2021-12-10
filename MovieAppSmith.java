// Dylan Smith
// Sample Movie App
// Add movies, remove movies, show most viewed, show highest rating

public class MovieAppSmith
{

}
class Movie implements Comparable
{
  private String name;
  private String genre;
  private int rating;
  private int views;

  // Constructor
  public Movie(String name, String genre, int rating, int views)
  {
    this.name = name;
    this.genre = genre;
    this.rating = rating;
    this.views = views;
  }
  // Set name
  public void setName(String setName)
  {
    name = setName;
  }
  // Set genre
  public void setGenre(String setGenre)
  {
    genre = setGenre;
  }
  // Set rating
  public void setRating(int setRating)
  {
    rating = setRating;
  }
  // Set views
  public void setViews(int setViews)
  {
    views = setViews;
  }
  // Get name
  public String getName()
  {
    return name;
  }
  // Get name
  public String getGenre()
  {
    return genre;
  }
  // Get name
  public int getRating()
  {
    return rating;
  }
  // Get name
  public int getViews()
  {
    return views;
  }
  // CompareTo
  public int compareTo(Object o)
  {
    Movie m = (Movie)o;
    return (this.name).compareTo(m.name);
  }
  // Equals
  public boolean equals(Movie other)
  {
    return this.name.equals(other.name) && this.genre.equals(other.genre);
  }
  // toString
  public String toString()
  {
    return name + " - " + genre + "\n";
  }
}
class ListNode
{
  private Movie movie;
  private ListNode next;

  // Constructor
  public ListNode(Movie m)
  {
    this.movie = m;
  }
  public ListNode()
  {
    // empty
  }
  public ListNode(Movie m, ListNode next)
  {
    this.movie = m;
    this.next = next;
  }
  // Set Next
  public void setNext(ListNode setNext)
  {
    next = setNext;
  }
  // Get Movie
  public Movie getMovie()
  {
    return movie;
  }
  // Get Next
  public ListNode getNext()
  {
    return next;
  }
}
interface List
{
  public void add(String name, String genre, int rating, int views);  
  public void add(int index, String name, String genre, int rating, int views); 
  public int indexOf(String name); //returns the index of the movie in the list
  public void remove(String name); //removes the movie from the list
  public int size(); //returns the size of the list
  public String toString(); //returns a string representing of all the movies in the list
  public Movie get(int position); //return the movie at the given position
}
class MovieList implements List
{
  private ListNode front;
  public static int size = 0;
  
  // Constructor
  public MovieList()
  {
    front = null;
  }
  public MovieList(Movie m)
  {
    front = new ListNode(m);
  }

  // Add movie to end of list
  public void add(String name, String genre, int rating, int views)
  {
    Movie m = new Movie(name, genre, rating, views);

    // copy fornt of list
    ListNode curr = front;

    // check if list is empty
    if(front ==null)
    {
      front = new ListNode(m);
      size++;
      return;
    }

    ListNode n = new ListNode(m);

    // finds end of list
    while(curr.getNext() != null)
    {
      curr = curr.getNext();
    }
    // add node to end
    curr.setNext(n);
    size++;
  }

  // Add movie at the given index
  public void add(int index, String name, String genre, int rating, int views)
  {
    Movie m = new Movie(name, genre, rating, views);
    // Checks if index is in range
    if(index > size)
    {
      return;
    }
    // Add to beginning of list
    if(index == 0)
    {
      ListNode n = new ListNode(m);
      n.setNext(front);
      front = n;
      size++;
      return;
    }
    // Add it at specific index
    ListNode curr = front;
    int i = 0;
    while(curr.getNext() != null && i < index-1)
    {
      curr = curr.getNext();
      i++;
    }
    ListNode n = new ListNode(m);
    n.setNext(curr.getNext());
    curr.setNext(n);
    size++;
  }

  // Returns the index of given movie
  public int indexOf(String name)
  {
    if(front == null)
    {
      return -1;
    }
    if(name.equals(front.getMovie().getName()))
    {
      return 0;
    }
    ListNode curr = front;
    int index = 0;
    while(curr != null && index <= size)
    {
      if(curr.getMovie().getName().equals(name))
      {
        return index;
      }
      curr = curr.getNext();
      index++;
    }
    return -1;
  }

  // Remove movie from list
  public void remove(String name)
  {
    if(front == null)
    {
      return;
    }
    if(front.getMovie().getName().equals(name))
    {
      front = front.getNext();
    }
    ListNode pre = front;
    ListNode curr = front;
    while(curr != null && !(curr.getMovie().getName().equals(name)))
    {
      pre = curr;
      curr = curr.getNext();
    }
    if(curr != null && curr.getNext() == null && (curr.getMovie().getName().equals(name)))
    {
      pre.setNext(null);
      size--;
      System.out.println("The last node was removed");
    }
    else if(curr == null)
    {
      System.out.println("Movie not found");
    }
    else
    {
      pre.setNext(curr.getNext());
      size--;
      System.out.println("A node in the middle was removed");
    }
  }

  // Return size of list
  public int size()
  {
    return size + 1;
  }

  // Return string of movies
  public String toString()
  {
    if(front == null)
    {
        return "";
    }
    ListNode curr = front;
    String s = "";

    while(curr != null)
    {
      s = s + curr.getMovie().toString();
      curr = curr.getNext();
    }
    return s;
  }

  // Return movie at given index
  public Movie get(int pos)
  {
    if(front == null)
    {
      return null;
    }
    if(pos > size)
    {
      return null;
    }

    ListNode curr = front;
    int index = 0;
    while(curr != null && pos != index)
    {
      index++;
      curr = curr.getNext();
    }
    if(curr == null)
    {
      return null;
    }
    return curr.getMovie();
  }

  // Return rating
  public String getMovie(int rating)
  {
    String s = "";
    ListNode curr = front;
    while(curr != null)
    {
      if(curr.getMovie().getRating() == rating)
      {
        s = s + curr.getMovie().getName() + "\n";
        curr = curr.getNext();
      }
      else
      {
        curr = curr.getNext();
      }
    }
    return s;
  }

  // Return most viewed
  public Movie mostWatched()
  {
    int mostViews = 0;
    ListNode curr = front;
    Movie mostViewedMovie = curr.getMovie();
    while(curr != null)
    {
      if(curr.getMovie().getViews() > mostViews)
      {
        mostViews = curr.getMovie().getViews();
        mostViewedMovie = curr.getMovie();
        curr = curr.getNext();
      }
      else
      {
        curr = curr.getNext();
      }
    }
    return mostViewedMovie;
  }
}
 /*
once you implement all the classes uncommnet the following code to test your program*/
class Driver
{
   public static void main (String [] args)
   {
    MovieList list = new MovieList();
    list.add("Sunny Day", "Action",5, 20000);
    list.add("Airplane", "Comedy", 3, 1200);
    list.add("Doctor Zhivago","Comedy", 4,23000);
    list.add("The Deer Hunter", "Family", 3, 2345);
    System.out.println("Here is the list of the movies\n");
    System.out.println(list);
    System.out.println("\nHere is the the movie that was most watched");
    System.out.println(list.mostWatched());
    System.out.println("Here is the list of 5 stars ratings\n");
    System.out.println(list.getMovie(5));
    System.out.println("Removing Reservoir movie\n");
    list.remove("Reservior Dogs");
    System.out.println(list);
    System.out.println("Displaying the second movie in the list\n");
    System.out.println(list.get(1));
    System.out.println("Adding a movie at position 2\n");
    list.add(2, "Up", "Cartoon",3,4500);
    System.out.println(list);
    int i = list.indexOf("Up"); 
    System.out.println("The movie up is in the position " + i);
   }
} 
/*To get the full credit you must create the following driver */
/*the following driver should be similar to the provided driver*/
class Driver2
{
   public static void main(String[] args)
   {
      //create a movie list
      MovieList list = new MovieList();
      //add 5 of your favoraite movie to it
      list.add("Dune", "Action", 5, 100000);
      list.add("Divergent", "Action", 3, 50000);
      list.add("Transformers", "Action", 4, 75000);
      list.add("Schindler's List", "Biography", 5, 200000);
      list.add("The Empire Strikes Back", "Action", 5, 300000);
      //display all the movies
      System.out.println(list);
      //display the most watched movie
      System.out.println("\nHere is the the movie that was most watched");
      System.out.println(list.mostWatched());
      //display the movie with the highest rating
      System.out.println("Here is the list of 5 stars ratings\n");
      System.out.println(list.getMovie(5));
      //display the index of one of the movies
      System.out.println("Displaying the fifth movie in the list\n");
      System.out.println(list.get(4));
      //remove one of the movies from the list
      System.out.println("Removing Divergent from movies\n");
      list.remove("Divergent");
      //display the list
      System.out.println(list);
      //display the movie at the index 1
      System.out.println("Displaying movie at index 1");
      System.out.println(list.get(1));
   }
}
