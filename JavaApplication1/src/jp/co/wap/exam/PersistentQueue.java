package jp.co.wap.exam;

import java.util.NoSuchElementException;

public class PersistentQueue<E> {

		private class PersistentNode<E> {

				final E e;
				final PersistentNode parent;

				public PersistentNode(E e, PersistentNode parent) {
						this.e = e;
						this.parent = parent;
				}
		}
		final PersistentNode<E> tail;
		final PersistentNode<E> head;
		final int size;
		PersistentNode<E> neck;

		public PersistentQueue() {
				this.head = null;
				this.neck = null;
				this.tail = null;
				this.size = 0;
		}

		private PersistentQueue(PersistentNode<E> node) {
				this.head = node;
				this.neck = null;
				this.tail = node;
				this.size = 1;
		}

		private PersistentQueue(PersistentNode<E> head, PersistentNode<E> neck, PersistentNode<E> tail, int size) {
				this.head = head;
				this.neck = neck;
				this.tail = tail;
				this.size = size;
		}

		public PersistentQueue<E> enqueue(E e) {
				if (e == null) {
						throw new IllegalArgumentException();
				}
				PersistentNode<E> child_end = new PersistentNode<E>(e, this.tail);
				if (this.size == 0) {
						return new PersistentQueue(child_end);
				} else {
						return new PersistentQueue(this.head, this.neck, child_end, this.size + 1);
				}
		}

		public PersistentQueue<E> dequeue() {
				if (this.size == 0) {
						throw new NoSuchElementException();
				}
				if (this.size == 1) {
						return new PersistentQueue();
				}
				if (this.size == 2) {
						return new PersistentQueue(this.tail, null, this.tail, this.size - 1);
				}
				if (this.neck == null) {
						PersistentNode<E> node = this.tail;
						for (int i = 0; i < size - 3; i++) {
								node = node.parent;
						}
						this.neck = node.parent;
						return new PersistentQueue(this.neck, node, this.tail, this.size - 1);
				}
				return new PersistentQueue(this.neck, null, this.tail, this.size - 1);
		}

		public E peek() {
				if (this.size == 0) {
						throw new NoSuchElementException();
				}
				return this.head.e;
		}

		public int size() {
				return this.size;
		}
}
