package com.juliovedovatto.animalsgame;

import javax.swing.JOptionPane;

/**
 * Core class of the Animals Game.
 * @author Julio Vedovatto (juliovedovatto@gmail.com)
 */
public class Game {
    
    private Node node;

    /**
     * Public constructor
     * @return Game
     */
    public static Game newGame() {
        return new Game(buildFirstNode());
    }
    
    /**
     * This method asks the first question of the game.
     */
    public void firstQuestion() {
        int input = JOptionPane.showOptionDialog(
            null, 
            "Pense em um Animal...", 
            "Jogo dos Animais", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, null, null
        );

        if(input == JOptionPane.OK_OPTION)
            this.play();
    }
    
    /**
     * This method initialize the game.
     */
    public void play() {
        this.play(this.node);
    }
    
//  PRIVATE METHODS -------------------------------------------------------------------------------------------------------------
    
    /**
     * Constructor
     */
    private Game() {}

    /**
     * Constructor with a node.
     * @param node 
     */
    private Game(Node node) {
        this.node = node;
    }
    
    /**
     * This method starts the game.
     * @param node 
     */
    private void play(Node node) {
        if (node.hasNextNode())
            this.play(this.question(node) ? node.getRightNode() : node.getLeftNode());
        else
            this.question(node);
    }
    
    /**
     * This method make a question for the user.
     * @param node
     * @return boolean
     */
    private boolean question(Node node) {
        int awswer = JOptionPane.showConfirmDialog(null, node.getQuestion(), "Jogo dos Animais", JOptionPane.YES_NO_OPTION);

        if (awswer == JOptionPane.YES_OPTION) {
            if (!node.hasNextNode())
                dingdingding();
            
            return true;
        } else if (!node.hasNextNode())
            this.repositionNode(node);
        
        return false;
    }
    
    /**
     * End of game, the house wins (the house always wins).
     */
    private void dingdingding() {
        JOptionPane.showMessageDialog(null, "Acertei!\nViu como sou bom nisso? ;-)");
    }
    
    /**
     * This method prompts the user to enter their response.
     * @param actual_node
     * @return Node
     */
    private Node ask(Node actual_node) {
        String animal = "", action = "", previous_animal = actual_node.getAnimal();
                
        animal = JOptionPane.showInputDialog(null, "Qual foi o animal que você pensou? ");
        
        animal = animal != null ? animal.trim() : null;
        if (animal != null && animal.equals("")) {
            JOptionPane.showOptionDialog(
                null, 
                "Você deve informar um animal para continuar a brincadeira.", 
                "Erro!", 
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null, null, null
            );
            
            return this.ask(actual_node);
        } else if (animal != null) {
            action = JOptionPane.showInputDialog(null, String.format("Um(a) %s ___________ mas um(a) %s não.", animal, previous_animal));
        
            action = action != null ? action.trim() : null;
            if (action != null && action.equals("")) {
                JOptionPane.showOptionDialog(
                    null, 
                    "Você deve informar o que o animal faz para continuar a brincadeira.", 
                    "Erro!", 
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null, null, null
                );

                return this.ask(actual_node);
            }
        }
            
        return animal != null && action != null ? this.buildNewNode(actual_node, animal, action) : new Node("");
    }
    
    /**
     * This method reposition the node (make the actual node a parent node),
     * asks user for awswer of their animal) and make the first question of game again.
     * @param actual_node 
     */
    private void repositionNode(Node actual_node) {
        Node node = this.ask(actual_node);
        if (!node.getQuestion().equals("")) { // check for dummy node
            Node parent = actual_node.getParent();

            actual_node.setParent(node);

            if (parent.getLeftNode() == actual_node)
                parent.setLeftNode(node);
            else
                parent.setRightNode(node);

            this.firstQuestion();
        }
    }
    
    /**
     * This method builds a new node of the chain of nodes.
     * @param actual_node
     * @param animal
     * @param action
     * @return Node
     */
    private Node buildNewNode(Node actual_node, String animal, String action) {
        Node node = new Node(String.format("O animal que você pensou %s?", action));
        Node right_node = new Node(String.format("O animal que você pensou é um(a) %s?", animal), animal);
        
        node.setParent(actual_node.getParent()).setRightNode(right_node).setLeftNode(actual_node);
        right_node.setParent(node);
        
        return node;
    }
    
    /**
     * This method initialize the node tree.
     * @return Node
     */
    private static Node buildFirstNode() {
        Node main_node = new Node("O animal que você pensou vive na Água?");
        Node right_node = new Node("O animal que você pensou é Tubarão?", "Tubarão");
        Node left_node = new Node("O animal que você pensou é Macaco?", "Macaco");
        
        main_node.setRightNode(right_node).setLeftNode(left_node);
        right_node.setParent(main_node);
        left_node.setParent(main_node);
        
        return main_node;
    }
    
//  /PRIVATE METHODS ------------------------------------------------------------------------------------------------------------
}
