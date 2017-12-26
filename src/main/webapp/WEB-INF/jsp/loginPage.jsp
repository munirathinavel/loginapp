<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
	<h3>Login Page:</h3>

		<div id='errorMessage'> <p> Incorrect Username or Password. Please try again! </p> </div>

	<div id="first">
		<form name='loginForm'>
			<table>
				<tr>
					<td>UserName:</td>
					<td><input type='text' id="name" name='name' value=''></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' id="password" name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'>
					<input id="login" name="login" type="button" value="login" disabled='disabled' /></td>
				</tr>
			</table>
		</form>
	</div>


	<div id="second">
		<p>You are in</p>
		<br />
		<input type='button' name='logout' id='logout' value='Log Out' />
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {

		$("#second").hide();
		$("#errorMessage").hide();

		$("#name").keypress(function(){
		    if($("#password").val() != ""){
		    	$('#login').prop('disabled', false);
		    } else {
		    	$('#login').prop('disabled', true);
		    }
		});
		
		$("#password").keypress(function(){
		    if($("#name").val() != ""){
		    	$('#login').prop('disabled', false);
		    } else {
		    	$('#login').prop('disabled', true);
		    }
		});
		// on click Sign In Button checks with the remote server that username =='vel' and password == '123456'
		$("#login").click(function() {
			$.ajax({
				url : '/login',
				type : 'post',
				data : {
					name : $('#name').val(),
					password : $('#password').val()
				},
				success : function(data) {
					if('SUCCESS' == data){
						$("#first").hide();
						$("#second").show();
						$("#errorMessage").hide();
                	} else {
                		$('#errorMessage').show();
                		$("form")[0].reset();
                		$('#login').prop('disabled', true);
                	}
					
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText);
				}
			});
		});

		$("#logout").click(function() {

			$.ajax({
				url : '/logout',
				type : 'get',
				success : function(data) {
					if('SUCCESS' == data){
						$("form")[0].reset();
						$("#first").show();
						$("#second").hide();
						$("#errorMessage").hide();
						$('#login').prop('disabled', true);
					}
				}
			});

		});

	});
</script>

</html>

