package bookMarket.view;

import java.util.Scanner;

import bookMarket.model.BookStorage;
import bookMarket.model.Cart;
import bookMarket.model.Customer;

public class ConsoleView {

   public void displayWelcome() {//
      String welcome = ("*****************************************\n"
                + "*    Welcome to YoonWu Book Market      *\n"
                + "*****************************************");
      System.out.println(welcome);
   }

   public int selectMenu(String[] menuList) {//
      displayMenu(menuList);
      
      int menu;
      do {
          System.out.print(">> 메뉴 선택 : ");
          menu = inputNumberWithValidation();
          if(menu <0 || menu>= menuList.length) {
             System.out.println("0부터" + (menuList.length-1) + "까지의 숫자를 입력하세요.");
          }
      }while(menu < 0 || menu >= menuList.length);
      
      return menu;
   }
   
   private int inputNumberWithValidation() {//
	   Scanner input = new Scanner(System.in);
       try {
    	   int number = input.nextInt();  
		   return number;
       } catch (Exception e) {
    	   System.out.print("숫자를 입력하세요 :");
    	   return inputNumberWithValidation();
		}
   }

   private void displayMenu(String[] menuList) {//
      System.out.println("=========================================");
      for(int i = 1; i < menuList.length; i++) {
         System.out.println(menuList[i]);
      }
      System.out.println(menuList[0]);
      System.out.println("=========================================");
   }

   

   public void displayBookInfo(BookStorage mBookStorage) {//
      for(int i = 0; i < mBookStorage.getNumBooks(); i++) {
         String bookInfo = mBookStorage.getBookInfo(i);
         System.out.println(bookInfo);
      }
   }

   public void showMessage(String message) {//
      System.out.println(message);
   }

   public void displayCart(Cart mCart) {//
      if(mCart.isEmpty()) {
         System.out.println(">>장바구니가 비어있습니다.");
         return;
      }
      
      for(int i = 0; i < mCart.getNumItems(); i++) {
         System.out.println(mCart.getItemInfo(i));
      }
   }

   public boolean askConfirm(String message, String yes) {//
      System.out.println(message);
      
      Scanner input = new Scanner(System.in);
      if(input.nextLine().equals(yes)) {
         return true;
      }else {
         return false;
      }
   }

   public int selectBookId(BookStorage mBookStorage) {//
      
      boolean result;
      int bookId;
      do {
         System.out.println("추가할 도서의 ID를 입력하세요 : ");
         bookId = inputNumberWithValidation();
         result = mBookStorage.isValidBook(bookId);
         if(!result) {
            System.out.println("잘못된 도서 ID입니다. ");
             }
      }while(!result);
      
      return bookId;
   }
   
   public int askForBookIdToRemove() {
	   
	   System.out.print(">> 삭제할 도서의 ID를 입력하세요: ");
	   return inputNumberWithValidation();
   }
   
   public int askForQuantityToRemove() {
	   
	   System.out.print(">> 삭제할 도서의 수량을 입력하세요: ");
	   return inputNumberWithValidation();
   }

public int selectBookId(Cart cart) {//
	
    boolean result;
    int bookId;
    do {
       System.out.println("도서의 ID 입력하세요 : ");
       bookId = inputNumberWithValidation();
       result = cart.isValidBook(bookId);
       if(!result) {
          System.out.println("잘못된 도서 ID입니다. ");
           }
    }while(!result);
    
    return bookId;
}

public int inputNumber(int min, int max) {//
	
	int number;
	do {
		System.out.println(">> 수량 입력 (" + min + " ~ " + max + "): ");
		number = inputNumberWithValidation();
		if(number < min || number > max) {
			System.out.println("잘못된 수량입니다.");
		}
	}while(number < min || number > max);
	return number;
}

public void inputCustomerInfo(Customer customer) {
	Scanner input = new Scanner(System.in);
	System.out.println("온라인 서점을 이용하시려면 이름과 전화번호를 입력하세요.");
	System.out.print(">> 이름 : ");
	customer.setName(input.nextLine());
	System.out.print(">> 전화번호 : ");
	customer.setPhone(input.nextLine());
}

public void displayDeliveryInfo(Customer mCustomer) {
	
	
	
}

public void inputDeliveryInfo(Customer mCustomer) {
	if(mCustomer.getAddress()!=null) {
		Scanner input = new Scanner(System.in);
		System.out.print(">> 주소 : ");
		mCustomer.setAddress(input.nextLine());
		System.out.print(">> email : ");
		mCustomer.setEmail(input.nextLine());
	}
}

public String inputString(String message) {
	Scanner input = new Scanner(System.in);
	System.out.print(message);
	return input.nextLine();
}
}