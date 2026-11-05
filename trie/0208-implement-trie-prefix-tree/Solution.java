class Trie {

    private class Node {
        private int val;
        private Map<Character, Node> children = new HashMap<>();
        private boolean isEndOfWord;

        public Node(int val) {
            this.val = val;
        }

        public boolean hasChild(char ch) {return children.containsKey(ch);}
        public void addChild(char ch) {children.put(ch, new Node(ch));}
        public Node getChild(char ch) {return children.get(ch);}
    }

    private Node root;

    public Trie() {
        root  = new Node(' ');
    }

    public void insert(String word) {
        Node current = root;

        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        if (word == null)
            return false;

        Node current = findLastNodeOf(word);
        return current != null && current.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null)
            return false;
        
        Node current = findLastNodeOf(prefix);

        return current != null;
    }

    private Node findLastNodeOf(String str) {
        Node current = root;
        for (char ch : str.toCharArray()) {
            if (!current.hasChild(ch))
                return null;
            current = current.getChild(ch);
        }
        return current;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
