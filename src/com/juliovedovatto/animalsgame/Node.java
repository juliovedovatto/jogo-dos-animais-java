package com.juliovedovatto.animalsgame;

/**
 * Node tree class. Consists in a main node, a left node and a right node. 
 * it' like a chain of nodes. Where every answer leads to a new node.
 * @author Julio Vedovatto (juliovedovatto@gmail.com)
 */
public class Node {
    
    private Node parent_node, right_node, left_node;
    
    private String question, animal;
    
    /**
     * Class constructor
     * @param question 
     */
    public Node(String question) {
        this.question = question;
    }
    
    public Node(String question, String animal) {
        this.question = question;
        this.animal = animal;
    }
    
    @Override
    public String toString() {
        return this.question;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        else if (object instanceof Node) {
            Node o = (Node) object; // clone object
            
            // check if the current node question is equals of the cloned object.
            return (this.question == null ? o.question == null : this.question.equals(o.question));
        }
        
        return false;
    }
    
    /**
     * This method check if the nodde tree as a next node (either left or right)
     * @return boolean
     */
    public boolean hasNextNode() {
        return this.right_node != null || this.left_node != null;
    }
    
//  GETTERS AND SETTERS ---------------------------------------------------------------------------------------------------------    
    
    public String getQuestion() {
        return this.question;
    }
    
    public String getAnimal() {
        return this.animal;
    }
    
    public Node getParent() {
        return parent_node;
    }

    public Node getRightNode() {
        return right_node;
    }
    
    public Node getLeftNode() {
        return left_node;
    }
    
    public Node setQuestion(String question) {
        this.question = question;
        
        return this;
    }
    
    public Node setAnimal(String animal) {
        this.animal = animal;
        
        return this;
    }
    
    public Node setParent(Node parent_node) {
        this.parent_node = parent_node;
        
        return this;
    }

    public Node setRightNode(Node right_node) {
        this.right_node = right_node;
        
        return this;
    }
    
    public Node setLeftNode(Node left_node) {
        this.left_node = left_node;
        
        return this;
    }
    
//  /GETTERS AND SETTERS --------------------------------------------------------------------------------------------------------
    
}
