package gui;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import main.ATM_Main;
import bank.Account;
import bank.Transaction;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableView;

public class AccountHistoryPane {

	private static ObservableList<Transaction> transactions;
	private static TableView<Transaction> transactionTable;
	
	public static Node getDesign(){
		Node root = null;
		try{
			root = FXMLLoader.load(AccountHistoryPane.class.getResource("designFiles/AccountHistoryGUI.fxml"));
		} catch (Exception e){
			e.printStackTrace();
		}
		return root;
	}
	
	public static void showTransactions(Account a){
		transactions = new ObservableList<Transaction>(){

			@Override
			public boolean add(Transaction e) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void add(int index, Transaction element) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean addAll(Collection<? extends Transaction> c) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean addAll(int index, Collection<? extends Transaction> c) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean contains(Object o) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Transaction get(int index) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int indexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Iterator<Transaction> iterator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int lastIndexOf(Object o) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public ListIterator<Transaction> listIterator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ListIterator<Transaction> listIterator(int index) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean remove(Object o) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Transaction remove(int index) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Transaction set(int index, Transaction element) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public List<Transaction> subList(int fromIndex, int toIndex) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> T[] toArray(T[] a) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void addListener(InvalidationListener listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeListener(InvalidationListener listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean addAll(Transaction... elements) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void addListener(
					ListChangeListener<? super Transaction> listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void remove(int from, int to) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean removeAll(Transaction... elements) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void removeListener(
					ListChangeListener<? super Transaction> listener) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean retainAll(Transaction... elements) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean setAll(Transaction... elements) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean setAll(Collection<? extends Transaction> col) {
				// TODO Auto-generated method stub
				return false;
			}};
		for(Transaction t: a.getTransactions()){
			transactions.add(t);
		}
		
		try{
			transactionTable = (TableView<Transaction>) RootGUI.getMainDisplay().getChildren().get(0);
			System.out.println(a);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
