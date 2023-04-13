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
        window.location.href = "/esb/login?target=/examples/websocket";
    };

    var stompSuccessCallback = function (error) {
        console.log('Stomp: Connected Successfully');
    };
	
	$('.usernameInput').on('keypress', function(key){
		if( key.charCode == 13 ) {
			if( $(this).val() ){
				$('.login.page').fadeOut();
			    $('.chat.page').css('display','flex');
			    $('.inputMessage').focus();
			    $('.login.page').off('click');
			    addUsername( $(this).val() )
			    webSocketSubscribe( $(this).val() );
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
				$('#chat').append(text);
				gotoBottom()
				publishMessage( getCurrentUser(), $(this).val() );
				$(this).val('');
			}
		}
	})

})

var webSocketSubscribe = function(username){
	stompClient.subscribe('/topic/guessingGame.'+username, function (data) {
        data = data.body.split(':');
        var text = '<div class="incoming_msg">'+
             '<div class="incoming_msg_img"> '+ data[0] +': </div><br>'+
              '<div class="received_msg">'+
                '<div class="received_withd_msg">'+
                  '<p>' + data[1] + '</p>'+
              '</div>'+
            '</div>'
        $('#chat').append(text);
        gotoBottom()

	});
}

var gotoBottom = function(){
	$('#chat').animate({scrollTop: document.getElementById('chat').scrollHeight }, 'fast')
}

var publishMessage = function( username, message ){
	stompClient.send("/topic/web-socket", {}, JSON.stringify({username: username, message: message}));
}

var addUsername = function( username ) {
	setCookie( 'username', username );
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