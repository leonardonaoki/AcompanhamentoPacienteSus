package com.acompanhamento.paciente.sus.acompanhamentopacientesus;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class AcompanhamentopacientesusApplicationTest {

  @Test
  void mainMethodStartsApplication() {
    System.setProperty("spring.profiles.active", "test");
    assertDoesNotThrow(() -> AcompanhamentopacientesusApplication.main(new String[] {}));
  }
}
