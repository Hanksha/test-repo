var currentUser;
var socket = new SockJS('/martini-ws');
$(document).ready(function(){

	stompConnect();

    function stompConnect() {
        stompClient = Stomp.over(socket);
        stompClient.connect( {}, function(frame){
            stompSuccessCallback();
        }, function(message) {
            stompFailureCallback();
        });
    }

    var stompFailureCallback = function (error) {
        console.log('Stomp: ' + error);
        window.location.href = "/esb/login?target=/examples/websocket/chat.jsp";
    };

    var stompSuccessCallback = function (error) {
        console.log('Stomp: Connected Successfully');
        webSocketNewUserSubscribe();
        webSocketDisconnectUserSubscribe();
    };
	
	$('.usernameInput').on('keypress', function(key){
		if( key.charCode == 13 ) {
            if( $(this).val() ){
                $('.login.page').fadeOut();
                $('.chat.page').css('display','flex');
                $('.inputMessage').focus();
                $('.login.page').off('click');
                addUsername( $(this).val() )
                webSocketSubscribe();
                window.onbeforeunload = function () {
                    publishMessage( "/topic/user-leave", {username: getCurrentUser() } );
                    setCookie('username', '');
                };
            }
		}
	});
	
	$('.inputMessage').on('keypress', function(key){
		if( key.charCode == 13 ) {
			if( $(this).val() != ''){
				var text = '<div class="outgoing_msg">'+
				            '<div style="float: right; font-size: 16px;">  '+ getCurrentUser() +': </div><br>'+
				              '<div class="sent_msg">'+
				                '<p style="margin: 0;"> ' + $(this).val() + '</p>'+
				            '</div>'
				appendChatMessage(text);
				publishMessage( "/topic/chat-room", {username: getCurrentUser(), message: $(this).val() } );
				$(this).val('');
			}
		}
	})
})

var webSocketSubscribe = function(){
	stompClient.subscribe('/topic/chat-room', function (data) {
        data = $.parseJSON( data.body )
        currentUser = getCurrentUser();
        if( currentUser != data.username && data.username != 'disconnect' ){
	        var text = '<div class="incoming_msg">'+
	             '<div class="incoming_msg_img"> '+ data.username +': </div><br>'+
	              '<div class="received_msg">'+
	                '<div class="received_withd_msg">'+
	                  '<p>' + data.message + '</p>'+
	              '</div>'+
	            '</div>'
	       appendChatMessage(text);
        }
	});
}

var webSocketNewUserSubscribe = function(){
	stompClient.subscribe('/topic/new-user', function(data){
		data = $.parseJSON( data.body )
		if( getCurrentUser() == data.username )
			data.username = 'You'
		var text = '<center style="color:#33ab0f">' + data.username + ' entered the chat room.</center>';
		appendChatMessage(text);
	})
}

var webSocketDisconnectUserSubscribe = function(){
	stompClient.subscribe('/topic/user-leave', function(data){
		data = $.parseJSON( data.body )
		var text = '<center style="color:#dc0000">' + data.username + ' left the chat room.</center>';
		appendChatMessage(text);
	})
}

var appendChatMessage = function( text ){
	$('#chat').append(text);
	gotoBottom()
}

var gotoBottom = function(){
	$('#chat').animate({scrollTop: document.getElementById('chat').scrollHeight }, 'fast')
}

var publishMessage = function( destination, data ){
	stompClient.send(destination, {}, JSON.stringify(data));
}

var addUsername = function( username ) {
	setCookie( 'username', username );
	publishMessage( "/topic/new-user", {username: username } );
}

var getCurrentUser = function() {
	var username = getCookie('username');
	if( !username ) {
		console.log( "No current user is logged in!" )
	}
	return username;
}

var setCookie = function( cookieName, cookieValue ){
	document.cookie = cookieName + "=" + cookieValue + ";"
}

var getCookie = function( cookieName ) {
	var name = cookieName + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}