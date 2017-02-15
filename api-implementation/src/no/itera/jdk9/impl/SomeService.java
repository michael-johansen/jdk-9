package no.itera.jdk9.impl;

import no.itera.jdk9.api.ServiceDefinition;

public class SomeService implements ServiceDefinition {

  @Override
  public int getRandom() {
    return 0;
  }
}
