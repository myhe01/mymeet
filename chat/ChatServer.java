import java.io.*;
import java.net.*;

/*
    To run (in main):
        ChatServer server = new ChatServer(port);
        server.initChat();
*/

public class ChatServer {
    private final int DEFAULT_PORT = 6338;
    private ServerSocket s;

    // Constructor with defined port
    public ChatServer(int port) {
        try {
            this.s = new ServerSocket(port);
        }

        catch (Exception e) {
            // TODO: exception handling
        }
    }

    // Default constructor with default port 
    public ChatServer() {
        try {
            this.s = new ServerSocket(DEFAULT_PORT);
        }

        catch (Exception e) {
            // TODO: exception handling
        }
    }

    // Starts server-side chat program, should be run upon whole server start
    public void initChat() {
        Socket socket;
        ChatHandler handler;

        try {
            // Listen for connecting clients
            while (!(s.isClosed())) {
                socket = s.accept();

                // Client has connected, start a ChatHandler thread (OS schedules thread)
                handler = new ChatHandler(socket);
                (new Thread(handler)).start();               
            }
        }
        
        catch (Exception e) {
            closeChat();
        }
    }
    
    // Stop server-side chat program
    public void closeChat() {
        try {
            if (s != null) {
                s.close();
            }
        } 
        
        catch (Exception e) {
            // TODO: Exception handling
        }
    }
}

/*
https://www.reddit.com/r/learnprogramming/comments/d0gg6j/comment/ez9euuh/?utm_source=share&utm_medium=web2x&context=3

Server
    - Maintains a list of active connections
    - Needs to react to a few message types:
        * Chat message
        * Connect message
        * Disconnect message
    - When a message comes in from one of its active connections it will ideally:
        * check what type of message it is and:
            > If its a chat message, send that message to all other clients
            > If its a Connect message notify other clients of a new chat participant (optional)
            > If its a disconnect message clean up the connection from its collection, and
              optionally notify other chatters that someone has left

Client
    - Must attempt to connect to the Server
    - Must react to incoming messages from the server

Messaging Protocol
    For this to work, Client and Server must agree on some form of messaging protocol to
    distinguish message types, because their behavior is driven by network events (messages) and
    what type of message it is.

Here is an example flow:
    - User connects to Server as client x.
    - Server adds client x connection to list of connections
    - Server notifies current active connections of a client x having joined the chat
    - Server sends a full list of active participants to client x so that client x can see who is
      in the chat room.
    - Client x sends the chat message: "I love lasagna"
    - Server sends a copy of "I love lasagna" to every other connected client, with meta-data
      attached to show it came from client x.
    - Client y sends the message: "I left the lasagna with Odie."
    - Client x sends a Disconnect message (maybe they're upset about Odie eating their lasagna)
    - Server sends the disconnect message to all other chatters to let them know that Client x has
      left the chat.
    - Note that telling people that someone has left is only really necessary for the clients to be
      able to update their view so the list of people in the chat is correct.

Manage Timeouts
    Sometimes people disconnect without sending a disconnect message. Maybe their internet went
    down, the power went out, etc.
    This is why you have timeouts so that the server doesn't keep a connection alive server-side
    forever because someone got disconnected improperly.
    This is often done by sending some kind of special message to clients every so often, which
    they have to respond to in a certain manner to verify that they are still connected.
*/