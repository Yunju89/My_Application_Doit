package com.example.myapplicationjava;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myapplicationjava.databinding.ActivitySampleSocketBinding;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SampleSocketActivity extends AppCompatActivity {
    private static final String TAG = SampleSocketActivity.class.getSimpleName();
    private ActivitySampleSocketBinding binding;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_socket);

        binding.buttonSend.setOnClickListener(view -> {
            final String data = binding.editSend.getText().toString();
            new Thread(new Runnable() {                             // 데이터 전송 네트워크 기능을 하기 위해 동작을 스레드로 만들어야 함.
                @Override
                public void run() {
                    send(data);                                     // 스레드 내부에서 메서드 호출
                }
            }).start();
        });

        binding.buttonStart.setOnClickListener(view -> {
            new Thread(new Runnable() {                             // 서버 연결 네트워크 기능을 하기 위해 동작을 스레드로 만들어야 함.
                @Override
                public void run() {                                 // 스레드 내부에서 메서드 호출
                    startServer();
                }
            }).start();
        });
    }

    public void printClientLog(final String data) {
        Log.d(TAG, data);
        handler.post(new Runnable() {                                   // 스레드를 사용하면서 UI 업데이트를 위해 핸들러 사용
            @Override
            public void run() {
                binding.textStart.append(data + "\n");
            }
        });
    }

    public void printServerLog(final String data) {
        handler.post(new Runnable() {                                   // 스레드를 사용하면서 UI 업데이트를 위해 핸들러 사용
            @Override
            public void run() {
                binding.textSend.append(data + "\n");
            }
        });
    }

    /**
     * 자바에서 데이터는 Stream을 통해 입출력됨.
     * Stream은 단일 방향으로 연속적으로 흘러가는 것을 의미, 데이터는 출발지에서 나와 도착지로 흘러간다는 개념
     * 사용을 끝내고 닫아줘야 한다.
     * 프로그램이 네트워크상의 다른 프로그램과 데이터를 교환하기 위해서는 양쪽 모두 입력 스트림과 출력스트림이 따로 필요함.
     *
     * 프로그램이 출발지/도착지인지에 따라 스트림의 종류가 결정됨.
     * 데이터를 입력 받을 때 - InputStream
     * 데이터를 출력 할 때 - OutputStream
     *
     * Buffer : 임시로 데이터를 담아둘 수 있는 일종의 큐. append 로 입력을 한번에 모아서 한번에 출력 한다.
     */

    public void send(String data) {
        try {
            int portNumber = 5001;
            Socket socket = new Socket("localhost", portNumber);                               // 소켓 객체 생성
            printClientLog("소켓 연결 함");
            Log.d("yj", "send : 소켓 연결 함");

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());     // 출력스트림 (쓰기)
            outputStream.writeObject(data);                                                         // 직렬화 (객체를 데이터 스트림으로 만듦 : 객체에 저장 된 스트림에 쓰기 위해 연속적인 데이터로 변환)
            outputStream.flush();                                                                   // 버퍼에 남아있는 출력 스트림을 출력한다
            printClientLog("데이터 전송 함");
            Log.d("yj", "send : 데이터 전송 함");

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());         // 입력스트림 (읽기)
            printClientLog("서버로부터 받음 " + inputStream.readObject());
            Log.d("yj", "send : 서버로부터 받음");
            socket.close();                                                                         // 소켓 닫아줌
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            int portNumber = 5001;

            ServerSocket serverSocket = new ServerSocket(portNumber);
            printServerLog("서버 시작 함 : " + portNumber);
            Log.d("yj", "server : 서버 시작 함 : " + portNumber);

            while (true) {                                                                          // while 사용해서 클라이언트 접속 기다림
                Socket socket = serverSocket.accept();                                              // 서버 소켓 대기 (실행을 멈추고 포트가 연결될 때 까지 대기)
                InetAddress clientHost = socket.getLocalAddress();
                int clientPort = socket.getPort();
                printServerLog("클라이언트 연결 됨 : " + clientHost + " : " + clientPort);
                Log.d("yj", "server : 클라이언트 연결 됨 : " + clientHost + " : " + clientPort);

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                Object obj = inputStream.readObject();
                printServerLog("데이터 받음 : " + obj);
                Log.d("yj", "server : 데이터 받음 : " + obj);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(obj + " from Server");
                outputStream.flush();
                printServerLog("데이터 보냄");
                Log.d("yj", "server : 데이터 보냄");

                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}