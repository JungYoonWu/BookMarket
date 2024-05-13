package bookMarket.model;

import java.util.ArrayList;

public class Cart {

   private ArrayList<CartItem> itemList = new ArrayList<>();

   
   public ArrayList<CartItem> getItemList(){
	   return itemList;
   }
   
   public boolean isEmpty() {
      return itemList.isEmpty();
   }

   public int getNumItems() {
      return itemList.size();
   }
   
   public String getItemInfo(int index) {
	      return itemList.get(index).toString();
	   }
 
   
   public void addItem(Book book) {
	      CartItem item = getCartItem(book);
	      if(item == null) {
	         itemList.add(new CartItem(book));
	      }else {
	         item.addQuantity(1);
	      }
	   
	   }
  
   
   private CartItem getCartItem(Book book) {
	   for(CartItem item : itemList) {
		   if(item.getBook() == book) {
			   return item;
		   }
	   }
	      return null;
   }
   
   
   public void resetCart() {
	      itemList.clear();
	   }
   
   
   //책을 장바구니에서 제거하는 메서드
   public void removeItem(int bookId, int quantityToRemove) {
	   //입력받은 책 ID에 해당하는 항목을 찾아서 제거
	   for(int i = 0; i < itemList.size(); i++) {
		   CartItem item = itemList.get(i);
		   if(item.getBook().getBookId() == bookId) {
			   int currentQuantity = item.getQuantity();
			   //입력받은 수량만큼 해당 책을 제거
			   if(currentQuantity <= quantityToRemove) {
				   itemList.remove(i);
				   return;
			   }else {
				   item.setQuantity(currentQuantity - quantityToRemove);
				   return;
			   }
		   }
	   }
	   
   }


public boolean isValidBook(int bookId) {
	for(CartItem item : itemList) {
		if(item.getBook().getBookId() == bookId) {
			return true;
		}
	}
	return false;
}


public void deleteItem(int bookId) {
	CartItem item = getCartItem(bookId);
	itemList.remove(item);
}

private CartItem getCartItem(int bookId) {
	for(CartItem item : itemList) {
		if(item.getBook().getBookId() == bookId) {
			return item;
		}
	}
	return null;
}

public void updateQuantity(int bookId, int quantity) {
	if(quantity == 0) {
		deleteItem(bookId);
	}else {
		CartItem item = getCartItem(bookId);
		item.setQuantity(quantity);
	}
}

}