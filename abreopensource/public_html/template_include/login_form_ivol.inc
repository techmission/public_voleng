<form name="individualForm" method="post" action="<%=request.getContextPath()%>/register.do">

<input type="hidden" name="method" value="processLogin">

  <table width="300" border="0" cellpadding="0" cellspacing="5" style="font-size: 11px;">

    <tr>

      <td width="90" align="right">e-mail</td>

      <td><input style="width:120px;" type="text" name="email1addr" maxlength="50" /></td>

      </tr>

    <tr>

      <td align="right">password</td>

      <td><input style="width:120px;" type="password" name="password1" maxlength="50" />

        <input name="submit" type="image" id="submit" value="Log In" src="<%=request.getContextPath()%>/imgs/search_arrow.gif" /></td>

      </tr>

  </table>

</form>
