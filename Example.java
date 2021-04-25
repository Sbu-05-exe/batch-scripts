package cs2;
/** Simple generic tree class.
  * This is an immutable, "open box" data structure,
  * with in-order, pre-order and post-order iterators.
  * @author George Wells
  * @version 3.0 (25 February 2010)
  */

public class Example 
{

public class Tree<T>
  { /** The data stored in this node.
      */
    private T data;
    /** Pointer to left subtree.
      */
    private Tree<T> lt;
    /** Pointer to right subtree.
      */
    private Tree<T> rt;

    /** Creates new node with left and right subtrees.
      * @param value The value to be stored in this node.
      * @param left The left subtree to be added to this node.
      * @param right The right subtree to be added to this node.
      */
    public Tree (T value, Tree<T> left, Tree<T> right) // check it out
      { lt = left;
        rt = right;
        data = value;
      } // Constructor

    /** Creates new node without subtrees.
      * @param value The value to be stored in this node.
      */
    public Tree (T value) // check this out
      { this(value, null, null);
      } // Constructor

    /** Return the left subtree of a tree.
      * @return Left subtree of this node.
      */
    public Tree<T> left () // check this out too
      { return lt; }

    /** Return the right subtree of a tree.
      * @return Right subtree of this node.
      */
    public Tree<T> right ()
      { return rt; }

    /** Add a left subtree to a node.
      * <I>Precondition:</I> There is no left subtree.
      * @param left The new left tree to be added.
      * @throws UnsupportedOperationException if there is a pre-existing left subtree.
      */
    public void addLeft (Tree<T> left)
      { if (lt != null)
          throw new UnsupportedOperationException("subtree already present");
        lt = left;
      } // addLeft

    /** Add a right subtree to a node.
      * <I>Precondition:</I> There is no right subtree.
      * @param right The new right tree to be added.
      * @throws UnsupportedOperationException if there is a pre-existing right subtree.
      */
    public void addRight (Tree<T> right) // Add a right subtree to a node
      { if (rt != null)
          throw new UnsupportedOperationException("subtree already present");
        rt = right;
      } // addRight

    /** Access the data value in a node of a tree.
      * @return The data contained in this tree node.
      */
    public T getData ()
      { return data; }

  } // class Tree

}// example