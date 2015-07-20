<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/xml;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<rss version="2.0">
    <channel>
        <title><![CDATA[Spring blog]]></title>
        <link>http://blog.millky.com/post/list</link>
        <description><![CDATA[아카이브, 블로그, 커뮤니티 서비스]]></description>
        <language>ko-kr</language>
        <generator>https://github.com/origoni/Spring-Blog</generator>
        <c:forEach var="post" items="${postList}">
            <item>
                <title><![CDATA[<c:out value="${post.title}" escapeXml="true" />]]></title>
                <link>http://blog.millky.com/post/${post.id}</link>
                <description>
                    <c:if test="${post.subtitle!=''}"><![CDATA[<c:out value="${post.subtitle}" escapeXml="true" />]]></c:if>
                    <![CDATA[${post.content}]]>
                </description>
                <category><![CDATA[<c:out value="${post.category.name}" escapeXml="true" />]]></category>
                <author><![CDATA[<c:out value="${post.name}" escapeXml="true" />]]></author>
                <guid>http://blog.millky.com/post/${post.id}</guid>
                <pubDate><fmt:formatDate value="${post.regDate}" pattern="EE, dd MMM yyyy HH:mm:ss Z"/></pubDate>
            </item>
        </c:forEach>
    </channel>
</rss>