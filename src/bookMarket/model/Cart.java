package bookMarket.model;

import java.util.ArrayList;

public class Cart {
/*주석이 ver0.5 itemList를 ArrayList로 바꿈 이전버전은 우선 주석으로 남김*/
   private ArrayList<CartItem> itemList = new ArrayList<>();
//   private CartItem[] itemList = new CartItem[64];
//   private int numItems = 0;
   
   public ArrayList<CartItem> getItemList(){
	   return itemList;
   }
   /*public CartItem[] getItemList() {
      return itemList;
   }*/
   
   
   /*
   public void setItemList(CartItem[] itemList) {
      this.itemList = itemList;
   }*/

   public boolean isEmpty() {
      return itemList.isEmpty();
   }

   public int getNumItems() {
      return itemList.size();
   }
   
   public String getItemInfo(int index) {
	      return itemList.get(index).toString();
	   }
   /*
   public String getItemInfo(int index) {
      return itemList[index].toString();
   }*/
   
   public void addItem(Book book) {
	      CartItem item = getCartItem(book);
	      if(item == null) {
	         itemList.add(new CartItem(book));
	      }else {
	         item.addQuantity(1);
	      }
	   
	   }
   /*
   public void addItem(Book book) {
      CartItem item = getCartItem(book);
      if(item == null) {
         itemList[numItems++] = new CartItem(book);
      }else {
         item.addQuantity(1);
      }
   
   }*/
   
   private CartItem getCartItem(Book book) {
	   for(CartItem item : itemList) {
		   if(item.getBook() == book) {
			   return item;
		   }
	   }
	      return null;
   }
   /*
   private CartItem getCartItem(Book book) {
      for(int i = 0; i < numItems; i++) {
         if(itemList[i].getBook() == book) {
            return itemList[i];
         }
      }
      return null;
   }*/
   
   public void resetCart() {
	      itemList.clear();
	   }
   /*
   public void resetCart() {
      numItems = 0;
      this.itemList = new CartItem[64];
   }*/
   
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

}