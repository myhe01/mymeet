import java.io.*;
import java.net.*;
import java.util.*;

public class ChatHandler implements Runnable {
    public static ArrayList<ChatHandler> chatHandlers = new ArrayList<ChatHandler>();
    private Socket s;
    private BufferedReader br;
    private BufferedWriter bw;
    private int userID;

    // Creating the client handler from the socket the server passes.
    public ChatHandler(Socket socket) {
        try {
            this.s = socket;
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            // TODO: get userID from InputStream bw
            this.userID = 0;

            // Add new handler to list so they can receive messages
            chatHandlers.add(this);
        }
        
        catch (Exception e) {
            abort(s, br, bw);
        }
    }
    
    // FIXME: what the fuck am i doing
    // FIXME: do we even need sockets if it's just tables
    // FIXME: this whole goddamn mess

    // This is the method run when the handler is passed to a thread in ChatServer
    @Override
    public void run() {
        // Listen for messages while a connection with the client is still established.
        while (s.isConnected()) {
            try {
                // Read what the client sent and then send it to every other client.
                // TODO: Read message from client here, get timestamp, send to table
                // TODO: Retrieve table and thus messages
            }
            
            catch (Exception e) {
                abort(s, br, bw);
                break;
            }
        }
    }

    /*
    // Send a message through each client handler thread so that everyone gets the message.
    // Basically each client handler is a connection to a client. So for any message that
    // is received, loop through each connection and send it down it.
    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                // You don't want to broadcast the message to the user who sent it.
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                // Gracefully close everything.
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }
    */

    // If the client disconnects for any reason remove them from the list so a message isn't sent down a broken connection.
    public void removeChattHandler() {
        chatHandlers.remove(this);
    }

    // Helper method to close everything so you don't have to repeat yourself.
    public void abort(Socket s, BufferedReader br, BufferedWriter bw) {
        // Note you only need to close the outer wrapper as the underlying streams are closed when you close the wrapper.
        // Note you want to close the outermost wrapper so that everything gets flushed.
        // Note that closing a socket will also close the socket's InputStream and OutputStream.
        // Closing the input stream closes the socket. You need to use shutdownInput() on socket to just close the input stream.
        // Closing the socket will also close the socket's input stream and output stream.
        // Close the socket after closing the streams.

        // The client disconnected or an error occurred so remove them from the list so no message is broadcasted.
        removeChattHandler();
        try {
            if (br != null) {
                br.close();
            }

            if (bw != null) {
                bw.close();
            }

            if (s != null) {
                s.close();
            }
        } 
        
        catch (IOException e) {
            // TODO: exception handling
        }
    }
}
