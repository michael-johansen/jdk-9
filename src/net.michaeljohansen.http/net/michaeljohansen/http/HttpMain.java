package net.michaeljohansen.http;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.net.CookieManager;
import java.net.URI;

import static java.lang.String.format;

public class HttpMain {
  public static void main(String[] args) throws Exception {
    CookieManager cookieManager = new CookieManager();

    HttpClient http = HttpClient.newBuilder()
      .followRedirects(HttpClient.Redirect.SECURE)
      .cookieManager(cookieManager)
      .build();

    HttpRequest getGitHub = HttpRequest.newBuilder(URI.create("https://github.com")).GET().build();
    HttpResponse.BodyHandler<String> asString = HttpResponse.BodyHandler.asString();

    System.out.println("--- Synchronous request ---");
    HttpResponse<String> synchronousResponse = http.send(getGitHub, asString);
    printResponseInfo(synchronousResponse);

    System.out.println("--- Cookies ---");
    cookieManager.getCookieStore()
      .getCookies()
      .stream()
      .map(cookie -> format("%s  - %s=%s", cookie.getDomain(), cookie.getName(), cookie.getValue()))
      .forEach(System.out::println);

    System.out.println("--- Asynchronous request ---");
    http.sendAsync(getGitHub, asString)
      .thenAccept(HttpMain::printResponseInfo)
      .join();
  }

  private static void printResponseInfo(HttpResponse<String> asyncResponse) {
    System.out.println("Status code: " + asyncResponse.statusCode());
    System.out.println("Bytes received: " + asyncResponse.body().getBytes().length);
  }
}
