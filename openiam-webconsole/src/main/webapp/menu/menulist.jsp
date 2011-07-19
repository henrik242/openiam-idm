<%@ page language="java" contentType="text/html; charset=ISO-8859-1"     pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 



<form:form commandName="menuListCmd">

<table width="60%">

<c:if test="${msg != null}" >
   <tr>
 		<td class="msg" colspan="2" align="center"  >
 		  ${msg}
 		</td>
  </tr> 
</c:if>

	<tr>
      <td colspan="2" class="title">         
          <strong>Menu List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="2" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
  </tr> 
     <tr>
		<td class="plaintext">Filter By:</td>
        <td class="plaintext">Menu Name: 
        			 <form:select path="menuGroup">
			  			<form:option value=""  label="-Select a value" />
			  			<form:options items="${menuList}" itemValue="id" itemLabel="menuName"  />
			  		</form:select>
	 </td>
  	</tr>
     <tr>
		<td class="plaintext"></td>
        <td class="plaintext">Language: 
        			 <form:select path="languageCd">
			  			<form:option value="en"  label="English" />
			  			<form:option value="fr"  label="French" />
			  			<form:option value="de"  label="German" />
			  			<form:option value="it"  label="Italian" />
			  			<form:option value="es"  label="Spanish" />
			  		</form:select>
	 </td>
  	</tr>

    	<tr>
 		   <td colspan="2" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    	</tr>
          <tr>
          	  <td><a href="menuDetail.cnt">New Menu</a></td>
              <td align="right"> <input type="submit" value="Search"/> </td>
          </tr>
</table>

</form:form>

<c:if test="${searchResult != null}" >

<table width="100%">
	<tr>
      <td colspan="5" class="title">         
          <strong>Menu List</strong>
      </td>
   </tr>
   
   <tr>
 		<td colspan="5" align="center" bgcolor="8397B4" >
 		  <font></font>
 		</td>
   </tr> 
  
    <tr>
		<td colspan="5" class="plaintext" >${resultsize} Menu(s) found.</td>       
    </tr>  
      
    <tr>
  		<td class="tdheader" >Name</td>
        <td class="tdheader" >Description</td>
        <td class="tdheader" >Menu Group </td>
        <td class="tdheader" >URL </td>
      </tr>
	

	<c:forEach items="${searchResult}" var="menu">
		<tr class="tdlight">

			<td><a href="menuDetail.cnt?menuId=${menu.id}">${menu.menuName}</a></td>
			<td> ${menu.menuDesc}</td>
			<td> ${menu.menuGroup}</td>
			<td> ${menu.url}</td>
		</tr>
	</c:forEach>

    <tr>
   	  <td colspan="5">&nbsp;</td>
   	</tr>
    <tr>
 		   <td colspan="5" align="center" bgcolor="8397B4" >
 		    <font></font>
 		   </td>
    </tr>

           
</table>

</c:if>

