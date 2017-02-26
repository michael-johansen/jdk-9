package com.greetings;

import com.socket.NetworkSocket;

public class Main {
  public static void main(String[] args) {
    System.out.println(NetworkSocket.open().getClass());
  }
}
