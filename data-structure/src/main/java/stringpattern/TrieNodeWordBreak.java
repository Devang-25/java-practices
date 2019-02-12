package stringpattern;


	

	// trie node
	 class TrieNode {
		private int ALPHABET_SIZE = 26;
		TrieNode children[];

		// isEndOfWord is true if the node represents
		// end of a word
		boolean isEndOfWord;

		TrieNode() {
			children = new TrieNode[ALPHABET_SIZE];
		}
		TrieNode getNode() {
			TrieNode pNode = new TrieNode();

			pNode.isEndOfWord = false;

			for (int i = 0; i < ALPHABET_SIZE; i++)
				pNode.children[i] = null;

			return pNode;
		}

		// If not present, inserts key into trie
		// If the key is prefix of trie node, just
		// marks leaf node
		void insert(TrieNode root, String key) 
		{ 
		    TrieNode pCrawl = root; 
		  
		    for (int i = 0; i < key.length(); i++) 
		    { 
		        int index = key.charAt(i) - 'a'; 
		        if (pCrawl.children[index]==null) 
		            pCrawl.children[index] = getNode(); 
		  
		        pCrawl = pCrawl.children[index]; 
		    } 
		  
		    // mark last node as leaf 
		    pCrawl.isEndOfWord = true; 
		}

		// Returns true if key presents in trie, else
		// false
		boolean search( TrieNode root, String key) 
		{ 
		     TrieNode pCrawl = root; 
		  
		    for (int i = 0; i < key.length(); i++) 
		    { 
		        int index = key.charAt(i) - 'a'; 
		        if (pCrawl.children[index]==null) 
		            return false; 
		  
		        pCrawl = pCrawl.children[index]; 
		    } 
		  
		    return (pCrawl != null && pCrawl.isEndOfWord); 
		}

		// returns true if string can be segmented into
		// space separated words, otherwise returns false
		boolean wordBreak(String str, TrieNode root) 
		{ 
		    int size = str.length(); 
		  
		    // Base case 
		    if (size == 0)  return true; 
		  
		    // Try all prefixes of lengths from 1 to size 
		    for (int i=1; i<=size; i++) 
		    { 
		        // The parameter for search is str.substr(0, i) 
		        // str.substr(0, i) which is prefix (of input 
		        // string) of length 'i'. We first check whether 
		        // current prefix is in dictionary. Then we 
		        // recursively check for remaining string 
		        // str.substr(i, size-i) which is suffix of 
		        // length size-i 
		        if (search(root, str.substring(0, i)) && 
		            wordBreak(str.substring(i, size), root)) 
		            return true; 
		    } 
		  
		    // If we have tried all prefixes and none 
		    // of them worked 
		    return false; 
		}

	
	}
	 
	 public class TrieNodeWordBreak{
			// Driver program to test above functions
			public static void main(String args[]) 
			{ 
			    String dictionary[] = {"mobile","samsung","sam", 
			                           "sung","man","mango", 
			                           "icecream","and","go","i", 
			                           "like","ice","cream"}; 
			    int n = dictionary.length; 
			    TrieNode tn= new TrieNode();
			     TrieNode root = tn.getNode(); 
			  
			    // Construct trie 
			    for (int i = 0; i < n; i++) {
			    	//System.out.println(dictionary[i]);
			        tn.insert(root, dictionary[i]); 
			  
			    }
			    System.out.println(tn.wordBreak("ilikesamsung", root)); 
			    System.out.println(tn.wordBreak("iiiiiiii", root));
			    System.out.println(tn.wordBreak("", root));
			    System.out.println(tn.wordBreak("ilikelikeimangoiii", root));
			    System.out.println(tn.wordBreak("samsunandmango", root));
			    System.out.println(tn.wordBreak("samsungandmangok", root));
			     
			}

	 }

