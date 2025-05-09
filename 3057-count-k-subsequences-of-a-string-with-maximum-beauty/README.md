<h2><a href="https://leetcode.com/problems/count-k-subsequences-of-a-string-with-maximum-beauty/">3057. Count K-Subsequences of a String With Maximum Beauty</a></h2><h3>Hard</h3><hr><p>You are given a string <code>s</code> and an integer <code>k</code>.</p>

<p>A <strong>k-subsequence</strong> is a <strong>subsequence</strong> of <code>s</code>, having length <code>k</code>, and all its characters are <strong>unique</strong>, <strong>i.e</strong>., every character occurs once.</p>

<p>Let <code>f(c)</code> denote the number of times the character <code>c</code> occurs in <code>s</code>.</p>

<p>The <strong>beauty</strong> of a <strong>k-subsequence</strong> is the <strong>sum</strong> of <code>f(c)</code> for every character <code>c</code> in the k-subsequence.</p>

<p>For example, consider <code>s = &quot;abbbdd&quot;</code> and <code>k = 2</code>:</p>

<ul>
	<li><code>f(&#39;a&#39;) = 1</code>, <code>f(&#39;b&#39;) = 3</code>, <code>f(&#39;d&#39;) = 2</code></li>
	<li>Some k-subsequences of <code>s</code> are:
	<ul>
		<li><code>&quot;<u><strong>ab</strong></u>bbdd&quot;</code> -&gt; <code>&quot;ab&quot;</code> having a beauty of <code>f(&#39;a&#39;) + f(&#39;b&#39;) = 4</code></li>
		<li><code>&quot;<u><strong>a</strong></u>bbb<strong><u>d</u></strong>d&quot;</code> -&gt; <code>&quot;ad&quot;</code> having a beauty of <code>f(&#39;a&#39;) + f(&#39;d&#39;) = 3</code></li>
		<li><code>&quot;a<strong><u>b</u></strong>bb<u><strong>d</strong></u>d&quot;</code> -&gt; <code>&quot;bd&quot;</code> having a beauty of <code>f(&#39;b&#39;) + f(&#39;d&#39;) = 5</code></li>
	</ul>
	</li>
</ul>

<p>Return <em>an integer denoting the number of k-subsequences </em><em>whose <strong>beauty</strong> is the <strong>maximum</strong> among all <strong>k-subsequences</strong></em>. Since the answer may be too large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>A subsequence of a string is a new string formed from the original string by deleting some (possibly none) of the characters without disturbing the relative positions of the remaining characters.</p>

<p><strong>Notes</strong></p>

<ul>
	<li><code>f(c)</code> is the number of times a character <code>c</code> occurs in <code>s</code>, not a k-subsequence.</li>
	<li>Two k-subsequences are considered different if one is formed by an index that is not present in the other. So, two k-subsequences may form the same string.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bcca&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> <span style="white-space: normal">From s we have f(&#39;a&#39;) = 1, f(&#39;b&#39;) = 1, and f(&#39;c&#39;) = 2.</span>
The k-subsequences of s are: 
<strong><u>bc</u></strong>ca having a beauty of f(&#39;b&#39;) + f(&#39;c&#39;) = 3 
<strong><u>b</u></strong>c<u><strong>c</strong></u>a having a beauty of f(&#39;b&#39;) + f(&#39;c&#39;) = 3 
<strong><u>b</u></strong>cc<strong><u>a</u></strong> having a beauty of f(&#39;b&#39;) + f(&#39;a&#39;) = 2 
b<strong><u>c</u></strong>c<u><strong>a</strong></u><strong> </strong>having a beauty of f(&#39;c&#39;) + f(&#39;a&#39;) = 3
bc<strong><u>ca</u></strong> having a beauty of f(&#39;c&#39;) + f(&#39;a&#39;) = 3 
There are 4 k-subsequences that have the maximum beauty, 3. 
Hence, the answer is 4. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbcd&quot;, k = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> From s we have f(&#39;a&#39;) = 1, f(&#39;b&#39;) = 2, f(&#39;c&#39;) = 1, and f(&#39;d&#39;) = 1. 
The k-subsequences of s are: 
<u><strong>ab</strong></u>b<strong><u>cd</u></strong> having a beauty of f(&#39;a&#39;) + f(&#39;b&#39;) + f(&#39;c&#39;) + f(&#39;d&#39;) = 5
<u style="white-space: normal;"><strong>a</strong></u>b<u><strong>bcd</strong></u> having a beauty of f(&#39;a&#39;) + f(&#39;b&#39;) + f(&#39;c&#39;) + f(&#39;d&#39;) = 5 
There are 2 k-subsequences that have the maximum beauty, 5. 
Hence, the answer is 2. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>
