package com.edrop.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.edrop.service.SensitiveWordService;
 
/**
 * 敏感词过滤
 * @ClassName:  ACFind   
 * @Description:TODO
 * @author: zcx 
 * @date: 2020年5月11日 下午5:11:56     
 * @Copyright: 2020 com.edrop. All rights reserved.
 */
@Component
public class ACFind {
	private TrieNode root;
	@Resource
	private SensitiveWordService sensitiveWordServiceImpl;
//	public static void main(String[] args) {
// 
//		String[] keywords = new String[] { "王八", "王八蛋", "哈哈哈", "aabcdgakjvfk"};
//        ACFind ac = new ACFind(keywords);
//        System.out.println(ac.sensitiveFilter("kk王八蛋haha王八"));
//		// int index = find.find("kk王八蛋haha王八");
//		// System.out.println(index);
// 
//	}
	public TrieNode getRoot() {
		return root;
	}
	public void acFindInit() {
		List<String> keywords = sensitiveWordServiceImpl.getKeywords();
		root = buildTree(keywords);
		addFailNode(root);
//		System.out.println("haha");
	}
//	public ACFind(String[] keywords) {
//		root = buildTree(keywords);
//		// printTree(root);
//		addFailNode(root);
//	}
// 
	public ACFind() {
		super();
	}

	/**
	 * 查找是否包含目标字符串
	 */
	public String sensitiveFilter(String text) {
        int len = text.length();
		TrieNode node = root;
        StringBuffer ans = new StringBuffer(text);
        System.out.println(ans.toString());
		for (int index = 0; index < len; index++) {
			char c = text.charAt(index);
 
			while (node != null && node.getSonNode(c) == null) {
				node = node.getFailNode();
			}
 
			node = (node == null ? root : node.getSonNode(c));
 
			TrieNode temp = node;
 
			while (temp != null) {
				if (temp.isWordEnd()) {
                    // System.out.println("start:" + index + " length:" + temp.length);
                    for (int i = index; i > index - temp.length; --i) {
                        ans.setCharAt(i, '*');
                    }
				}
				temp = temp.getFailNode();
			}
        }
        // System.out.println(ans.toString());
		return ans.toString();
	}
 
	/**
	 * 初始化字典树
	 */
	private TrieNode buildTree(List<String> keywords) {
		final TrieNode root = new TrieNode(' ');
		for (String word : keywords) {
			TrieNode temp = root;
			for (char ch : word.toCharArray()) {
				if (temp.containsSonNode(ch)) {
					temp = temp.getSonNode(ch);
				} else {
                    TrieNode newNode = new TrieNode(ch);
                    newNode.setLength(temp.length + 1);
					temp.addSonNode(newNode);
					temp = newNode;
				}
			}
			temp.setWordEnd(true);
		}
		return root;
	}
 
	/**
	 * 添加节点
	 */
	public void addWordToTree(TrieNode rootNode, String word) {
		if (word == null || word.length() == 0) {
			return;
		}
		for (char c : word.toCharArray()) {
			if (rootNode.containsSonNode(c)) {
				rootNode = rootNode.getSonNode(c);
			} else {
                TrieNode newNode = new TrieNode(c);
                newNode.setLength(rootNode.length + 1);
				rootNode.addSonNode(newNode);
				rootNode = newNode;
			}
		}
		rootNode.setWordEnd(true);
	}
 
	public void printTree(TrieNode root) {
		Queue<TrieNode> queue = new LinkedList<>();
		queue.offer(root);
		TrieNode enterNode = new TrieNode('\n');
		queue.add(enterNode);
		while (!queue.isEmpty()) {
			TrieNode parent = queue.poll();
			System.out.print(parent.value + ";");
			if (parent == enterNode && queue.size() > 1) {
				queue.offer(enterNode);
				continue;
			}
			queue.addAll(parent.getSonsNode());
		}
 
	}
 
	/**
	 * BFS遍历树，给每一个节点建立FailNode
	 */
	private void addFailNode(final TrieNode root) {
		root.setFailNode(null);
		Queue<TrieNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TrieNode parent = queue.poll();
			TrieNode temp;
			for (TrieNode child : parent.getSonsNode()) {
				if (parent == root) {
					child.setFailNode(root);
				} else {
					temp = parent.getFailNode();
					while (temp != null) {
 
						TrieNode node = temp.getSonNode(child.getValue());
						if (node != null) {
							child.setFailNode(node);
							break;
						}
						temp = temp.getFailNode();
					}
					if (temp == null) {
						child.setFailNode(root);
					}
				}
				queue.add(child);
			}
		}
	}
 
	class TrieNode {
        // 失配指针
        private TrieNode failNode;
        private char value;
        // 当前节点是否是一个终点
        private boolean isWordEnd = false;
        // 当前节点的孩子节点
        private Map<Character, TrieNode> sons;
        // 如果当前节点是一个终止节点，length 表示匹配字符的长度
        private Integer length;
		public TrieNode(char value) {
            this.length = 0;
			this.value = value;
			sons = new HashMap<Character, TrieNode>();
        }
        public Integer getLength() {
            return this.length;
        }
        public void setLength(Integer length) {
            this.length = length;
        }
		// 添加子节点
		public void addSonNode(TrieNode node) {
			sons.put(node.value, node);
		}
		// 获取子节点中指定字符节点
		public TrieNode getSonNode(char ch) {
			return sons.get(ch);
		}
		// 判断子节点中是否存在该字符
		public boolean containsSonNode(char ch) {
			return sons.containsKey(ch);
		}
		// 获取字符
		public char getValue() {
			return value;
		}
		// 设置失败指针并且返回
		public void setFailNode(TrieNode failNode) {
			this.failNode = failNode;
		}
		public TrieNode getFailNode() {
			return failNode;
		}
		// 获取所有的孩子节点
		public Collection<TrieNode> getSonsNode() {
			return sons.values();
		}
		public boolean isWordEnd() {
			return isWordEnd;
		}
		public void setWordEnd(boolean isWordEnd) {
			this.isWordEnd = isWordEnd;
		}
	}
}