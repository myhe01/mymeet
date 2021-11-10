import java.io.*;
import java.net.*;
import java.util.*;

// A client sends messages to the server, the server spawns a thread to communicate with the client.
// Each communication with a client is added to an array list so any message sent gets sent to every other client
// by looping through it.

public class ChatClient implements Runnable {

    // A client has a socket to connect to the server and a reader and writer to receive and send messages respectively.
    private Socket s;
    private BufferedReader br;
    private BufferedWriter bw;
    private String userID;

    public ChatClient(Socket socket, String username) {
        try {
            this.s = socket;

            // TODO: get userID
            this.userID = ;
            // FIXME: all fucked up
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bw= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } 
        
        catch (Exception e) {
            // Gracefully close everything.
            abort(s, br, bw);
        }
    }

    // Sending a message isn't blocking and can be done without spawning a thread, unlike waiting for a message.
    public void sendMessage() {
        try {
            // Initially send the username of the client.
            bw.write(username);
            // TODO: send to table here
            // System.currentTimeMillis() + userID + message

            bw.newLine();
            bw.flush();


            // Create a scanner for user input.
            Scanner scanner = new Scanner(System.in);


            // While there is still a connection with the server, continue to scan the terminal and then send the message.
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bw.write(username + ": " + messageToSend);
                bw.newLine();
                bw.flush();
            }
        } 
        
        catch (Exception e) {
            // Gracefully close everything.
            abort(s, br, bw);
        }
    }

    // Listening for a message is blocking so need a separate thread for that.
    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                // While there is still a connection with the server, continue to listen for messages on a separate thread.
                while (s.isConnected()) {
                    try {
                        // Get the messages sent from other users and print it to the console.
                        msgFromGroupChat = br.readLine();
                        System.out.println(msgFromGroupChat);
                    }
                    
                    catch (Exception e) {
                        // Close everything gracefully.
                        abort(s, br, bw);
                    }
                }
            }
        }).start();
    }

    // Helper method to close everything so you don't have to repeat yourself.
    public void abort(Socket s, BufferedReader br, BufferedWriter bw) {
        // Note you only need to close the outer wrapper as the underlying streams are closed when you close the wrapper.
        // Note you want to close the outermost wrapper so that everything gets flushed.
        // Note that closing a socket will also close the socket's InputStream and OutputStream.
        // Closing the input stream closes the socket. You need to use shutdownInput() on socket to just close the input stream.
        // Closing the socket will also close the socket's input stream and output stream.
        // Close the socket after closing the streams.
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
        
        catch (Exception e) {
            // TODO: exception handling
        }
    }

    // Run the program.
    public static void main(String[] args) throws IOException {

        // Get a username for the user and a socket connection.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username for the group chat: ");
        String username = scanner.nextLine();
        // Create a socket to connect to the server.
        Socket socket = new Socket("localhost", 1234);

        // Pass the socket and give the client a username.
        Client client = new Client(socket, username);
        // Infinite loop to read and send messages.
        client.listenForMessage();
        client.sendMessage();
    }
}
