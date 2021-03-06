<%@ page language="java" charset="UTF-8">
<DOCTYPE! html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <title>MyMeet</title>
        
        <link 
        type= "text/css" rel="stylesheet"
        href="./resources/css/index.css">
    </head>

    <body>
    <div>
        <div class="logo"><span>MyMeet</span></div>
        <div class="vline"></div>
        <div class="split left">

            <!-- Add an area that contains banner, img, and names -->
            <div id="top_box">
                <div id="banner_and_image">
                    <div id="banner">
                        <img src="./resources/images/spooky.jpg" alt="spooky">
                    </div>
        
                    <div id="home_user_image">
                        <img src="./resources/images/birb.png" alt="birb">
                    </div>
                </div>
            </div>
            <% userID=request.getAttribute("userID");
            User user = Query.userByUserID(userID);
            %>
            <div id="user_names">
                <h1><%=user.getFirstName()+" "+user.getLastName()%></h1>
                <h2><%=user.getUserName()%></h2>
            </div>

            <div class="home_info">
                <h1>Interests</h1>
                <ul>
                    <%String s="";
                    ArrayList<String> interest=user.getInterest();
                    for (int i=0; i<interest.length(); i++) {
                        s=s+"<li>"+interst.get(i)+"</li>"
                    }
                    %>
                    <%=s%>
                </ul>
                <br>

                <h1>Groups</h1>
                <ul>
                    <%<ArrayList<int> groups=Query.allGroupsInUser(userID);
                    String s = "";
                    for (int i = 0; i < groups.length(); i++) {
                        s=s+"<li>"+groups.get(i)+" "+Query.groupByGroupID(groups.get(i)).getGroupName()+"</li>";
                    }
                    %>
                    <%=s%>
                </ul>
                <br>

                <h1>Events</h1>
                <ul>
                    <%ArrayList<int> events=Query.allEventsInUser(userID);
                    String s = "";
                    for (int i = 0; i < events.length(); i++) {
                        s=s+"<li>"+events.get(i)+" "+Query.eventByEventID(event.get(i))+"</li>";
                    }
                    %>
                    <%=s%>
                </ul>

            </div>
        </div>

        <div class="split right">
            <nav class="menu">
                <div class="box one"><h1><a href="interests.html">MyInterests</a></h1></div>
                <div class="box two"><h1>MyGroups</h1></div>
                <div class="box three"><h1>MyEvents</h1></div>
                <div class="box four"><h1>MySettings</h1></div>
        </div>
    </div>
    </body>

</html>