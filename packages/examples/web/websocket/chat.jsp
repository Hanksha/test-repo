<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="The examples package ships with Martini by default and contains several services and API files that demonstrate many of Martini's features.">

    <title>Web Socket Chat Room Example - Examples Package Homepage</title>

    <!-- Bootstrap core CSS -->
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../static/css/cover.css" rel="stylesheet">
    <link href="../static/css/gradient.css" rel="stylesheet">
  </head>

  <body>
    <div class="gradient-bg"></div>
    <div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
      <header class="masthead mb-auto">
        <div class="inner">
          <h3 class="masthead-brand"></h3>
          <nav class="nav nav-masthead justify-content-center">
          </nav>
        </div>
      </header>
      <main role="main" class="inner cover text-center">
        <ul class="pages">
          <li class="chat page">
            <div class="chatRoomContainer">
              <div class="chatArea">
                <ul class="messages"id="chat">
                <div class="mesgs">
                  <div class="msg_history"></div>
                </div>
                </ul>
              </div>
            <input class="inputMessage" placeholder="Type here..." autofocus/>
            </div>
          </li>
          <li class="login page">
            <div class="form">
              <img class="ninjaImg" src="../static/img/ninja-sword-2.png" alt="">
              <h3 class="title">What's your ninja name?</h3>
              <input class="usernameInput" type="text" maxlength="20" autofocus/>
            </div>
          </li>
        </ul>
      </main>
      <footer class="mastfoot mt-auto">
      </footer>
    </div>
    <script src="../static/js/jquery-1.10.2.min.js"></script>
    <script src="../static/js/sockjs.min.js"></script>
    <script src="../static/js/stomp.min.js"></script>
    <script src="../static/js/chat.js"></script>
  </body>
</html>