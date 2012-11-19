<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<%String id = request.getParameter("id"); %>
<script>
try {
	parent.parent.publish('<%=id%>');
} catch (err) {
	alert(err.text);
}
</script>

</html>