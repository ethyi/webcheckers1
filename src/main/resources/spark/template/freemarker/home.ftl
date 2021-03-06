<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />

    <#if names??>

        <form action ="/game" method="GET">

            <#list names as n>
                <#if n !=currentUser>
                <p>
                    <input type = "submit" name="challenger" value="${n}"/>
                </p>
                </#if>
            </#list>

        </form>

    </#if>

    <#if numPlayers??>
        Current Players: ${numPlayers}
    </#if>



    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->

  </div>

</div>
</body>

</html>
