<html>
<body>
<h2>Jersey RESTful Web Application!</h2>

<p><a href="webresources/myresource">Jersey resource</a>

<p>Visit the <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!

<h2>Klokke</h2>
<div id="klokke"></div>

<h2>Resttjenester</h2>
<ul>
    <li><a href="rest/hi">Hi</a></li>
    <li><a href="rest/sse">Server Sent Event</a></li>
</ul>

Time: <span id="foo"></span>
<br><br>
<button onclick="start()">Start</button>

<script type="text/javascript">
    var timer;
    var time;
    function start() {
        var eventSource = new EventSource("rest/sse");

//        eventSource.addEventListener("message-to-client", msgClient, false);

/*
        eventSource.onmessage = function (event) {
            document.getElementById('foo').innerHTML = event.data;
        };
*/

        eventSource.addEventListener('klock', function (event) {
            startTime(event);
        })
    }

    function msgClient(event) {
        document.getElementById('foo').innerHTML = event.data;
    }

    function startTime(event) {
        if (event) {
            time = 1*event.data;
        }
        var tiden = new Date(time);
        var h = tiden.getHours();
        var m = tiden.getMinutes();
        var s = tiden.getSeconds();
        m = checkTime(m);
        s = checkTime(s);
        document.getElementById('klokke').innerHTML = h + ":" + m + ":" + s;
        timer = setTimeout(function () {
            time += 1000;
            startTime();
        }, 1000);
    }

    function checkTime(i) {
        if (i < 10) {
            i = "0" + i
        }  // add zero in front of numbers < 10
        return i;
    }
</script>
</body>
</html>
