<html>
<body>
<h2>Tomcat root redirect test</h2>
<p>Testing issues related to <a href="https://bz.apache.org/bugzilla/show_bug.cgi?id=58660">https://bz.apache.org/bugzilla/show_bug.cgi?id=58660</a> in tomcat 8.0.28-30.</p>
<p>If test is passed, both values should match.  The test fails with tomcat 8.0.29-30 when the slashless link is used.</p>
<p>Current tomcat version: <%=pageContext.getServletContext().getServerInfo()%></p>
Request Value: <input readonly value="<%=request.getAttribute("requestValue")%>"/>
<br/>
Session Value: <input readonly value="<%=request.getAttribute("sessionValue")%>"/>
<br/>
Match: <input readonly value="<%=request.getAttribute("match")%>"/>
</body>
<br/>
Link: <a href="<%=pageContext.getServletContext().getContextPath()%>">Context without slash <%=pageContext.getServletContext().getContextPath()%></a>
<br/>
Link: <a href="<%=pageContext.getServletContext().getContextPath()%>/">Context with slash <%=pageContext.getServletContext().getContextPath()%>/</a>
</html>
