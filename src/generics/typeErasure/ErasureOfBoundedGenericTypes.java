package generics.typeErasure;

public class ErasureOfBoundedGenericTypes {

}

/*
 * Erasure of unbounded generic types
 */
class Node<T> {
	private T data;
	private Node<T> next;
}

// After compilation
// class Node<Object> {
//		private Object data;
//		private Node<Object> data;
//}



/*
 * Erasure of bounded generic types
 */
class Nd<T extends Comparable<T>> {
	private T data;
	private Node<T> next;
}

// After compilation
// class Nd<Comparable> {
// 		private Comparable data;
//		private Node<Comparable> data;
// }