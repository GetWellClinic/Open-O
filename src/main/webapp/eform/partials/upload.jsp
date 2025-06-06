<%--

    Copyright (c) 2001-2002. Department of Family Medicine, McMaster University. All Rights Reserved.
    This software is published under the GPL GNU General Public License.
    This program is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public License
    as published by the Free Software Foundation; either version 2
    of the License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.

    This software was written for the
    Department of Family Medicine
    McMaster University
    Hamilton
    Ontario, Canada

--%>
<!DOCTYPE html>
<%@ page import="oscar.eform.data.*, oscar.eform.*, java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html:html lang="en">

<head>
    <script src="${pageContext.request.contextPath}/js/global.js"></script>
    <script src="<%=request.getContextPath() %>/library/jquery/jquery-3.6.4.min.js"></script>
    <script src="<%=request.getContextPath() %>/library/jquery/jquery-ui-1.12.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/library/jquery/jquery-ui.theme-1.12.1.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/library/jquery/jquery-ui.structure-1.12.1.min.css" rel="stylesheet">

<style>
body{background-color:#f5f5f5;}

.uploadEformTitle{display:inline-block;}
</style>

</head>

<body>

<c:if test="${ not empty status }">
    <div class="alert alert-success">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong> Your eform was uploaded.
    </div>

    <script>
		window.top.location.href = "<%=request.getContextPath()%>/administration/?show=Forms";
	</script>
</c:if>


<html:form action="/eform/uploadHtml" method="POST" onsubmit="return checkFormAndDisable()" enctype="multipart/form-data">
<div class="alert alert-error" style="display:none"> <html:errors /> </div>

                                        <div class='uploadEformTitle'>
                                        <bean:message key="eform.uploadhtml.formName" /> <span class="text-error textExists" style='display:none;'>Name already exists</span><br>
                                        <input type="text" name="formName" size="30" class="check" required>
                                        </div>

                                        <div class='uploadEformTitle'>
                                        <bean:message key="eform.uploadhtml.formSubject" /><br>
                                        <input type="text" name="formSubject" size="30">
                                        </div>

                                        <div class='uploadEformTitle'>
                                        <bean:message key="eform.uploadhtml.btnRoleType"/><br>
                                        <select name="roleType">
                                        <option value="" >- select one -</option>
                                       <%  ArrayList roleList = EFormUtil.listSecRole();
  											for (int i=0; i<roleList.size(); i++) {
  										%>

                                        		<option value="<%=roleList.get(i) %>"><%=roleList.get(i) %></option>

                                        <%} %>
                                        </select>
                                        </div>

                                        <div class='uploadEformTitle'>
                                        	<div>
	                                        <label class="checkbox">
	                                        <input type="checkbox" name="showLatestFormOnly" value="true"/><bean:message key="eform.uploadhtml.showLatestFormOnly"/>
	                                        </label>
	                                        </div>
	                                        <div>
	                                        <label class="checkbox">
	                                        <input type="checkbox" name="patientIndependent" value="true"/><bean:message key="eform.uploadhtml.patientIndependent"/>
	                                        </label>
	                                        </div>
                                        </div>

                                        <input type="file" name="formHtml" id="formHtml" class="check" size="50" required>
                                    	<span style="color:red;">
								         <i class="icon-warning-sign" title="<bean:message key="global.uploadWarningBody"/>" ></i>
								        </span>


                                        <input type="submit" name="subm" class="btn btn-primary upload" value="<bean:message key="eform.uploadhtml.btnUpload"/>" disabled>

</html:form>

<div style="font-size:0; line-height:0">&nbsp;</div>

<script src="<%=request.getContextPath() %>/js/jquery-1.12.3.js"></script>

<script>
$( document ).ready(function() {
$(".check").on("change",validate).keyup(validate);
});

function validate()
{
var v = $(this).val();
var id = $(this).attr("id");
var formHtml = $("#formHtml").val();

var inputCheck=checkRow(v);

if (v!="" && inputCheck=="" && formHtml!="") {
	    $('.upload').removeAttr("disabled");
		$('.upload').addClass("btn-success");
		$('.textExists').hide();
    } else if(inputCheck=="exists"){
	    $('.upload').attr("disabled", "disabled");
		$('.upload').removeClass("btn-success");
		$('.textExists').show();
    }else{
	    $('.upload').attr("disabled", "disabled");
		$('.upload').removeClass("btn-success");
		$('.textExists').hide();
    }
}

function checkRow(textInput)
{
var result="";
window.parent.$('#eformTbl tbody').find('tr').each(function(){

    if($('td:nth(1)',$(this)).attr("title")===textInput){
	result="exists";
        return false;
    }
});

return result
}
</script>
</body>
</html:html>