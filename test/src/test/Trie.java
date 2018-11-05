package test;

import java.util.ArrayList;
import java.util.Iterator;

//Java implementation of search and insert operations
//on Trie
public class Trie {

	// Alphabet size (# of symbols)
	static final int ALPHABET_SIZE = 26;

	// trie node
	static class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];

		// isEndOfWord is true if the node represents
		// end of a word
		boolean isEndOfWord;

		TrieNode() {
			isEndOfWord = false;
			for (int i = 0; i < ALPHABET_SIZE; i++)
				children[i] = null;
		}
	};

	static TrieNode root;

	// If not present, inserts key into trie
	// If the key is prefix of trie node,
	// just marks leaf node
	static void insert(String key) {
		int level;
		int length = key.length();
		int index;

		TrieNode pCrawl = root;

		for (level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';
			if (pCrawl.children[index] == null)
				pCrawl.children[index] = new TrieNode();

			// System.out.println(key.charAt(level)+" " + index);
			pCrawl = pCrawl.children[index];

		}
		// System.out.println(pCrawl +" " + pCrawl.isEndOfWord);
		// mark last node as leaf
		pCrawl.isEndOfWord = true;
	}

	// Returns true if key presents in trie, else false
	static boolean search(String key) {
		int level;
		int length = key.length();
		int index;
		TrieNode pCrawl = root;

		for (level = 0; level < length; level++) {
			index = key.charAt(level) - 'a';

			if (pCrawl.children[index] == null)
				return false;

			pCrawl = pCrawl.children[index];
		}

		return (pCrawl != null && pCrawl.isEndOfWord);
	}

	static ArrayList<String> searchWithPrefix(String key) {
		ArrayList<String> res = new ArrayList<String>();

		int level;
		int l = key.length();
		int index;
		TrieNode pCrawl = root;

		for (level = 0; level < l; level++) {
			index = key.charAt(level) - 'a';
			if (pCrawl.children[index] == null) {
				return null;
			}
			pCrawl = pCrawl.children[index];
		}
		if (pCrawl != null) {
			if (pCrawl.isEndOfWord) {
				res.add(key);
			}
			checkWords(pCrawl, key, res);
		}
		return res;
	}

	static void checkWords(TrieNode pCrawl, String key, ArrayList<String> res) {
		TrieNode curr;
		for (int i = 0; i < 26; i++) {
			curr = pCrawl;
			String temp=key;
			if (curr.children[i] != null) {
				curr = curr.children[i];
				temp += (char) ('a' + i);
				if (curr.isEndOfWord) {
					res.add(temp);
				}
				checkWords(curr, temp, res);
			}
		}
	}

	// Driver
	public static void main(String args[]) {
		// Input keys (use only 'a' through 'z' and lower case)
		String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their" };

		String output[] = { "Not present in trie", "Present in trie" };

		root = new TrieNode();

		// Construct trie
		int i;
		for (i = 0; i < keys.length; i++)
			insert(keys[i]);

		// Search for different keys
		// if(search("the") == true)
		// System.out.println("the --- " + output[1]);
		// else System.out.println("the --- " + output[0]);
		//
		// if(search("these") == true)
		// System.out.println("these --- " + output[1]);
		// else System.out.println("these --- " + output[0]);
		//
		// if(search("their") == true)
		// System.out.println("their --- " + output[1]);
		// else System.out.println("their --- " + output[0]);
		//
		// if(search("thaw") == true)
		// System.out.println("thaw --- " + output[1]);
		// else System.out.println("thaw --- " + output[0]);

		ArrayList<String> list = searchWithPrefix("th");
		System.out.println(list.size());
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}
}
