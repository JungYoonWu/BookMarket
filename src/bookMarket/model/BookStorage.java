package bookMarket.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BookStorage {
   
   ArrayList<Book> bookList = new ArrayList<>();
   final int MAX_QUANTITY = 10;
   private String bookFilename = "booklist.txt";
   
   public BookStorage()throws IOException{
	   loadBookListFromFile();
   }
   
   public void loadBookListFromFile() throws IOException{
	   FileReader fr;
	   try {
		   fr = new FileReader(bookFilename);
		   BufferedReader br = new BufferedReader(fr);
		   String idStr;
		   while((idStr = br.readLine()) != null) {
			   int id = Integer.parseInt(idStr);
			   String title = br.readLine();
			   String author = br.readLine();
			   String publisher = br.readLine();
			   int price = Integer.parseInt(br.readLine());
			   bookList.add(new Book(id,title,author,publisher,price));
		   }
		   fr.close();
		   br.close();
	   }catch(FileNotFoundException | NumberFormatException e) {
		   System.out.println(e.getMessage());
	   }
   }
   /*
   public BookStorage() {
	   bookList.add(new Book(2401, "쉽게 배우는 자바 프로그래밍 2판", "우종정", "한빛아카데미", 20000));
	   bookList.add(new Book(2402, "코딩 자율학습 HTML+CSS+자바스크립트", "김기수", "길벗", 30000));
	   bookList.add(new Book(2403, "Do It! 자료구조와 함께 배우는 알고리즘 입문 - 자바편", "보요시바타", "이지스퍼블리싱", 25000));
      
   }*/
   
   public int getNumBooks() {   //
      return bookList.size();
   }

   public String getBookInfo(int i) {//
      
      return bookList.get(i).toString();
   }

   public boolean isValidBook(int bookId) {//
      for(Book book : bookList) {
         if(book.getBookId() == bookId) {return true;}
      }
      return false;
   }

   public Book getBookById(int bookId) {
      for(Book book : bookList) {
         if(book.getBookId() == bookId) {return book;}
      }
      return null;
   }

public int getMaxQuantity() {
	return MAX_QUANTITY;
}

}