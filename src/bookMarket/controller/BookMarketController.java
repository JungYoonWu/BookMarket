package bookMarket.controller;


import bookMarket.model.Admin;
import bookMarket.model.BookStorage;
import bookMarket.model.Cart;
import bookMarket.model.Customer;
import bookMarket.view.ConsoleView;

public class BookMarketController {
   
   ConsoleView view;
   BookStorage mBookStorage;
   Cart mCart;
   Customer mCustomer;
   Admin mAdmin;
   
   String[] menuList = {
      "0. 종료",
      "1. 도서 정보 보기", 
      "2. 장바구니 보기", 
      "3. 장바구니 에 도서추가", 
      "4. 장바구니 도서 삭제", 
      "5. 장바구니 도서 수량 변경", 
      "6. 장바구니 비우기", 
      "7. 주문",
      "8. 관리자 모드"
   };
   String[] adminMenuList = {
	  "0. 종료",
	  "1. 도서 정보 추가",
	  "2. 도서 정보 삭제",
	  "3. 도서 정보 파일 저장"
   };

   public BookMarketController(BookStorage bookStorage, Cart cart, ConsoleView view) {
      this.view = view;
      this.mBookStorage = bookStorage;
      this.mCart = cart;
      mCustomer = new Customer();
      mAdmin = new Admin();
   }

   public void start() {
      view.displayWelcome();
      view.inputCustomerInfo(mCustomer);
      
      //menuNum을 받고 메뉴를 확인하니까 dowhile
      int menu;
      do {
         menu = view.selectMenu(menuList);
         
         switch(menu) {
         case 1 -> viewBookInfo();
         case 2 -> viewCart();
         case 3 -> addBook2Cart();
         case 4 -> deleteBookInCart();
         case 5 -> updateBookInCart();
         case 6 -> resetCart();
         case 7 -> order();
         case 8 -> adminMode();
         case 0 -> end();
         
         }
      }while(menu != 0);
   }

   
private void adminMode() {
	
	if(!authenticateAdmin()) {
		view.showMessage("관리자 모드로 전환할 수 없습니다.");
		return;
	}
	int menu;
	do {
        menu = view.selectMenu(menuList);
        
        switch(menu) {
        case 1 -> addBook2Storage();
        case 2 -> deleteBookInStorage();
        case 3 -> saveBookList2File();
        case 0 -> adminEnd();
        
        }
     }while(menu != 0);
	
	//관리자 모드 진입 -> 도서 추가, 삭제, 도서 정보 파일 저장
		//관리자모드일때의 메뉴 출력
		//메뉴 선택하면 해당 기능 실행
}

private void adminEnd() {
	view.showMessage("관리자 모드가 종료 되었습니다.\n");
}

private void saveBookList2File() {

}

private void deleteBookInStorage() {
	
}

private void addBook2Storage() {
}

private boolean authenticateAdmin() {
	//관리자 인증 (id, pwd 확인)
	view.showMessage("관리자 모드 진입을 위한 관리장 인증");
	String id = view.inputString("관리자 ID : ");
	String password = view.inputString("관리자 password : ");
	return mAdmin.login(id, password);
}

private void order() {
	//배송정보 추가
	addDeliveryInfo();
	//구매정보 보기 : 장바구니 내역, 배송정보
	displayOrderInfo();
	//진짜 주문할거니?
	if(view.askConfirm("주문하려면 yes를 입력하세요", "yes")) {
		//주문처리 ->장바구니 초기화
		mCart.resetCart();
	}
}

private void displayOrderInfo() {
	view.displayCart(mCart);
	view.displayDeliveryInfo(mCustomer);
}

private void addDeliveryInfo() {
	view.inputDeliveryInfo(mCustomer);
	
}

private void end() {
	view.showMessage(">> YoonWu book Market을 종료합니다.");
}

private void updateBookInCart() {
	// 장바구니 보여주기
	view.displayCart(mCart);
	if(!mCart.isEmpty()) {
		// 도서 ID 입력 받기
		int bookId = view.selectBookId(mCart);
		// 수량 입력받기
		int quantity = view.inputNumber(0, mBookStorage.getMaxQuantity());
		// 도서 ID에 해당하는 cartItem 가져와서
		// cartItem quantity set 수량
		mCart.updateQuantity(bookId, quantity);
	}
	
}

private void deleteBookInCart() {
	//장바구니 보여주기
	view.displayCart(mCart);
	if(!mCart.isEmpty()) {
		//도서 ID입력
		int bookId = view.selectBookId(mCart);
		if(view.askConfirm(">> 해당 도서를 삭제하려면 yes를 입력하세요 : ", "yes")) {
			mCart.deleteItem(bookId);
			view.showMessage("해당도서를 삭제했습니다.");
		}
	}
}

private void resetCart() {
      view.displayCart(mCart);
      if(!mCart.isEmpty()) {
         if(view.askConfirm(">> 장바구니를 비우려면 yes를 입력하세요. : ","yes")) {
        	 mCart.resetCart();
             view.showMessage(">> 장바구니를 비웠습니다.");
         }else {
        	 view.showMessage(">> 장바구니 비우기가 취소되었습니다.");
         }
        
      }
   }
   
/*   private void removeBookFromCart() {
	   view.displayCart(mCart);
	   int bookId = view.askForBookIdToRemove();
	   int quantityToRemove = view.askForQuantityToRemove();
	   if(view.askConfirm(">> 선택한 도서를 삭제하려면 yes를 입력하세요. : ", "yes")) {
		   mCart.removeItem(bookId, quantityToRemove);
		   if(mBookStorage.isValidBook(bookId)) {
			   view.showMessage(">>도서를 삭제했습니다.");
			   view.displayCart(mCart);   
		   }else {
			   view.showMessage(">>일치하는 도서가 없습니다.");
		   }
	   }else {
		   view.showMessage(">> 도서삭제가 취소되었습니다.");
	   }
	   
   }
*/
   private void addBook2Cart() {
      view.displayBookInfo(mBookStorage);
      int bookId = view.selectBookId(mBookStorage);
      if(mBookStorage.isValidBook(bookId)) {
          mCart.addItem(mBookStorage.getBookById(bookId));
          view.showMessage(">> 장바구니에 도서를 추가하였습니다. ");  
      }else {
    	 view.showMessage(">> 일치하는 도서가 없습니다. "); 
    	 
    	 
    	 
      }
      
   }

   private void viewCart() {
      view.displayCart(mCart);
   }

   private void viewBookInfo() {
      view.displayBookInfo(mBookStorage);
   }

}