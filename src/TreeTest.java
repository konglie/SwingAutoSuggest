/*
   1. Only write your code in the methods that have the comment 'write your code here' or in the section 'write optional helper functions here'
   2. Do not modify anything else
   3. If your code cannot compile or fails the test cases in 'main()', you will not receive a response from us
*/

/*
	konglie@kurungkurawal.com
	http://kurungkurawal.com
 */

import java.util.*;

public class TreeTest {
	public static void main(String... args) {

		/*
		   Consider the following tree:

		         1
		      /  |  \
		     2   4   6
		   / |  / \  |  \  
		  3  9 5  7  11 12
		      / \      / | \
		     13 16    14 23 17
		       / \
		      24 32


		  Assuming the numbers are the ids of each node, the tree can be written down as follows:

		  1(2,4,6) 2(3,9) 4(5,7) 6(11,12) 5(13,16) 12(14,23,17) 16(24,32)

		  In the example above, for the group 1(2,4,6), node 2, 4 and 6 are the child nodes of
		  node 1. Note that extra whitespaces should be accepted. Assume ids are positive integers only.
		*/

		Tree first = new Tree("1(2,4, 6) 2(3, 9) 4(5,7)  6(11,12 ) 5(13,16)   12(14, 23, 17) 16( 24,32 )");
		assertTrue(first.getDepthOfNodeWithId(1) == 1);
		assertTrue(first.getDepthOfNodeWithId(4) == 2);
		assertTrue(first.getDepthOfNodeWithId(5) == 3);
		assertTrue(first.getDepthOfNodeWithId(12) == 3);
		assertTrue(first.getDepthOfNodeWithId(16) == 4);
		assertTrue(first.getDepthOfNodeWithId(23) == 4);
		assertTrue(first.getDepthOfNodeWithId(32) == 5);

		/*
			  2
		   / | | \ 
		  5  4 3  1
		  |     \
		  7      9
		 / \   /  \
		12 10 11  14
		    |
		    13
		   / | \
		 16  8  15
		*/

		Tree second = new Tree(" 2(5, 4, 3,1)  5(7)   3(9) 7(12, 10)   9(11, 14)  10(13) 13(16,8,15)");
		assertTrue(second.getDepthOfNodeWithId(2) == 1);
		assertTrue(second.getDepthOfNodeWithId(5) == 2);
		assertTrue(second.getDepthOfNodeWithId(3) == 2);
		assertTrue(second.getDepthOfNodeWithId(12) == 4);
		assertTrue(second.getDepthOfNodeWithId(11) == 4);
		assertTrue(second.getDepthOfNodeWithId(13) == 5);
		assertTrue(second.getDepthOfNodeWithId(8) == 6);

		// remove this,
		// this violates the rules
		// this is custom code
		System.out.println("Everything passed.");
	}

	private static void assertTrue(boolean v) {
		if (!v) {
			Thread.dumpStack();
			System.exit(0);
		}
	}
}

class Tree {
	private Node root;

	public Tree(String treeData) {
		// write your code here
		parseData(treeData);
	}

	public int getDepthOfNodeWithId(int id) {
		// write your code here
		// if a node is not found, return a negative value


		// ****************CODE*************************************
		int depth = traverseToFindDepth(id, root, 1);
//		System.out.println("x " + id + ", d: " + depth);
		print("\tid: " + id + " d: " + depth);
		return depth;
		// **************END CODE***********************************
	}

	// write optional helper functions here

	// ****************CODE*************************************
	int traverseToFindDepth(int id, Node node, int depth){
		print("find " + id + " from " + node.getId());
		if(node.getId() == id){
			return depth;
		}

		int d;
		for(Node child : node.getChildren()){
			print("\td is " + depth + " n " + child.getId());
			d = traverseToFindDepth(id, child, depth + 1);
			if(d > 0){
				return d;
			}
		}
		return -1;
	}

	void parseData(String treeData){
		print(treeData);
		// Regex-wise operation sounds more elegant,
		// something like ([0-9]+\([0-9,\s]+\)), not tested.

		// but let do it the simpler, traditional way
		// split by ) to separate 1 level to another
		// 1(2,4,6) 2(3,9)
		String[] datas = treeData.trim().split("\\)");
		Node lastRoot = null;
		for(String data : datas){
			// split by ( to separate ID and its member
			String[] nodeData = data.trim().split("\\(");

			// of course, parsing integer can sometime fails
			// assume it is always int-parsable
			int nodeID = Integer.parseInt(nodeData[0].trim());

			print(nodeData[0] + " => " + nodeData[1]);

			// we construct the tree, top to bottom
			// keep the reference to current "top"
			Node currentRoot;

			// starting point
			// the very-top one, the root itself
			if(root == null){
				currentRoot = new Node(nodeID);
				root = currentRoot;
				lastRoot = currentRoot;
			} else {
				// find parent for current ID
				currentRoot = findParent(nodeID, lastRoot);
			}

			for(String childID : nodeData[1].split(",")){
				print("\t\tid: " + childID + " parent: " + currentRoot.getId());

				// create the node
				Node node = new Node(Integer.parseInt(childID.trim()), currentRoot);

				// attach it to the parent
				currentRoot.appendChild(node);
			}
		}
	}

	boolean debug = false;
	void print(Object o){
		if(!debug){
			return;
		}
		System.out.println(o);
	}

	Node findParent(int id, Node root){
		// this would be much easier
		// if we can ( or allowed to) access property parent from Node

		print("\tfind: " + id + " from " + root.getId());

		if(root != null){
			// this happens when the root doesn't have any parent
			// e.g the root itself
			if(root.getId() == id){
				return root;
			}

			for(Node child : root.getChildren()){
				if(hasChild(id, child)){
					print("\t\t" + child.getId() + " parent of " + id);

					// the ID is among the children of this child
					// retrieve that node, because this node is designed to be the "next" root
					return getChild(id, child);
				}  else {

					// nothing found on this level
					// recurse to find one
					Node n = findParent(id, child);
					if(n != null){
						print("\t\t" + n.getId() + " xparent of " + id);
						return n;
					}
				}
			}
		}

		return null;
	}

	Node getChild(int id, Node root){
		for(Node n : root.getChildren()){
			if(n.getId() == id){
				return n;
			}
		}

		return null;
	}

	boolean hasChild(int id, Node root){
		for(Node n : root.getChildren()){
			if(n.getId() == id){
				return true;
			}
		}

		return false;
	}
	// **************END CODE***********************************
}

class Node {
	private Node parent;
	private List<Node> children;
	private int id;

	public Node(int id) {
		this.id = id;
		this.children = new ArrayList<Node>();
	}

	public Node(int id, Node parent) {
		this(id);
		this.parent = parent;
	}

	public void appendChild(Node child) {
		children.add(child);
	}

	public int getId() {
		return id;
	}

	public List<Node> getChildren() {
		return Collections.unmodifiableList(children);
	}
}
