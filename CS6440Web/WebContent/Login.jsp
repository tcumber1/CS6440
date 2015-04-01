<%@ include file="WEB-INF/header.jsp" %>
<div style="clear: both; display: block; overflow: hidden; visibility: hidden; width: 0; height:0px;"></div>
    <div id="divLogin" style="float:left; padding-top:50px; background-color : #56A5E7; text-align:center; width:100%; Height:100%">
       <table style="width:500px; margin:0 auto">
           <tr>
               <td style="width:50%; padding-top:50px;">
                   <div style="border: solid 1px; border-color:#333; width:100%; ">
                       <div style="width:100%; padding-left:20px; ">
                           <h3 id="loginTitle" style="padding:0px;">Log In</h3>
                       </div>
                   </div>
               </td>
               <td style="width:50%; padding-left:20px;" rowspan="2">
                   <img id="imgLogin" style="height:300px; width:250px; " src="Images/Dr.jpg" />
               </td>
           </tr>
           <tr>
               <td>
                   <div id="divUserName" style="border: solid 1px; border-color:#333; width:100%">
                       <table style="width:100%; padding:20px;">
                           <tr>
                               <td style="width:75px;">
                                   <label style="font-weight: bold">UserName</label>
                               </td>
                               <td>
                                   <input type="text" name="userName" style="width:150px;" />
                               </td>
                           </tr>
                           <tr>
                               <td style="width:75px;">
                                   <label style="font-weight: bold">Password</label>
                               </td>
                               <td>
                                   <input type="text" name="password" style="width:150px;" />
                               </td>
                           </tr>
                           <tr>
                               <td colspan="2">&nbsp;</td>
                           </tr>
                           <tr>
                               <td  style="width:75px;">&nbsp;</td>
                               <td style="text-align:right; margin:5px;">
                                   <input type="button" id="btnLogin" title="Log In" value="Log In" style="height:40px; width:100px;" />
                               </td>
                           </tr>
                       </table>

                   </div>
               </td>
           </tr>
       </table>
    </div>
    <%@ include file="WEB-INF/footer.jsp" %>
    