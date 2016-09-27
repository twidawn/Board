<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./board/1.css">
</head>
<body>
	<center>
		<h1>����� �Խ���</h1>
		<table width="600" id="cat_table" align="center">
			<tr>
				<td>
					<!-- <div class=" button-wrapper">
            			<div class="button1">
        					<span>
            					<a href="list.do" class="cat1 a_btn">��ü</a>
            				</span>
            			</div>
        			</div>

          			<div class=" button-wrapper">
              			<div class="button1">
        					<span>
            					<a href="list.do?category=1" class="cat1 a_btn">����</a>
        					</span>
              			</div>
         			 </div>
         			 
         			 <div class=" button-wrapper">
              			<div class="button1">
        					<span>
            					<a href="list.do?category=2" class="cat1 a_btn">������</a>
        					</span>
              			</div>
         			 </div>
         			 
         			 <div class=" button-wrapper">
              			<div class="button1">
        					<span>
            					<a href="list.do?category=3" class="cat1 a_btn">������</a>
        					</span>
              			</div>
         			 </div>
         			 
         			 <div class=" button-wrapper">
              			<div class="button1">
        					<span>
            					<a href="list.do?category=4" class="cat1 a_btn">����</a>
        					</span>
              			</div>
         			 </div>
         			 
         			 <div class=" button-wrapper">
              			<div class="button1">
        					<span>
            					<a href="list.do?category=5" class="cat1 a_btn">��������</a>
        					</span>
              			</div>
         			 </div>
         			 
         			 <div class=" button-wrapper">
              			<div class="button1">
        					<span>
            					<a href="list.do?category=6" class="cat1 a_btn">����</a>
        					</span>
              			</div>
         			 </div> -->
         			 
         			 <c:forEach var="i" begin="0" end="6" step="1">
         			 	<c:choose>
         			 		<c:when test="${i >= 1 }">
         			 			<c:if test="${category eq i}">
         			 				<div class=" button-wrapper">
              							<div class="button1">
        									<span class="check">
            									<a href="list.do?category=${i }" class="cat1 a_btn">${categoryList[i] }</a>
        									</span>
              							</div>
         			 				</div>
         			 			</c:if>
         			 			
         			 			<c:if test="${category ne i}">
         			 				<div class=" button-wrapper">
              							<div class="button1">
        									<span>
            									<a href="list.do?category=${i }" class="cat1 a_btn">${categoryList[i] }</a>
        									</span>
              							</div>
         			 				</div>
         			 			</c:if>
         			 			
         			 		</c:when>
         			 		<c:otherwise>
         			 			<c:if test="${category eq null }">
         			 				<div class=" button-wrapper">
              							<div class="button1">
        									<span class="check">
            									<a href="list.do" class="cat1 a_btn">${categoryList[i] }</a>
        									</span>
              							</div>
         			 				</div>
         			 			</c:if>
         			 			
         			 			<c:if test="${category ne null }">
         			 				<div class=" button-wrapper">
              							<div class="button1">
        									<span>
            									<a href="list.do" class="cat1 a_btn">${categoryList[i] }</a>
        									</span>
              							</div>
         			 				</div>
         			 			</c:if>
         			 		
         			 		</c:otherwise>
         			 	</c:choose>
         			 </c:forEach>
         			 
         			 
				</td>
			</tr>
		</table>
	
	
		<table width="600" id="maintable">
			<tr id="title">
				<th width="10%">��ȣ</th>
				<th width="40%">����</th>
				<th width="15%">�۾���</th>
				<th width="25%">��¥</th>
				<th width="10%">��ȸ��</th>
			</tr>
			
			<%--Ȯ�ο� �ӽ� tr --%>
			<tr>
				<td>1</td>
				<td class="subject">2</td>
				<td>3</td>
				<td>4</td>
				<td>5</td>
			</tr>
			<tr>
				<td>1</td>
				<td class="subject">23</td>
				<td>3</td>
				<td>4</td>
				<td>5</td>
			</tr>
			
			<!--�������  -->
			
			<c:forEach items="${list }" var="vo">
				<tr>
					<td>${vo.no }</td>
					<td class="subject">
					<a href="#">
					<c:choose>
						<c:when test="${vo.category eq 1 }">[����]</c:when>
						<c:when test="${vo.category eq 2 }">[������]</c:when>
						<c:when test="${vo.category eq 3 }">[����]</c:when>
						<c:when test="${vo.category eq 4 }">[����]</c:when>
						<c:when test="${vo.category eq 5 }">[��������]</c:when>
						<c:when test="${vo.category eq 6 }">[����]</c:when>
					
					</c:choose>
					${vo.subject }
					</a></td>
					<td>${vo.name }</td>
					<td>${vo.regdate }</td>
					<td>${vo.hit }</td>
				</tr>
			</c:forEach>
		
		</table>
		
		<table>
			<tr>
				<td>
				<c:if test="${block < curpage || curpage eq total }">
					<c:choose>
						<c:when test="${category >= 1 && category <= 6 }">
							<a href="list.do?category=${category }&page=${formpage > 1 ? formpage-1 : 1}">ȣ��</a>
						</c:when>
						
						<c:otherwise>
							<a href="list.do?page=${formpage > 1 ? formpage-1 : 1}">ȣ��</a>
						</c:otherwise>
					</c:choose>
				</c:if>
				
				<c:forEach var="i" begin="${formpage}" end="${topage}">
					<c:choose>
						<c:when test="${category >= 1 && category <= 6 }">
							<a href="list.do?category=${category }&page=${i}">[${i }]</a>
						</c:when>
						
						<c:otherwise>
							<a href="list.do?page=${i}">[${i }]</a>
						</c:otherwise>
					</c:choose>
				
				</c:forEach>
				
				<c:if test="${topage > curpage }">
					<c:choose>
						<c:when test="${category >= 1 && category <= 6 }">
							<a href="list.do?category=${category }&page=${topage < total ? topage+1 : topage}">ȣ��</a>
						</c:when>
						
						<c:otherwise>
							<a href="list.do?page=${topage < total ? topage+1 : topage}">ȣ��</a>
						</c:otherwise>
					</c:choose>
				</c:if>
				
				</td>
			</tr>
		</table>
		
		<table border="0">
    		<tr>
        		<td id="blackbtn">
            		<div class="button-wrapper">
                		<div class="button1">
        					<span>
            					<a href="http://www.naver.com" class="a_btn">����</a>
							</span>
                		</div>
            		</div>
            		
            		<div class="button-wrapper">
                		<div class="button1">
        					<span>
            					<a href="http://www.naver.com" class="a_btn">����</a>
							</span>
                		</div>
            		</div>
            		
            		<div class="button-wrapper">
                		<div class="button1">
        					<span>
            					<a href="http://www.naver.com"  class="a_btn">���</a>
        					</span>
                		</div>
            		</div>
        		</td>

        		<td>
            		<div id="btnwrt">
                		<div class="btn1 button">
        					<span class="span1">
            					<a href="http://www.naver.com">�۾���</a>
        					</span>
                		</div>
            		</div>
        		</td>
    		</tr>
		</table>
		
	</center>
</body>
</html>