package com.edrop.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.edrop.service.ArticleService;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:springmvc.xml", "classpath:applicationcontext.xml"})
public class HuffmanCodeTest {
	@Resource
	private ArticleService articleServiceImpl;
	
	@Test
	public void addArticleTest() {
		Integer id = articleServiceImpl.addArticleTest("# 堆排序的应用\r\n" + 
				"\r\n" + 
				"## 1. Top K 问题\r\n" + 
				"\r\n" + 
				"[题目链接：](https://www.acwing.com/problem/content/description/840/)\r\n" + 
				"\r\n" + 
				"题解：构造小顶堆，依次输出 k 次堆顶元素即可\r\n" + 
				"\r\n" + 
				"**c++ 代码**\r\n" + 
				"\r\n" + 
				"~~~c++\r\n" + 
				"#include<bits/stdc++.h>\r\n" + 
				"using namespace std;\r\n" + 
				"\r\n" + 
				"const int N = 1e5 + 5;\r\n" + 
				"int arr[N];\r\n" + 
				"\r\n" + 
				"//对编号为 x 的节点进行调整\r\n" + 
				"void heap_adjust (int x, int len) {\r\n" + 
				"    int child;\r\n" + 
				"    for (int i = x; i < len; i = child) {\r\n" + 
				"        child = i;\r\n" + 
				"        int left = 2 * i + 1;    //获取左孩子 \r\n" + 
				"        int right = 2 * i + 2;\r\n" + 
				"        if (left < len && arr[left] < arr[child]) {\r\n" + 
				"            child = left;\r\n" + 
				"        }      \r\n" + 
				"        if (right < len && arr[right] < arr[child]) {\r\n" + 
				"            child = right;\r\n" + 
				"        }      \r\n" + 
				"        if (child == i) {\r\n" + 
				"            break;\r\n" + 
				"        } else {\r\n" + 
				"            swap(arr[child], arr[i]);\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"int main () {\r\n" + 
				"    int n, m;\r\n" + 
				"    cin >> n >> m;\r\n" + 
				"    for (int i = 0; i < n; ++i) {\r\n" + 
				"        scanf(\"%d\", &arr[i]);\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    //堆化\r\n" + 
				"    for (int i = n / 2; i >= 0; --i) {\r\n" + 
				"        heap_adjust(i, n);\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    int end = n - 1;\r\n" + 
				"    //做 m 次调整即可\r\n" + 
				"    while (m--) {\r\n" + 
				"        printf(\"%d \", arr[0]);\r\n" + 
				"        swap(arr[0], arr[end]);\r\n" + 
				"        heap_adjust(0, end--);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    return 0;\r\n" + 
				"}\r\n" + 
				"~~~\r\n" + 
				"\r\n" + 
				"");
//		System.out.println(id);
	}
	
	@Test
	public void selectArticleTest() {
		String ans = articleServiceImpl.selectArticleById(2);
		System.out.println(ans);
	}
}
