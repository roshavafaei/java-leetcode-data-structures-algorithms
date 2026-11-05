class WordDictionary {

    private class Node {
        private char val;
        private Map<Character, Node> children = new HashMap<>();
        private boolean isEndOfWord;

        public Node(char val) {
            this.val = val;
        }
        
        public boolean hasChild(char ch) {return children.containsKey(ch);}
        public void addChild(char ch) {children.put(ch, new Node(ch));}
        public Node getChild(char ch) {return children.get(ch);}
        public Node[] getChildren() {return children.values().toArray(new Node[0]);}
    }
    public WordDictionary() {

    }
    
    private Node root = new Node(' ');

    public void addWord(String word) {
        Node current = root;
        
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                current.addChild(ch);
            
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        return search(root, word, 0);
    }
    
    private boolean search(Node current, String word, int index) {
        if (index == word.length())
            return current.isEndOfWord;
        
        char ch = word.charAt(index);
        if (ch != '.') {
            if (!current.hasChild(ch))
                return false;
            
            return search(current.getChild(ch), word, index + 1);
        }
        
        for (Node child : current.getChildren()) {
            if (search(child, word, index + 1))
                return true;
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
