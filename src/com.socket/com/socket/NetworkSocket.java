package com.socket;

import com.socket.spi.NetworkSocketProvider;

import java.io.Closeable;
import java.util.ServiceLoader;

public interface NetworkSocket extends Closeable {
  static NetworkSocket open() {
    return ServiceLoader.load(NetworkSocketProvider.class)
      .findFirst()
      .orElseThrow(() -> new IllegalStateException("No service providers found!"))
      .openNetworkSocket();
  }
}
